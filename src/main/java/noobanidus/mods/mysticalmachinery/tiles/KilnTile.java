package noobanidus.mods.mysticalmachinery.tiles;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import noobanidus.mods.mysticalmachinery.container.KilnContainer;
import noobanidus.mods.mysticalmachinery.init.ModRecipes;
import noobanidus.mods.mysticalmachinery.init.ModTiles;

import javax.annotation.Nullable;

public class KilnTile extends AbstractFastFurnaceTileEntity {
  @SuppressWarnings("ConstantConditions")
  public KilnTile() {
    super(ModTiles.KILN.get(), ModRecipes.KILN_TYPE);
  }

  @Override
  protected ITextComponent getDefaultName() {
    return new TranslationTextComponent("mysticalmachinery.container.kiln");
  }

  @Override
  protected Container createMenu(int id, PlayerInventory player) {
    return new KilnContainer(id, player, this, this.furnaceData);
  }

  protected AbstractCookingRecipe curRecipe;
  protected ItemStack failedMatch = ItemStack.EMPTY;

  @Override
  protected boolean canSmelt(@Nullable IRecipe<?> recipe) {
    if (!this.items.get(0).isEmpty() && recipe != null) {
      ItemStack recipeOutput = recipe.getRecipeOutput();
      if (!recipeOutput.isEmpty()) {
        ItemStack output = this.items.get(OUTPUT);
        if (output.isEmpty()) return true;
        else if (!output.isItemEqual(recipeOutput)) return false;
        else return output.getCount() + recipeOutput.getCount() <= output.getMaxStackSize();
      }
    }
    return false;
  }

  protected void smeltItem(@Nullable IRecipe<?> recipe) {
    if (recipe != null && this.canSmelt(recipe)) {
      ItemStack itemstack = this.items.get(0);
      ItemStack itemstack1 = recipe.getRecipeOutput();
      ItemStack itemstack2 = this.items.get(2);
      if (itemstack2.isEmpty()) {
        this.items.set(2, itemstack1.copy());
      } else if (itemstack2.getItem() == itemstack1.getItem()) {
        itemstack2.grow(itemstack1.getCount());
      }

      if (!this.world.isRemote) {
        this.setRecipeUsed(recipe);
      }

      if (itemstack.getItem() == Blocks.WET_SPONGE.asItem() && !this.items.get(1).isEmpty() && this.items.get(1).getItem() == Items.BUCKET) {
        this.items.set(1, new ItemStack(Items.WATER_BUCKET));
      }

      itemstack.shrink(1);
    }
  }

  @Override
  protected int getCookTime() {
    AbstractCookingRecipe rec = getRecipe();
    if (rec == null) return 200;
    return rec.getCookTime();
  }

  @SuppressWarnings("unchecked")
  protected AbstractCookingRecipe getRecipe() {
    ItemStack input = this.getStackInSlot(INPUT);
    if (input.isEmpty() || input == failedMatch) return null;
    if (curRecipe != null && curRecipe.matches(this, world)) return curRecipe;
    else {
      AbstractCookingRecipe rec = world.getRecipeManager().getRecipe((IRecipeType<AbstractCookingRecipe>) this.recipeType, this, this.world).orElse(null);
      if (rec == null) failedMatch = input;
      else failedMatch = ItemStack.EMPTY;
      return curRecipe = rec;
    }
  }
}
