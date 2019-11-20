package noobanidus.mods.mysticalmachinery.init;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import noobanidus.mods.mysticalmachinery.MysticalMachinery;
import noobanidus.mods.mysticalmachinery.blocks.CookieGeneratorBlock;
import noobanidus.mods.mysticalmachinery.blocks.KilnBlock;
import noobanidus.mods.mysticalmachinery.blocks.MachineFrame;
import noobanidus.mods.mysticalmachinery.blocks.StoredHeadGeneratorBlock;

import java.util.HashMap;
import java.util.Map;

import static noobanidus.mods.mysticalmachinery.MysticalMachinery.REGISTRY;

public class ModBlocks {
  public static RegistryObject<KilnBlock> KILN = REGISTRY.registerBlock("kiln", REGISTRY.block(KilnBlock::new, () -> Block.Properties.create(Material.ROCK).hardnessAndResistance(3.5F).lightValue(13)), () -> new Item.Properties().group(MysticalMachinery.ITEM_GROUP));

  public static RegistryObject<CookieGeneratorBlock> COOKIE_GENERATOR = REGISTRY.registerBlock("cookie_generator", REGISTRY.block(CookieGeneratorBlock::new, () -> Block.Properties.create(Material.IRON).hardnessAndResistance(2.5f).sound(SoundType.METAL)), () -> new Item.Properties().group(MysticalMachinery.ITEM_GROUP));

  public static RegistryObject<StoredHeadGeneratorBlock> STORED_HEAD_GENERATOR = REGISTRY.registerBlock("stored_head_generator", REGISTRY.block(StoredHeadGeneratorBlock::new, () -> Block.Properties.create(Material.IRON).hardnessAndResistance(2.5f).sound(SoundType.METAL)), () -> new Item.Properties().group(MysticalMachinery.ITEM_GROUP));

  public static Map<MachineFrame.Type, RegistryObject<MachineFrame>> MACHINE_FRAMES = new HashMap<>();

  static {
    for (MachineFrame.Type type : MachineFrame.Type.values()) {
      String frameName = type.getName() + "_machine_frame";

      RegistryObject<MachineFrame> frameObject = REGISTRY.registerBlock(frameName, REGISTRY.block((b) -> new MachineFrame(b, type), () -> Block.Properties.create(Material.IRON).hardnessAndResistance(2f).sound(SoundType.METAL)), () -> new Item.Properties().group(MysticalMachinery.ITEM_GROUP));
      MACHINE_FRAMES.put(type, frameObject);
    }
  }

  public static void load() {

  }
}
