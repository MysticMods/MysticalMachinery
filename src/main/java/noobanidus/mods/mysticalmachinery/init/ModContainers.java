package noobanidus.mods.mysticalmachinery.init;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.fml.RegistryObject;
import noobanidus.mods.mysticalmachinery.container.KilnContainer;

import static noobanidus.mods.mysticalmachinery.MysticalMachinery.REGISTRY;

public class ModContainers {
  public static final RegistryObject<ContainerType<KilnContainer>> KILN_CONTAINER = REGISTRY.registerContainerType("kiln", () -> new ContainerType<>(KilnContainer::new));

  public static void load () {
  }
}
