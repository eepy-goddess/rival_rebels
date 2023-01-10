package com.rivalrebels.common.blocks;

import com.rivalrebels.common.tileentity.TileNuke;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class NukeBlock extends RRBlock {
    //I know PropertyDirection exists, but I don't care
    public static PropertyInteger meta = PropertyInteger.create("meta", 0, 5);
    public NukeBlock(Material mat){
        super("nuke", mat);
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.INVISIBLE;
    }



    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    public static int determineOrientation(World world, int x, int y, int z, EntityPlayer entity)
    {
        if (MathHelper.abs((float) entity.posX - x) < 2.0F && MathHelper.abs((float) entity.posZ - z) < 2.0F)
        {
            double var5 = entity.posY + 1.82D - entity.getYOffset();

            if (var5 - y > 2.0D)
            {
                return 1;
            }

            if (y - var5 > 0.0D)
            {
                return 0;
            }
        }

        int var7 = MathHelper.floor((entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        return var7 == 0 ? 2 : (var7 == 1 ? 5 : (var7 == 2 ? 3 : (var7 == 3 ? 4 : 0)));
    }
    /**
     * Called when the block is placed in the world.
     */
    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase entity, ItemStack stack)
    {
        if (MathHelper.abs((float) entity.posX - pos.getX()) < 2.0F && MathHelper.abs((float) entity.posZ - pos.getZ()) < 2.0F)
        {
            double var5 = entity.posY + 1.82D - entity.getYOffset();

            if (var5 - pos.getY() > 2.0D)
            {
                world.setBlockState(pos, this.getDefaultState().withProperty(meta, 1));
                return;
            }

            if (pos.getY() - var5 > 0.0D)
            {
                world.setBlockState(pos, this.getDefaultState().withProperty(meta, 0));
                return;
            }
        }
        int var7 = MathHelper.floor((entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        world.setBlockState(pos, this.getDefaultState().withProperty(meta, var7 == 0 ? 2 : (var7 == 1 ? 5 : (var7 == 2 ? 3 : (var7 == 3 ? 4 : 0)))));
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
    public boolean isBlockNormalCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        return false;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World worldIn, IBlockState meta) {
        return new TileNuke();
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
