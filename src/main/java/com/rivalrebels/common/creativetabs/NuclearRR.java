package com.rivalrebels.common.creativetabs;

import com.rivalrebels.common.init.RRItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class NuclearRR extends CreativeTabs {
    public NuclearRR(String lable){
        super(lable);
    }
    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(RRItems.nuclear_rod);
    }
}
