package noobanidus.mods.mysticalmachinery.integration.jei;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.integration.jei.FlintAndSteelWrapper;
import epicsquid.mysticalworld.integration.jei.KnifeHornWrapper;
import epicsquid.mysticalworld.integration.jei.KnifeWrapper;
import epicsquid.mysticalworld.integration.jei.SpindleWrapper;
import epicsquid.mysticalworld.recipe.FlintAndSteelRecipe;
import epicsquid.mysticalworld.recipe.KnifeHornRecipe;
import epicsquid.mysticalworld.recipe.KnifeRecipe;
import epicsquid.mysticalworld.recipe.SpindleRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IVanillaCategoryExtensionRegistration;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.util.ResourceLocation;
import noobanidus.mods.mysticalmachinery.MysticalMachinery;

import java.util.Arrays;

@JeiPlugin
public class MMJEIPlugin implements IModPlugin {
  private static final ResourceLocation UID = new ResourceLocation(MysticalMachinery.MODID, MysticalMachinery.MODID);

  @Override
  public ResourceLocation getPluginUid() {
    return UID;
  }

  @Override
  public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
    /*if (ModList.get().isLoaded("jeresources")) {
      JERIntegration.init();
    } <-- Re-enable when JER API is supported. */
  }

  @Override
  public void registerVanillaCategoryExtensions(IVanillaCategoryExtensionRegistration registration) {

  }

  @Override
  public void registerRecipes(IRecipeRegistration registration) {
  }
}
