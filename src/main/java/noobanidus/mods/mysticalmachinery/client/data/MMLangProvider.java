package noobanidus.mods.mysticalmachinery.client.data;

import epicsquid.mysticallib.client.data.DeferredLanguageProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.fml.RegistryObject;
import noobanidus.mods.mysticalmachinery.MysticalMachinery;
import noobanidus.mods.mysticalmachinery.blocks.MachineFrame;
import noobanidus.mods.mysticalmachinery.init.ModBlocks;

public class MMLangProvider extends DeferredLanguageProvider {
  public MMLangProvider(DataGenerator gen) {
    super(gen, MysticalMachinery.MODID);
  }

  @Override
  protected void addTranslations() {
    addItemGroup(MysticalMachinery.ITEM_GROUP, "Mystical Machinery");

    addBlock(ModBlocks.KILN);

    for (RegistryObject<MachineFrame> type : ModBlocks.MACHINE_FRAMES.values()) {
      addBlock(type);
    }

    addBlock(ModBlocks.COOKIE_GENERATOR);

    add("mysticalmachinery.container.kiln", "Kiln");
    add("mysticalmachinery.jei.kiln", "Kiln Baking");
    add("mysticalmachinery.tile.cookie_generator.contains", "Contains %s/%sFCE (Forge Cookie Energy)");
    // TODO: Containers
  }
}
