package com.rivalrebels.common.items;

import com.rivalrebels.common.entity.EntityGasGrenade;
import com.rivalrebels.common.init.RRItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class GasGrenade extends RRItem {
    public GasGrenade(String name){
        super(name);
    }
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand){
        ItemStack stack = player.getHeldItem(hand);
        player.setActiveHand(hand);
        world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_SLIME_ATTACK, SoundCategory.AMBIENT, 1.0f, 1.0f);
        return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 64;
    }
    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.BOW;
    }
    @Override
    public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityLivingBase player, int i)
    {
        if (((EntityPlayer)player).capabilities.isCreativeMode || ((EntityPlayer)player).inventory.hasItemStack(new ItemStack(RRItems.gas_grenade)) /*|| RivalRebels.infiniteGrenades*/)
        {
            float f = (getMaxItemUseDuration(itemstack) - i) / 20.0F;
            f = (f * f + f * 2) * 0.3333f;
            if (f > 1.0F) f = 1.0F;
            EntityGasGrenade entitysuperarrow = new EntityGasGrenade(world, (EntityPlayer) player, 0.3f + f * 0.5f);
            if (!((EntityPlayer)player).capabilities.isCreativeMode)
            {
                itemstack.shrink(1);
            }
            //RivalRebelsSoundPlayer.playSound(player, 4, 3, 1, 0.9f);
            if (!world.isRemote)
            {
                world.spawnEntity(entitysuperarrow);
                entitysuperarrow.setPosition(entitysuperarrow.posX, entitysuperarrow.posY - 0.05, entitysuperarrow.posZ);
            }
        }
    }

    @Override
    public void onUsingTick(ItemStack stack, EntityLivingBase player, int count)
    {
        int time = 75 - count;
        if (time == 15 || time == 30 || time == 45 || time == 60)
        {
            player.world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.BLOCK_NOTE_SNARE, SoundCategory.AMBIENT, 1.0F, 1.0F);
        }
        if (time == 75)
        {
            player.world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.BLOCK_NOTE_SNARE, SoundCategory.AMBIENT, 1.0F, 1.0F);
            player.addPotionEffect(new PotionEffect(MobEffects.POISON, 80, 1));
            player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 200, 0));
            player.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 80, 0));
            player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 80, 0));
            if (!((EntityPlayer)player).capabilities.isCreativeMode)
            {
                stack.shrink(1);
            }
        }
    }

}
