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
public class SawmillRecipe extends AbstractCookingRecipe {
  public SawmillRecipe(ResourceLocation idIn, String groupIn, Ingredient ingredientIn, ItemStack resultIn, float experienceIn, int cookTimeIn) {
    super(ModRecipes.SAWMILL_TYPE, idIn, groupIn, ingredientIn, resultIn, experienceIn, cookTimeIn);
  }

  @Override
  public IRecipeSerializer<?> getSerializer() {
    return ModRecipes.SAWMILL_SERIALIZER.get();
  }

  public static class Serializer extends SawmillRecipeSerializer<SawmillRecipe> {
    public Serializer() {
      super(SawmillRecipe::new, 30);
    }
  }

  public static SawmillRecipe logRecipe(Item log, Item plank) {
    return new SawmillRecipe(new ResourceLocation(MysticalMachinery.MODID,
        log.getRegistryName().getPath() + "_to_" + plank.getRegistryName().getPath() + "_via_sawmill"),
        "sawmill", Ingredient.fromItems(log), new ItemStack(plank, 6), 0.35f, 30);
  }

  public static class Dynamic extends SawmillRecipe {
    public static ResourceLocation DYNAMIC_ID = new ResourceLocation(MysticalMachinery.MODID, "__dynamic");

    public Dynamic(Item logIn) {
      super(DYNAMIC_ID, "sawmill", Ingredient.fromItems(logIn), new ItemStack(LogPlankLoader.getPlank(logIn), 6), 0.35f, 30);
    }
  }
}
