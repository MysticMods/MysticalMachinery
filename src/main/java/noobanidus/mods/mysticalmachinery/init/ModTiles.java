package noobanidus.mods.mysticalmachinery.init;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import noobanidus.mods.mysticalmachinery.tiles.*;

import static noobanidus.mods.mysticalmachinery.MysticalMachinery.REGISTRY;

public class ModTiles {
  public static final RegistryObject<TileEntityType<KilnTile>> KILN = REGISTRY.registerTileEntity("kiln", REGISTRY.tile(KilnTile::new, ModBlocks.KILN));
  public static final RegistryObject<TileEntityType<CharcoalKilnTile>> CHARCOAL_KILN = REGISTRY.registerTileEntity("charcoal_kiln", REGISTRY.tile(CharcoalKilnTile::new, ModBlocks.CHARCOAL_KILN));
  public static final RegistryObject<TileEntityType<SawmillTile>> SAWMILL = REGISTRY.registerTileEntity("sawmill", REGISTRY.tile(SawmillTile::new, ModBlocks.SAWMILL));
  public static final RegistryObject<TileEntityType<CookieGeneratorTile>> COOKIE_GENERATOR = REGISTRY.registerTileEntity("cookie_generator", REGISTRY.tile(CookieGeneratorTile::new, ModBlocks.COOKIE_GENERATOR));
  //public static final RegistryObject<TileEntityType<StoredHeatGeneratorTile>> STORED_HEAT_GENERATOR = REGISTRY.registerTileEntity("stored_heat_generator", REGISTRY.tile(StoredHeatGeneratorTile::new, ModBlocks.STORED_HEAT_GENERATOR));
  public static final RegistryObject<TileEntityType<EndStoneFabricatorTile>> END_STONE_FABRICATOR = REGISTRY.registerTileEntity("end_stone_generator", REGISTRY.tile(EndStoneFabricatorTile::new, ModBlocks.END_STONE_FABRICATOR));
  public static final RegistryObject<TileEntityType<FabricatorTile>> BLOCK_FABRICATOR = REGISTRY.registerTileEntity("block_generator", REGISTRY.tile(FabricatorTile::new, ModBlocks.SAND_FABRICATOR, ModBlocks.RED_SAND_FABRICATOR, ModBlocks.CLAY_FABRICATOR, ModBlocks.NETHERRACK_FABRICATOR, ModBlocks.SOUL_SAND_FABRICATOR, ModBlocks.SLIME_FABRICATOR, ModBlocks.ICE_FABRICATOR, ModBlocks.SNOW_FABRICATOR, ModBlocks.DIRT_FABRICATOR, ModBlocks.GRAVEL_FABRICATOR));
  //public static final RegistryObject<TileEntityType<DragonfireForgeTile>> DRAGONFIRE_FORGE = REGISTRY.registerTileEntity("dragonfire_forge", REGISTRY.tile(DragonfireForgeTile::new, ModBlocks.DRAGONFIRE_FORGE));
  public static final RegistryObject<TileEntityType<WaterFabricatorTile>> WATER_FABRICATOR = REGISTRY.registerTileEntity("water_fabricator", REGISTRY.tile(WaterFabricatorTile::new, ModBlocks.WATER_FABRICATOR));

  public static void load() {

  }
}
