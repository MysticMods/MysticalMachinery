package noobanidus.mods.mysticalmachinery.client.data;

import com.google.common.collect.Streams;
import epicsquid.mysticallib.client.data.DeferredBlockStateProvider;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.data.DataGenerator;
import net.minecraft.state.BooleanProperty;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;
import noobanidus.mods.mysticalmachinery.MysticalMachinery;
import noobanidus.mods.mysticalmachinery.blocks.MachineFrame;
import noobanidus.mods.mysticalmachinery.blocks.MachineFrameBlock;
import noobanidus.mods.mysticalmachinery.blocks.SawmillBlock;
import noobanidus.mods.mysticalmachinery.blocks.StoredHeatGeneratorBlock;
import noobanidus.mods.mysticalmachinery.init.ModBlocks;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class MMBlockStateProvider extends DeferredBlockStateProvider {
  public MMBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
    super("Mystical Machinery Block State & Model Generator", gen, MysticalMachinery.MODID, exFileHelper);
  }

  @Override
  protected void registerStatesAndModels() {
    horizontalModel(ModBlocks.COOKIE_GENERATOR);
    horizontalModel(ModBlocks.DRAGONFIRE_FORGE);

    horizontalBooleanStateBlock(ModBlocks.STORED_HEAT_GENERATOR, booleanStateLoc("stored_heat_generator_heat_%s_rf_%s"), booleanStateDescriptor(StoredHeatGeneratorBlock.HEATED), booleanStateDescriptor(StoredHeatGeneratorBlock.POWERED));

    horizontalBooleanStateBlock(ModBlocks.SAWMILL, booleanStateLoc("sawmill_%s"), booleanStateDescriptor(SawmillBlock.LIT));

    ModBlocks.BLOCKS_WITH_MODELS.forEach(this::simpleModel);

    for (Map.Entry<MachineFrame, RegistryObject<MachineFrameBlock>> entry : ModBlocks.MACHINE_FRAMES.entrySet()) {
      getVariantBuilder(entry.getValue().get()).partialState().addModels(new ConfiguredModel(getExistingFile(entry.getKey().model())));
    }
  }
}
