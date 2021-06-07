package com.rivalrebels.common.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public abstract class TileEntityInventoryBase extends TileEntity {
    public ItemStackHandler inventory = createInventory();
    public TileEntityInventoryBase(){
    }
    public ItemStackHandler createInventory(){
        return new ItemStackHandler(getSlotSize()){
            @Override
            protected void onContentsChanged(int slot) {
                super.onContentsChanged(slot);
                markDirty();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return getValidItems(slot, stack);
            }
        };
    }
    public boolean isUseableByPlayer(EntityPlayer player){
        if(world.getTileEntity(pos) != this)
        {
            return false;
        }else{
            return player.getDistanceSq(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D) <=128;
        }
    }
    public void readFromNBT(NBTTagCompound nbt){
        super.readFromNBT(nbt);
        if(nbt.hasKey("contents"))
            inventory.deserializeNBT(nbt.getCompoundTag("contents"));
    }
    public NBTTagCompound writeToNBT(NBTTagCompound nbt){
        nbt.setTag("contents", inventory.serializeNBT());
        return super.writeToNBT(nbt);
    }
    public abstract int getSlotSize();
    public boolean getValidItems(int index, ItemStack item){
        return true;
    }
}
