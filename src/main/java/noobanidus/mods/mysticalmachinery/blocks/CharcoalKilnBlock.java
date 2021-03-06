package noobanidus.mods.mysticalmachinery.blocks;

import epicsquid.mysticallib.util.VoxelUtil;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneTorchBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import noobanidus.mods.mysticalmachinery.MMTags;
import noobanidus.mods.mysticalmachinery.init.ModSounds;
import noobanidus.mods.mysticalmachinery.tiles.CharcoalKilnTile;

import javax.annotation.Nullable;
import java.util.Random;

@SuppressWarnings({"NullableProblems", "deprecation", "Duplicates"})
public class CharcoalKilnBlock extends AbstractFastFurnaceBlock {
  public static final DirectionProperty FACING = AbstractFurnaceBlock.FACING;
  public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;
  public static final BooleanProperty BLOCKED = BooleanProperty.create("blocked");

  public static VoxelShape SHAPE = VoxelUtil.multiOr(Block.makeCuboidShape(1, 0, 1, 15, 12, 15), Block.makeCuboidShape(6, 12, 1, 10, 18, 5), Block.makeCuboidShape(6, 12, 11, 10, 18, 15), Block.makeCuboidShape(1, 12, 6, 5, 18, 10), Block.makeCuboidShape(11, 12, 6, 15, 18, 10), Block.makeCuboidShape(9, 18, 1, 10, 19, 5), Block.makeCuboidShape(9, 18, 11, 10, 19, 15), Block.makeCuboidShape(6, 18, 1, 7, 19, 5), Block.makeCuboidShape(6, 18, 11, 7, 19, 15), Block.makeCuboidShape(7, 18, 1, 9, 19, 2), Block.makeCuboidShape(7, 18, 11, 9, 19, 12), Block.makeCuboidShape(7, 18, 4, 9, 19, 5), Block.makeCuboidShape(7, 18, 14, 9, 19, 15), Block.makeCuboidShape(1, 18, 9, 5, 19, 10), Block.makeCuboidShape(11, 18, 9, 15, 19, 10), Block.makeCuboidShape(1, 18, 7, 2, 19, 9), Block.makeCuboidShape(11, 18, 7, 12, 19, 9), Block.makeCuboidShape(4, 18, 7, 5, 19, 9), Block.makeCuboidShape(14, 18, 7, 15, 19, 9), Block.makeCuboidShape(1, 18, 6, 5, 19, 7), Block.makeCuboidShape(11, 18, 6, 15, 19, 7), Block.makeCuboidShape(0.5, 0, 6, 1, 1, 10), Block.makeCuboidShape(15, 0, 6, 15.5, 1, 10), Block.makeCuboidShape(0.5, 2, 6, 1, 3, 10), Block.makeCuboidShape(15, 2, 6, 15.5, 3, 10), Block.makeCuboidShape(9, 1, 0.5, 10, 2, 1), Block.makeCuboidShape(9, 1, 15, 10, 2, 15.5), Block.makeCuboidShape(6, 2, 0.5, 10, 3, 1), Block.makeCuboidShape(6, 2, 15, 10, 3, 15.5), Block.makeCuboidShape(6, 0, 0.5, 10, 1, 1), Block.makeCuboidShape(6, 0, 15, 10, 1, 15.5), Block.makeCuboidShape(6, 1, 0.5, 7, 2, 1), Block.makeCuboidShape(6, 1, 15, 7, 2, 15.5), Block.makeCuboidShape(0.5, 1, 6, 1, 2, 7), Block.makeCuboidShape(15, 1, 6, 15.5, 2, 7), Block.makeCuboidShape(0.5, 1, 9, 1, 2, 10), Block.makeCuboidShape(15, 1, 9, 15.5, 2, 10), Block.makeCuboidShape(2, 12, 2, 14, 13, 14));

