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
    generated(ModItems.ENCHANTMENT_DUST);
    generated(ModItems.POWERCELL_TIN);
    generated(ModItems.POWERCELL_COPPER);
    generated(ModItems.POWERCELL_LEAD);
    generated(ModItems.POWERCELL_SILVER);
    generated(ModItems.POWERCELL_QUICKSILVER);

    blockItem(ModBlocks.KILN);

    for (RegistryObject<MachineFrameBlock> block : ModBlocks.MACHINE_FRAMES.values()) {
      blockItem(block);
    }

    blockItem(ModBlocks.COOKIE_GENERATOR);
    blockItem(ModBlocks.DRAGONFIRE_FORGE);
    withExistingParent(name(ModItems.HEAT_CAPACITOR), modLoc("block/" + name(ModItems.HEAT_CAPACITOR)));
    withExistingParent(name(ModItems.SOLID_STATE_HEAT_CONVERTER), modLoc("block/" + name(ModItems.SOLID_STATE_HEAT_CONVERTER)));
    withExistingParent(name(ModBlocks.STORED_HEAT_GENERATOR), modLoc("block/stored_heat_generator_heat_on_rf_on"));
    withExistingParent(name(ModBlocks.SAWMILL), modLoc("block/sawmill_off"));

    ModBlocks.BLOCKS_WITH_MODELS.forEach(this::blockItem);
  }
}
