package com.rivalrebels.common.blocks;

import com.rivalrebels.common.explosion.RivalRebelsExplosion;
import com.rivalrebels.common.init.RRBlocks;
import com.rivalrebels.common.init.RRConfigOptions;
import com.rivalrebels.common.init.RivalRebelsDamageSource;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class Flare extends RRBlock{
    protected static final AxisAlignedBB STANDING_AABB = new AxisAlignedBB(0.4000000059604645D, 0.0D, 0.4000000059604645D, 0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);

    public Flare(String name, Material material) {
        super(name, material);
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return null;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return STANDING_AABB;
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
        return BlockFaceShape.UNDEFINED;
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean isBlockNormalCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos) {
        return false;
    }

    @Override
    public boolean isNormalCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    private boolean canPlaceTorchOn(World par1World, int par2, int par3, int par4) {
        if (par1World.isBlockNormalCube(new BlockPos(par2, par3, par4), true)) {
            return true;
        }

        Block i = par1World.getBlockState(new BlockPos(par2, par3, par4)).getBlock();

        return i == Blocks.OAK_FENCE || i == Blocks.NETHER_BRICK_FENCE || i == Blocks.GLASS;
    }

    /**
     * Checks to see if it's valid to put this block at the specified coordinates.
     * @param world the instance of the world
     * @param pos the block position
     */
    @Override
    public boolean canPlaceBlockAt(World world, BlockPos pos) {
        return canPlaceTorchOn(world, pos.getX(), pos.getY() - 1, pos.getZ());
    }
    @Override
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
        world.spawnParticle(EnumParticleTypes.LAVA, pos.getX() + .5, pos.getY() + .6, pos.getZ() + .5, 0, 0.5, 0);
        world.spawnParticle(EnumParticleTypes.LAVA, pos.getX() + .5, pos.getY() + .8, pos.getZ() + .5, 0, 0.5, 0);
        world.spawnParticle(EnumParticleTypes.LAVA, pos.getX() + .5, pos.getY() + 1, pos.getZ() + .5, 0, 0.5, 0);
        world.spawnParticle(EnumParticleTypes.FLAME, pos.getX() + .5, pos.getY() + 1.2, pos.getZ() + .5, (-0.5 + world.rand.nextFloat()) * 0.1, 0.5 + world.rand.nextFloat() * 0.5, (-0.5 + world.rand.nextFloat()) * 0.1);
        world.spawnParticle(EnumParticleTypes.FLAME, pos.getX() + .5, pos.getY() + 1.4, pos.getZ() + .5, (-0.5 + world.rand.nextFloat()) * 0.1, 0.5 + world.rand.nextFloat() * 0.5, (-0.5 + world.rand.nextFloat()) * 0.1);
        world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, pos.getX() + .5, pos.getY() + 1.6, pos.getZ() + .5, (-0.5 + world.rand.nextFloat()) * 0.1, 0.5 + world.rand.nextFloat() * 0.5, (-0.5 + world.rand.nextFloat()) * 0.1);
        world.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, SoundCategory.AMBIENT, 3F, 2, false);
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random random) {
        world.scheduleBlockUpdate(pos, this, 0, 1);
        if (world.getBlockState(pos.add(1, 0, 0)) == Blocks.WATER.getDefaultState() || world.getBlockState(pos.add(-1, 0, 0)) == Blocks.WATER.getDefaultState() || world.getBlockState(pos.add(0, 0, 1)) == Blocks.WATER.getDefaultState() || world.getBlockState(pos.add(0, 0, -1)) == Blocks.WATER.getDefaultState()) {
            world.setBlockToAir(pos);
            world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(RRBlocks.flare)));
        }
    }
    @Override
    public void onBlockDestroyedByPlayer(World world, BlockPos pos, IBlockState state) {
        if (RRConfigOptions.flare_explode) {
            world.setBlockToAir(pos);
            new RivalRebelsExplosion(world, pos.getX(), pos.getY(), pos.getZ(), 3, true, false, RivalRebelsDamageSource.flare);
            world.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.AMBIENT, 0.5f, 0.3f, false);
        }
    }

    @Override
    public void onEntityCollidedWithBlock(World par1World, BlockPos pos, IBlockState state, Entity par5Entity) {
        par5Entity.attackEntityFrom(RivalRebelsDamageSource.flare, 1);
        par5Entity.setFire(5);
    }
}
