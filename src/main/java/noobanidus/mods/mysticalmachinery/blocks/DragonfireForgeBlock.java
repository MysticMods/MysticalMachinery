package noobanidus.mods.mysticalmachinery.blocks;

import epicsquid.mysticallib.util.VoxelUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import noobanidus.mods.mysticalmachinery.tiles.DragonfireForgeTile;

import javax.annotation.Nullable;
import java.util.Random;

public class DragonfireForgeBlock extends Block {
  private static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
  private static final VoxelShape SHAPE_NORTH = VoxelUtil.multiOr(Block.makeCuboidShape(2, 3, 11,14, 7, 15.5), Block.makeCuboidShape(2, 7, 11,14, 17, 15.5), Block.makeCuboidShape(3, 12, 15.3,5, 16, 17.3), Block.makeCuboidShape(11, 12, 15.5,13, 16, 17.5), Block.makeCuboidShape(2, 7, 10.5,3, 17, 11), Block.makeCuboidShape(13, 7, 10.5,14, 17, 11), Block.makeCuboidShape(3, 16, 10.5,13, 17, 11), Block.makeCuboidShape(13, 6.5, 0.5,14, 7, 11), Block.makeCuboidShape(3, 6.5, 0.5,13, 7, 1.5), Block.makeCuboidShape(2, 6.5, 0.5,3, 7, 11), Block.makeCuboidShape(2, 3, 0.5,14, 6.5, 11), Block.makeCuboidShape(0, 0, 0,16, 3, 16), Block.makeCuboidShape(14.5, 3, 12.3,15.5, 15, 13.3), Block.makeCuboidShape(13.9, 14, 12.3,14.5, 15, 13.3), Block.makeCuboidShape(0.5, 3, 12.3,1.5, 15, 13.3), Block.makeCuboidShape(1.5, 14, 12.3,2.1, 15, 13.3), Block.makeCuboidShape(0.5, 3, 7.5,1.5, 6, 8.5), Block.makeCuboidShape(1.5, 5, 7.5,2.1, 6, 8.5), Block.makeCuboidShape(0.5, 3, 2.3,1.5, 6, 3.3), Block.makeCuboidShape(1.5, 5, 2.3,2.1, 6, 3.3), Block.makeCuboidShape(14.5, 3, 7.5,15.5, 6, 8.5), Block.makeCuboidShape(14, 5, 7.5,14.6, 6, 8.5), Block.makeCuboidShape(14.5, 3, 2.3,15.5, 6, 3.3), Block.makeCuboidShape(14, 5, 2.3,14.6, 6, 3.3));
  private static final VoxelShape SHAPE_EAST = VoxelUtil.multiOr(Block.makeCuboidShape(0.5, 3, 2,5, 7, 14), Block.makeCuboidShape(0.5, 7, 2,5, 17, 14), Block.makeCuboidShape(-1.3, 12, 3,0.7, 16, 5), Block.makeCuboidShape(-1.5, 12, 11,0.5, 16, 13), Block.makeCuboidShape(5, 7, 2,5.5, 17, 3), Block.makeCuboidShape(5, 7, 13,5.5, 17, 14), Block.makeCuboidShape(5, 16, 3,5.5, 17, 13), Block.makeCuboidShape(5, 6.5, 13,15.5, 7, 14), Block.makeCuboidShape(14.5, 6.5, 3,15.5, 7, 13), Block.makeCuboidShape(5, 6.5, 2,15.5, 7, 3), Block.makeCuboidShape(5, 3, 2,15.5, 6.5, 14), Block.makeCuboidShape(0, 0, 0,16, 3, 16), Block.makeCuboidShape(2.7, 3, 14.5,3.7, 15, 15.5), Block.makeCuboidShape(2.7, 14, 13.9,3.7, 15, 14.5), Block.makeCuboidShape(2.7, 3, 0.5,3.7, 15, 1.5), Block.makeCuboidShape(2.7, 14, 1.5,3.7, 15, 2.1), Block.makeCuboidShape(7.5, 3, 0.5,8.5, 6, 1.5), Block.makeCuboidShape(7.5, 5, 1.5,8.5, 6, 2.1), Block.makeCuboidShape(12.7, 3, 0.5,13.7, 6, 1.5), Block.makeCuboidShape(12.7, 5, 1.5,13.7, 6, 2.1), Block.makeCuboidShape(7.5, 3, 14.5,8.5, 6, 15.5), Block.makeCuboidShape(7.5, 5, 14,8.5, 6, 14.6), Block.makeCuboidShape(12.7, 3, 14.5,13.7, 6, 15.5), Block.makeCuboidShape(12.7, 5, 14,13.7, 6, 14.6));
  private static final VoxelShape SHAPE_SOUTH = VoxelUtil.multiOr(Block.makeCuboidShape(2, 3, 0.5,14, 7, 5), Block.makeCuboidShape(2, 7, 0.5,14, 17, 5), Block.makeCuboidShape(11, 12, -1.3,13, 16, 0.7), Block.makeCuboidShape(3, 12, -1.5,5, 16, 0.5), Block.makeCuboidShape(13, 7, 5,14, 17, 5.5), Block.makeCuboidShape(2, 7, 5,3, 17, 5.5), Block.makeCuboidShape(3, 16, 5,13, 17, 5.5), Block.makeCuboidShape(2, 6.5, 5,3, 7, 15.5), Block.makeCuboidShape(3, 6.5, 14.5,13, 7, 15.5), Block.makeCuboidShape(13, 6.5, 5,14, 7, 15.5), Block.makeCuboidShape(2, 3, 5,14, 6.5, 15.5), Block.makeCuboidShape(0, 0, 0,16, 3, 16), Block.makeCuboidShape(0.5, 3, 2.7,1.5, 15, 3.7), Block.makeCuboidShape(1.5, 14, 2.7,2.1, 15, 3.7), Block.makeCuboidShape(14.5, 3, 2.7,15.5, 15, 3.7), Block.makeCuboidShape(13.9, 14, 2.7,14.5, 15, 3.7), Block.makeCuboidShape(14.5, 3, 7.5,15.5, 6, 8.5), Block.makeCuboidShape(13.9, 5, 7.5,14.5, 6, 8.5), Block.makeCuboidShape(14.5, 3, 12.7,15.5, 6, 13.7), Block.makeCuboidShape(13.9, 5, 12.7,14.5, 6, 13.7), Block.makeCuboidShape(0.5, 3, 7.5,1.5, 6, 8.5), Block.makeCuboidShape(1.4, 5, 7.5,2, 6, 8.5), Block.makeCuboidShape(0.5, 3, 12.7,1.5, 6, 13.7), Block.makeCuboidShape(1.4, 5, 12.7,2, 6, 13.7));
  private static final VoxelShape SHAPE_WEST = VoxelUtil.multiOr(Block.makeCuboidShape(11, 3, 2,15.5, 7, 14), Block.makeCuboidShape(11, 7, 2,15.5, 17, 14), Block.makeCuboidShape(15.3, 12, 11,17.3, 16, 13), Block.makeCuboidShape(15.5, 12, 3,17.5, 16, 5), Block.makeCuboidShape(10.5, 7, 13,11, 17, 14), Block.makeCuboidShape(10.5, 7, 2,11, 17, 3), Block.makeCuboidShape(10.5, 16, 3,11, 17, 13), Block.makeCuboidShape(0.5, 6.5, 2,11, 7, 3), Block.makeCuboidShape(0.5, 6.5, 3,1.5, 7, 13), Block.makeCuboidShape(0.5, 6.5, 13,11, 7, 14), Block.makeCuboidShape(0.5, 3, 2,11, 6.5, 14), Block.makeCuboidShape(0, 0, 0,16, 3, 16), Block.makeCuboidShape(12.3, 3, 0.5,13.3, 15, 1.5), Block.makeCuboidShape(12.3, 14, 1.5,13.3, 15, 2.1), Block.makeCuboidShape(12.3, 3, 14.5,13.3, 15, 15.5), Block.makeCuboidShape(12.3, 14, 13.9,13.3, 15, 14.5), Block.makeCuboidShape(7.5, 3, 14.5,8.5, 6, 15.5), Block.makeCuboidShape(7.5, 5, 13.9,8.5, 6, 14.5), Block.makeCuboidShape(2.3, 3, 14.5,3.3, 6, 15.5), Block.makeCuboidShape(2.3, 5, 13.9,3.3, 6, 14.5), Block.makeCuboidShape(7.5, 3, 0.5,8.5, 6, 1.5), Block.makeCuboidShape(7.5, 5, 1.4,8.5, 6, 2), Block.makeCuboidShape(2.3, 3, 0.5,3.3, 6, 1.5), Block.makeCuboidShape(2.3, 5, 1.4,3.3, 6, 2));

