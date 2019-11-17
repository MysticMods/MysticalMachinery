package noobanidus.mods.mysticalmachinery.client.data;

import epicsquid.mysticallib.client.data.DeferredLanguageProvider;
import net.minecraft.data.DataGenerator;
import noobanidus.mods.mysticalmachinery.MysticalMachinery;
import noobanidus.mods.mysticalmachinery.init.ModBlocks;

public class MMLangProvider extends DeferredLanguageProvider {
  public MMLangProvider(DataGenerator gen) {
    super(gen, MysticalMachinery.MODID);
  }

  @Override
  protected void addTranslations() {
    addItemGroup(MysticalMachinery.ITEM_GROUP, "Mystical Machinery");

    addBlock(ModBlocks.KILN);

    add("mysticalmachinery.container.kiln", "Kiln");
    add("mysticalmachinery.jei.kiln", "Kiln Baking");
    // TODO: Containers
  }
}
