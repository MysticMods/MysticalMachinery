package noobanidus.mods.mysticalmachinery.tiles;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;

public interface ITickingEnergyTileEntity {

  EnergyStorage getStorageInternal();

  default void energyTick(int MAX_RF_XFER, BlockPos pos, World world) {
    EnergyStorage energyStorage = getStorageInternal();
    for (Direction facing : Direction.values()) {
      BlockPos checking = pos.offset(facing);
      TileEntity checkingTile = world.getTileEntity(checking);
      if (checkingTile != null) {
        LazyOptional<IEnergyStorage> optional = checkingTile.getCapability(CapabilityEnergy.ENERGY, facing.getOpposite());
        optional.ifPresent(storage -> {
          int energy = storage.receiveEnergy(Math.min(energyStorage.getEnergyStored(), MAX_RF_XFER), false);
          if (energy > 0) {
            energyStorage.extractEnergy(energy, false);
          }
        });
        if (optional.isPresent()) {
          break;
        }
      }
    }
  }
}
