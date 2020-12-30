package noobanidus.mods.mysticalmachinery.tiles;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IRecipeHelperPopulator;
import net.minecraft.inventory.IRecipeHolder;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import noobanidus.libs.noobutil.util.MathUtil;
import noobanidus.mods.mysticalmachinery.blocks.CharcoalKilnBlock;
import noobanidus.mods.mysticalmachinery.container.CharcoalKilnContainer;
import noobanidus.mods.mysticalmachinery.init.ModRecipes;
import noobanidus.mods.mysticalmachinery.init.ModTiles;
import noobanidus.mods.mysticalmachinery.recipes.CharcoalKilnRecipe;

import javax.annotation.Nullable;

@SuppressWarnings({"Duplicates", "WeakerAccess", "NullableProblems"})
public class CharcoalKilnTile extends LockableTileEntity implements ISidedInventory, IRecipeHolder, IRecipeHelperPopulator, ITickableTileEntity {
  public static final int INPUT = 0;
  public static final int OUTPUT = 1;
  protected static final int[] SLOTS_UP = new int[]{0};
  protected static final int[] SLOTS_DOWN = new int[]{1};
  protected static final int[] SLOTS_HORIZONTAL = new int[]{0};
  protected NonNullList<ItemStack> items = NonNullList.withSize(2, ItemStack.EMPTY);
  protected boolean burning;
  protected boolean blocked = false;
  protected int cookTime;
  protected int cookTimeTotal;
  private int additional = -2;
  private int tickCountdown = 60;
  protected final IIntArray furnaceData = new IIntArray() {
    @Override
    public int get(int index) {
      switch (index) {
        case 0:
          return CharcoalKilnTile.this.burning ? 1 : 0;
        case 1:
          return CharcoalKilnTile.this.cookTime;
        case 2:
          return CharcoalKilnTile.this.cookTimeTotal;
        case 3:
          return CharcoalKilnTile.this.blocked ? 1 : 0;
        default:
          return 0;
      }
    }

    @Override
    public void set(int index, int value) {
      switch (index) {
        case 0:
          CharcoalKilnTile.this.burning = (value == 1);
          break;
        case 1:
          CharcoalKilnTile.this.cookTime = value;
          break;
        case 2:
          CharcoalKilnTile.this.cookTimeTotal = value;
          break;
        case 3:
          CharcoalKilnTile.this.blocked = (value == 1);
          break;
      }

    }

    @Override
    public int size() {
      return 4;
    }
  };

  public CharcoalKilnTile(TileEntityType<? extends CharcoalKilnTile> type) {
    super(type);
  }

  public boolean isBlocked() {
    return this.blocked;
  }

  protected boolean isBurning() {
    return this.burning;
  }

