package com.rivalrebels.common.blocks;

import com.rivalrebels.common.init.RRBlocks;
import com.rivalrebels.common.init.RRItems;
import com.rivalrebels.common.packet.PacketDispatcher;
import com.rivalrebels.common.packet.TextPacket;
import com.rivalrebels.common.tileentity.TileNukeCrate;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class NukeCrate extends RRBlock {
    public static PropertyInteger meta = PropertyInteger.create("meta", 0, 5);

    public NukeCrate(String name, Material mat){
        super(name, mat);
    }

    public int determineOrientation(World world, int x, int y, int z, EntityPlayer entity)
    {
        int orientation = 0;
        if (this == RRBlocks.nuke_crate_top)
        {
            if (world.getBlockState(new BlockPos(x, y + 1, z)).getBlock() == RRBlocks.nuke_crate_bottom)
            {
                orientation = 0;
            }
            else if (world.getBlockState(new BlockPos(x, y - 1, z)).getBlock() == RRBlocks.nuke_crate_bottom)
            {
                orientation = 1;
            }
            else if (world.getBlockState(new BlockPos(x, y, z + 1)).getBlock() == RRBlocks.nuke_crate_bottom)
            {
                orientation = 2;
            }
            else if (world.getBlockState(new BlockPos(x, y, z - 1)).getBlock() == RRBlocks.nuke_crate_bottom)
            {
                orientation = 3;
            }
            else if (world.getBlockState(new BlockPos(x + 1, y, z)).getBlock() == RRBlocks.nuke_crate_bottom)
            {
                orientation = 4;
            }
            else if (world.getBlockState(new BlockPos(x - 1, y, z)).getBlock() == RRBlocks.nuke_crate_bottom)
            {
                orientation = 5;
            }
        }
        if (this == RRBlocks.nuke_crate_bottom)
        {
            if (world.getBlockState(new BlockPos(x, y + 1, z)).getBlock() == RRBlocks.nuke_crate_top)
            {
                orientation = 1;
            }
            else if (world.getBlockState(new BlockPos(x, y - 1, z)).getBlock() == RRBlocks.nuke_crate_top)
            {
                orientation = 0;
            }
            else if (world.getBlockState(new BlockPos(x, y, z + 1)).getBlock() == RRBlocks.nuke_crate_top)
            {
                orientation = 3;
            }
            else if (world.getBlockState(new BlockPos(x, y, z - 1)).getBlock() == RRBlocks.nuke_crate_top)
            {
                orientation = 2;
            }
            else if (world.getBlockState(new BlockPos(x + 1, y, z)).getBlock() == RRBlocks.nuke_crate_top)
            {
                orientation = 5;
            }
            else if (world.getBlockState(new BlockPos(x - 1, y, z)).getBlock() == RRBlocks.nuke_crate_top)
            {
                orientation = 4;
            }
        }
        return orientation;
    }

    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
        world.setBlockState(pos, this.getDefaultState().withProperty(meta, determineOrientation(world, pos.getX(), pos.getY(), pos.getZ(), null)), 0);
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        if (world.getBlockState(new BlockPos(x, y + 1, z)).getBlock() == RRBlocks.nuke_crate_top)
        {
            neighborChanged(state, world, new BlockPos(x, y + 1, z), this, pos);
        }
        else if (world.getBlockState(new BlockPos(x, y - 1, z)).getBlock() == RRBlocks.nuke_crate_top)
        {
            neighborChanged(state, world, new BlockPos(x, y - 1, z), this, pos);

        }
        else if (world.getBlockState(new BlockPos(x, y, z + 1)).getBlock() == RRBlocks.nuke_crate_top)
        {
            neighborChanged(state, world, new BlockPos(x, y, z + 1), this, pos);

        }
        else if (world.getBlockState(new BlockPos(x, y, z - 1)).getBlock() == RRBlocks.nuke_crate_top)
        {
            neighborChanged(state, world, new BlockPos(x, y, z - 1), this, pos);

        }
        else if (world.getBlockState(new BlockPos(x + 1, y, z)).getBlock() == RRBlocks.nuke_crate_top)
        {
            neighborChanged(state, world, new BlockPos(x + 1, y, z), this, pos);

        }
        else if (world.getBlockState(new BlockPos(x - 1, y, z)).getBlock() == RRBlocks.nuke_crate_top)
        {
            neighborChanged(state, world, new BlockPos(x - 1, y, z), this, pos);

        }
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if(worldIn.getBlockState(pos).getBlock() == RRBlocks.nuke_crate_top) {
            worldIn.setBlockState(pos, RRBlocks.nuke_crate_top.getDefaultState().withProperty(meta, determineOrientation(worldIn, pos.getX(), pos.getY(), pos.getZ(), null)), 2);
        } else if(worldIn.getBlockState(pos).getBlock() == RRBlocks.nuke_crate_top) {
            worldIn.setBlockState(pos, RRBlocks.nuke_crate_bottom.getDefaultState().withProperty(meta, determineOrientation(worldIn, pos.getX(), pos.getY(), pos.getZ(), null)), 2);
        }
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = playerIn.getHeldItem(hand);

        if (this == RRBlocks.nuke_crate_top) {
            if (stack.getItem().equals(RRItems.pliers)) {
                int orientation = 0;
                int x = pos.getX();
                int y = pos.getY();
                int z = pos.getZ();

                if (world.getBlockState(new BlockPos(x, y + 1, z)).getBlock() == RRBlocks.nuke_crate_bottom) {
                    world.setBlockState(new BlockPos(x, y + 1, z), Blocks.AIR.getDefaultState());
                    orientation = 0;
                } else if (world.getBlockState(new BlockPos(x, y - 1, z)).getBlock() == RRBlocks.nuke_crate_bottom) {
                    world.setBlockState(new BlockPos(x, y - 1, z), Blocks.AIR.getDefaultState());
                    orientation = 1;
                } else if (world.getBlockState(new BlockPos(x, y, z + 1)).getBlock() == RRBlocks.nuke_crate_bottom) {
                    world.setBlockState(new BlockPos(x, y, z + 1), Blocks.AIR.getDefaultState());
                    orientation = 2;
                } else if (world.getBlockState(new BlockPos(x, y, z - 1)).getBlock() == RRBlocks.nuke_crate_bottom) {
                    world.setBlockState(new BlockPos(x, y, z - 1), Blocks.AIR.getDefaultState());
                    orientation = 3;
                } else if (world.getBlockState(new BlockPos(x + 1, y, z)).getBlock() == RRBlocks.nuke_crate_bottom) {
                    world.setBlockState(new BlockPos(x + 1, y, z), Blocks.AIR.getDefaultState());
                    orientation = 4;
                } else if (world.getBlockState(new BlockPos(x - 1, y, z)).getBlock() == RRBlocks.nuke_crate_bottom) {
                    world.setBlockState(new BlockPos(x - 1, y, z), Blocks.AIR.getDefaultState());
                    orientation = 5;
                } else {
                    return false;
                }
                world.setBlockState(pos, RRBlocks.nuke.getDefaultState().withProperty(NukeBlock.meta, orientation));
                return true;
            } else if (!world.isRemote) {
                PacketDispatcher.wrapper.sendTo(new TextPacket(TextFormatting.RED, "RivalRebels.Orders RivalRebels.message.use " + RRItems.pliers.getUnlocalizedName() + ".name"), (EntityPlayerMP) playerIn);
            }
        }
        return false;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{meta});
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(this.meta, meta);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(meta);
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileNukeCrate();
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
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        return false;
    }

    @Override
    public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos) {
        return false;
    }

    @Override
    public boolean isNormalCube(IBlockState state) {
        return false;
    }
}
