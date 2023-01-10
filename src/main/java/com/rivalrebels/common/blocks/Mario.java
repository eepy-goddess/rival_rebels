package com.rivalrebels.common.blocks;

import com.rivalrebels.common.tileentity.TileMario;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class Mario extends RRBlock {
    public Mario(String name, Material blockMaterial) {
        super(name, blockMaterial);
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }
    @Override
    public TileEntity createTileEntity(World world, IBlockState state){
        return new TileMario();
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.INVISIBLE;
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        float f = 0.0625f;
        return new AxisAlignedBB(0, 0, 0, 1, 1 - f, 1);
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        worldIn.scheduleBlockUpdate(pos, this, this.tickRate(worldIn), 1);
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        worldIn.setBlockToAir(pos);
        worldIn.setBlockState(pos, Blocks.GRAVEL.getDefaultState());
    }
}
