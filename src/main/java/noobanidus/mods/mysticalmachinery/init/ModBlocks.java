package noobanidus.mods.mysticalmachinery.init;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import noobanidus.mods.mysticalmachinery.MysticalMachinery;
import noobanidus.mods.mysticalmachinery.blocks.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import static noobanidus.mods.mysticalmachinery.MysticalMachinery.REGISTRY;

public class ModBlocks {
  private static Supplier<Block.Properties> FABRICATOR_PROPS = () -> Block.Properties.create(Material.IRON).hardnessAndResistance(4.5f).sound(SoundType.METAL);
  private static Supplier<Item.Properties> ITEM_PROPS = () -> new Item.Properties().group(MysticalMachinery.ITEM_GROUP);

  public static RegistryObject<KilnBlock> KILN = REGISTRY.registerBlock("kiln", REGISTRY.block(KilnBlock::new, () -> Block.Properties.create(Material.ROCK).hardnessAndResistance(3.5F).lightValue(13)), ITEM_PROPS);

  public static RegistryObject<CookieGeneratorBlock> COOKIE_GENERATOR = REGISTRY.registerBlock("cookie_generator", REGISTRY.block(CookieGeneratorBlock::new, () -> Block.Properties.create(Material.IRON).hardnessAndResistance(2.5f).sound(SoundType.METAL)), ITEM_PROPS);

  public static RegistryObject<StoredHeatGeneratorBlock> STORED_HEAT_GENERATOR = REGISTRY.registerBlock("stored_heat_generator", REGISTRY.block(StoredHeatGeneratorBlock::new, () -> Block.Properties.create(Material.IRON).hardnessAndResistance(2.5f).sound(SoundType.METAL)), ITEM_PROPS);

  public static RegistryObject<EndStoneGeneratorBlock> END_STONE_FABRICATOR = REGISTRY.registerBlock("end_stone_fabricator", REGISTRY.block(EndStoneGeneratorBlock::new, () -> Block.Properties.create(Material.IRON).hardnessAndResistance(4.5f).sound(SoundType.METAL)), ITEM_PROPS);

  public static RegistryObject<BlockGeneratorBlock> SAND_FABRICATOR = REGISTRY.registerBlock("sand_fabricator", REGISTRY.block((b) -> new BlockGeneratorBlock(b, () -> Blocks.SAND, 1000000, 5000, 50, 25), FABRICATOR_PROPS), ITEM_PROPS);
  public static RegistryObject<BlockGeneratorBlock> RED_SAND_FABRICATOR = REGISTRY.registerBlock("red_sand_fabricator", REGISTRY.block((b) -> new BlockGeneratorBlock(b, () -> Blocks.RED_SAND, 1000000, 5000, 250, 45), FABRICATOR_PROPS), ITEM_PROPS);
  public static RegistryObject<BlockGeneratorBlock> CLAY_FABRICATOR = REGISTRY.registerBlock("clay_fabricator", REGISTRY.block((b) -> new BlockGeneratorBlock(b, () -> Blocks.CLAY, 1000000, 5000, 400, 65), FABRICATOR_PROPS), ITEM_PROPS);
  public static RegistryObject<BlockGeneratorBlock> NETHERRACK_FABRICATOR = REGISTRY.registerBlock("netherrack_fabricator", REGISTRY.block((b) -> new BlockGeneratorBlock(b, () -> Blocks.NETHERRACK, 1000000, 5000, 50, 25), FABRICATOR_PROPS), ITEM_PROPS);
  public static RegistryObject<BlockGeneratorBlock> SOUL_SAND_FABRICATOR = REGISTRY.registerBlock("soul_sand_fabricator", REGISTRY.block((b) -> new BlockGeneratorBlock(b, () -> Blocks.SOUL_SAND, 1000000, 5000, 400, 120), FABRICATOR_PROPS), ITEM_PROPS);
  public static RegistryObject<BlockGeneratorBlock> SLIME_FABRICATOR = REGISTRY.registerBlock("slime_fabricator", REGISTRY.block((b) -> new BlockGeneratorBlock(b, () -> Blocks.SLIME_BLOCK, 1000000, 5000, 5000, 600), FABRICATOR_PROPS), ITEM_PROPS);
  public static RegistryObject<BlockGeneratorBlock> ICE_FABRICATOR = REGISTRY.registerBlock("ice_fabricator", REGISTRY.block((b) -> new BlockGeneratorBlock(b, () -> Blocks.ICE, 1000000, 5000, 250, 95), FABRICATOR_PROPS), ITEM_PROPS);
  public static RegistryObject<BlockGeneratorBlock> SNOW_FABRICATOR = REGISTRY.registerBlock("snow_fabricator", REGISTRY.block((b) -> new BlockGeneratorBlock(b, () -> Blocks.SNOW_BLOCK, 1000000, 5000, 250, 95), FABRICATOR_PROPS), ITEM_PROPS);
  public static RegistryObject<BlockGeneratorBlock> DIRT_FABRICATOR = REGISTRY.registerBlock("dirt_fabricator", REGISTRY.block((b) -> new BlockGeneratorBlock(b, () -> Blocks.DIRT, 1000000, 5000, 50, 25), FABRICATOR_PROPS), ITEM_PROPS);
  public static RegistryObject<BlockGeneratorBlock> GRAVEL_FABRICATOR = REGISTRY.registerBlock("gravel_fabricator", REGISTRY.block((b) -> new BlockGeneratorBlock(b, () -> Blocks.GRAVEL, 1000000, 5000, 50, 25), FABRICATOR_PROPS), ITEM_PROPS);

  public static Map<MachineFrame, RegistryObject<MachineFrameBlock>> MACHINE_FRAMES = new HashMap<>();

  static {
    for (MachineFrame type : MachineFrame.values()) {
      String frameName = type.getName() + "_machine_frame";

      RegistryObject<MachineFrameBlock> frameObject = REGISTRY.registerBlock(frameName, REGISTRY.block((b) -> new MachineFrameBlock(b, type), () -> Block.Properties.create(Material.IRON).hardnessAndResistance(2f).sound(SoundType.METAL)), () -> new Item.Properties().group(MysticalMachinery.ITEM_GROUP));
      MACHINE_FRAMES.put(type, frameObject);
    }
  }

  public static void load() {

  }
}
