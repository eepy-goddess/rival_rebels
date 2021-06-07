package com.rivalrebels.common.blocks;

import com.rivalrebels.RivalRebels;
import com.rivalrebels.common.tileentity.TileLoader;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;

import javax.annotation.Nullable;

public class LoaderBlock extends Block {
    public static PropertyInteger meta = PropertyInteger.create("meta", 0, 3);
    public LoaderBlock(Material materialIn) {
        super(materialIn);
        setUnlocalizedName("loader");
        setRegistryName("loader");
        RivalRebels.other_blocks.add(this);
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.INVISIBLE;
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
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        return false;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        EnumFacing direction = placer.getAdjustedHorizontalFacing();
        if(direction == EnumFacing.NORTH){
            worldIn.setBlockState(pos, this.getDefaultState().withProperty(meta, 0));
        } else if(direction == EnumFacing.EAST){
            worldIn.setBlockState(pos, this.getDefaultState().withProperty(meta, 1));
        } else if(direction == EnumFacing.SOUTH){
            worldIn.setBlockState(pos, this.getDefaultState().withProperty(meta, 2));
        } else if(direction == EnumFacing.WEST){
            worldIn.setBlockState(pos, this.getDefaultState().withProperty(meta, 3));
        }
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileLoader();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = playerIn.getHeldItem(hand);
        if(!playerIn.isSneaking()){
            FMLNetworkHandler.openGui(playerIn, RivalRebels.instance, 1, worldIn, pos.getX(), pos.getY(), pos.getZ());
            return true;
        }
        return false;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{meta});
    }

    @Override
    public IBlockState getStateFromMeta(int metadata) {
        return this.getDefaultState().withProperty(meta, metadata);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(meta);
    }
}
