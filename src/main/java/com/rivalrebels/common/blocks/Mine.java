package com.rivalrebels.common.blocks;

import com.rivalrebels.RivalRebels;
import com.rivalrebels.common.explosion.RivalRebelsExplosion;
import com.rivalrebels.common.init.RRSounds;
import com.rivalrebels.common.init.RivalRebelsDamageSource;
import com.rivalrebels.common.tileentity.MineTileEntity;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class Mine extends BlockFalling {
    public Mine(String name, Material material) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        RivalRebels.other_blocks.add(this);
        setTickRandomly(true);
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.INVISIBLE;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new MineTileEntity();
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
        createExplosion(worldIn, pos);
    }

    @Override
    public void onEndFalling(World worldIn, BlockPos pos, IBlockState p_176502_3_, IBlockState p_176502_4_) {
        createExplosion(worldIn, pos);
    }

    public void createExplosion(World world, BlockPos pos){
        if(!world.isRemote) {
            world.setBlockToAir(pos);
            new RivalRebelsExplosion(world, pos.getX(), pos.getY(), pos.getZ(), 5, false, false, RivalRebelsDamageSource.plasmaexplosion);
        }
        world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), RRSounds.generic_explosion, SoundCategory.AMBIENT, 1f, 0.3f);
    }

}
