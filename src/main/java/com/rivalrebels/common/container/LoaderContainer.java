package com.rivalrebels.common.container;

import com.rivalrebels.common.tileentity.TileLoader;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

public class LoaderContainer extends Container {
    public TileLoader lowerInventory;
    public IInventory upperInventory;
    public LoaderContainer(IInventory playerInventory, TileLoader lowerLoaderInventory){
        this.lowerInventory = lowerLoaderInventory;
        this.upperInventory = playerInventory;
        int var4;
        int var5;

        for (var4 = 0; var4 < 6; ++var4)
        {
            for (var5 = 0; var5 < 2; ++var5)
            {
                this.addSlotToContainer(new SlotItemHandler(lowerLoaderInventory.inventory, var5 + var4 * 2, 10 + var5 * 18, 73 + var4 * 18));
            }
        }

        for (var4 = 0; var4 < 6; ++var4)
        {
            for (var5 = 0; var5 < 2; ++var5)
            {
                this.addSlotToContainer(new SlotItemHandler(lowerLoaderInventory.inventory, 12 + var5 + var4 * 2, 212 + var5 * 18, 73 + var4 * 18));
            }
        }

        for (var4 = 0; var4 < 4; ++var4)
        {
            for (var5 = 0; var5 < 9; ++var5)
            {
                this.addSlotToContainer(new SlotItemHandler(lowerLoaderInventory.inventory, 24 + var5 + var4 * 9, 48 + var5 * 18, 48 + var4 * 18));
            }
        }

        for (var4 = 0; var4 < 3; ++var4)
        {
            for (var5 = 0; var5 < 9; ++var5)
            {
                this.addSlotToContainer(new Slot(playerInventory, var5 + var4 * 9 + 9, 48 + var5 * 18, 127 + var4 * 18));
            }
        }

        for (var4 = 0; var4 < 9; ++var4)
        {
            this.addSlotToContainer(new Slot(playerInventory, var4, 48 + var4 * 18, 183));
        }
    }
    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack var3 = null;
        Slot var4 = (Slot) this.inventorySlots.get(par2);

        if (var4 != null && var4.getHasStack())
        {
            ItemStack var5 = var4.getStack();
            var3 = var5.copy();

            if (par2 < 60)
            {
                if (!this.mergeItemStack(var5, 60, this.inventorySlots.size(), true))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(var5, 0, 60, false))
            {
                return null;
            }

            if (var5.getCount() == 0)
            {
                var4.putStack(ItemStack.EMPTY);
            }
            else
            {
                var4.onSlotChanged();
            }
        }

        return var3;
    }
    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return lowerInventory.isUseableByPlayer(playerIn);
    }
}
