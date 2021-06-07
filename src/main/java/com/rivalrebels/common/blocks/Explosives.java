package com.rivalrebels.common.blocks;

import com.rivalrebels.common.explosion.RivalRebelsExplosion;
import com.rivalrebels.common.init.RRSounds;
import com.rivalrebels.common.init.RivalRebelsDamageSource;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class Explosives extends RRBlock{
    public AxisAlignedBB bounds = new AxisAlignedBB(0.0625, 0, 0.0625, 1 - 0.0625, 0.5, 1 - 0.0625);
    public Explosives(String name, Material material) {
        super(name, material);
        setTickRandomly(true);

    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return bounds;
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        worldIn.scheduleBlockUpdate(pos, this, this.tickRate(worldIn), 1);
    }

    @Override
    public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
        return bounds;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos) {
        return false;
    }

    @Override
    public boolean isBlockNormalCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isNormalCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {

        if(worldIn.getBlockState(pos.add(1, 0, 0)).getBlock() == Blocks.FIRE || worldIn.getBlockState(pos.add(0, 1, 0)).getBlock() == Blocks.FIRE || worldIn.getBlockState(pos.add(-1, 0, 0)).getBlock() == Blocks.FIRE || worldIn.getBlockState(pos.add(0, -1, 0)).getBlock() == Blocks.FIRE || worldIn.getBlockState(pos.add(0, 0, 1)).getBlock() == Blocks.FIRE || worldIn.getBlockState(pos.add(0, 0, -1)).getBlock() == Blocks.FIRE){
            explode(worldIn, pos);
        }
        worldIn.scheduleBlockUpdate(pos, this, this.tickRate(worldIn), 1);
    }

    public static void explode(World world, BlockPos pos){
        world.setBlockToAir(pos);
        world.playSound(null, pos, RRSounds.generic_explosion, SoundCategory.AMBIENT, 1f, 1f);
        new RivalRebelsExplosion(world, pos.getX(), pos.getY(), pos.getZ(), 5, false, false, RivalRebelsDamageSource.plasmaexplosion);
    }
}
