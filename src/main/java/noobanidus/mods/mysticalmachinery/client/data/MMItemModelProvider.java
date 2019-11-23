package noobanidus.mods.mysticalmachinery.client.data;

import epicsquid.mysticallib.client.data.DeferredItemModelProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;
import noobanidus.mods.mysticalmachinery.MysticalMachinery;
import noobanidus.mods.mysticalmachinery.blocks.MachineFrameBlock;
import noobanidus.mods.mysticalmachinery.init.ModBlocks;
import noobanidus.mods.mysticalmachinery.init.ModItems;

public class MMItemModelProvider extends DeferredItemModelProvider {
  public MMItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
    super("Mystical Machinery Item Model Generator", generator, MysticalMachinery.MODID, existingFileHelper);
  }

  @Override
  protected void registerModels() {
    blockItem(ModBlocks.KILN);

    for (RegistryObject<MachineFrameBlock> block : ModBlocks.MACHINE_FRAMES.values()) {
      blockItem(block);
    }

    blockItem(ModBlocks.COOKIE_GENERATOR);
    withExistingParent(name(ModItems.HEAT_CAPACITOR), modLoc("block/" + name(ModItems.HEAT_CAPACITOR)));
    withExistingParent(name(ModItems.SOLID_STATE_HEAT_CONVERTER), modLoc("block/" + name(ModItems.SOLID_STATE_HEAT_CONVERTER)));
    withExistingParent(name(ModBlocks.STORED_HEAT_GENERATOR), modLoc("block/stored_heat_generator_heat_on_rf_on"));
  }
}
