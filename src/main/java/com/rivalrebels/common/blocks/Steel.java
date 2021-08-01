package com.rivalrebels.common.blocks;

import com.rivalrebels.common.tileentity.TileEntitySteel;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BlockModelRenderer;
import net.minecraft.client.renderer.EnumFaceDirection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

import javax.annotation.Nullable;
import java.util.List;

public class Steel extends RRBlock {
    public Steel(Material mat){
        super("steel", mat);
    }
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }


    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }
    @Override
    public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer) {
        return layer == BlockRenderLayer.CUTOUT;
    }
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess par1World, BlockPos pos)
    {
        float f = 0.0625F;
        return new AxisAlignedBB(f, f, f, 1 - f, + 1, 1 - f);
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entity) {
        if (entity instanceof EntityLivingBase) {
            if (entity.isSneaking() && !entity.collidedHorizontally) {
                entity.motionY = 0.08;
                entity.fallDistance = 0;
            } else if (entity.collidedHorizontally) {
                entity.motionY = 0.25;
                entity.fallDistance = 0;
            } else if (entity.onGround) {
            } else if (entity.collidedVertically) {
                entity.motionY = 0.08;
                entity.fallDistance = 0;
            } else {
                entity.motionY = -0.1;
                entity.fallDistance = 0;
            }
        }
    }

    @Override
    public boolean isLadder(IBlockState state, IBlockAccess world, BlockPos pos, EntityLivingBase entity) {
        return true;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }


    @Override
    public boolean causesSuffocation(IBlockState state) {
        return false;
    }

}
