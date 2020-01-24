package noobanidus.mods.mysticalmachinery.init;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import noobanidus.mods.mysticalmachinery.MysticalMachinery;

import static noobanidus.mods.mysticalmachinery.MysticalMachinery.REGISTRY;

public class ModSounds {
  public static final RegistryObject<SoundEvent> COOKIE_MUNCH = REGISTRY.registerSoundEvent("cookie_munch", REGISTRY.sound(new ResourceLocation(MysticalMachinery.MODID, "block.cookie_generator.munch")));

  public static final RegistryObject<SoundEvent> KILN_CRACKLE = REGISTRY.registerSoundEvent("kiln_crackle", REGISTRY.sound(new ResourceLocation(MysticalMachinery.MODID, "block.kiln.fire_crackle")));

  public static final RegistryObject<SoundEvent> CHARCOAL_KILN_CRACKLE = REGISTRY.registerSoundEvent("charcoal_kiln_crackle", REGISTRY.sound(new ResourceLocation(MysticalMachinery.MODID, "block.charcoal_kiln.fire_crackle")));

  public static final RegistryObject<SoundEvent> END_STONE_GENERATE = REGISTRY.registerSoundEvent("end_stone_generate", REGISTRY.sound(new ResourceLocation(MysticalMachinery.MODID, "block.end_stone_generator.generate")));

  public static void load () {

  }
}
