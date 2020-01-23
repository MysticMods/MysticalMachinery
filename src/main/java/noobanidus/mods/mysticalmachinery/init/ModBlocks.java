package noobanidus.mods.mysticalmachinery.init;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import noobanidus.mods.mysticalmachinery.MysticalMachinery;
import noobanidus.mods.mysticalmachinery.blocks.*;
import noobanidus.mods.mysticalmachinery.config.ConfigManager;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static noobanidus.mods.mysticalmachinery.MysticalMachinery.REGISTRY;

public class ModBlocks {
  private static Supplier<Block.Properties> FABRICATOR_PROPS = () -> Block.Properties.create(Material.IRON).hardnessAndResistance(4.5f).sound(SoundType.METAL);
  private static Supplier<Item.Properties> ITEM_PROPS = () -> new Item.Properties().group(MysticalMachinery.ITEM_GROUP);

  public static RegistryObject<KilnBlock> KILN = REGISTRY.registerBlock("kiln", REGISTRY.block(KilnBlock::new, () -> Block.Properties.create(Material.ROCK).hardnessAndResistance(3.5F).lightValue(13)), ITEM_PROPS);

  public static RegistryObject<CharcoalKilnBlock> CHARCOAL_KILN = REGISTRY.registerBlock("charcoal_kiln", REGISTRY.block(CharcoalKilnBlock::new, () -> Block.Properties.create(Material.ROCK).hardnessAndResistance(3.5F).lightValue(13)), ITEM_PROPS);

  public static RegistryObject<SawmillBlock> SAWMILL = REGISTRY.registerBlock("sawmill", REGISTRY.block(SawmillBlock::new, () -> Block.Properties.create(Material.ROCK).hardnessAndResistance(3.5f).lightValue(13)), ITEM_PROPS);

  public static RegistryObject<CookieGeneratorBlock> COOKIE_GENERATOR = REGISTRY.registerBlock("cookie_generator", REGISTRY.block(CookieGeneratorBlock::new, () -> Block.Properties.create(Material.IRON).hardnessAndResistance(2.5f).sound(SoundType.METAL)), ITEM_PROPS);

  public static RegistryObject<InfiniteWaterFabricatorBlock> WATER_FABRICATOR = REGISTRY.registerBlock("water_fabricator", REGISTRY.block(InfiniteWaterFabricatorBlock::new, () -> Block.Properties.create(Material.IRON).hardnessAndResistance(4f).sound(SoundType.METAL)), ITEM_PROPS);

  //public static RegistryObject<StoredHeatGeneratorBlock> STORED_HEAT_GENERATOR = REGISTRY.registerBlock("stored_heat_generator", REGISTRY.block(StoredHeatGeneratorBlock::new, () -> Block.Properties.create(Material.IRON).hardnessAndResistance(2.5f).sound(SoundType.METAL)), ITEM_PROPS);

  //public static RegistryObject<DragonfireForgeBlock> DRAGONFIRE_FORGE = REGISTRY.registerBlock("dragonfire_forge", REGISTRY.block(DragonfireForgeBlock::new, () -> Block.Properties.create(Material.IRON).hardnessAndResistance(6f).sound(SoundType.METAL)), ITEM_PROPS);

