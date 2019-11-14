package noobanidus.mods.mysticalmachinery.recipes;

import epicsquid.mysticallib.recipe.AbstractCookingRecipeSerializer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import noobanidus.mods.mysticalmachinery.init.ModRecipes;

@SuppressWarnings({"NullableProblems", "WeakerAccess"})
public class KilnRecipe extends AbstractCookingRecipe {
  public KilnRecipe(ResourceLocation idIn, String groupIn, Ingredient ingredientIn, ItemStack resultIn, float experienceIn, int cookTimeIn) {
    super(ModRecipes.KILN_TYPE, idIn, groupIn, ingredientIn, resultIn, experienceIn, cookTimeIn);
  }

  @Override
  public IRecipeSerializer<?> getSerializer() {
    return ModRecipes.KILN_SERIALIZER.get();
  }

  public static class Serializer extends AbstractCookingRecipeSerializer<KilnRecipe> {
    public Serializer() {
      super(KilnRecipe::new, 100);
    }
  }
}