  public DragonfireForgeBlock(Properties properties) {
    super(properties);
    this.setDefaultState(this.getStateContainer().getBaseState().with(FACING, Direction.NORTH));
  }

  @Override
  public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
    switch (state.get(FACING)) {
      case NORTH:
        return SHAPE_NORTH;
      case EAST:
        return SHAPE_EAST;
      case SOUTH:
        return SHAPE_SOUTH;
      case WEST:
        return SHAPE_WEST;
    }
    return super.getShape(state, worldIn, pos, context);
  }

  @Override
  public BlockState getStateForPlacement(BlockItemUseContext context) {
    return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
  }

  @Override
  public BlockRenderType getRenderType(BlockState state) {
    return BlockRenderType.MODEL;
  }

  @Override
  public BlockState rotate(BlockState state, Rotation rot) {
    return state.with(FACING, rot.rotate(state.get(FACING)));
  }

  @Override
  public BlockState mirror(BlockState state, Mirror mirrorIn) {
    return state.rotate(mirrorIn.toRotation(state.get(FACING)));
  }

  @Override
  protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
    builder.add(FACING);
  }

  @Override
  public boolean hasTileEntity(BlockState state) {
    return true;
  }

  @Nullable
  @Override
  public TileEntity createTileEntity(BlockState state, IBlockReader world) {
    return new DragonfireForgeTile();
  }

  @Override
  public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
    super.animateTick(stateIn, worldIn, pos, rand);
  }
}
