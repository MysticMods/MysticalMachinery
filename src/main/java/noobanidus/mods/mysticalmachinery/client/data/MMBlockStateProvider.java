package noobanidus.mods.mysticalmachinery.client.data;

import epicsquid.mysticallib.client.data.DeferredBlockStateProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.fml.RegistryObject;
import noobanidus.mods.mysticalmachinery.MysticalMachinery;
import noobanidus.mods.mysticalmachinery.blocks.CharcoalKilnBlock;
import noobanidus.mods.mysticalmachinery.blocks.MachineFrame;
import noobanidus.mods.mysticalmachinery.blocks.MachineFrameBlock;
import noobanidus.mods.mysticalmachinery.blocks.SawmillBlock;
import noobanidus.mods.mysticalmachinery.init.ModBlocks;

import java.util.Map;

public class MMBlockStateProvider extends DeferredBlockStateProvider {
  public MMBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
    super("Mystical Machinery Block State & Model Generator", gen, MysticalMachinery.MODID, exFileHelper);
  }

  @Override
  protected void registerStatesAndModels() {
    horizontalModel(ModBlocks.COOKIE_GENERATOR);
    //horizontalModel(ModBlocks.DRAGONFIRE_FORGE);

    //horizontalBooleanStateBlock(ModBlocks.STORED_HEAT_GENERATOR, booleanStateLoc("stored_heat_generator_heat_%s_rf_%s"), booleanStateDescriptor(StoredHeatGeneratorBlock.HEATED), booleanStateDescriptor(StoredHeatGeneratorBlock.POWERED));

    horizontalBooleanStateBlock(ModBlocks.SAWMILL, booleanStateLoc("sawmill_%s"), booleanStateDescriptor(SawmillBlock.LIT));

    getVariantBuilder(ModBlocks.CHARCOAL_KILN.get()).forAllStates((state) -> {
      if (state.get(CharcoalKilnBlock.LIT)) {
        return ConfiguredModel.builder()
            .modelFile(getBuilder("charcoal_kiln_hot")
                .parent(getExistingFile(new ResourceLocation(MysticalMachinery.MODID, "block/charcoal_kiln")))
                .texture("kiln_face", new ResourceLocation(MysticalMachinery.MODID, "block/charcoal_kiln_face_hot"))
                .texture("kiln_bottom", new ResourceLocation(MysticalMachinery.MODID, "block/charcoal_kiln_bottom_hot")))
            .rotationY(((int) state.get(BlockStateProperties.HORIZONTAL_FACING).getHorizontalAngle() + 180) % 360)
            .build();
      } else {
        return ConfiguredModel.builder()
            .modelFile(getBuilder("charcoal_kiln_cold")
                .parent(getExistingFile(new ResourceLocation(MysticalMachinery.MODID, "block/charcoal_kiln")))
                .texture("kiln_face", new ResourceLocation(MysticalMachinery.MODID, "block/charcoal_kiln_face"))
                .texture("kiln_bottom", new ResourceLocation(MysticalMachinery.MODID, "block/charcoal_kiln_bottom")))
            .rotationY(((int) state.get(BlockStateProperties.HORIZONTAL_FACING).getHorizontalAngle() + 180) % 360)
            .build();
      }
    });

    ModBlocks.BLOCKS_WITH_MODELS.forEach(this::simpleModel);

    simpleBlock(ModBlocks.WATER_FABRICATOR, getExistingFile(blockModel(ModBlocks.WATER_FABRICATOR)));

    for (Map.Entry<MachineFrame, RegistryObject<MachineFrameBlock>> entry : ModBlocks.MACHINE_FRAMES.entrySet()) {
      simpleBlock(entry.getValue(), getExistingFile(entry.getKey().model()));
      //getVariantBuilder(entry.getValue().get()).partialState().addModels(new ConfiguredModel(getExistingFile(entry.getKey().model())));
    }
  }
}
