package com.rivalrebels.common.items;

import api.hbm.energy.IBatteryItem;
import com.rivalrebels.RivalRebels;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class BatteryItem extends RRItem implements IBatteryItem {
    private long maxCharge = 100000000L;
    private long chargeRate = 100000L;
    private long dischargeRate = 100000L;
    public BatteryItem(String name) {
        super(name);
    }
    public void chargeBattery(ItemStack stack, long i) {
            if (stack.getItem() instanceof BatteryItem) {
                if (stack.hasTagCompound()) {
                    stack.getTagCompound().setLong("charge", stack.getTagCompound().getLong("charge") + i);
                } else {
                    stack.setTagCompound(new NBTTagCompound());
                    stack.getTagCompound().setLong("charge", i);
                }
            }

    }

    public void setCharge(ItemStack stack, long i) {
            if (stack.getItem() instanceof BatteryItem) {
                if (stack.hasTagCompound()) {
                    stack.getTagCompound().setLong("charge", i);
                } else {
                    stack.setTagCompound(new NBTTagCompound());
                    stack.getTagCompound().setLong("charge", i);
                }
            }

    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        try {
            //makes sure the class isn't null without crashing
            if(Class.forName("com.hbm.main.MainRegistry") != null)
                tooltip.add(TextFormatting.DARK_GREEN + "Usable as a power source for HBM nuker tech machines");
        } catch (ClassNotFoundException e) {
            System.out.println("Main class of HBM nuker tech not found, canceling tootip writing on " + this.getRegistryName());
        }
        if(this.getCharge(stack) == 0){
            tooltip.add("No charge");
        }
    }

    public void dischargeBattery(ItemStack stack, long i) {
            if (stack.getItem() instanceof BatteryItem) {
                if (stack.hasTagCompound()) {
                    stack.getTagCompound().setLong("charge", stack.getTagCompound().getLong("charge") - i);
                } else {
                    stack.setTagCompound(new NBTTagCompound());
                    stack.getTagCompound().setLong("charge", this.maxCharge - i);
                }
            }

    }

    public long getCharge(ItemStack stack) {
        if (stack.getItem() instanceof BatteryItem) {
            if (stack.hasTagCompound()) {
                return stack.getTagCompound().getLong("charge");
            } else {
                stack.setTagCompound(new NBTTagCompound());
                stack.getTagCompound().setLong("charge", ((BatteryItem)stack.getItem()).maxCharge);
                return stack.getTagCompound().getLong("charge");
            }
        } else {
            return 0L;
        }
    }

    public long getMaxCharge() {
        return this.maxCharge;
    }

    public long getChargeRate() {
        return this.chargeRate;
    }

    public long getDischargeRate() {
        return this.dischargeRate;
    }
}
