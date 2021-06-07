package com.rivalrebels.common.blocks;

import com.rivalrebels.common.init.RRSounds;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class Quicksand extends RRBlock{


    public Quicksand(String name, Material material) {
        super(name, material);
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return null;
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        entityIn.fallDistance = 0.5f;
        entityIn.motionY = entityIn.motionY * 0.005;
        if (worldIn.rand.nextFloat() > 0.95) worldIn.playSound(null, pos, RRSounds.quicksand, SoundCategory.AMBIENT, 1f, 1f);
    }
}
