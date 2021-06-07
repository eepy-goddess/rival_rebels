package com.rivalrebels.common.entity;

import com.rivalrebels.RivalRebels;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class EntityPlasmoid extends Entity {
    private EntityPlayer thrower;
    public int				rotation	= 45;
    public int				size		= 3;
    public float			slide		= 0;
    boolean gravity = false;

    public EntityPlasmoid(World par1World)
    {
        super(par1World);
        setSize(0.5F, 0.5F);
        size = 3;
    }

    public EntityPlasmoid(World par1World, double par2, double par4, double par6)
    {
        super(par1World);
        setSize(0.5F, 0.5F);
        setPosition(par2, par4, par6);
        setRenderYawOffset(0.0F);
        size = 3;
    }

    public EntityPlasmoid(World par1World, EntityPlayer par2EntityLiving, EntityLiving par3EntityLiving, float par4, float par5)
    {
        super(par1World);
        thrower = par2EntityLiving;

        posY = par2EntityLiving.posY + par2EntityLiving.getEyeHeight() - 0.10000000149011612D;
        double var6 = par3EntityLiving.posX - par2EntityLiving.posX;
        double var8 = par3EntityLiving.posY + par3EntityLiving.getEyeHeight() - 0.699999988079071D - posY;
        double var10 = par3EntityLiving.posZ - par2EntityLiving.posZ;
        double var12 = MathHelper.sqrt(var6 * var6 + var10 * var10);

        if (var12 >= 1.0E-7D)
        {
            float var14 = (float) (Math.atan2(var10, var6) * 180.0D / Math.PI) - 90.0F;
            float var15 = (float) (-(Math.atan2(var8, var12) * 180.0D / Math.PI));
            double var16 = var6 / var12;
            double var18 = var10 / var12;
            setLocationAndAngles(par2EntityLiving.posX + var16, posY, par2EntityLiving.posZ + var18, var14, var15);
            setRenderYawOffset(0.0F);
            float var20 = (float) var12 * 0.2F;
            setAccurateHeading(var6, var8 + var20, var10, par4, par5);
        }
        size = 3;
    }

    public EntityPlasmoid(World par1World, EntityPlayer par2EntityLiving, float par3, boolean drop)
    {
        super(par1World);
        par3 *= (gravity ? 3 : 1);
        gravity = drop;
        thrower = par2EntityLiving;
        setSize(0.5F, 0.5F);
        setLocationAndAngles(par2EntityLiving.posX, par2EntityLiving.posY + par2EntityLiving.getEyeHeight(), par2EntityLiving.posZ, par2EntityLiving.rotationYaw, par2EntityLiving.rotationPitch);
        posX -= (MathHelper.cos(rotationYaw / 180.0F * (float) Math.PI) * 0.16F);
        posY -= 0.0D;
        posZ -= (MathHelper.sin(rotationYaw / 180.0F * (float) Math.PI) * 0.16F);
        setPosition(posX, posY, posZ);
        setRenderYawOffset(0.0F);
        motionX = (-MathHelper.sin(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI));
        motionZ = (MathHelper.cos(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI));
        motionY = (-MathHelper.sin(rotationPitch / 180.0F * (float) Math.PI));
        setAccurateHeading(motionX, motionY, motionZ, par3 * 1.5F, 1.0F);
    }

    public EntityPlasmoid(World world, double px, double py, double pz, double x, double y, double z, float d)
    {
        super(world);
        setSize(0.5F, 0.5F);
        double f = d/MathHelper.sqrt(x*x+y*y+z*z);
        setPosition(px+x*f, py+y*f, pz+z*f);
        setRenderYawOffset(0.0F);
        size = 3;
        motionX = x;
        motionY = y;
        motionZ = z;
        float var10 = MathHelper.sqrt(x * x + z * z);
        prevRotationYaw = rotationYaw = (float) (Math.atan2(x, z) * 180.0D / Math.PI);
        prevRotationPitch = rotationPitch = (float) (Math.atan2(y, var10) * 180.0D / Math.PI);
    }

    public void setAccurateHeading(double par1, double par3, double par5, float par7, float par8)
    {
        float var9 = MathHelper.sqrt(par1 * par1 + par3 * par3 + par5 * par5);
        par1 /= var9;
        par3 /= var9;
        par5 /= var9;
        par1 *= par7;
        par3 *= par7;
        par5 *= par7;
        motionX = par1;
        motionY = par3;
        motionZ = par5;
        float var10 = MathHelper.sqrt(par1 * par1 + par5 * par5);
        prevRotationYaw = rotationYaw = (float) (Math.atan2(par1, par5) * 180.0D / Math.PI);
        prevRotationPitch = rotationPitch = (float) (Math.atan2(par3, var10) * 180.0D / Math.PI);
    }

    @Override
    public int getBrightnessForRender() {
        return 1000;
    }

    @Override
    public float getBrightness() {
        return 1000F;
    }

    @Override
    public boolean isInRangeToRenderDist(double par1)
    {
        return true;
    }

    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void onUpdate()
    {
        super.onUpdate();
        if (ticksExisted == 0)
        {
            rotation = world.rand.nextInt(360);
            slide = world.rand.nextInt(21) - 10;
        }
        if (gravity)
        {
            motionY -= 0.05f;
        }
        ++ticksExisted;
        rotation += (int) slide;
        slide *= 0.9;
        if (ticksExisted >= RivalRebels.plasmoidDecay * (gravity ? 3 : 1)) explode();

        Vec3d vec31 = new Vec3d(posX, posY, posZ);
        Vec3d vec3 = new Vec3d(posX + motionX, posY + motionY, posZ + motionZ);
        RayTraceResult mop = world.rayTraceBlocks(vec31, vec3, false, true, false);
        vec31 = new Vec3d(posX, posY, posZ);
        if (mop != null) vec3 = new Vec3d(mop.hitVec.x, mop.hitVec.y, mop.hitVec.z);
        else vec3 = new Vec3d(posX + motionX, posY + motionY, posZ + motionZ);

        List list = world.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().contract(motionX, motionY, motionZ).expand(1.0D, 1.0D, 1.0D));
        double d0 = Double.MAX_VALUE;
        for (int i = 0; i < list.size(); ++i)
        {
            Entity entity = (Entity) list.get(i);
            if (entity.canBeCollidedWith() && (entity != thrower || ticksExisted >= 5))
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
        if (mop != null) explode();

        posX += motionX;
        posY += motionY;
        posZ += motionZ;
        float var16 = MathHelper.sqrt(motionX * motionX + motionZ * motionZ);
        rotationYaw = (float) (Math.atan2(motionX, motionZ) * 180.0D / Math.PI);
        for (rotationPitch = (float) (Math.atan2(motionY, var16) * 180.0D / Math.PI); rotationPitch - prevRotationPitch < -180.0F; prevRotationPitch -= 360.0F)
            ;
        while (rotationPitch - prevRotationPitch >= 180.0F)
            prevRotationPitch += 360.0F;
        while (rotationYaw - prevRotationYaw < -180.0F)
            prevRotationYaw -= 360.0F;
        while (rotationYaw - prevRotationYaw >= 180.0F)
            prevRotationYaw += 360.0F;
        rotationPitch = prevRotationPitch + (rotationPitch - prevRotationPitch) * 0.2F;
        rotationYaw = prevRotationYaw + (rotationYaw - prevRotationYaw) * 0.2F;
        setPosition(posX, posY, posZ);
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {

    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {

    }

    protected void explode()
    {
        if (!world.isRemote) {
            setDead();
            Block block = Blocks.STONE;

            int i = -1;

            world.setBlockState(new BlockPos(posX, posY, posZ), RivalRebels.plasmaexplosion.getDefaultState());
        }
    }

    @Override
    protected void entityInit()
    {
    }
}
