package noobanidus.mods.mysticalmachinery.integration.jei;

import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.plugins.vanilla.cooking.AbstractCookingCategory;
import mezz.jei.util.Translator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import noobanidus.mods.mysticalmachinery.MysticalMachinery;
import noobanidus.mods.mysticalmachinery.init.ModBlocks;
import noobanidus.mods.mysticalmachinery.recipes.CharcoalKilnRecipe;
import noobanidus.mods.mysticalmachinery.recipes.KilnRecipe;

public class CharcoalKilnCategory extends AbstractCookingCategory<CharcoalKilnRecipe> {
  public static ResourceLocation UID = new ResourceLocation(MysticalMachinery.MODID, "charcoal_kiln_category");

  public CharcoalKilnCategory(IGuiHelper guiHelper) {
    super(guiHelper, ModBlocks.KILN.get(), "mysticalmachinery.jei.charcoal_kiln", 100);
  }

  @Override
  public ResourceLocation getUid() {
    return UID;
  }

  @Override
  public Class<? extends CharcoalKilnRecipe> getRecipeClass() {
    return CharcoalKilnRecipe.class;
  }

  @Override
  public void draw(CharcoalKilnRecipe recipe, double mouseX, double mouseY) {
    super.draw(recipe, mouseX, mouseY);
    int additional = recipe.getMaxAdditional();
    if (additional > 0) {
      String experienceString = I18n.format("mysticalmachinery.jei.charcoal_kiln.max_additional", additional);
      Minecraft minecraft = Minecraft.getInstance();
      FontRenderer fontRenderer = minecraft.fontRenderer;
      int stringWidth = fontRenderer.getStringWidth(experienceString);
      fontRenderer.drawString(experienceString, (float)(this.getBackground().getWidth() - stringWidth), 16.0F, -8355712);
    }
  }
}
