package com.rivalrebels.common.blocks;

import com.rivalrebels.common.init.RivalRebelsDamageSource;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class ToxicGas extends RRBlock {
    public ToxicGas(String name, Material material) {
        super(name, material);
        setTickRandomly(true);
    }

    @Override
    public boolean isFlammable(IBlockAccess world, BlockPos pos, EnumFacing face) {
        return true;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public int damageDropped(IBlockState state) {
        return 0;
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
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.INVISIBLE;
    }
    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return null;
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        if(entityIn instanceof EntityLivingBase) {
            ((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 80));
            ((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(MobEffects.POISON, 200));
            ((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 200));
            ((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 80));
            ((EntityLivingBase)entityIn).attackEntityFrom(RivalRebelsDamageSource.gas, 1);
        }
    }
    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state)
    {
        world.scheduleBlockUpdate(pos, this, 8, 0);
    }
    @Override
    public void updateTick(World par1World, BlockPos pos, IBlockState state, Random par5Random)
    {
        par1World.scheduleBlockUpdate(pos, this, 8, 0);
        if (par1World.rand.nextInt(25) == 1)
        {
            par1World.setBlockState(pos, Blocks.AIR.getDefaultState());
        }
    }
    @Override
    public void randomDisplayTick(IBlockState state, World w, BlockPos pos, Random r)
    {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        w.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, x + 0.5, y + 0.5, z + 0.5, (r.nextFloat() - 0.5) * 0.1, (r.nextFloat() - 0.5) * 0.1, (r.nextFloat() - 0.5) * 0.1);
        w.spawnParticle(EnumParticleTypes.SPELL, x + 0.5, y + 0.5, z + 0.5, (r.nextFloat() - 0.5) * 0.1, (r.nextFloat() - 0.5) * 0.1, (r.nextFloat() - 0.5) * 0.1);
    }
}
