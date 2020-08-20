package noobanidus.mods.mysticalmachinery.init;

import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.tileentity.TileEntityType;
import noobanidus.mods.mysticalmachinery.tiles.CharcoalKilnTile;
import noobanidus.mods.mysticalmachinery.tiles.KilnTile;
import noobanidus.mods.mysticalmachinery.tiles.SawmillTile;

import static noobanidus.mods.mysticalmachinery.MysticalMachinery.REGISTRATE;

public class ModTiles {
  public static final RegistryEntry<TileEntityType<KilnTile>> KILN = REGISTRATE.tileEntity("kiln", KilnTile::new).validBlock(ModBlocks.KILN).register();
  public static final RegistryEntry<TileEntityType<CharcoalKilnTile>> CHARCOAL_KILN = REGISTRATE.tileEntity("charcoal_kiln", CharcoalKilnTile::new).validBlock(ModBlocks.CHARCOAL_KILN).register();
  public static final RegistryEntry<TileEntityType<SawmillTile>> SAWMILL = REGISTRATE.tileEntity("sawmill", SawmillTile::new).validBlock(ModBlocks.SAWMILL).register();

  public static void load() {

  }
}
