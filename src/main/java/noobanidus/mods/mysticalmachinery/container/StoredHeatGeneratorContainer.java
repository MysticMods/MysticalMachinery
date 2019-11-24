package noobanidus.mods.mysticalmachinery.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import noobanidus.mods.mysticalmachinery.tiles.StoredHeatGeneratorTile;

import javax.annotation.Nullable;

public class StoredHeatGeneratorContainer extends Container {
  protected final StoredHeatGeneratorTile tile;
  protected final int maximumFE = 5000000;

  protected StoredHeatGeneratorContainer(@Nullable ContainerType<?> type, int id, StoredHeatGeneratorTile tile) {
    super(type, id);
    this.tile = tile;
  }

  @Override
  public boolean canInteractWith(PlayerEntity playerIn) {
    return true;
  }
}
