package noobanidus.mods.mysticalmachinery.blocks;

import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import noobanidus.mods.mysticalmachinery.tiles.AbstractFastFurnaceTileEntity;

public abstract class AbstractFastFurnaceBlock extends AbstractFurnaceBlock {
  protected AbstractFastFurnaceBlock(Properties p_i50000_1_) {
    super(p_i50000_1_);
  }

  @Override
  public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
    if (state.getBlock() != newState.getBlock()) {
      TileEntity tileentity = worldIn.getTileEntity(pos);
      if (tileentity instanceof AbstractFastFurnaceTileEntity) {
        InventoryHelper.dropInventoryItems(worldIn, pos, (AbstractFastFurnaceTileEntity) tileentity);
        worldIn.updateComparatorOutputLevel(pos, this);
      }

      super.onReplaced(state, worldIn, pos, newState, isMoving);
    }
  }
}
