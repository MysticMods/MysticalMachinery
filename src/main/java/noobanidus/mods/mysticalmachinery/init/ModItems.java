package noobanidus.mods.mysticalmachinery.init;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.RegistryObject;
import noobanidus.mods.mysticalmachinery.MysticalMachinery;
import noobanidus.mods.mysticalmachinery.items.EnergyItem;

import static noobanidus.mods.mysticalmachinery.MysticalMachinery.REGISTRY;

public class ModItems {
  public static final RegistryObject<Item> HEAT_CAPACITOR = REGISTRY.registerItem("heat_capacitor", REGISTRY.item(Item::new, () -> new Item.Properties().group(MysticalMachinery.ITEM_GROUP)));
  public static final RegistryObject<Item> SOLID_STATE_HEAT_CONVERTER = REGISTRY.registerItem("solid_state_heat_converter", REGISTRY.item(Item::new, () -> new Item.Properties().group(MysticalMachinery.ITEM_GROUP)));

  public static final RegistryObject<Item> ENCHANTMENT_DUST = REGISTRY.registerItem("enchantment_dust", REGISTRY.item(Item::new, () -> new Item.Properties().group(MysticalMachinery.ITEM_GROUP)));

  public static final RegistryObject<Item> SAWDUST = REGISTRY.registerItem("sawdust", REGISTRY.item((b) -> new Item(b) {
    @Override
    public int getBurnTime(ItemStack itemStack) {
      return 34;
    }
  }, () -> new Item.Properties().group(MysticalMachinery.ITEM_GROUP)));

  public static final RegistryObject<EnergyItem> POWERCELL_TIN = REGISTRY.registerItem("tin_power_cell", REGISTRY.item((b) -> new EnergyItem(b, 250000, 5000, 5000), () -> new Item.Properties().group(MysticalMachinery.ITEM_GROUP)));

  public static final RegistryObject<EnergyItem> POWERCELL_LEAD = REGISTRY.registerItem("lead_power_cell", REGISTRY.item((b) -> new EnergyItem(b, 250000, 5000, 5000), () -> new Item.Properties().group(MysticalMachinery.ITEM_GROUP)));

  public static final RegistryObject<EnergyItem> POWERCELL_COPPER = REGISTRY.registerItem("copper_power_cell", REGISTRY.item((b) -> new EnergyItem(b, 750000, 8000, 8000), () -> new Item.Properties().group(MysticalMachinery.ITEM_GROUP)));

  public static final RegistryObject<EnergyItem> POWERCELL_SILVER = REGISTRY.registerItem("silver_power_cell", REGISTRY.item((b) -> new EnergyItem(b, 2250000, 10000, 10000), () -> new Item.Properties().group(MysticalMachinery.ITEM_GROUP)));

  public static final RegistryObject<EnergyItem> POWERCELL_QUICKSILVER = REGISTRY.registerItem("quicksilver_power_cell", REGISTRY.item((b) -> new EnergyItem(b, 6750000, 25000, 25000), () -> new Item.Properties().group(MysticalMachinery.ITEM_GROUP)));

  public static void load () {

  }
}
