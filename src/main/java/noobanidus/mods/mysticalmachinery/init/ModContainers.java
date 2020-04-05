package noobanidus.mods.mysticalmachinery.init;

import com.tterrag.registrate.util.RegistryEntry;
import net.minecraft.inventory.container.ContainerType;
import noobanidus.mods.mysticalmachinery.container.CharcoalKilnContainer;
import noobanidus.mods.mysticalmachinery.container.KilnContainer;
import noobanidus.mods.mysticalmachinery.container.SawmillContainer;

import static noobanidus.mods.mysticalmachinery.MysticalMachinery.REGISTRATE;

public class ModContainers {
  public static final RegistryEntry<ContainerType<KilnContainer>> KILN_CONTAINER = REGISTRATE.containerType("kiln", KilnContainer::new).register();
  public static final RegistryEntry<ContainerType<SawmillContainer>> SAWMILL_CONTAINER = REGISTRATE.containerType("sawmill", SawmillContainer::new).register();
  public static final RegistryEntry<ContainerType<CharcoalKilnContainer>> CHARCOAL_KILN_CONTAINER = REGISTRATE.containerType("charcoal_kiln", CharcoalKilnContainer::new).register();

  public static void load() {
  }
}
