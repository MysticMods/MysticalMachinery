package noobanidus.mods.mysticalmachinery.recipes;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import noobanidus.mods.mysticalmachinery.MysticalMachinery;
import noobanidus.mods.mysticalmachinery.init.ModRecipes;

@SuppressWarnings({"NullableProblems", "WeakerAccess"})
public class CharcoalKilnRecipe extends AbstractCookingRecipe {
  private final int ingredientCount;
  private final int maxAdditional;

  public CharcoalKilnRecipe(ResourceLocation idIn, String groupIn, Ingredient ingredientIn, int ingredientCount, ItemStack resultIn, int maxAdditional, float experienceIn, int cookTimeIn) {
    super(ModRecipes.CHARCOAL_KILN_TYPE, idIn, groupIn, ingredientIn, resultIn, experienceIn, cookTimeIn);
    this.ingredientCount = ingredientCount;
    this.maxAdditional = maxAdditional;
  }

  @Override
  public IRecipeSerializer<?> getSerializer() {
    return ModRecipes.CHARCOAL_KILN_SERIALIZER.get();
  }

  public int getIngredientCount () {
    return ingredientCount;
  }

  public int getMaxAdditional() {
    return maxAdditional;
  }
}