  public void setBurning() {
    this.burning = true;
    this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).with(CharcoalKilnBlock.LIT, true), 3);
    this.tickCountdown = 60;
  }

  public void setBlocked() {
    if (!blocked) {
      BlockState state = this.world.getBlockState(this.pos);
      if (!state.get(CharcoalKilnBlock.BLOCKED)) {
        this.world.setBlockState(this.pos, state.with(CharcoalKilnBlock.BLOCKED, true));
      }
    }
    this.blocked = true;
  }

  public void extinguish() {
    this.burning = false;
    this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).with(CharcoalKilnBlock.LIT, false), 3);
  }

  public void setUnblocked() {
    if (this.blocked) {
      BlockState state = this.world.getBlockState(this.pos);
      if (state.get(CharcoalKilnBlock.BLOCKED)) {
        this.world.setBlockState(this.pos, state.with(CharcoalKilnBlock.BLOCKED, false));
      }
    }
    this.blocked = false;
  }

  @Override
  public void read(BlockState state, CompoundNBT compound) {
    super.read(state, compound);
    this.items = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
    ItemStackHelper.loadAllItems(compound, this.items);
    if (compound.contains("Blocked")) {
      this.blocked = compound.getBoolean("Blocked");
    } else {
      this.blocked = false;
      // TODO: ???
    }
    this.burning = compound.getBoolean("Burning");
    this.cookTime = compound.getInt("CookTime");
    this.cookTimeTotal = compound.getInt("CookTimeTotal");
    this.additional = compound.getInt("Additional");
  }

  @Override
  public CompoundNBT write(CompoundNBT compound) {
    super.write(compound);
    compound.putBoolean("Blocked", this.blocked);
    compound.putBoolean("Burning", this.burning);
    compound.putInt("CookTime", this.cookTime);
    compound.putInt("CookTimeTotal", this.cookTimeTotal);
    compound.putInt("Additional", this.additional);
    ItemStackHelper.saveAllItems(compound, this.items);

    return compound;
  }

  @Override
  public void tick() {
    boolean dirty = false;

    if (!this.world.isRemote) {
      if (this.isBurning() && !this.items.get(INPUT).isEmpty()) {
        CharcoalKilnRecipe recipe = getRecipe();
        boolean valid = this.canSmelt(recipe);
        if (valid) {
          ++this.cookTime;
          tickCountdown = 60;
          if (this.cookTime == this.cookTimeTotal) {
            this.cookTime = 0;
            this.cookTimeTotal = this.getCookTime();
            this.smeltItem(recipe);
            dirty = true;
          }
        } else {
          this.cookTime = 0;
          this.additional = -2;
          if (recipe == null) {
            this.tickCountdown--;
          }
        }
      } else if (this.isBurning() && this.items.get(INPUT).isEmpty()) {
        tickCountdown--;
      }
    }

    if (dirty) {
      this.markDirty();
    }

    if (!this.world.isRemote()) {
      if (tickCountdown <= 0) {
        this.extinguish();
      }
    }
  }

  @Override
  public int[] getSlotsForFace(Direction side) {
    if (side == Direction.DOWN) {
      return SLOTS_DOWN;
    } else {
      return side == Direction.UP ? SLOTS_UP : SLOTS_HORIZONTAL;
    }
  }

  @Override
  public boolean canInsertItem(int index, ItemStack itemStackIn, @Nullable Direction direction) {
    return this.isItemValidForSlot(index, itemStackIn);
  }

  @Override
  public boolean canExtractItem(int index, ItemStack stack, Direction direction) {
    return true;
  }

  @Override
  public int getSizeInventory() {
    return this.items.size();
  }

  @Override
  public boolean isEmpty() {
    for (ItemStack itemstack : this.items) {
      if (!itemstack.isEmpty()) {
        return false;
      }
    }

    return true;
  }

  @Override
  public ItemStack getStackInSlot(int index) {
    return this.items.get(index);
  }

  @Override
  public ItemStack decrStackSize(int index, int count) {
    return ItemStackHelper.getAndSplit(this.items, index, count);
  }

  @Override
  public ItemStack removeStackFromSlot(int index) {
    return ItemStackHelper.getAndRemove(this.items, index);
  }

  @Override
  public void setInventorySlotContents(int index, ItemStack stack) {
    ItemStack itemstack = this.items.get(index);
    boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
    this.items.set(index, stack);
    if (stack.getCount() > this.getInventoryStackLimit()) {
      stack.setCount(this.getInventoryStackLimit());
    }

    if (index == 0 && !flag) {
      this.cookTimeTotal = this.getCookTime();
      this.cookTime = 0;
      this.markDirty();
    }
  }

  @Override
  public boolean isUsableByPlayer(PlayerEntity player) {
    if (this.world.getTileEntity(this.pos) != this) {
      return false;
    } else {
      return player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
    }
  }

  @Override
  public boolean isItemValidForSlot(int index, ItemStack stack) {
    return index != 1;
  }

  @Override
  public void clear() {
    this.items.clear();
  }

  @Override
  public void setRecipeUsed(@Nullable IRecipe<?> recipe) {
  }

  @Override
  @Nullable
  public IRecipe<?> getRecipeUsed() {
    return null;
  }

  @Override
  public void onCrafting(PlayerEntity player) {
  }

  @Override
  public void fillStackedContents(RecipeItemHelper helper) {
    for (ItemStack itemstack : this.items) {
      helper.accountStack(itemstack);
    }
  }

  private LazyOptional<? extends IItemHandler>[] handlers = SidedInvWrapper.create(this, Direction.UP, Direction.DOWN);

  @Override
  public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
    if (!this.removed && facing != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
      if (facing == Direction.UP)
        return handlers[0].cast();
      else
        return handlers[1].cast();
    }
    return super.getCapability(capability, facing);
  }

  @Override
  public void remove() {
    super.remove();
    for (LazyOptional<? extends IItemHandler> handler : handlers) {
      handler.invalidate();
    }
  }

  @Override
  protected ITextComponent getDefaultName() {
    return new TranslationTextComponent("mysticalmachinery.container.charcoal_kiln");
  }

  @Override
  protected Container createMenu(int id, PlayerInventory player) {
    return new CharcoalKilnContainer(id, player, this, this.furnaceData);
  }

  protected CharcoalKilnRecipe curRecipe;

  protected boolean canSmelt(@Nullable CharcoalKilnRecipe recipe) {
    if (this.additional == -2 && recipe != null) {
      if (recipe.getMaxAdditional() != 0) {
        additional = MathUtil.rand.nextInt(recipe.getMaxAdditional()) + 1;
      } else {
        additional = -1;
      }
    }
    if (!this.items.get(0).isEmpty() && recipe != null) {
      ItemStack recipeOutput = recipe.getRecipeOutput();
      if (!recipeOutput.isEmpty()) {
        ItemStack output = this.items.get(OUTPUT);
        if (output.isEmpty()) {
          setUnblocked();
          return true;
        } else if (!output.isItemEqual(recipeOutput)) {
          return false;
        } else {
          if (output.getCount() + recipeOutput.getCount() + ((additional > 0) ? additional : 0) <= output.getMaxStackSize()) {
            setUnblocked();
            return true;
          } else {
            setBlocked();
            return false;
          }
        }
      }
    }
    return false;
  }

  protected void smeltItem(@Nullable CharcoalKilnRecipe recipe) {
    if (recipe != null && this.canSmelt(recipe)) {
      ItemStack itemstack = this.items.get(0);
      ItemStack itemstack1 = recipe.getRecipeOutput().copy();
      if (additional > 0) {
        itemstack1.grow(additional);
      }

      ItemStack itemstack2 = this.items.get(1);
      if (itemstack2.isEmpty()) {
        this.items.set(1, itemstack1.copy());
      } else if (itemstack2.getItem() == itemstack1.getItem()) {
        itemstack2.grow(itemstack1.getCount());
      }

      itemstack.shrink(recipe.getIngredientCount());
      this.additional = -2;
    }
  }

  protected int getCookTime() {
    AbstractCookingRecipe rec = getRecipe();
    if (rec == null) return 2000;
    return rec.getCookTime();
  }

  @SuppressWarnings("unchecked")
  protected CharcoalKilnRecipe getRecipe() {
    ItemStack input = this.getStackInSlot(INPUT);
    if (input.isEmpty()) {
      return null;
    }
    if (curRecipe != null && curRecipe.matches(this, world)) {
      return curRecipe;
    }
    else {
      CharcoalKilnRecipe rec = world.getRecipeManager().getRecipe(ModRecipes.CHARCOAL_KILN_TYPE, this, this.world).orElse(null);
      return curRecipe = rec;
    }
  }
}
