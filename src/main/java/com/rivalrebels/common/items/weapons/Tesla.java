package com.rivalrebels.common.items.weapons;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

import org.lwjgl.input.Keyboard;

import com.rivalrebels.RivalRebels;
import com.rivalrebels.common.config.RRConfigx;
import com.rivalrebels.common.entity.EntityElectricityRaytrace;
import com.rivalrebels.common.init.RRConfigOptions;
import com.rivalrebels.common.init.RRItems;
import com.rivalrebels.common.init.RRSounds;
import com.rivalrebels.common.init.RivalRebelsDamageSource;
import com.rivalrebels.common.items.RRItem;

public class Tesla extends RRItem {
    boolean message = true; // bruh this is singleton
    
    public Tesla(String name) {
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

    public int getItemEnchantability() {
        return 100;
    }

    public boolean isFull3D() {
        return true;
    }

    @Override 
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        int degree = this.getDegree(stack);
        float rate = Math.abs(degree - 90) / 90.0F;
        if (!player.capabilities.isCreativeMode && !player.inventory.hasItemStack(new ItemStack(RRItems.battery)) && !RRConfigx.infinite_ammo) {
            if (!world.isRemote) {
                player.sendStatusMessage(
                    new TextComponentTranslation("rivalrebels.message.out_of_batteries")
                        .setStyle(new Style().setColor(TextFormatting.RED)),
                    true
                );
            }
        } else {

            player.setActiveHand(hand);
        }

        if (message && world.isRemote) {
            player.sendStatusMessage(
                new TextComponentTranslation("rivalrebels.orders")
                    .setStyle(new Style().setColor(TextFormatting.DARK_RED))
                    .appendSibling(new TextComponentTranslation("rivalrebels.message.use")
                        .setStyle(new Style().setColor(TextFormatting.RED)))
                    .appendSibling(new TextComponentString(" [R].")
                        .setStyle(new Style().setColor(TextFormatting.RED))),
                false
            );
            message = false;
        }

        return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
    }

    private boolean consumeBatteries(EntityPlayer player, int amount) {
        for (ItemStack stack : player.inventory.mainInventory) {
            if (!stack.isEmpty() && stack.getItem() == RRItems.battery) {
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
    public void onUpdate(ItemStack item, World world, Entity entity, int par4, boolean par5) {

        if (!item.hasTagCompound()) item.setTagCompound(new NBTTagCompound());

        if (!item.getTagCompound().getBoolean("isReady")) {
            item.getTagCompound().setBoolean("isReady", true);
            item.getTagCompound().setInteger("mode", 2);
        }


        Side side = FMLCommonHandler.instance().getEffectiveSide();
        if (side == Side.CLIENT) openGui(item, entity);

    }

    public void openGui(ItemStack item, Entity entity)
    {
        if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
            if ((RRConfigOptions.alt_R_key? Keyboard.isKeyDown(Keyboard.KEY_F):Keyboard.isKeyDown(Keyboard.KEY_R)) && item == ((EntityPlayer) entity).inventory.getCurrentItem() && Minecraft.getMinecraft().currentScreen == null)
            {
                RivalRebels.proxy.teslaGui(getDegree(item));
            }
    }


    @Override 
    public void onUsingTick(ItemStack stack, EntityLivingBase entity, int count) {
        World world = entity.world;

        if (!world.isRemote && entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;

            if (!player.capabilities.isCreativeMode
                    && !RRConfigx.infinite_ammo) {

                int degree = getDegree(stack);
                float rate = Math.abs(degree - 90) / 90.0F;

                // 1 battery / 20 ticks at center
                // 3 batteries / 20 ticks at extremes
                int consumption = Math.round(1 + 2 * rate);

                int interval = 20 / consumption;

                if ((72000 - count) % interval == 0) {
                    if (!consumeBatteries(player, 1)) {
                        player.stopActiveHand();
                        return;
                    }
                }
            }
        }


        if (entity.isWet()) {
            //RivalRebelsDamageSource damage = new RivalRebelsDamageSource(RivalRebelsDamageSource.RR_damage_sources.ELECTRICITY, null, null);
            DamageSource damage = RivalRebelsDamageSource.charge; // temp
            if (entity instanceof EntityPlayer) {
                if (!(((EntityPlayer)entity).capabilities.isCreativeMode)) {
                    entity.attackEntityFrom(damage, 2.0F);
                }
            } else {
                entity.attackEntityFrom(damage, 2.0F);
            }
        }

        if (entity.world.rand.nextInt(10) == 0) {

            world.playSound(null, entity.posX, entity.posY, entity.posZ, RRSounds.tesla_fire, SoundCategory.AMBIENT, 0.5f, 1);
        }

        int degree = this.getDegree(stack);
        float chance = Math.abs(degree - 90) / 90.0F;
        if (degree - 90 > 0) {
            chance /= 10.0F;
        }

        float dist = 7.0F + (1.0F - degree / 180.0F) * 73.0F;
        float randomness = degree / 720.0F;
        int num = degree / 25 + 1;
        if (!world.isRemote) {
            for (int i = 0; i < num; i++) {
                world.spawnEntity(new EntityElectricityRaytrace(world, entity, dist, randomness, chance, !stack.isItemEnchanted()));
            }
        }
    }

    public int getDegree(ItemStack item) {
        return (!item.hasTagCompound()) ? 0 : item.getTagCompound().getInteger("dial") + 90;
    }

    /*
    public void registerIcons(IIconRegister iconregister) {
        this.itemIcon = iconregister.registerIcon("RivalRebels:ax");
    }
    */
}

