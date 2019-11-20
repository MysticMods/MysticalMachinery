package noobanidus.mods.mysticalmachinery.tiles;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;
import noobanidus.mods.mysticalmachinery.init.ModTiles;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class StoredHeatGeneratorTile extends TileEntity implements ITickableTileEntity {
  public static final int MAX_RF = 500000;
  public static final int MAX_RF_XFER = 300;
  public static final int RF_PER_COOKIE = 50;
  private CookieEnergyStorage energyStorage;
  private LazyOptional<IEnergyStorage> energyHandler;

  @SuppressWarnings("ConstantConditions")
  public StoredHeatGeneratorTile() {
    super(ModTiles.COOKIE_GENERATOR.get());
    this.energyStorage = new CookieEnergyStorage(MAX_RF, MAX_RF_XFER);
    this.energyHandler = LazyOptional.of(() -> this.energyStorage);
  }

  @Nonnull
  @Override
  public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
    if (cap == CapabilityEnergy.ENERGY) {
      return energyHandler.cast();
    }

    return super.getCapability(cap, side);
  }

  @Override
  public void read(CompoundNBT compound) {
    super.read(compound);
    this.energyStorage.setEnergy(compound.getInt("Energy"));
  }

  @Override
  public CompoundNBT write(CompoundNBT compound) {
    CompoundNBT tag = super.write(compound);
    tag.putInt("Energy", this.energyStorage.getEnergyStored());
    return tag;
  }

  @Nullable
  @Override
  public SUpdateTileEntityPacket getUpdatePacket() {
    return new SUpdateTileEntityPacket(this.pos, 9, getUpdateTag());
  }

  @Override
  public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
    read(pkt.getNbtCompound());
  }

  @Override
  public CompoundNBT getUpdateTag() {
    return write(super.getUpdateTag());
  }

  @Override
  public void remove() {
    energyHandler.invalidate();
    super.remove();
  }

  public void acceptCookie() {
    this.energyStorage.receiveEnergy(RF_PER_COOKIE, false);
  }

  @Override
  public void tick() {
    for (Direction facing : Direction.values()) {
      BlockPos checking = this.pos.offset(facing);
      TileEntity checkingTile = this.world.getTileEntity(checking);
      if (checkingTile != null) {
        checkingTile.getCapability(CapabilityEnergy.ENERGY, facing.getOpposite()).ifPresent(storage -> {
          int energy = storage.receiveEnergy(Math.min(this.energyStorage.getEnergyStored(), MAX_RF_XFER), false);
          if (energy > 0) {
            this.energyStorage.extractEnergy(energy, false);
            return;
          }
        });
      }
    }
  }

  public static class CookieEnergyStorage extends EnergyStorage {
    public CookieEnergyStorage(int capacity, int maxTransfer) {
      super(capacity, maxTransfer);
    }

    public void setEnergy(int amount) {
      this.energy = amount;
    }
  }
}
