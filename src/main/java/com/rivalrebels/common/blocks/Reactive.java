package com.rivalrebels.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class Reactive extends RRBlock {
    public static PropertyInteger meta = PropertyInteger.create("stage", 0, 15);
    public Reactive(String name, Material material){
        super(name, material);
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        super.breakBlock(world, pos, state);
        if (state.getValue(meta) < 15 && state.getValue(meta) >= 0)
        {
            world.setBlockState(pos, this.getDefaultState().withProperty(meta, state.getValue(meta) + 1));
        }
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(meta);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(Reactive.meta, meta);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{meta});
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
        tooltip.add(TextFormatting.GOLD + "WARNING: Do not sprint on unless you want your game to crash!");
    }
}
