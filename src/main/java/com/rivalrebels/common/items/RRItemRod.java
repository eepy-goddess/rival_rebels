package com.rivalrebels.common.items;

import java.util.HashSet;
import com.rivalrebels.common.init.RRItems;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

public class RRItemRod extends ItemTool {
    public int power;

    public RRItemRod() {
        super(ToolMaterial.DIAMOND, new HashSet<Block>());
        this.maxStackSize = 1;
        //this.setMaxDurability(32); This should be handled
        this.setCreativeTab(RRItems.nuclear_rr);
    }

   public boolean isItemTool(ItemStack is) {
      return true;
   }

   public boolean isDamageable() {
      return true;
   }
}
