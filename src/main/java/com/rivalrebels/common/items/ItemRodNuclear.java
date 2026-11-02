package com.rivalrebels.common.items;

import java.util.Random;

import com.rivalrebels.RivalRebels;
import com.rivalrebels.common.init.RRItems;
import com.rivalrebels.common.init.RivalRebelsDamageSource;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemRodNuclear extends RRItemRod {
    public ItemRodNuclear(String name) {
        super(name);
    }

    @Override
    public void onUpdate(ItemStack item, World world, Entity entity, int par4, boolean par5) {
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entity;
            Random rand = new Random();
            if (player.inventory.getStackInSlot(player.inventory.currentItem) != null
                && player.inventory.getStackInSlot(player.inventory.currentItem).getItem() == RRItems.nuclear_rod
                && rand.nextInt(16) == 0
                && !player.capabilities.isCreativeMode) {
                player.attackEntityFrom(RivalRebelsDamageSource.radiation, rand.nextInt(4));
            }
        }
    }


}
