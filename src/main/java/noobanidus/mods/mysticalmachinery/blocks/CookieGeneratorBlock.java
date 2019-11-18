package noobanidus.mods.mysticalmachinery.blocks;

import epicsquid.mysticallib.util.VoxelUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import noobanidus.mods.mysticalmachinery.tiles.CookieGeneratorTile;

import javax.annotation.Nullable;

@SuppressWarnings("deprecation")
public class CookieGeneratorBlock extends Block {
  public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

  public CookieGeneratorBlock(Properties properties) {
    super(properties);
    this.setDefaultState(this.getStateContainer().getBaseState().with(FACING, Direction.NORTH));
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
    return new CookieGeneratorTile();
  }

  @Override
  public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
    if (worldIn.isRemote()) return;

    if (!(entityIn instanceof ItemEntity)) return;

    ItemEntity item = (ItemEntity) entityIn;
    if (item.getItem().getItem() == Items.COOKIE) {
      RayTraceContext context = new RayTraceContext(entityIn.getPositionVec(), new Vec3d(pos), RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, item);
      BlockRayTraceResult result = worldIn.rayTraceBlocks(context);
      if (result.getFace() == state.get(FACING)) {
        TileEntity te = worldIn.getTileEntity(pos);
        if (te instanceof CookieGeneratorTile) {
          for (int i = 0; i < item.getItem().getCount(); i++) {
            ((CookieGeneratorTile) te).acceptCookie();
          }
          worldIn.playSound(null, pos, SoundEvents.ENTITY_PANDA_EAT, SoundCategory.BLOCKS, 1f, 1f);
          entityIn.remove();
        }
      }
    }
  }
}
