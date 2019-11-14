package noobanidus.mods.mysticalmachinery.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import noobanidus.mods.mysticalmachinery.MysticalMachinery;
import noobanidus.mods.mysticalmachinery.blocks.KilnBlock;

import static noobanidus.mods.mysticalmachinery.MysticalMachinery.REGISTRY;

public class ModBlocks {
  public static RegistryObject<KilnBlock> KILN = REGISTRY.registerBlock("kiln", REGISTRY.block(KilnBlock::new, () -> Block.Properties.create(Material.ROCK).hardnessAndResistance(3.5F).lightValue(13)), () -> new Item.Properties().group(MysticalMachinery.ITEM_GROUP));

  public static void load() {

  }
}
