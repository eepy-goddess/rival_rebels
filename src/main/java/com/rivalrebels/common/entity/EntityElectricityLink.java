package com.rivalrebels.common.entity;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityElectricityLink extends Entity {
    public Random rand = new Random();
    public Long randLong = this.rand.nextLong();

    public EntityElectricityLink(World par1World) {
        super(par1World);
        this.ignoreFrustumCheck = true;
        this.setSize(0.5F, 0.5F);
        this.ticksExisted = 0;
    }

    public EntityElectricityLink(World par1World, Entity player, double distance) {
        super(par1World);
        this.ignoreFrustumCheck = true;
        this.motionX = distance / 100.0;
        this.setLocationAndAngles(player.posX, player.posY, player.posZ, player.rotationYaw, player.rotationPitch);
        this.posX = this.posX - MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F;
        this.posY -= 0.12;
        this.posZ = this.posZ - MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F;
        this.setPosition(this.posX, this.posY, this.posZ);
        this.setSize(0.5F, 0.5F);
        this.ticksExisted = 0;
    }

    public EntityElectricityLink(World par1World, double x, double y, double z, float yaw, float pitch, double distance) {
        super(par1World);
        this.ignoreFrustumCheck = true;
        this.setLocationAndAngles(x, y, z, yaw, pitch);
        this.motionX = distance / 100.0;
        this.setPosition(this.posX, this.posY, this.posZ);
        this.setSize(0.5F, 0.5F);
        this.ticksExisted = 0;
    }

    public boolean isInRangeToRenderDist(double par1) {
        return true;
    }

    public int getBrightnessForRender(float par1) {
        return 1000;
    }

    public float getBrightness(float par1) {
        return 1000.0F;
    }

    @Override
    protected void entityInit() {
    }

    public void onUpdate() {
        super.onUpdate();
        if (this.ticksExisted > 1) {
            this.setDead();
        }

        this.ticksExisted++;
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound var1) {
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound var1) {
    }
}
