package com.rivalrebels.common.creativetabs;

import com.rivalrebels.common.init.RRItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class HydroRR extends CreativeTabs {
    public HydroRR(String lable){
        super(lable);
    }
    public ItemStack getTabIconItem(){
        return new ItemStack(RRItems.hydro_rod);
    }
}
