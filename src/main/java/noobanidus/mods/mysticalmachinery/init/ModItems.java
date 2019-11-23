package noobanidus.mods.mysticalmachinery.init;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import noobanidus.mods.mysticalmachinery.MysticalMachinery;

import static noobanidus.mods.mysticalmachinery.MysticalMachinery.REGISTRY;

public class ModItems {
  public static final RegistryObject<Item> HEAT_CAPACITOR = REGISTRY.registerItem("heat_capacitor", REGISTRY.item(Item::new, () -> new Item.Properties().group(MysticalMachinery.ITEM_GROUP)));
  public static final RegistryObject<Item> SOLID_STATE_HEAT_CONVERTER = REGISTRY.registerItem("solid_state_heat_converter", REGISTRY.item(Item::new, () -> new Item.Properties().group(MysticalMachinery.ITEM_GROUP)));

  public static void load () {

  }
}
