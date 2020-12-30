package noobanidus.mods.mysticalmachinery.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import noobanidus.libs.noobutil.util.VoxelUtil;
import noobanidus.mods.mysticalmachinery.init.ModSounds;
import noobanidus.mods.mysticalmachinery.init.ModTiles;
import noobanidus.mods.mysticalmachinery.tiles.KilnTile;

import javax.annotation.Nullable;
import java.util.Random;

@SuppressWarnings("deprecation")
public class KilnBlock extends AbstractFastFurnaceBlock {
  private static VoxelShape SHAPE = VoxelUtil.multiOr(Block.makeCuboidShape(0.0, 0.0, 0.0, 16.0, 10.0, 16.0),
      Block.makeCuboidShape(1.0, 10.0, 1.0, 15.0, 12.0, 15.0),
      Block.makeCuboidShape(2.0, 12.0, 2.0, 14.0, 14.0, 14.0),
      Block.makeCuboidShape(5.0, 14.0, 5.0, 11.0, 18.0, 11.0),
      Block.makeCuboidShape(4.0, 18.0, 4.0, 12.0, 19.0, 12.0),
      Block.makeCuboidShape(4.0, 19.0, 11.0, 12.0, 20.0, 12.0),
      Block.makeCuboidShape(4.0, 19.0, 4.0, 12.0, 20.0, 5.0),
      Block.makeCuboidShape(4.0, 19.0, 5.0, 5.0, 20.0, 11.0),
      Block.makeCuboidShape(11.0, 19.0, 5.0, 12.0, 20.0, 11.0));

  @Override
  public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
    return SHAPE;
  }

  public KilnBlock(Properties properties) {
    super(properties);
  }

  @Override
  protected void interactWith(World world, BlockPos blockPos, PlayerEntity playerEntity) {
    TileEntity te = world.getTileEntity(blockPos);
    if (te instanceof KilnTile) {
      playerEntity.openContainer((INamedContainerProvider) te);
    }
  }

  @Nullable
  @Override
  public TileEntity createNewTileEntity(IBlockReader worldIn) {
    return new KilnTile(ModTiles.KILN.get());
  }

  @Override
  @OnlyIn(Dist.CLIENT)
  public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
    if (stateIn.get(LIT)) {
      double d0 = (double) pos.getX() + 0.5D;
      double d1 = (double) pos.getY();
      double d2 = (double) pos.getZ() + 0.5D;
      if (rand.nextDouble() < 0.1D) {
        worldIn.playSound(d0, d1, d2, ModSounds.KILN_CRACKLE.get(), SoundCategory.BLOCKS, 1.0F, 1.0F, false);
      }

      Direction direction = stateIn.get(FACING);
      Direction.Axis direction$axis = direction.getAxis();
      double d4 = rand.nextDouble() * 0.6D - 0.3D;
      double d5 = direction$axis == Direction.Axis.X ? (double) direction.getXOffset() * 0.52D : d4;
      double d6 = rand.nextDouble() * 6.0D / 16.0D;
      double d7 = direction$axis == Direction.Axis.Z ? (double) direction.getZOffset() * 0.52D : d4;
      worldIn.addParticle(ParticleTypes.SMOKE, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
      worldIn.addParticle(ParticleTypes.FLAME, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);

      if (worldIn.getRandom().nextInt(3) != 0) {
        worldIn.addOptionalParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, true, pos.getX() + 0.5D, pos.getY() + 1.6D, pos.getZ() + 0.5, 0.0D, 0.03D, 0.0D);
      }
    }
  }
}
