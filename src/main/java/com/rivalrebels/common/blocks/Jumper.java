package com.rivalrebels.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Jumper extends RRBlock{
    public Jumper(String name, Material mat){
        super(name, mat);
    }
    @Override
    public void onEntityCollidedWithBlock(World par1World, BlockPos pos, IBlockState state, Entity par5Entity)
    {
        if (par5Entity instanceof EntityLivingBase)
        {
            par5Entity.motionY += 2;
            par1World.playSound(null, pos, SoundEvents.ENTITY_ARROW_HIT, SoundCategory.AMBIENT, 3F, 2);
        }
    }
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess par1World, BlockPos pos)
    {
        float f = 0.0625F;
        return new AxisAlignedBB(0, 0, 0, 1, 1 - f, 1);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

}
