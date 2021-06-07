package com.rivalrebels.common.items;

import com.rivalrebels.common.init.RRSounds;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class VerticeLemonade extends RRItem{
    public VerticeLemonade(String name) {
        super(name);

    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand handIn) {
        ItemStack stack = player.getHeldItem(handIn);
        player.setActiveHand(handIn);
        if (!world.isRemote)
        {
            player.sendStatusMessage(new TextComponentString("§7[".substring(1) + "§6Status".substring(1) + "§7]".substring(1) + "§e Regenerating...".substring(1)), false);
            world.playSound(null, player.posX, player.posY, player.posZ, RRSounds.gulp, SoundCategory.AMBIENT, 1, 1);
            world.playSound(null, player.posX, player.posY, player.posZ, RRSounds.toyguy, SoundCategory.AMBIENT, 1, 1);
            world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_MAGMACUBE_JUMP, SoundCategory.AMBIENT, 1.0F, 1.0F);
            world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_GHAST_HURT, SoundCategory.MASTER, 1.0F, 1.0F);

            ((EntityLivingBase) player).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 10, 20));
            player.getFoodStats().addStats(10, 200);
            player.heal(10);
            stack.shrink(1);
            if(handIn == EnumHand.OFF_HAND){
                player.sendStatusMessage(new TextComponentString("you have reached a secret message, that is all"), false);
            }
        }
        return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.EAT;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 32;
    }

}
