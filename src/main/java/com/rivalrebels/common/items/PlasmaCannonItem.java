package com.rivalrebels.common.items;

import com.rivalrebels.RivalRebels;
import com.rivalrebels.common.entity.EntityPlasmoid;
import com.rivalrebels.common.init.RRSounds;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import java.util.HashSet;

public class PlasmaCannonItem extends ItemTool implements IHasModel{
     public PlasmaCannonItem(String name){
         super(ToolMaterial.DIAMOND, new HashSet());
         setUnlocalizedName(name);
         setRegistryName(name);
         maxStackSize = 1;
         attackSpeed = 0.52F;
         RivalRebels.model_items.add(this);
         RivalRebels.other_items.add(this);
     }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
         ItemStack stack = playerIn.getHeldItem(handIn);
         playerIn.setActiveHand(handIn);
         if(!worldIn.isRemote){
             worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, RRSounds.plasma_prime, SoundCategory.AMBIENT, 0.25f, 1f);
         }
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
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase player, int timeLeft) {
        if (!player.world.isRemote)
        {
            float f = (getMaxItemUseDuration(stack) - timeLeft) / 20.0F;
            f = (f * f + f * 2) * 0.3333f;
            if (f > 1.0F) f = 1.0F;
            f+=0.2f;
            worldIn.playSound(null, player.posX, player.posY, player.posZ, RRSounds.plasma_fire, SoundCategory.AMBIENT, 1, 1);
            Entity entity = new EntityPlasmoid(player.world, (EntityPlayer) player, f+0.5f, stack.getEnchantmentTagList() != null);
            player.world.spawnEntity(entity);
        }
    }

    @Override
    public String getModelName() {
        return RivalRebels.modid + ":plasma_cannon";
    }

}
