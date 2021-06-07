package com.rivalrebels.common.tileentity;

import com.rivalrebels.RivalRebels;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

public class MineTileEntity extends TileEntity {
    public IBlockState getBlockState(){
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        IBlockState[] n = new IBlockState[6];
        n[0] = world.getBlockState(new BlockPos(x + 1, y, z));
        n[1] = world.getBlockState(new BlockPos(x - 1, y, z));
        n[2] = world.getBlockState(new BlockPos(x, y + 1, z));
        n[3] = world.getBlockState(new BlockPos(x, y - 1, z));
        n[4] = world.getBlockState(new BlockPos(x, y, z + 1));
        n[5] = world.getBlockState(new BlockPos(x, y, z - 1));

        int popularity1 = 0;
        int popularity2 = 0;
        IBlockState mode = Blocks.GRASS.getDefaultState();
        IBlockState array_item = null;
        for (int i = 0; i < 6; i++)
        {
            array_item = n[i];
            if (array_item == null || !array_item.isOpaqueCube() || array_item == RivalRebels.mine.getDefaultState() || array_item == RivalRebels.mario.getDefaultState() /*|| array_item == RivalRebels.mario || array_item == RivalRebels.amario || array_item == RivalRebels.quicksand || array_item == RivalRebels.aquicksand*/) continue;
            for (int j = 0; j < n.length; j++)
            {
                if (array_item == n[j]) popularity1++;
                if (popularity1 >= popularity2)
                {
                    mode = array_item;
                    popularity2 = popularity1;
                }
            }
            popularity1 = 0;
        }
        return mode;
    }
}
