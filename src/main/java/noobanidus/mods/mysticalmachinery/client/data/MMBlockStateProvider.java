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

    getVariantBuilder(ModBlocks.END_STONE_FABRICATOR.get()).partialState().addModels(new ConfiguredModel(getExistingFile(new ResourceLocation(MysticalMachinery.MODID, "block/end_stone_fabricator"))));
    horizontalBlock(ModBlocks.STORED_HEAT_GENERATOR.get(), (b) -> getExistingFile(new ResourceLocation(MysticalMachinery.MODID, String.format("stored_heat_generator_heat_%s_rf_%s", b.get(StoredHeatGeneratorBlock.HEATED) ? "on" : "off", b.get(StoredHeatGeneratorBlock.POWERED) ? "on" : "off"))));

    getVariantBuilder(ModBlocks.SAND_FABRICATOR.get()).partialState().addModels(new ConfiguredModel(getExistingFile(modLoc(name(ModBlocks.SAND_FABRICATOR)))));
    getVariantBuilder(ModBlocks.RED_SAND_FABRICATOR.get()).partialState().addModels(new ConfiguredModel(getExistingFile(modLoc(name(ModBlocks.RED_SAND_FABRICATOR)))));
    getVariantBuilder(ModBlocks.CLAY_FABRICATOR.get()).partialState().addModels(new ConfiguredModel(getExistingFile(modLoc(name(ModBlocks.CLAY_FABRICATOR)))));
    getVariantBuilder(ModBlocks.NETHERRACK_FABRICATOR.get()).partialState().addModels(new ConfiguredModel(getExistingFile(modLoc(name(ModBlocks.NETHERRACK_FABRICATOR)))));
    getVariantBuilder(ModBlocks.SOUL_SAND_FABRICATOR.get()).partialState().addModels(new ConfiguredModel(getExistingFile(modLoc(name(ModBlocks.SOUL_SAND_FABRICATOR)))));
    getVariantBuilder(ModBlocks.SLIME_FABRICATOR.get()).partialState().addModels(new ConfiguredModel(getExistingFile(modLoc(name(ModBlocks.SLIME_FABRICATOR)))));
    getVariantBuilder(ModBlocks.ICE_FABRICATOR.get()).partialState().addModels(new ConfiguredModel(getExistingFile(modLoc(name(ModBlocks.ICE_FABRICATOR)))));
    getVariantBuilder(ModBlocks.SNOW_FABRICATOR.get()).partialState().addModels(new ConfiguredModel(getExistingFile(modLoc(name(ModBlocks.SNOW_FABRICATOR)))));
    getVariantBuilder(ModBlocks.DIRT_FABRICATOR.get()).partialState().addModels(new ConfiguredModel(getExistingFile(modLoc(name(ModBlocks.DIRT_FABRICATOR)))));
    getVariantBuilder(ModBlocks.GRAVEL_FABRICATOR.get()).partialState().addModels(new ConfiguredModel(getExistingFile(modLoc(name(ModBlocks.GRAVEL_FABRICATOR)))));

    for (Map.Entry<MachineFrame, RegistryObject<MachineFrameBlock>> entry : ModBlocks.MACHINE_FRAMES.entrySet()) {
      getVariantBuilder(entry.getValue().get()).partialState().addModels(new ConfiguredModel(getExistingFile(entry.getKey().model())));
    }
  }
}
