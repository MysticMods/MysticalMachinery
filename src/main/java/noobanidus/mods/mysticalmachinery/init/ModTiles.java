package noobanidus.mods.mysticalmachinery.init;

import net.minecraft.block.Blocks;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import noobanidus.mods.mysticalmachinery.tiles.*;

import static noobanidus.mods.mysticalmachinery.MysticalMachinery.REGISTRY;

public class ModTiles {
  public static final RegistryObject<TileEntityType<KilnTile>> KILN = REGISTRY.registerTileEntity("kiln", REGISTRY.tile(KilnTile::new, ModBlocks.KILN));
  public static final RegistryObject<TileEntityType<CookieGeneratorTile>> COOKIE_GENERATOR = REGISTRY.registerTileEntity("cookie_generator", REGISTRY.tile(CookieGeneratorTile::new, ModBlocks.COOKIE_GENERATOR));
  public static final RegistryObject<TileEntityType<StoredHeatGeneratorTile>> STORED_HEAT_GENERATOR = REGISTRY.registerTileEntity("stored_heat_generator", REGISTRY.tile(StoredHeatGeneratorTile::new, ModBlocks.STORED_HEAT_GENERATOR));
  public static final RegistryObject<TileEntityType<EndStoneGeneratorTile>> END_STONE_FABRICATOR = REGISTRY.registerTileEntity("end_stone_generator", REGISTRY.tile(EndStoneGeneratorTile::new, ModBlocks.END_STONE_FABRICATOR));
  public static final RegistryObject<TileEntityType<BlockGeneratorTile>> BLOCK_FABRICATOR = REGISTRY.registerTileEntity("block_generator", REGISTRY.tile(BlockGeneratorTile::new, ModBlocks.SAND_FABRICATOR, ModBlocks.RED_SAND_FABRICATOR, ModBlocks.CLAY_FABRICATOR, ModBlocks.NETHERRACK_FABRICATOR, ModBlocks.SOUL_SAND_FABRICATOR, ModBlocks.SLIME_FABRICATOR, ModBlocks.ICE_FABRICATOR, ModBlocks.SNOW_FABRICATOR, ModBlocks.DIRT_FABRICATOR, ModBlocks.GRAVEL_FABRICATOR));

  public static void load () {

  }
}
