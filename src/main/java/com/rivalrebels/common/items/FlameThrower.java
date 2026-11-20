package com.rivalrebels.common.items;

import com.rivalrebels.RivalRebels;
import com.rivalrebels.common.entity.EntityFlameBallHighTemp;
import com.rivalrebels.common.entity.EntityFlameBallLowTemp;
import com.rivalrebels.common.entity.EntityFlameBallMediumTemp;

import com.rivalrebels.common.init.RRConfigOptions;
import com.rivalrebels.common.init.RRItems;
import com.rivalrebels.common.init.RRSounds;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

import org.lwjgl.input.Keyboard;

public class FlameThrower extends RRItem {
    public FlameThrower(String name) {
        super(name);
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.BOW;
    }
    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 72000;
    }
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        if (player.capabilities.isCreativeMode || player.inventory.hasItemStack(new ItemStack(RRItems.fuel)) || RRConfigOptions.infinite_ammo)
        {
            player.setActiveHand(hand);

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
    public void onUsingTick(ItemStack item, EntityLivingBase entity, int ticks_remaining) {
        shrink = ticks_remaining;
        World world = entity.world;


        if (!world.isRemote && entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;

            if (!player.capabilities.isCreativeMode
                    && !RRConfigOptions.infinite_ammo) {

                int mode = getMode(item);

                int interval = mode == 0 ? 13 : mode == 1 ? 21 : 64;

                if ((72000 - ticks_remaining) % interval == 0) {
                    if (!consumeFuel(player, 1)) {
                        player.stopActiveHand();
                        return;
                    }
                }
            }
        }

        
        if (!world.isRemote)
        {
            if (((EntityPlayer)entity).inventory.getCurrentItem() != ItemStack.EMPTY)
            {
                if (((EntityPlayer)entity).inventory.getCurrentItem().getItem() == this)
                {
                    if (world.rand.nextInt(10) == 0 && !entity.isInWater()) /*if (world.getTotalWorldTime() % 10 == 0 && !entity.isInWater()) */
                    {
                        world.playSound(null, entity.posX, entity.posY, entity.posZ, RRSounds.flamethrower_fire, SoundCategory.AMBIENT, 0.03f, 1);
                        if (world.rand.nextInt(3) == 0 && !entity.isInWater())
                        {
                            world.playSound(null, entity.posX, entity.posY, entity.posZ, RRSounds.flamethrower_fire, SoundCategory.AMBIENT, 0.1f, 1);
                        }
                    }
                    if (!item.isItemEnchanted())
                    {
                        switch (getMode(item))
                        {
                            case 0:
                                for (int i = 0; i < 4; i++) world.spawnEntity(new EntityFlameBallHighTemp(world, (EntityPlayer) entity, (float) (Math.random() + 0.5f)));
                                break;
                            case 1:
                                world.spawnEntity(new EntityFlameBallMediumTemp(entity.world, (EntityPlayer) entity, 1));
                                break;
                            case 2:
                                world.spawnEntity(new EntityFlameBallLowTemp(world, (EntityPlayer) entity, 1));
                                break;
                        }
                    }
                }
            }
        }
    }

    private boolean consumeFuel(EntityPlayer player, int amount) {
        for (ItemStack stack : player.inventory.mainInventory) {
            if (!stack.isEmpty() && stack.getItem() == RRItems.fuel) {
                int consumed = Math.min(amount, stack.getCount());
                stack.shrink(consumed);
                amount -= consumed;

                if (amount <= 0) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public void onUpdate(ItemStack item, World world, Entity entity, int par4, boolean par5)
    {
        // Setup the tag if it doesn't exist
        if (!item.hasTagCompound()) item.setTagCompound(new NBTTagCompound());

        if (!item.getTagCompound().getBoolean("isReady")) {
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
            if ((RRConfigOptions.alt_R_key? Keyboard.isKeyDown(Keyboard.KEY_F):Keyboard.isKeyDown(Keyboard.KEY_R)) && item == ((EntityPlayer) entity).inventory.getCurrentItem() && Minecraft.getMinecraft().currentScreen == null)
            {
                RivalRebels.proxy.flamethrowerGui(getMode(item));
            }
    }

    // [mode 0 is melting tool uses 5 fuel], [mode 1 is Armor piercing tool uses 3 fuel], [mode 2 is Full spread mode uses 1 fuel]
    private int getMode(ItemStack item) {
        if (!item.hasTagCompound()) return 0;
        else return item.getTagCompound().getInteger("mode");
    }
}
