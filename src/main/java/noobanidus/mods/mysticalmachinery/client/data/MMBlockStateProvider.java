package noobanidus.mods.mysticalmachinery.client.data;

import epicsquid.mysticallib.client.data.DeferredBlockStateProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;
import noobanidus.mods.mysticalmachinery.MysticalMachinery;
import noobanidus.mods.mysticalmachinery.blocks.MachineFrame;
import noobanidus.mods.mysticalmachinery.blocks.MachineFrameBlock;
import noobanidus.mods.mysticalmachinery.blocks.StoredHeatGeneratorBlock;
import noobanidus.mods.mysticalmachinery.init.ModBlocks;

import java.util.Map;

public class MMBlockStateProvider extends DeferredBlockStateProvider {
  public MMBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
    super("Mystical Machinery Block State & Model Generator", gen, MysticalMachinery.MODID, exFileHelper);
  }

  @Override
  protected void registerStatesAndModels() {
    horizontalBlock(ModBlocks.COOKIE_GENERATOR.get(), (b) -> getExistingFile(new ResourceLocation(MysticalMachinery.MODID, "block/cookie_generator")));
    horizontalBlock(ModBlocks.STORED_HEAT_GENERATOR.get(), (b) -> getExistingFile(new ResourceLocation(MysticalMachinery.MODID, String.format("stored_heat_generator_heat_%s_rf_%s", b.get(StoredHeatGeneratorBlock.HEATED) ? "on" : "off", b.get(StoredHeatGeneratorBlock.POWERED) ? "on" : "off"))));

    for (Map.Entry<MachineFrame, RegistryObject<MachineFrameBlock>> entry : ModBlocks.MACHINE_FRAMES.entrySet()) {
      getVariantBuilder(entry.getValue().get()).partialState().addModels(new ConfiguredModel(getExistingFile(entry.getKey().model())));
    }
  }
}
