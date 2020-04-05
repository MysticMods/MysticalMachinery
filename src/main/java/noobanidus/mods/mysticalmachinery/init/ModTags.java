package noobanidus.mods.mysticalmachinery.init;

import com.tterrag.registrate.providers.ProviderType;
import net.minecraft.item.Items;
import noobanidus.mods.mysticalmachinery.MMTags;

import static noobanidus.mods.mysticalmachinery.MysticalMachinery.REGISTRATE;

public class ModTags {
  static {
    REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, (p) -> {
      p.getBuilder(MMTags.Items.SAWDUST).add(ModItems.SAWDUST.get()).build(MMTags.Items.SAWDUST.getId());
      p.getBuilder(MMTags.Items.FIRELIGHTERS).add(Items.FLINT_AND_STEEL).build(MMTags.Items.FIRELIGHTERS.getId());
    });
  }

  public static void load() {
  }
}
