package noobanidus.mods.mysticalmachinery.recipes;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import noobanidus.mods.mysticalmachinery.init.ModRecipes;

public class KilnRecipeBuilder extends RecipeBuilder<KilnRecipe> {
  protected KilnRecipeBuilder(IItemProvider result, Ingredient ingredient, float xp, int cookTime) {
    super(result, ingredient, xp, cookTime);
  }

  @Override
  public IRecipeSerializer<?> getSerializer() {
    return ModRecipes.KILN_SERIALIZER.get();
  }

  public static KilnRecipeBuilder kilnRecipe(Ingredient input, IItemProvider result, float xp, int cookTime) {
    return new KilnRecipeBuilder(result, input, xp, cookTime);
  }
}
