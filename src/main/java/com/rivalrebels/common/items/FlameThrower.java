package com.rivalrebels.common.items;

import com.rivalrebels.RivalRebels;
import com.rivalrebels.common.entity.EntityFlameBall;
import com.rivalrebels.common.entity.EntityFlameBall1;
import com.rivalrebels.common.entity.EntityFlameBall2;
import net.minecraft.block.BlockTorch;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import org.lwjgl.input.Keyboard;

public class FlameThrower extends RRItem{
    public FlameThrower(String name) {
        super(name);
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.BOW;
    }
    @Override
    public int getMaxItemUseDuration(ItemStack stack){
        return 64;
    }
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand){
        ItemStack stack = player.getHeldItem(hand);
        if (player.capabilities.isCreativeMode || player.inventory.hasItemStack(new ItemStack(RivalRebels.fuel)) || RivalRebels.infiniteAmmo)
        {
            player.setActiveHand(hand);
            if (!player.capabilities.isCreativeMode && !RivalRebels.infiniteAmmo)
            {
                player.inventory.getStackInSlot(player.inventory.findSlotMatchingUnusedItem(new ItemStack(RivalRebels.fuel))).shrink(1);
                if (getMode(stack) != 2) player.inventory.getStackInSlot(player.inventory.findSlotMatchingUnusedItem(new ItemStack(RivalRebels.fuel))).shrink(1);
                if (getMode(stack) != 2) player.inventory.getStackInSlot(player.inventory.findSlotMatchingUnusedItem(new ItemStack(RivalRebels.fuel))).shrink(1);
                if (getMode(stack) == 0) player.inventory.getStackInSlot(player.inventory.findSlotMatchingUnusedItem(new ItemStack(RivalRebels.fuel))).shrink(1);
                if (getMode(stack) == 0) player.inventory.getStackInSlot(player.inventory.findSlotMatchingUnusedItem(new ItemStack(RivalRebels.fuel))).shrink(1);
            }
            /*if (stack.isItemEnchanted() && !world.isRemote)
            {
                par2World.spawnEntityInWorld(new EntityFlameBallGreen(par2World, par3EntityPlayer, (float) (Math.random() + 1.0f)));
            }*/
        }else if (!world.isRemote)
        {
            player.sendStatusMessage(new TextComponentString("§cOut of fuel".substring(1)), false);
        }
        if (message && world.isRemote)
        {
            player.sendStatusMessage(new TextComponentString(I18n.translateToLocal("RivalRebels.Orders")+" "+I18n.translateToLocal("RivalRebels.message.use")+" [R]."), false);
            message = false;
        }
        return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
    }
    boolean message = true;
    int shrink;
    @Override
    public void onUsingTick(ItemStack item, EntityLivingBase entity, int par4)
    {
        shrink = par4;
        World world = entity.world;
        if (!world.isRemote)
        {
            if (((EntityPlayer)entity).inventory.getCurrentItem() != ItemStack.EMPTY)
            {
                if (((EntityPlayer)entity).inventory.getCurrentItem().getItem() == this)
                {
                    if (world.rand.nextInt(10) == 0 && !entity.isInWater())
                    {
                        //RivalRebelsSoundPlayer.playSound(entity, 8, 0, 0.03f);
                        if (world.rand.nextInt(3) == 0 && !entity.isInWater())
                        {
                            //RivalRebelsSoundPlayer.playSound(entity, 8, 1, 0.1f);
                        }
                    }
                    if (!item.isItemEnchanted())
                    {
                        switch (getMode(item))
                        {
                            case 0:
                                for (int i = 0; i < 4; i++) world.spawnEntity(new EntityFlameBall2(world, (EntityPlayer) entity, (float) (Math.random() + 0.5f)));
                                break;
                            case 1:
                                world.spawnEntity(new EntityFlameBall1(entity.world, (EntityPlayer) entity, 1));
                                break;
                            case 2:
                                world.spawnEntity(new EntityFlameBall(world, (EntityPlayer) entity, 1));
                                break;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onUpdate(ItemStack item, World world, Entity entity, int par4, boolean par5)
    {
        if (!item.hasTagCompound()) item.setTagCompound(new NBTTagCompound());
        if (!item.getTagCompound().getBoolean("isReady"))
        {
            item.getTagCompound().setBoolean("isReady", true);
            item.getTagCompound().setInteger("mode", 2);
        }
        if (entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) entity;
            if (player.inventory.getCurrentItem() != ItemStack.EMPTY)
            {
                if (player.inventory.getCurrentItem().getItem() == this)
                {
                    if (world.rand.nextInt(10) == 0 && !player.isInWater())
                    {
                        //RivalRebelsSoundPlayer.playSound(player, 8, 0, 0.03f);
                    }
                }
            }
        }
        Side side = FMLCommonHandler.instance().getEffectiveSide();
        if (side == Side.CLIENT) openGui(item, entity);
    }

    public void openGui(ItemStack item, Entity entity)
    {
        if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
            if ((RivalRebels.altRkey? Keyboard.isKeyDown(Keyboard.KEY_F):Keyboard.isKeyDown(Keyboard.KEY_R)) && item == ((EntityPlayer) entity).inventory.getCurrentItem() && Minecraft.getMinecraft().currentScreen == null)
            {
                RivalRebels.proxy.flamethrowerGui(getMode(item));
            }
    }

    public int getMode(ItemStack item)
    {
        if (!item.hasTagCompound()) return 0;
        else return item.getTagCompound().getInteger("mode");
    }
}
