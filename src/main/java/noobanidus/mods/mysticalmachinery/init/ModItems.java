package noobanidus.mods.mysticalmachinery.init;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import noobanidus.mods.mysticalmachinery.MysticalMachinery;
import noobanidus.mods.mysticalmachinery.items.EnergyItem;

import static noobanidus.mods.mysticalmachinery.MysticalMachinery.REGISTRY;

public class ModItems {
  public static final RegistryObject<Item> HEAT_CAPACITOR = REGISTRY.registerItem("heat_capacitor", REGISTRY.item(Item::new, () -> new Item.Properties().group(MysticalMachinery.ITEM_GROUP)));
  public static final RegistryObject<Item> SOLID_STATE_HEAT_CONVERTER = REGISTRY.registerItem("solid_state_heat_converter", REGISTRY.item(Item::new, () -> new Item.Properties().group(MysticalMachinery.ITEM_GROUP)));

  public static final RegistryObject<Item> ENCHANTMENT_DUST = REGISTRY.registerItem("enchantment_dust", REGISTRY.item(Item::new, () -> new Item.Properties().group(MysticalMachinery.ITEM_GROUP)));

  public static final RegistryObject<Item> SAWDUST = REGISTRY.registerItem("sawdust", REGISTRY.item(Item::new, () -> new Item.Properties().group(MysticalMachinery.ITEM_GROUP)));

  public static final RegistryObject<EnergyItem> POWERCELL_TIN = REGISTRY.registerItem("tin_power_cell", REGISTRY.item((b) -> new EnergyItem(b, 100000, 100, 100), () -> new Item.Properties().group(MysticalMachinery.ITEM_GROUP)));

  public static final RegistryObject<EnergyItem> POWERCELL_LEAD = REGISTRY.registerItem("lead_power_cell", REGISTRY.item((b) -> new EnergyItem(b, 300000, 200, 200), () -> new Item.Properties().group(MysticalMachinery.ITEM_GROUP)));

  public static final RegistryObject<EnergyItem> POWERCELL_COPPER = REGISTRY.registerItem("copper_power_cell", REGISTRY.item((b) -> new EnergyItem(b, 900000, 400, 400), () -> new Item.Properties().group(MysticalMachinery.ITEM_GROUP)));

  public static final RegistryObject<EnergyItem> POWERCELL_SILVER = REGISTRY.registerItem("silver_power_cell", REGISTRY.item((b) -> new EnergyItem(b, 2700000, 800, 800), () -> new Item.Properties().group(MysticalMachinery.ITEM_GROUP)));

  public static final RegistryObject<EnergyItem> POWERCELL_QUICKSILVER = REGISTRY.registerItem("quicksilver_power_cell", REGISTRY.item((b) -> new EnergyItem(b, 8100000, 1600, 1600), () -> new Item.Properties().group(MysticalMachinery.ITEM_GROUP)));

  public static void load () {

  }
}
