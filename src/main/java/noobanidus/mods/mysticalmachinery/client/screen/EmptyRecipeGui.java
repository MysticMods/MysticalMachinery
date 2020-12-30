package noobanidus.mods.mysticalmachinery.client.screen;

import net.minecraft.client.gui.recipebook.AbstractRecipeBookGui;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

@OnlyIn(Dist.CLIENT)
public class EmptyRecipeGui extends AbstractRecipeBookGui {
  @Override
  protected Set<Item> func_212958_h() {
    return Collections.emptySet();
  }

  @Override
  public void mouseMoved(double p_212927_1_, double p_212927_3_) {
  }

  @Override
  public boolean mouseReleased(double p_231048_1_, double p_231048_3_, int p_231048_5_) {
    return false;
  }

  @Override
  public boolean mouseDragged(double p_231045_1_, double p_231045_3_, int p_231045_5_, double p_231045_6_, double p_231045_8_) {
    return false;
  }

  @Override
  public boolean mouseScrolled(double p_231043_1_, double p_231043_3_, double p_231043_5_) {
    return false;
  }

  @Override
  public void placeRecipe(int p_201501_1_, int p_201501_2_, int p_201501_3_, IRecipe<?> p_201501_4_, Iterator<Ingredient> p_201501_5_, int p_201501_6_) {
  }
}
