package noobanidus.mods.mysticalmachinery.client.screen;

import net.minecraft.client.gui.recipebook.AbstractRecipeBookGui;
import net.minecraft.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Collections;
import java.util.Set;

@OnlyIn(Dist.CLIENT)
public class EmptyRecipeGui extends AbstractRecipeBookGui {
  @Override
  protected boolean func_212962_b() {
    return false;
  }

  @Override
  protected void func_212959_a(boolean p_212959_1_) {
  }

  @Override
  protected boolean func_212963_d() {
    return false;
  }

  @Override
  protected void func_212957_c(boolean p_212957_1_) {
  }

  @Override
  protected String func_212960_g() {
    return "gui.recipebook.toggleRecipes.smeltable";
  }

  @Override
  protected Set<Item> func_212958_h() {
    return Collections.emptySet();
  }
}
