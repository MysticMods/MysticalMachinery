package noobanidus.mods.mysticalmachinery.tiles;

import com.google.common.collect.Maps;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IRecipeHelperPopulator;
import net.minecraft.inventory.IRecipeHolder;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

import javax.annotation.Nullable;
import java.util.Map;

public abstract class AbstractFastFurnaceTileEntity extends LockableTileEntity implements ISidedInventory, IRecipeHolder, IRecipeHelperPopulator, ITickableTileEntity {
  public static final int INPUT = 0;
  public static final int FUEL = 1;
  public static final int OUTPUT = 2;
  protected static final int[] SLOTS_UP = new int[]{0};
  protected static final int[] SLOTS_DOWN = new int[]{2, 1};
  protected static final int[] SLOTS_HORIZONTAL = new int[]{1};
  protected NonNullList<ItemStack> items = NonNullList.withSize(3, ItemStack.EMPTY);
  protected int burnTime;
  protected int recipesUsed;
  protected int cookTime;
  protected int cookTimeTotal;
  protected final IIntArray furnaceData = new IIntArray() {
    @Override
    public int get(int index) {
      switch (index) {
        case 0:
          return AbstractFastFurnaceTileEntity.this.burnTime;
        case 1:
          return AbstractFastFurnaceTileEntity.this.recipesUsed;
        case 2:
          return AbstractFastFurnaceTileEntity.this.cookTime;
        case 3:
          return AbstractFastFurnaceTileEntity.this.cookTimeTotal;
        default:
          return 0;
      }
    }

    @Override
    public void set(int index, int value) {
      switch (index) {
        case 0:
          AbstractFastFurnaceTileEntity.this.burnTime = value;
          break;
        case 1:
          AbstractFastFurnaceTileEntity.this.recipesUsed = value;
          break;
        case 2:
          AbstractFastFurnaceTileEntity.this.cookTime = value;
          break;
        case 3:
          AbstractFastFurnaceTileEntity.this.cookTimeTotal = value;
      }

    }

    @Override
    public int size() {
      return 4;
    }
  };
  private final Map<ResourceLocation, Integer> recipeMap = Maps.newHashMap();
  protected final IRecipeType<? extends AbstractCookingRecipe> recipeType;

  protected AbstractFastFurnaceTileEntity(TileEntityType<?> tileTypeIn, IRecipeType<? extends AbstractCookingRecipe> recipeTypeIn) {
    super(tileTypeIn);
    this.recipeType = recipeTypeIn;
  }

  protected boolean isBurning() {
    return this.burnTime > 0;
  }

  @Override
  public void read(BlockState state, CompoundNBT compound) {
    super.read(state, compound);
    this.items = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
    ItemStackHelper.loadAllItems(compound, this.items);
    this.burnTime = compound.getInt("BurnTime");
    this.cookTime = compound.getInt("CookTime");
    this.cookTimeTotal = compound.getInt("CookTimeTotal");
    this.recipesUsed = this.getBurnTime(this.items.get(1));
    int i = compound.getShort("RecipesUsedSize");

    for (int j = 0; j < i; ++j) {
      ResourceLocation resourcelocation = new ResourceLocation(compound.getString("RecipeLocation" + j));
      int k = compound.getInt("RecipeAmount" + j);
      this.recipeMap.put(resourcelocation, k);
    }

  }

  @Override
  public CompoundNBT write(CompoundNBT compound) {
    super.write(compound);
    compound.putInt("BurnTime", this.burnTime);
    compound.putInt("CookTime", this.cookTime);
    compound.putInt("CookTimeTotal", this.cookTimeTotal);
    ItemStackHelper.saveAllItems(compound, this.items);
    compound.putShort("RecipesUsedSize", (short) this.recipeMap.size());
    int i = 0;

    for (Map.Entry<ResourceLocation, Integer> entry : this.recipeMap.entrySet()) {
      compound.putString("RecipeLocation" + i, entry.getKey().toString());
      compound.putInt("RecipeAmount" + i, entry.getValue());
      ++i;
    }

    return compound;
  }

