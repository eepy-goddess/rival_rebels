package com.rivalrebels.common.items;

import com.rivalrebels.RivalRebels;
import com.rivalrebels.common.entity.EntityCuchillo;
import com.rivalrebels.common.init.RRSounds;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashSet;

public class Cuchillo extends ItemTool implements IHasModel{
    public Cuchillo(String name) {
        super(ToolMaterial.IRON, new HashSet());
        setUnlocalizedName(name);
        setRegistryName(name);
        RivalRebels.model_items.add(this);
        RivalRebels.other_items.add(this);
    }
    @Override
    public int getMaxItemUseDuration(ItemStack stack){
        return 72000;
    }
    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.BOW;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand){
        ItemStack stack = player.getHeldItem(hand);
        player.setActiveHand(hand);
        return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase player, int i) {
        if(player instanceof EntityPlayer) {
            if (((EntityPlayer)player).capabilities.isCreativeMode || ((EntityPlayer)player).inventory.hasItemStack(new ItemStack(RivalRebels.knife))) {
                float f = (getMaxItemUseDuration(stack) - i) / 20.0F;
                f = (f * f + f * 2) * 0.3333f;
                if (f < 0.1D) return;
                if (f > 1.0F) f = 1.0F;
                if (!((EntityPlayer)player).capabilities.isCreativeMode)
                    ((EntityPlayer)player).inventory.decrStackSize(((EntityPlayer)player).inventory.currentItem, 1);
                worldIn.playSound(null, player.posX, player.posY, player.posZ, RRSounds.knife_throw, SoundCategory.AMBIENT, 1f, 1f);
                if (!worldIn.isRemote) worldIn.spawnEntity(new EntityCuchillo(worldIn, (EntityPlayer) player, 0.5f + f));
            }
        }
    }

    @Override
    public String getModelName() {
        return RivalRebels.modid + ":knife";
    }
}
