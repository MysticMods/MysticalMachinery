package noobanidus.mods.mysticalmachinery.integration.jei;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.plugins.vanilla.cooking.AbstractCookingCategory;
import net.minecraft.util.ResourceLocation;
import noobanidus.mods.mysticalmachinery.MysticalMachinery;
import noobanidus.mods.mysticalmachinery.init.ModBlocks;
import noobanidus.mods.mysticalmachinery.recipes.KilnRecipe;
import noobanidus.mods.mysticalmachinery.recipes.SawmillRecipe;

public class SawmillCategory extends AbstractCookingCategory<SawmillRecipe> {
  public static ResourceLocation UID = new ResourceLocation(MysticalMachinery.MODID, "sawmill_category");

  public SawmillCategory(IGuiHelper guiHelper) {
    super(guiHelper, ModBlocks.SAWMILL.get(), "mysticalmachinery.jei.sawmill", 100);
  }

  @Override
  public ResourceLocation getUid() {
    return UID;
  }

  @Override
  public Class<? extends SawmillRecipe> getRecipeClass() {
    return SawmillRecipe.class;
  }
}
