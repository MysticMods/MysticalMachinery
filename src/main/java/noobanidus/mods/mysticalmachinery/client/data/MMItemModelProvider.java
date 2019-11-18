package noobanidus.mods.mysticalmachinery.client.data;

import epicsquid.mysticallib.client.data.DeferredItemModelProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;
import noobanidus.mods.mysticalmachinery.MysticalMachinery;
import noobanidus.mods.mysticalmachinery.blocks.MachineFrame;
import noobanidus.mods.mysticalmachinery.init.ModBlocks;

public class MMItemModelProvider extends DeferredItemModelProvider {
  public MMItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
    super("Mystical Machinery Item Model Generator", generator, MysticalMachinery.MODID, existingFileHelper);
  }

  @Override
  protected void registerModels() {
    blockItem(ModBlocks.KILN);

    for (RegistryObject<MachineFrame> block : ModBlocks.MACHINE_FRAMES.values()) {
      blockItem(block);
    }

    blockItem(ModBlocks.COOKIE_GENERATOR);
  }
}
