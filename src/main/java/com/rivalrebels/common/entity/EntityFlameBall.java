package com.rivalrebels.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public abstract class EntityFlameBall extends Entity {
	public int sequence;
	public float rotation;
	public float motionr;

	public EntityFlameBall(World world)
	{
		super(world);
		setSize(0.5F, 0.5F);
		initializeRotation();
	}

	public EntityFlameBall(World world, double x, double y, double z)
	{
		this(world);
		setPosition(x, y, z);
	}

	public EntityFlameBall(World world, EntityPlayer player, float speed)
	{
		this(world);
		setPosition(player.posX, player.posY + player.getEyeHeight(), player.posZ);
		motionX = -MathHelper.sin(player.rotationYaw / 180.0F * (float) Math.PI)
				* MathHelper.cos(player.rotationPitch / 180.0F * (float) Math.PI);
		motionZ = MathHelper.cos(player.rotationYaw / 180.0F * (float) Math.PI)
				* MathHelper.cos(player.rotationPitch / 180.0F * (float) Math.PI);
		motionY = -MathHelper.sin(player.rotationPitch / 180.0F * (float) Math.PI);
		posX -= MathHelper.cos(player.rotationYaw / 180.0F * (float) Math.PI) * 0.2F;
		posY -= 0.13;
		posZ -= MathHelper.sin(player.rotationYaw / 180.0F * (float) Math.PI) * 0.2F;
		motionX *= speed;
		motionY *= speed;
		motionZ *= speed;
	}

	public EntityFlameBall(World world, double x, double y, double z, double motionX, double motionY, double motionZ)
	{
		this(world, x, y, z);
		this.motionX = motionX;
		this.motionY = motionY;
		this.motionZ = motionZ;
	}

	private void initializeRotation()
	{
		rotation = (float) (Math.random() * 360);
		motionr = (float) (Math.random() - 0.5f) * 5;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound)
	{
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound)
	{
	}

	@Override
	protected void entityInit()
	{
	}

	@Override
	public int getBrightnessForRender()
	{
		return 1000;
	}

	@Override
	public float getBrightness()
	{
		return 1000;
	}

	@Override
	public boolean isInRangeToRenderDist(double distance)
	{
		return true;
	}

	@Override
	public boolean canBeAttackedWithItem()
	{
		return false;
	}

	@Override
	public boolean shouldRenderInPass(int pass)
	{
		return true;
	}
}
