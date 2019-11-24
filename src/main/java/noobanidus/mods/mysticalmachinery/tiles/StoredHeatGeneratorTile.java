package noobanidus.mods.mysticalmachinery.tiles;

import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraftforge.common.util.LazyOptional;
import noobanidus.mods.mysticalmachinery.capability.SettableEnergyStorage;
import noobanidus.mods.mysticalmachinery.init.ModTiles;

public class StoredHeatGeneratorTile extends EnergyTileEntity implements ITickableTileEntity, ITickingEnergyTileEntity {
  public static final int MAX_FE = 5000000;
  public static final int MAX_FE_XFER = 1000;

  @SuppressWarnings("ConstantConditions")
  public StoredHeatGeneratorTile() {
    super(ModTiles.COOKIE_GENERATOR.get());
    this.energyStorage = new SettableEnergyStorage(MAX_FE, MAX_FE_XFER);
    this.energyHandler = LazyOptional.of(() -> this.energyStorage);
  }

  @Override
  public void tick() {
    energyTick(MAX_FE_XFER, this.pos, this.world);
  }

  @Override
  public int getMaxFE() {
    return MAX_FE;
  }
}
