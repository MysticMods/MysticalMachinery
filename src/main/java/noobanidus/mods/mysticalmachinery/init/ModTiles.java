package noobanidus.mods.mysticalmachinery.init;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import noobanidus.mods.mysticalmachinery.tiles.CookieGeneratorTile;
import noobanidus.mods.mysticalmachinery.tiles.EndStoneGeneratorTile;
import noobanidus.mods.mysticalmachinery.tiles.KilnTile;
import noobanidus.mods.mysticalmachinery.tiles.StoredHeatGeneratorTile;

import static noobanidus.mods.mysticalmachinery.MysticalMachinery.REGISTRY;

public class ModTiles {
  public static final RegistryObject<TileEntityType<KilnTile>> KILN = REGISTRY.registerTileEntity("kiln", REGISTRY.tile(KilnTile::new, ModBlocks.KILN));
  public static final RegistryObject<TileEntityType<CookieGeneratorTile>> COOKIE_GENERATOR = REGISTRY.registerTileEntity("cookie_generator", REGISTRY.tile(CookieGeneratorTile::new, ModBlocks.COOKIE_GENERATOR));
  public static final RegistryObject<TileEntityType<StoredHeatGeneratorTile>> STORED_HEAD_GENERATOR = REGISTRY.registerTileEntity("stored_heat_generator", REGISTRY.tile(StoredHeatGeneratorTile::new, ModBlocks.STORED_HEAT_GENERATOR));
  public static final RegistryObject<TileEntityType<EndStoneGeneratorTile>> END_STONE_GENERATOR = REGISTRY.registerTileEntity("end_stone_generator", REGISTRY.tile(EndStoneGeneratorTile::new, ModBlocks.END_STONE_GENERATOR));

  public static void load () {

  }
}
