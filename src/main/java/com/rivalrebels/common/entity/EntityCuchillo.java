package com.rivalrebels.common.entity;

import com.rivalrebels.RivalRebels;
import com.rivalrebels.common.init.RRSounds;
import com.rivalrebels.common.init.RivalRebelsDamageSource;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


import java.util.List;

public class EntityCuchillo extends Entity {
    public Entity	shootingEntity;
    private boolean	inGround;
    private int		ticksInGround;

    public EntityCuchillo(World par1World)
    {
        super(par1World);
        setSize(0.5F, 0.5F);
    }

    public EntityCuchillo(World par1World, EntityPlayer player, float par3)
    {
        super(par1World);
        shootingEntity = player;
        setSize(0.5F, 0.5F);
        setLocationAndAngles(player.posX, player.posY + player.getEyeHeight(), player.posZ, player.rotationYaw, player.rotationPitch);
        posX -= (MathHelper.cos(rotationYaw / 180.0F * (float) Math.PI) * 0.16F);
        posY -= 0.1f;
        posZ -= (MathHelper.sin(rotationYaw / 180.0F * (float) Math.PI) * 0.16F);
        setPosition(posX, posY, posZ);
        setRenderYawOffset(0.0F);
        motionX = (-MathHelper.sin(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI)) * par3;
        motionZ = (MathHelper.cos(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI)) * par3;
        motionY = (-MathHelper.sin(rotationPitch / 180.0F * (float) Math.PI)) * par3;
    }
    public EntityCuchillo(World par1World, double x, double y,double z, double mx, double my, double mz)
    {
        super(par1World);
        setSize(0.5F, 0.5F);
        setPosition(x,y,z);
        setRenderYawOffset(0.0F);
        setAnglesMotion(mx, my, mz);
    }

    public void setAnglesMotion(double mx, double my, double mz)
    {
        motionX = mx;
        motionY = my;
        motionZ = mz;
        prevRotationYaw = rotationYaw = (float) (Math.atan2(mx, mz) * 180.0D / Math.PI);
        prevRotationPitch = rotationPitch = (float) (Math.atan2(my, MathHelper.sqrt(mx * mx + mz * mz)) * 180.0D / Math.PI);
    }

    @Override
    protected void entityInit()
    {
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
        ticksExisted++;
        if (!inGround)
        {
            Vec3d vec31 = new Vec3d(posX, posY, posZ);
            Vec3d vec3 = new Vec3d(posX + motionX, posY + motionY, posZ + motionZ);
            RayTraceResult mop = world.rayTraceBlocks(vec31, vec3, false, true, false);
            vec31 = new Vec3d(posX, posY, posZ);
            if (mop != null) vec3 = new Vec3d(mop.hitVec.x, mop.hitVec.y, mop.hitVec.z);
            else vec3 = new Vec3d(posX + motionX, posY + motionY, posZ + motionZ);

            List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().contract(motionX, motionY, motionZ).expand(1.0D, 1.0D, 1.0D));
            double d0 = Double.MAX_VALUE;
            for (int i = 0; i < list.size(); ++i)
            {
                Entity entity = (Entity) list.get(i);
                if (entity.canBeCollidedWith() && (ticksExisted >= 5 || entity != shootingEntity))
                {
                    RayTraceResult mop1 = entity.getEntityBoundingBox().expand(0.5f, 0.5f, 0.5f).calculateIntercept(vec31, vec3);
                    if (mop1 != null)
                    {
                        double d1 = vec31.squareDistanceTo(mop1.hitVec);
                        if (d1 < d0)
                        {
                            mop = new RayTraceResult(entity, mop1.hitVec);
                            d0 = d1;
                        }
                    }
                }
            }

            if (mop != null)
            {
                if (!world.isRemote)
                {
                    if (mop.entityHit != null)
                    {
                        mop.entityHit.attackEntityFrom(RivalRebelsDamageSource.cuchillo, 7);
                        setDead();
                    }
                    else
                    {
                        Block block = world.getBlockState(new BlockPos(mop.hitVec.x, mop.hitVec.y, mop.hitVec.z)).getBlock();
                        Material hit = block.getDefaultState().getMaterial();
                        if (block == Blocks.GLASS || block == Blocks.GLASS_PANE || block == Blocks.STAINED_GLASS || block == Blocks.STAINED_GLASS_PANE)
                        {
                            world.setBlockToAir(new BlockPos(mop.hitVec.x, mop.hitVec.y, mop.hitVec.z));
                            world.playSound(null, posX, posY, posZ, RRSounds.glass_break, SoundCategory.AMBIENT,5F, 0.3F);
                            return;
                        }
                        else if (hit == Material.ROCK)
                        {
                            world.spawnEntity(new EntityItem(world, posX, posY, posZ, new ItemStack(RivalRebels.knife, 1)));
                            setDead();
                        }
                        else
                        {
                            inGround = true;
                        }
                    }
                }
            }
            else
            {
                posX += motionX;
                posY += motionY;
                posZ += motionZ;
                rotationYaw = (float) (Math.atan2(motionX, motionZ) * 180.0D / Math.PI);
                while (rotationYaw - prevRotationYaw < -180.0F)
                    prevRotationYaw -= 360.0F;
                while (rotationYaw - prevRotationYaw >= 180.0F)
                    prevRotationYaw += 360.0F;
                rotationPitch -= 30;
                rotationYaw = prevRotationYaw + (rotationYaw - prevRotationYaw) * 0.2F;

                float friction = 0.98f;

                if (isInWater())
                {
                    for (int var26 = 0; var26 < 4; ++var26)
                        world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, posX - motionX * 0.25F, posY - motionY * 0.25F, posZ - motionZ * 0.25F, motionX, motionY, motionZ);
                    friction = 0.8F;
                }
                motionX *= friction;
                motionY *= friction;
                motionZ *= friction;
                motionY -= 0.026F;
                setPosition(posX, posY, posZ);
            }
        }
        else
        {
            motionX *= 0.2f;
            motionY *= 0.2f;
            motionZ *= 0.2f;
            ticksInGround++;
            if (ticksInGround == 60)
            {
                world.spawnEntity(new EntityItem(world, posX, posY, posZ, new ItemStack(RivalRebels.knife, 1)));
                setDead();
            }
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer par1EntityPlayer)
    {
        if (!world.isRemote && inGround)
        {
            if (!par1EntityPlayer.capabilities.isCreativeMode) par1EntityPlayer.inventory.addItemStackToInventory(new ItemStack(RivalRebels.knife, 1));
            world.playSound(null, posX, posY, posZ, SoundEvents.BLOCK_LAVA_POP, SoundCategory.AMBIENT, 0.2F, ((rand.nextFloat() - rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
            setDead();
        }
    }

    @Override
    protected boolean canTriggerWalking() {
        return false;
    }

    @Override
    public boolean isInRangeToRenderDist(double par1)
    {
        return true;
    }
}
