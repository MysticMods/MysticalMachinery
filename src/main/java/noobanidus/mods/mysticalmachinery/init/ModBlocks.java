package noobanidus.mods.mysticalmachinery.init;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import noobanidus.mods.mysticalmachinery.MysticalMachinery;
import noobanidus.mods.mysticalmachinery.blocks.*;

import java.util.HashMap;
import java.util.Map;

import static noobanidus.mods.mysticalmachinery.MysticalMachinery.REGISTRY;

public class ModBlocks {
  public static RegistryObject<KilnBlock> KILN = REGISTRY.registerBlock("kiln", REGISTRY.block(KilnBlock::new, () -> Block.Properties.create(Material.ROCK).hardnessAndResistance(3.5F).lightValue(13)), () -> new Item.Properties().group(MysticalMachinery.ITEM_GROUP));

  public static RegistryObject<CookieGeneratorBlock> COOKIE_GENERATOR = REGISTRY.registerBlock("cookie_generator", REGISTRY.block(CookieGeneratorBlock::new, () -> Block.Properties.create(Material.IRON).hardnessAndResistance(2.5f).sound(SoundType.METAL)), () -> new Item.Properties().group(MysticalMachinery.ITEM_GROUP));

  public static RegistryObject<StoredHeatGeneratorBlock> STORED_HEAT_GENERATOR = REGISTRY.registerBlock("stored_heat_generator", REGISTRY.block(StoredHeatGeneratorBlock::new, () -> Block.Properties.create(Material.IRON).hardnessAndResistance(2.5f).sound(SoundType.METAL)), () -> new Item.Properties().group(MysticalMachinery.ITEM_GROUP));

  public static RegistryObject<EndStoneGeneratorBlock> END_STONE_GENERATOR = REGISTRY.registerBlock("end_stone_generator", REGISTRY.block(EndStoneGeneratorBlock::new, () -> Block.Properties.create(Material.IRON).hardnessAndResistance(4.5f).sound(SoundType.METAL)), () -> new Item.Properties().group(MysticalMachinery.ITEM_GROUP));

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
