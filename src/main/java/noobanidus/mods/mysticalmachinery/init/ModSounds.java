package noobanidus.mods.mysticalmachinery.init;

import com.tterrag.registrate.util.RegistryEntry;
import net.minecraft.util.SoundEvent;

import static noobanidus.mods.mysticalmachinery.MysticalMachinery.REGISTRATE;

public class ModSounds {
  public static final RegistryEntry<SoundEvent> KILN_CRACKLE = REGISTRATE.soundEvent("block.kiln.fire_crackle").register();

  public static final RegistryEntry<SoundEvent> CHARCOAL_KILN_CRACKLE = REGISTRATE.soundEvent("block.charcoal_kiln.fire_crackle").register();

  public static void load() {
  }
}
