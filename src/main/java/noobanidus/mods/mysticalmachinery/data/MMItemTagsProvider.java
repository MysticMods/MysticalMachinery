package noobanidus.mods.mysticalmachinery.data;

import epicsquid.mysticallib.data.DeferredItemTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Items;

import static noobanidus.mods.mysticalmachinery.MMTags.Items.COOKIES;
import static noobanidus.mods.mysticalmachinery.MMTags.Items.GENERATOR_COOKIES;

public class MMItemTagsProvider extends DeferredItemTagsProvider {
  public MMItemTagsProvider(DataGenerator generatorIn) {
    super(generatorIn, "Mystical Machinery Item Tags Provider");
  }

  @Override
  protected void registerTags() {
    addItemsToTag(COOKIES, () -> Items.COOKIE);
    appendToTag(GENERATOR_COOKIES, COOKIES);
  }
}
