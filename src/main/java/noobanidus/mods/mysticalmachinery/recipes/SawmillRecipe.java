package noobanidus.mods.mysticalmachinery.recipes;

import epicsquid.mysticallib.recipe.AbstractCookingRecipeSerializer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import noobanidus.mods.mysticalmachinery.init.ModRecipes;

@SuppressWarnings({"NullableProblems", "WeakerAccess"})
public class SawmillRecipe extends AbstractCookingRecipe {
  public SawmillRecipe(ResourceLocation idIn, String groupIn, Ingredient ingredientIn, ItemStack resultIn, float experienceIn, int cookTimeIn) {
    super(ModRecipes.SAWMILL_TYPE, idIn, groupIn, ingredientIn, resultIn, experienceIn, cookTimeIn);
  }

  @Override
  public IRecipeSerializer<?> getSerializer() {
    return ModRecipes.SAWMILL_SERIALIZER.get();
  }

  public static class Serializer extends AbstractCookingRecipeSerializer<SawmillRecipe> {
    public Serializer() {
      super(SawmillRecipe::new, 100);
    }
  }
}
