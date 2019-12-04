package noobanidus.mods.mysticalmachinery.recipes;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ICraftingRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogPlankLoader {
  private static Map<Item, Item> LOG_TO_PLANK_MAP = new HashMap<>();

  @Nullable
  public static Item getPlank(Item logItem) {
    if (LOG_TO_PLANK_MAP.get(logItem) == null) {
      MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
      RecipeManager manager = server.getRecipeManager();
      FakeCraftingInventory fake = new FakeCraftingInventory();
      fake.setInventorySlotContents(0, new ItemStack(logItem));
      List<ICraftingRecipe> recipes = manager.getRecipes(IRecipeType.CRAFTING, fake, server.getWorld(DimensionType.OVERWORLD));
      for (ICraftingRecipe recipe : recipes) {
        ItemStack output = recipe.getRecipeOutput();
        if (output.getItem().isIn(ItemTags.PLANKS)) {
          LOG_TO_PLANK_MAP.put(logItem, output.getItem());
        }
      }
    }
    return LOG_TO_PLANK_MAP.get(logItem);
  }
}
