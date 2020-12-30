package noobanidus.mods.mysticalmachinery.container;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.AbstractFurnaceContainer;
import net.minecraft.item.crafting.RecipeBookCategory;
import net.minecraft.util.IIntArray;
import noobanidus.mods.mysticalmachinery.init.ModContainers;
import noobanidus.mods.mysticalmachinery.init.ModRecipes;

public class KilnContainer extends AbstractFurnaceContainer {
  public KilnContainer(int id, PlayerInventory playerInventoryIn) {
    super(ModContainers.KILN_CONTAINER.get(), ModRecipes.KILN_TYPE, RecipeBookCategory.FURNACE, id, playerInventoryIn);
  }

  public KilnContainer(int id, PlayerInventory playerInventoryIn, IInventory furnaceInventoryIn, IIntArray info) {
    super(ModContainers.KILN_CONTAINER.get(), ModRecipes.KILN_TYPE, RecipeBookCategory.FURNACE, id, playerInventoryIn, furnaceInventoryIn, info);
  }
}