  @Override
  public void tick() {
    boolean wasBurning = this.isBurning();
    boolean dirty = false;
    if (this.isBurning()) {
      --this.burnTime;
    }

    if (!this.world.isRemote) {
      ItemStack fuel = this.items.get(FUEL);
      if (this.isBurning() || !fuel.isEmpty() && !this.items.get(INPUT).isEmpty()) {
        AbstractCookingRecipe irecipe = getRecipe();
        boolean valid = this.canSmelt(irecipe);
        if (!this.isBurning() && valid) {
          this.burnTime = this.getBurnTime(fuel);
          this.recipesUsed = this.burnTime;
          if (this.isBurning()) {
            dirty = true;
            if (fuel.hasContainerItem()) this.items.set(1, fuel.getContainerItem());
            else if (!fuel.isEmpty()) {
              fuel.shrink(1);
              if (fuel.isEmpty()) {
                this.items.set(1, fuel.getContainerItem());
              }
            }
          }
        }

        if (this.isBurning() && valid) {
          ++this.cookTime;
          if (this.cookTime == this.cookTimeTotal) {
            this.cookTime = 0;
            this.cookTimeTotal = this.getCookTime();
            this.smeltItem(irecipe);
            dirty = true;
          }
        } else {
          this.cookTime = 0;
        }
      } else if (!this.isBurning() && this.cookTime > 0) {
        this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.cookTimeTotal);
      }

      if (wasBurning != this.isBurning()) {
        dirty = true;
        this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).with(AbstractFurnaceBlock.LIT, this.isBurning()), 3);
      }
    }

    if (dirty) {
      this.markDirty();
    }
  }

  protected abstract boolean canSmelt(@Nullable IRecipe<?> recipeIn);

  protected abstract void smeltItem(@Nullable IRecipe<?> recipeIn);

  protected abstract AbstractCookingRecipe getRecipe();

  protected int getBurnTime(ItemStack stack) {
    if (stack.isEmpty()) {
      return 0;
    } else {
      return net.minecraftforge.common.ForgeHooks.getBurnTime(stack);
    }
  }

  protected int getCookTime() {
    return this.world.getRecipeManager().getRecipe((IRecipeType<AbstractCookingRecipe>) this.recipeType, this, this.world).map(AbstractCookingRecipe::getCookTime).orElse(200);
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
    if (direction == Direction.DOWN && index == 1) {
      Item item = stack.getItem();
      return item == Items.WATER_BUCKET || item == Items.BUCKET;
    }

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
    if (index == 2) {
      return false;
    } else if (index != 1) {
      return true;
    } else {
      ItemStack itemstack = this.items.get(1);
      return AbstractFurnaceTileEntity.isFuel(stack) || stack.getItem() == Items.BUCKET && itemstack.getItem() != Items.BUCKET;
    }
  }

  @Override
  public void clear() {
    this.items.clear();
  }

  @Override
  public void setRecipeUsed(@Nullable IRecipe<?> recipe) {
    if (recipe != null) {
      this.recipeMap.compute(recipe.getId(), (p_214004_0_, p_214004_1_) -> 1 + (p_214004_1_ == null ? 0 : p_214004_1_));
    }
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

  net.minecraftforge.common.util.LazyOptional<? extends net.minecraftforge.items.IItemHandler>[] handlers =
      net.minecraftforge.items.wrapper.SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);

  @Override
  public <T> net.minecraftforge.common.util.LazyOptional<T> getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, @Nullable Direction facing) {
    if (!this.removed && facing != null && capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
      if (facing == Direction.UP)
        return handlers[0].cast();
      else if (facing == Direction.DOWN)
        return handlers[1].cast();
      else
        return handlers[2].cast();
    }
    return super.getCapability(capability, facing);
  }

  @Override
  public void remove() {
    super.remove();
    for (int x = 0; x < handlers.length; x++)
      handlers[x].invalidate();
  }
}