  public static RegistryObject<EndStoneFabricatorBlock> END_STONE_FABRICATOR = REGISTRY.registerBlock("end_stone_fabricator", REGISTRY.block(EndStoneFabricatorBlock::new, () -> Block.Properties.create(Material.IRON).hardnessAndResistance(4.5f).sound(SoundType.METAL)), ITEM_PROPS);
  public static RegistryObject<FabricatorBlock> SAND_FABRICATOR = REGISTRY.registerBlock("sand_fabricator", REGISTRY.block((b) -> new FabricatorBlock(b, () -> Blocks.SAND, ConfigManager.get("sand").values()), FABRICATOR_PROPS), ITEM_PROPS);
  public static RegistryObject<FabricatorBlock> RED_SAND_FABRICATOR = REGISTRY.registerBlock("red_sand_fabricator", REGISTRY.block((b) -> new FabricatorBlock(b, () -> Blocks.RED_SAND, ConfigManager.get("red_sand").values()), FABRICATOR_PROPS), ITEM_PROPS);
  public static RegistryObject<FabricatorBlock> CLAY_FABRICATOR = REGISTRY.registerBlock("clay_fabricator", REGISTRY.block((b) -> new FabricatorBlock(b, () -> Blocks.CLAY, ConfigManager.get("clay").values()), FABRICATOR_PROPS), ITEM_PROPS);
  public static RegistryObject<FabricatorBlock> NETHERRACK_FABRICATOR = REGISTRY.registerBlock("netherrack_fabricator", REGISTRY.block((b) -> new FabricatorBlock(b, () -> Blocks.NETHERRACK, ConfigManager.get("netherrack").values()), FABRICATOR_PROPS), ITEM_PROPS);
  public static RegistryObject<FabricatorBlock> SOUL_SAND_FABRICATOR = REGISTRY.registerBlock("soul_sand_fabricator", REGISTRY.block((b) -> new FabricatorBlock(b, () -> Blocks.SOUL_SAND, ConfigManager.get("soul_sand").values()), FABRICATOR_PROPS), ITEM_PROPS);
  public static RegistryObject<FabricatorBlock> SLIME_FABRICATOR = REGISTRY.registerBlock("slime_fabricator", REGISTRY.block((b) -> new FabricatorBlock(b, () -> Blocks.SLIME_BLOCK, ConfigManager.get("slime").values()), FABRICATOR_PROPS), ITEM_PROPS);
  public static RegistryObject<FabricatorBlock> ICE_FABRICATOR = REGISTRY.registerBlock("ice_fabricator", REGISTRY.block((b) -> new FabricatorBlock(b, () -> Blocks.ICE, ConfigManager.get("ice").values()), FABRICATOR_PROPS), ITEM_PROPS);
  public static RegistryObject<FabricatorBlock> SNOW_FABRICATOR = REGISTRY.registerBlock("snow_fabricator", REGISTRY.block((b) -> new FabricatorBlock(b, () -> Blocks.SNOW_BLOCK, ConfigManager.get("snow").values()), FABRICATOR_PROPS), ITEM_PROPS);
  public static RegistryObject<FabricatorBlock> DIRT_FABRICATOR = REGISTRY.registerBlock("dirt_fabricator", REGISTRY.block((b) -> new FabricatorBlock(b, () -> Blocks.DIRT, ConfigManager.get("dirt").values()), FABRICATOR_PROPS), ITEM_PROPS);
  public static RegistryObject<FabricatorBlock> GRAVEL_FABRICATOR = REGISTRY.registerBlock("gravel_fabricator", REGISTRY.block((b) -> new FabricatorBlock(b, () -> Blocks.GRAVEL, ConfigManager.get("gravel").values()), FABRICATOR_PROPS), ITEM_PROPS);

  public static Map<MachineFrame, RegistryObject<MachineFrameBlock>> MACHINE_FRAMES = new HashMap<>();

  static {
    for (MachineFrame type : MachineFrame.values()) {
      String frameName = type.getName() + "_machine_frame";

      RegistryObject<MachineFrameBlock> frameObject = REGISTRY.registerBlock(frameName, REGISTRY.block((b) -> new MachineFrameBlock(b, type), () -> Block.Properties.create(Material.IRON).hardnessAndResistance(2f).sound(SoundType.METAL)), () -> new Item.Properties().group(MysticalMachinery.ITEM_GROUP));
      MACHINE_FRAMES.put(type, frameObject);
    }
  }

  public static List<Supplier<? extends Block>> BLOCKS_WITH_MODELS = Arrays.asList(ModBlocks.SAND_FABRICATOR, ModBlocks.RED_SAND_FABRICATOR, ModBlocks.CLAY_FABRICATOR, ModBlocks.NETHERRACK_FABRICATOR, ModBlocks.SOUL_SAND_FABRICATOR, ModBlocks.SLIME_FABRICATOR, ModBlocks.ICE_FABRICATOR, ModBlocks.SNOW_FABRICATOR, ModBlocks.DIRT_FABRICATOR, ModBlocks.GRAVEL_FABRICATOR, ModBlocks.END_STONE_FABRICATOR);

  public static void load() {

  }
}
