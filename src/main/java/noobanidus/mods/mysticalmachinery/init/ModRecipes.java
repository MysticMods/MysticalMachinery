package noobanidus.mods.mysticalmachinery.init;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import noobanidus.mods.mysticalmachinery.MysticalMachinery;
import noobanidus.mods.mysticalmachinery.recipes.*;

import static noobanidus.mods.mysticalmachinery.MysticalMachinery.REGISTRY;

public class ModRecipes {
  public static final IRecipeType<KilnRecipe> KILN_TYPE = IRecipeType.register(new ResourceLocation(MysticalMachinery.MODID, "kiln").toString());
  public static final IRecipeType<SawmillRecipe> SAWMILL_TYPE = IRecipeType.register(new ResourceLocation(MysticalMachinery.MODID, "sawmill").toString());
  public static final IRecipeType<CharcoalKilnRecipe> CHARCOAL_KILN_TYPE = IRecipeType.register(new ResourceLocation(MysticalMachinery.MODID, "charcoal_kiln").toString());

  public static final RegistryObject<IRecipeSerializer<KilnRecipe>> KILN_SERIALIZER = REGISTRY.registerRecipeSerializer("kiln", KilnRecipe.Serializer::new);
  public static final RegistryObject<IRecipeSerializer<CharcoalKilnRecipe>> CHARCOAL_KILN_SERIALIZER = REGISTRY.registerRecipeSerializer("charcoal_kiln", CharcoalKilnRecipeSerializer::new);
  public static final RegistryObject<IRecipeSerializer<SawmillRecipe>> SAWMILL_SERIALIZER = REGISTRY.registerRecipeSerializer("sawmill", SawmillRecipe.Serializer::new);
  public static final RegistryObject<IRecipeSerializer<BatteryRecipe>> BATTERY_SERIALIZER = REGISTRY.registerRecipeSerializer("battery", BatteryRecipe.Serializer::new);

  public static void load() {

  }
}
