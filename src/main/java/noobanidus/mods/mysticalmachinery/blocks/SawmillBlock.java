package noobanidus.mods.mysticalmachinery.blocks;

import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import noobanidus.mods.mysticalmachinery.tiles.KilnTile;
import noobanidus.mods.mysticalmachinery.tiles.SawmillTile;

import javax.annotation.Nullable;

public class SawmillBlock extends AbstractFurnaceBlock {
  public SawmillBlock(Properties properties) {
    super(properties);
  }

  @Override
  public BlockRenderType getRenderType(BlockState state) {
    return BlockRenderType.MODEL;
  }

  @Override
  public BlockRenderLayer getRenderLayer() {
    return BlockRenderLayer.CUTOUT;
  }

  @Override
  protected void interactWith(World world, BlockPos pos, PlayerEntity player) {
    TileEntity te = world.getTileEntity(pos);
    if (te instanceof KilnTile) {
      player.openContainer((INamedContainerProvider) te);
    }
  }

  @Nullable
  @Override
  public TileEntity createNewTileEntity(IBlockReader worldIn) {
    return new SawmillTile();
  }
}