  public CharcoalKilnBlock(Properties properties) {
    super(properties);
    this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(LIT, false).with(BLOCKED, false));
  }

  @Override
  public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
    return SHAPE;
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

  @Override
  public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
    if (state.get(LIT) && entityIn instanceof LivingEntity) {
      entityIn.attackEntityFrom(DamageSource.HOT_FLOOR, 1.0F);
    }
  }

  @Override
  public void onEntityWalk(World world, BlockPos pos, Entity entity) {
    BlockState state = world.getBlockState(pos);
    if (state.get(LIT)) {
      if (!entity.isImmuneToFire() && entity instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity) entity)) {
        entity.attackEntityFrom(DamageSource.HOT_FLOOR, 1.0F);
      }
    }

    super.onEntityWalk(world, pos, entity);
  }

  @Override
  @OnlyIn(Dist.CLIENT)
  public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
    if (stateIn.get(LIT) && !stateIn.get(BLOCKED)) {
      if (rand.nextDouble() < 0.1D) {
        worldIn.playSound(null, pos, ModSounds.CHARCOAL_KILN_CRACKLE.get(), SoundCategory.BLOCKS, 1.0F, 1.0F);
      } else if (worldIn.isRainingAt(pos) && worldIn.canBlockSeeSky(pos) && rand.nextInt(40) == 0) {
        worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.NEUTRAL, 0.2f, 1.3f);
      }

      if (rand.nextInt(4) == 0) {
        worldIn.addOptionalParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, true, pos.getX() + 0.5D, pos.getY() + 1.35D, pos.getZ() + 0.2, 0.0D, 0.03D, 0.0D);
      }
      if (rand.nextInt(4) == 0) {
        worldIn.addOptionalParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, true, pos.getX() + 0.5D, pos.getY() + 1.35D, pos.getZ() + 0.8D, 0.0D, 0.03D, 0.0D);
      }
      if (rand.nextInt(4) == 0) {
        worldIn.addOptionalParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, true, pos.getX() + 0.2D, pos.getY() + 1.35D, pos.getZ() + 0.5D, 0.0D, 0.03D, 0.0D);
      }
      if (rand.nextInt(4) == 0) {
        worldIn.addOptionalParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, true, pos.getX() + 0.8D, pos.getY() + 1.35D, pos.getZ() + 0.5D, 0.0D, 0.03D, 0.0D);
      }

      if (rand.nextInt(7) == 0) {
        worldIn.addParticle(ParticleTypes.SMOKE, pos.getX() + 0.5D, pos.getY() + 0.1, pos.getZ() + 0.05D, 0.0D, 0.03D, -0.03D);
      }
      if (rand.nextInt(7) == 0) {
        worldIn.addParticle(ParticleTypes.SMOKE, pos.getX() + 0.5D, pos.getY() + 0.1, pos.getZ() + 0.95D, 0.0D, 0.03D, 0.03D);
      }
      if (rand.nextInt(7) == 0) {
        worldIn.addParticle(ParticleTypes.SMOKE, pos.getX() + 0.05D, pos.getY() + 0.1, pos.getZ() + 0.5D, -0.03D, 0.03D, 0.0D);
      }
      if (rand.nextInt(7) == 0) {
        worldIn.addParticle(ParticleTypes.SMOKE, pos.getX() + 0.95D, pos.getY() + 0.1, pos.getZ() + 0.5D, 0.03D, 0.03D, 0.0D);
      }

      if (rand.nextInt(21) == 0) {
        worldIn.addParticle(ParticleTypes.FLAME, pos.getX() + 0.5D, pos.getY() + 1.25D, pos.getZ() + 0.2, 0.0D, 0D, 0.0D);
      }
      if (rand.nextInt(21) == 0) {
        worldIn.addParticle(ParticleTypes.FLAME, pos.getX() + 0.5D, pos.getY() + 1.25D, pos.getZ() + 0.8D, 0.0D, 0D, 0.0D);
      }
      if (rand.nextInt(21) == 0) {
        worldIn.addParticle(ParticleTypes.FLAME, pos.getX() + 0.2D, pos.getY() + 1.25D, pos.getZ() + 0.5D, 0.0D, 0D, 0.0D);
      }
      if (rand.nextInt(21) == 0) {
        worldIn.addParticle(ParticleTypes.FLAME, pos.getX() + 0.8D, pos.getY() + 1.25D, pos.getZ() + 0.5D, 0.0D, 0D, 0.0D);
      }
    }
  }

  @Override
  protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
    builder.add(FACING, LIT, BLOCKED);
  }
}
