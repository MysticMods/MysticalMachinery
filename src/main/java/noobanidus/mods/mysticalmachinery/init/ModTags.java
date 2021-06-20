package noobanidus.mods.mysticalmachinery.init;

import com.tterrag.registrate.providers.ProviderType;
import net.minecraft.item.Items;
import noobanidus.mods.mysticalmachinery.MMTags;

import static noobanidus.mods.mysticalmachinery.MysticalMachinery.REGISTRATE;

public class ModTags {
  static {
    REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, (p) -> {
      p.getOrCreateBuilder(MMTags.Items.SAWDUST1).add(ModItems.SAWDUST.get());
      p.getOrCreateBuilder(MMTags.Items.SAWDUST2).add(ModItems.SAWDUST.get());
      p.getOrCreateBuilder(MMTags.Items.SAWDUST).add(ModItems.SAWDUST.get());
      p.getOrCreateBuilder(MMTags.Items.FIRELIGHTERS).add(Items.FLINT_AND_STEEL);
    });
  }

  public static void load() {
  }
}
