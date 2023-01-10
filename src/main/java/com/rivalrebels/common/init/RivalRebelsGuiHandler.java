package com.rivalrebels.common.init;

import com.rivalrebels.client.guiLoader.LoaderGui;
import com.rivalrebels.common.container.LoaderContainer;
import com.rivalrebels.common.tileentity.TileLoader;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

public class RivalRebelsGuiHandler implements IGuiHandler {
    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
        if (ID == 1) {
            if (tileEntity instanceof TileLoader) {
                return new LoaderContainer(player.inventory, (TileLoader) tileEntity);
            }
        }
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
        if (ID == 1) {
            if (tileEntity instanceof TileLoader) {
                return new LoaderGui(player.inventory, (TileLoader) tileEntity);
            }
        }
        return null;
    }
}
