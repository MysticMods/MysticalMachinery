package noobanidus.mods.mysticalmachinery.blocks;

import epicsquid.mysticallib.util.VoxelUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.RedstoneTorchBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import noobanidus.mods.mysticalmachinery.MMTags;
import noobanidus.mods.mysticalmachinery.tiles.CharcoalKilnTile;

import javax.annotation.Nullable;

@SuppressWarnings({"NullableProblems", "deprecation"})
public class CharcoalKilnBlock extends AbstractFastFurnaceBlock {
  public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
  public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;

  public static VoxelShape NORTH = VoxelUtil.multiOr(Block.makeCuboidShape(0, 0, 0,16, 12, 16), Block.makeCuboidShape(6, 12, 0,10, 18, 3), Block.makeCuboidShape(6, 12, 13,10, 18, 16), Block.makeCuboidShape(0, 12, 6,3, 18, 10), Block.makeCuboidShape(13, 12, 6,16, 18, 10), Block.makeCuboidShape(9, 18, 0,10, 19, 3), Block.makeCuboidShape(9, 18, 13,10, 19, 16), Block.makeCuboidShape(6, 18, 0,7, 19, 3), Block.makeCuboidShape(6, 18, 13,7, 19, 16), Block.makeCuboidShape(7, 18, 0,9, 19, 1), Block.makeCuboidShape(7, 18, 13,9, 19, 14), Block.makeCuboidShape(7, 18, 2,9, 19, 3), Block.makeCuboidShape(7, 18, 15,9, 19, 16), Block.makeCuboidShape(0, 18, 9,3, 19, 10), Block.makeCuboidShape(13, 18, 9,16, 19, 10), Block.makeCuboidShape(0, 18, 7,1, 19, 9), Block.makeCuboidShape(13, 18, 7,14, 19, 9), Block.makeCuboidShape(2, 18, 7,3, 19, 9), Block.makeCuboidShape(15, 18, 7,16, 19, 9), Block.makeCuboidShape(0, 18, 6,3, 19, 7), Block.makeCuboidShape(13, 18, 6,16, 19, 7), Block.makeCuboidShape(-0.5, 0, 6,0, 1, 10), Block.makeCuboidShape(16, 0, 6,16.5, 1, 10), Block.makeCuboidShape(-0.5, 2, 6,0, 3, 10), Block.makeCuboidShape(16, 2, 6,16.5, 3, 10), Block.makeCuboidShape(9, 1, -0.5,10, 2, 0), Block.makeCuboidShape(9, 1, 16,10, 2, 16.5), Block.makeCuboidShape(6, 2, -0.5,10, 3, 0), Block.makeCuboidShape(6, 2, 16,10, 3, 16.5), Block.makeCuboidShape(6, 0, -0.5,10, 1, 0), Block.makeCuboidShape(6, 0, 16,10, 1, 16.5), Block.makeCuboidShape(6, 1, -0.5,7, 2, 0), Block.makeCuboidShape(6, 1, 16,7, 2, 16.5), Block.makeCuboidShape(-0.5, 1, 6,0, 2, 7), Block.makeCuboidShape(16, 1, 6,16.5, 2, 7), Block.makeCuboidShape(-0.5, 1, 9,0, 2, 10), Block.makeCuboidShape(16, 1, 9,16.5, 2, 10), Block.makeCuboidShape(1, 12, 1,15, 14, 15), Block.makeCuboidShape(2, 14, 2,14, 16, 14), Block.makeCuboidShape(3, 16, 3,13, 18, 13), Block.makeCuboidShape(9, 18, 7,10, 19, 9), Block.makeCuboidShape(6, 19, 7,10, 20, 9), Block.makeCuboidShape(6, 18, 7,7, 19, 9));
  public static VoxelShape EAST = VoxelUtil.multiOr(Block.makeCuboidShape(0, 0, 0,16, 12, 16), Block.makeCuboidShape(13, 12, 6,16, 18, 10), Block.makeCuboidShape(0, 12, 6,3, 18, 10), Block.makeCuboidShape(6, 12, 0,10, 18, 3), Block.makeCuboidShape(6, 12, 13,10, 18, 16), Block.makeCuboidShape(13, 18, 9,16, 19, 10), Block.makeCuboidShape(0, 18, 9,3, 19, 10), Block.makeCuboidShape(13, 18, 6,16, 19, 7), Block.makeCuboidShape(0, 18, 6,3, 19, 7), Block.makeCuboidShape(15, 18, 7,16, 19, 9), Block.makeCuboidShape(2, 18, 7,3, 19, 9), Block.makeCuboidShape(13, 18, 7,14, 19, 9), Block.makeCuboidShape(0, 18, 7,1, 19, 9), Block.makeCuboidShape(6, 18, 0,7, 19, 3), Block.makeCuboidShape(6, 18, 13,7, 19, 16), Block.makeCuboidShape(7, 18, 0,9, 19, 1), Block.makeCuboidShape(7, 18, 13,9, 19, 14), Block.makeCuboidShape(7, 18, 2,9, 19, 3), Block.makeCuboidShape(7, 18, 15,9, 19, 16), Block.makeCuboidShape(9, 18, 0,10, 19, 3), Block.makeCuboidShape(9, 18, 13,10, 19, 16), Block.makeCuboidShape(6, 0, -0.5,10, 1, 0), Block.makeCuboidShape(6, 0, 16,10, 1, 16.5), Block.makeCuboidShape(6, 2, -0.5,10, 3, 0), Block.makeCuboidShape(6, 2, 16,10, 3, 16.5), Block.makeCuboidShape(16, 1, 9,16.5, 2, 10), Block.makeCuboidShape(-0.5, 1, 9,0, 2, 10), Block.makeCuboidShape(16, 2, 6,16.5, 3, 10), Block.makeCuboidShape(-0.5, 2, 6,0, 3, 10), Block.makeCuboidShape(16, 0, 6,16.5, 1, 10), Block.makeCuboidShape(-0.5, 0, 6,0, 1, 10), Block.makeCuboidShape(16, 1, 6,16.5, 2, 7), Block.makeCuboidShape(-0.5, 1, 6,0, 2, 7), Block.makeCuboidShape(9, 1, -0.5,10, 2, 0), Block.makeCuboidShape(9, 1, 16,10, 2, 16.5), Block.makeCuboidShape(6, 1, -0.5,7, 2, 0), Block.makeCuboidShape(6, 1, 16,7, 2, 16.5), Block.makeCuboidShape(1, 12, 1,15, 14, 15), Block.makeCuboidShape(2, 14, 2,14, 16, 14), Block.makeCuboidShape(3, 16, 3,13, 18, 13), Block.makeCuboidShape(7, 18, 9,9, 19, 10), Block.makeCuboidShape(7, 19, 6,9, 20, 10), Block.makeCuboidShape(7, 18, 6,9, 19, 7));

  public CharcoalKilnBlock(Properties properties) {
    super(properties);
  }

  @Override
  public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
    Direction facing = state.get(FACING);
    if (facing == Direction.NORTH || facing == Direction.SOUTH) {
      return NORTH;
    } else {
      return EAST;
    }
  }

  @Override
  protected void interactWith(World world, BlockPos pos, PlayerEntity player) {
    TileEntity te = world.getTileEntity(pos);
    if (te instanceof CharcoalKilnTile) {
      CharcoalKilnTile tile = (CharcoalKilnTile) te;
      ItemStack mainhand = player.getHeldItemMainhand();
      if (mainhand.getItem().isIn(MMTags.Items.FIRELIGHTERS)) {
        if (mainhand.isDamageable() && !player.isCreative()) {
          mainhand.damageItem(1, player, (damager) -> damager.sendBreakAnimation(Hand.MAIN_HAND));
        }

        tile.setBurning();
        return;
      }

      player.openContainer(tile);
    }
  }

  @Nullable
  @Override
  public TileEntity createNewTileEntity(IBlockReader worldIn) {
    return new CharcoalKilnTile();
  }
}
