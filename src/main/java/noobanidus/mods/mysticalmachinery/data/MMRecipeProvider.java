package noobanidus.mods.mysticalmachinery.data;

import epicsquid.mysticallib.data.DeferredRecipeProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import noobanidus.mods.mysticalmachinery.MysticalMachinery;

import java.util.function.Consumer;

public class MMRecipeProvider extends DeferredRecipeProvider {
  public MMRecipeProvider(DataGenerator generatorIn) {
    super(generatorIn, MysticalMachinery.MODID);
  }

  @Override
  protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
  }
}
