package com.rivalrebels.common.items;

import java.util.HashSet;
import com.rivalrebels.common.init.RRItems;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class RRItemRod extends RRToolItem {
   public int power;

   public RRItemRod(String name) {
      super(name, ToolMaterial.DIAMOND, new HashSet<Block>());
      this.maxStackSize = 1;
      this.setMaxDamage(32);
      
      this.setCreativeTab(RRItems.nuclear_rr);
   }

   public boolean isItemTool(ItemStack is) {
      return true;
   }

   public boolean isDamageable() {
      return true;
   }
}
