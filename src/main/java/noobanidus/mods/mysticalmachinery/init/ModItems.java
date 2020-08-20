package noobanidus.mods.mysticalmachinery.init;

import com.tterrag.registrate.util.entry.RegistryEntry;
import noobanidus.mods.mysticalmachinery.items.DustItem;

import static noobanidus.mods.mysticalmachinery.MysticalMachinery.REGISTRATE;

public class ModItems {
  public static final RegistryEntry<DustItem> SAWDUST = REGISTRATE.item("sawdust", DustItem::new).register();


  public static void load() {

  }

}
