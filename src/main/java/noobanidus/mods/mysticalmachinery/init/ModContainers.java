package noobanidus.mods.mysticalmachinery.init;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.fml.RegistryObject;
import noobanidus.mods.mysticalmachinery.container.CharcoalKilnContainer;
import noobanidus.mods.mysticalmachinery.container.KilnContainer;
import noobanidus.mods.mysticalmachinery.container.SawmillContainer;

import static noobanidus.mods.mysticalmachinery.MysticalMachinery.REGISTRY;

public class ModContainers {
  public static final RegistryObject<ContainerType<KilnContainer>> KILN_CONTAINER = REGISTRY.registerContainerType("kiln", () -> new ContainerType<>(KilnContainer::new));
  public static final RegistryObject<ContainerType<SawmillContainer>> SAWMILL_CONTAINER = REGISTRY.registerContainerType("sawmill", () -> new ContainerType<>(SawmillContainer::new));
  public static final RegistryObject<ContainerType<CharcoalKilnContainer>> CHARCOAL_KILN_CONTAINER = REGISTRY.registerContainerType("charcoal_kiln", () -> new ContainerType<>(CharcoalKilnContainer::new));

  public static void load () {
  }
}
