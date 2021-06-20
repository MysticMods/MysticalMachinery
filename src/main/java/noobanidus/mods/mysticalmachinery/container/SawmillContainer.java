package noobanidus.mods.mysticalmachinery.container;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.AbstractFurnaceContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeBookCategory;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.IIntArray;
import noobanidus.mods.mysticalmachinery.MMTags;
import noobanidus.mods.mysticalmachinery.init.ModContainers;
import noobanidus.mods.mysticalmachinery.init.ModRecipes;

public class SawmillContainer extends AbstractFurnaceContainer {
  public SawmillContainer(int id, PlayerInventory playerInventoryIn) {
    super(ModContainers.SAWMILL_CONTAINER.get(), ModRecipes.SAWMILL_TYPE, RecipeBookCategory.FURNACE, id, playerInventoryIn);
  }

  public SawmillContainer(int id, PlayerInventory playerInventoryIn, IInventory furnaceInventoryIn, IIntArray p_i50104_6_) {
    super(ModContainers.SAWMILL_CONTAINER.get(), ModRecipes.SAWMILL_TYPE, RecipeBookCategory.FURNACE, id, playerInventoryIn, furnaceInventoryIn, p_i50104_6_);
  }

  @Override
  protected boolean hasRecipe(ItemStack stack) {
    return stack.getItem().isIn(ItemTags.LOGS) || super.hasRecipe(stack);
  }
}
