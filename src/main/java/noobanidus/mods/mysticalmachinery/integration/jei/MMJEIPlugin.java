package noobanidus.mods.mysticalmachinery.integration.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaRecipeCategoryUid;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.registration.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import noobanidus.mods.mysticalmachinery.MysticalMachinery;
import noobanidus.mods.mysticalmachinery.client.screen.KilnScreen;
import noobanidus.mods.mysticalmachinery.client.screen.SawmillScreen;
import noobanidus.mods.mysticalmachinery.container.KilnContainer;
import noobanidus.mods.mysticalmachinery.container.SawmillContainer;
import noobanidus.mods.mysticalmachinery.init.ModBlocks;
import noobanidus.mods.mysticalmachinery.init.ModRecipes;
import noobanidus.mods.mysticalmachinery.recipes.KilnRecipe;
import noobanidus.mods.mysticalmachinery.recipes.SawmillRecipe;

import javax.annotation.Nullable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
@JeiPlugin
public class MMJEIPlugin implements IModPlugin {
  private static final ResourceLocation UID = new ResourceLocation(MysticalMachinery.MODID, MysticalMachinery.MODID);

  @Nullable
  private KilnCategory kilnCategory;

  @Nullable
  private SawmillCategory sawmillCategory;

  @Override
  public ResourceLocation getPluginUid() {
    return UID;
  }

  @Override
  public void registerCategories(IRecipeCategoryRegistration registration) {
    IJeiHelpers jeiHelpers = registration.getJeiHelpers();
    IGuiHelper guiHelper = jeiHelpers.getGuiHelper();
    kilnCategory = new KilnCategory(guiHelper);
    registration.addRecipeCategories(kilnCategory);
    sawmillCategory = new SawmillCategory(guiHelper);
    registration.addRecipeCategories(sawmillCategory);
  }

  @Override
  public void registerRecipes(IRecipeRegistration registration) {
    ClientWorld world = Minecraft.getInstance().world;
    RecipeManager manager = world.getRecipeManager();
    Collection<IRecipe<?>> allRecipes = manager.getRecipes();
    List<KilnRecipe> recipes = new ArrayList<>();
    allRecipes.stream().filter(o -> o instanceof KilnRecipe).forEach(o -> recipes.add((KilnRecipe) o));
    List<SawmillRecipe> sawmillRecipes = new ArrayList<>();
    allRecipes.stream().filter(o -> o instanceof SawmillRecipe).forEach(o -> sawmillRecipes.add((SawmillRecipe) o));
    registration.addRecipes(recipes, KilnCategory.UID);
    registration.addRecipes(sawmillRecipes, SawmillCategory.UID);
  }

  @Override
  public void registerGuiHandlers(IGuiHandlerRegistration registration) {
    registration.addRecipeClickArea(KilnScreen.class, 78, 32, 28, 23, KilnCategory.UID, VanillaRecipeCategoryUid.FUEL);
    registration.addRecipeClickArea(SawmillScreen.class, 78, 32, 28, 23, SawmillCategory.UID, VanillaRecipeCategoryUid.FUEL);
  }

  @Override
  public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
    registration.addRecipeTransferHandler(KilnContainer.class, KilnCategory.UID, 0, 1, 3, 36);
    registration.addRecipeTransferHandler(KilnContainer.class, VanillaRecipeCategoryUid.FUEL, 1, 1, 3, 36);
    registration.addRecipeTransferHandler(SawmillContainer.class, SawmillCategory.UID, 0, 1, 3, 36);
    registration.addRecipeTransferHandler(SawmillContainer.class, VanillaRecipeCategoryUid.FUEL, 1, 1, 3, 36);
  }

  @Override
  public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
    registration.addRecipeCatalyst(new ItemStack(ModBlocks.KILN.get()), KilnCategory.UID, VanillaRecipeCategoryUid.FUEL);
    registration.addRecipeCatalyst(new ItemStack(ModBlocks.SAWMILL.get()), SawmillCategory.UID, VanillaRecipeCategoryUid.FUEL);
  }
}
