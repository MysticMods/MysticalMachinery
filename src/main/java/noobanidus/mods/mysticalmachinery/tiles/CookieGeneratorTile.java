package noobanidus.mods.mysticalmachinery.tiles;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;
import noobanidus.mods.mysticalmachinery.init.ModTiles;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CookieGeneratorTile extends TileEntity {
  public static final int MAX_RF = 500000;
  public static final int MAX_RF_XFER = 300;
  public static final int RF_PER_COOKIE = 50;
  private CookieEnergyStorage energyStorage = new CookieEnergyStorage(MAX_RF, MAX_RF_XFER);
  private LazyOptional<IEnergyStorage> energyHandler = LazyOptional.of(() -> energyStorage);

  @SuppressWarnings("ConstantConditions")
  public CookieGeneratorTile() {
    super(ModTiles.COOKIE_GENERATOR.get());
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
    this.energyStorage.receiveEnergy(50, false);
  }

  public static class CookieEnergyStorage extends EnergyStorage {
    public CookieEnergyStorage(int capacity, int maxTransfer) {
      super(capacity, maxTransfer);
    }

    public void setEnergy (int amount) {
      this.energy = amount;
    }
  }
}
