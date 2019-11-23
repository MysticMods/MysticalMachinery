package noobanidus.mods.mysticalmachinery.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.world.IBlockReader;
import noobanidus.mods.mysticalmachinery.tiles.CookieGeneratorTile;
import noobanidus.mods.mysticalmachinery.tiles.StoredHeatGeneratorTile;

import javax.annotation.Nullable;

public class StoredHeadGeneratorBlock extends Block {
  public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
  public static final BooleanProperty HEATED = BooleanProperty.create("heated");
  public static final BooleanProperty POWERED = BooleanProperty.create("powered");

  public StoredHeadGeneratorBlock(Properties properties) {
    super(properties);
    this.setDefaultState(this.getStateContainer().getBaseState().with(FACING, Direction.NORTH).with(HEATED, false).with(POWERED, false));
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
    builder.add(FACING, HEATED, POWERED);
  }

  @Override
  public boolean hasTileEntity(BlockState state) {
    return true;
  }

  @Nullable
  @Override
  public TileEntity createTileEntity(BlockState state, IBlockReader world) {
    return new StoredHeatGeneratorTile();
  }
}
