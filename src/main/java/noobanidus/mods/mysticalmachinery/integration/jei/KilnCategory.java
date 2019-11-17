package noobanidus.mods.mysticalmachinery.integration.jei;

import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.plugins.vanilla.cooking.AbstractCookingCategory;
import net.minecraft.util.ResourceLocation;
import noobanidus.mods.mysticalmachinery.MysticalMachinery;
import noobanidus.mods.mysticalmachinery.init.ModBlocks;
import noobanidus.mods.mysticalmachinery.recipes.KilnRecipe;

public class KilnCategory extends AbstractCookingCategory<KilnRecipe> {
  public static ResourceLocation UID = new ResourceLocation(MysticalMachinery.MODID, "kiln_category");

  public KilnCategory(IGuiHelper guiHelper) {
    super(guiHelper, ModBlocks.KILN.get(), "mysticalmachinery.jei.kiln", 100);
  }

  @Override
  public ResourceLocation getUid() {
    return UID;
  }

  @Override
  public Class<? extends KilnRecipe> getRecipeClass() {
    return KilnRecipe.class;
  }
}
