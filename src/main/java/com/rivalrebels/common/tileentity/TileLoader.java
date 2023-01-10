package com.rivalrebels.common.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;

public class TileLoader extends TileEntity implements ITickable {
    public double slide = 0;
    double test = Math.PI;
    int counter;
    public TileEntityList machines = new TileEntityList();
    public ItemStackHandler inventory;
    public TileLoader(){
        inventory = new ItemStackHandler(60){
            @Override
            protected void onContentsChanged(int slot) {
                markDirty();
            }
        };
    }
    @Override
    public void update() {

        slide = (Math.cos(test) + 1) / 32 * 14;

        List<EntityPlayer> players = world.playerEntities;
        Iterator iter = players.iterator();
        boolean i = false;
        while (iter.hasNext()) {
            EntityPlayer player = (EntityPlayer) iter.next();
            if (player.getDistanceSq(pos.getX() + 0.5f, pos.getY() + 0.5f, pos.getZ() + 0.5f) <= 9) {
                i = true;
            }
        }
        if (i) {
            if (slide < 0.871) test += 0.05;
        } else {
            if (slide > 0.004) test -= 0.05;
        }
        counter++;
        if (counter % 10 == 0) {

        }
        for(int dis = 0; dis < 8; dis++){
            TileEntity test = world.getTileEntity(new BlockPos(pos.getX() + dis, pos.getY(), pos.getZ()));
            if(test != null && test instanceof TileNuke){
                machines.add(test);
            }
            test = world.getTileEntity(new BlockPos(pos.getX() - dis, pos.getY(), pos.getZ()));
            if(test != null && test instanceof TileNuke){
                machines.add(test);
            }
            test = world.getTileEntity(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + dis));
            if(test != null && test instanceof TileNuke){
                machines.add(test);
            }
            test = world.getTileEntity(new BlockPos(pos.getX(), pos.getY(), pos.getZ() - dis));
            if(test != null && test instanceof TileNuke){
                machines.add(test);
            }
        }
        for(int index = 0; index < machines.getSize(); index++){
            TileEntity loadable = machines.get(index);
            if(loadable != null && !loadable.isInvalid()){

            } else{
                machines.remove(index);
            }
        }
        TileEntity te = world.getTileEntity(new BlockPos(getPos().getX(), getPos().getY() - 1, getPos().getZ()));
        if (te instanceof TileLoader)
        {
            TileLoader tel = (TileLoader) te;
            for (int q = 0; q < inventory.getSlots(); q++)
            {
                if (inventory.getStackInSlot(q) != ItemStack.EMPTY)
                {
                    for (int j = 0; j < tel.inventory.getSlots(); j++)
                    {
                        if (tel.inventory.getStackInSlot(j) == ItemStack.EMPTY)
                        {
                            tel.inventory.setStackInSlot(j, inventory.getStackInSlot(q));
                            inventory.setStackInSlot(q, ItemStack.EMPTY);
                            return;
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && inventory != null) || super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inventory);
        }
        return super.getCapability(capability, facing);
    }
    public boolean isUseableByPlayer(EntityPlayer player) {
        if(world.getTileEntity(pos) != this)
        {
            return false;
        }else{
            return player.getDistanceSq(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D) <=128;
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        if(compound.hasKey("inventory"))
        inventory.deserializeNBT(compound.getCompoundTag("inventory"));
        super.readFromNBT(compound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setTag("inventory", inventory.serializeNBT());
        return super.writeToNBT(compound);
    }

    @Override
    public void invalidate() {
        super.invalidate();
        this.updateContainingBlockInfo();
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return TileEntity.INFINITE_EXTENT_AABB;
    }

    @Override
    public double getMaxRenderDistanceSquared() {
        return 1650D;
    }
    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return oldState.getBlock() != newSate.getBlock();
    }
}
