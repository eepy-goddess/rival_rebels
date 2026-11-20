package com.rivalrebels.common.entity;

import java.util.List;

import com.rivalrebels.RivalRebels;
import com.rivalrebels.common.core.BlackList;
import com.rivalrebels.common.init.RRBlocks;
import com.rivalrebels.common.init.RivalRebelsDamageSource;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityElectricityRaytrace extends Entity {

    public Entity shootingEntity;
    private int ticksInAir = 0;
    private float range = 0.0F;
    private float c;

	@Override
	protected void entityInit()
	{
	}

    public EntityElectricityRaytrace(World world) {
        super(world);
        this.setSize(0.5F, 0.5F);
    }

    public EntityElectricityRaytrace(World world, double x, double y, double z, double initialMotionX, double initialMotionY, double initialMotionZ) {
        super(world);
        this.setSize(0.5F, 0.5F);
        this.setPosition(x, y, z);
        this.setAnglesMotion(initialMotionX, initialMotionY, initialMotionZ);
        this.c = 1.0F;
        this.range = MathHelper.sqrt(initialMotionX * initialMotionX + initialMotionY * initialMotionY + initialMotionZ * initialMotionZ);
    }


    public void setAnglesMotion(double mx, double my, double mz) {
        this.motionX = mx;
        this.motionY = my;
        this.motionZ = mz;
        this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(mx, mz) * 180.0 / Math.PI);
        this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(my, MathHelper.sqrt(mx * mx + mz * mz)) * 180.0 / Math.PI);
    }


    public EntityElectricityRaytrace(World world, EntityLivingBase player, float distance, float randomness, float chance, boolean shift) {
        super(world);
        this.c = chance;
        this.range = distance;
        this.shootingEntity = player;
        this.setSize(0.5F, 0.5F);
        this.setLocationAndAngles(player.posX, player.posY + player.getEyeHeight(), player.posZ, player.rotationYaw, player.rotationPitch);
        this.setPosition(this.posX, this.posY, this.posZ);
        this.motionX = -MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI)
            * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI);
        this.motionZ = MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI)
            * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI);
        this.motionY = -MathHelper.sin(this.rotationPitch / 180.0F * (float) Math.PI);
        if (shift) {
            this.posX = this.posX - MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F;
            this.posZ = this.posZ - MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F;
            this.posX = this.posX + this.motionX;
            this.posY = this.posY + this.motionY;
            this.posZ = this.posZ + this.motionZ;
        } else {
            this.posY -= 0.05F;
            this.posX = this.posX - MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * 0.3F;
            this.posZ = this.posZ - MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * 0.3F;
        }

        this.setArrowHeading(this.motionX, this.motionY, this.motionZ, this.range, randomness);
    }

    public void setArrowHeading(double motionX, double motionY, double motionZ, float speed, float inaccuracy) {
        float motionMagnitude = MathHelper.sqrt(motionX * motionX + motionY * motionY + motionZ * motionZ);
        motionX /= motionMagnitude;
        motionY /= motionMagnitude;
        motionZ /= motionMagnitude;
        motionX += this.world.rand.nextGaussian() * inaccuracy;
        motionY += this.world.rand.nextGaussian() * inaccuracy;
        motionZ += this.world.rand.nextGaussian() * inaccuracy;
        motionX *= speed;
        motionY *= speed;
        motionZ *= speed;
        this.motionX = motionX;
        this.motionY = motionY;
        this.motionZ = motionZ;
        float horizontalMotionMagnitude = MathHelper.sqrt(motionX * motionX + motionZ * motionZ);
        this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(motionX, motionZ) * 180.0 / Math.PI);
        this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(motionY, horizontalMotionMagnitude) * 180.0 / Math.PI);
    }

    public void onUpdate() {
        super.onUpdate();
        Vec3d rayStart = new Vec3d(this.posX, this.posY, this.posZ);
        Vec3d rayEnd = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        RayTraceResult MOP = this.world.rayTraceBlocks(rayStart, rayEnd, false, true, false);
        rayStart = new Vec3d(this.posX, this.posY, this.posZ);
        if (MOP != null) {
            rayEnd = new Vec3d(MOP.hitVec.x, MOP.hitVec.y, MOP.hitVec.z);
        } else {
            rayEnd = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        }

        List<Entity> list = this.world
            .getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(this.motionX, this.motionY, this.motionZ).grow(1.0, 30.0, 1.0));
        double closestHitDistance = Double.MAX_VALUE;

        for (int entityIndex = 0; entityIndex < list.size(); entityIndex++) {
            Entity entity = (Entity)list.get(entityIndex);
            if ((entity.canBeCollidedWith() /*|| entity instanceof EntityRhodes*/) && entity != this.shootingEntity) {
                RayTraceResult mop1 = entity.getEntityBoundingBox().grow(0.5, 0.5, 0.5).calculateIntercept(rayStart, rayEnd);
                if (mop1 != null) {
                double candidateHitDistance = rayStart.squareDistanceTo(mop1.hitVec);
                if (candidateHitDistance < closestHitDistance) {
                    MOP = new RayTraceResult(entity, mop1.hitVec);
                    closestHitDistance = candidateHitDistance;
                }
                }
            }
        }

        if (MOP != null) {
            if (MOP.entityHit == null) {
                if (!this.world.isRemote) {
                this.world
                    .spawnEntity(
                        new EntityElectricityLink(
                            this.world,
                            this,
                            this.getDistance(MOP.hitVec.x, MOP.hitVec.y, MOP.hitVec.z)
                        )
                    );
                }

                int MOP_block_x = MOP.getBlockPos().getX();
                int MOP_block_y = MOP.getBlockPos().getY();
                int MOP_block_z = MOP.getBlockPos().getZ();

                Block hitBlock = this.world.getBlockState(new BlockPos(MOP_block_x, MOP_block_y, MOP_block_z)).getBlock();
                float randomValue = this.world.rand.nextFloat();
                
                if (hitBlock == RRBlocks.green_camo || hitBlock == RRBlocks.brown_camo || hitBlock == RRBlocks.grey_camo) {
                    if (randomValue * 10.0F <= this.c) {
                        if (!this.world.isRemote) {
                            this.world.setBlockState(new BlockPos(MOP_block_x, MOP_block_y, MOP_block_z), Blocks.AIR.getDefaultState());
                        }

                        for (int particleIndex = 0; particleIndex < 4; particleIndex++) {
                            this.world
                                .spawnParticle(
                                EnumParticleTypes.EXPLOSION_NORMAL,
                                MOP_block_x,
                                MOP_block_y - 1 + particleIndex * 0.5,
                                MOP_block_z,
                                (this.world.rand.nextFloat() - 0.5F) * 0.1,
                                this.world.rand.nextFloat() * 0.05,
                                (this.world.rand.nextFloat() - 0.5F) * 0.1
                                );
                        }
                    }
                } else if (hitBlock == RRBlocks.reactive) {
                if (randomValue * 15.0F <= this.c) {
                    if (!this.world.isRemote) {
                        this.world.setBlockState(new BlockPos(MOP_block_x, MOP_block_y, MOP_block_z), Blocks.AIR.getDefaultState());
                    }

                    for (int particleIndex = 0; particleIndex < 4; particleIndex++) {
                        this.world
                            .spawnParticle(
                            EnumParticleTypes.EXPLOSION_NORMAL,
                            MOP_block_x,
                            MOP_block_y - 1 + particleIndex * 0.5,
                            MOP_block_z,
                            (this.world.rand.nextFloat() - 0.5F) * 0.1,
                            this.world.rand.nextFloat() * 0.05,
                            (this.world.rand.nextFloat() - 0.5F) * 0.1
                            );
                    }
                }
            } else if (!BlackList.tesla(hitBlock) && randomValue <= this.c) {
                if (!this.world.isRemote) {
                    this.world.setBlockState(new BlockPos(MOP_block_x, MOP_block_y, MOP_block_z), Blocks.AIR.getDefaultState());
                }

                for (int particleIndex = 0; particleIndex < 4; particleIndex++) {
                    this.world
                        .spawnParticle(
                            EnumParticleTypes.EXPLOSION_NORMAL,
                            MOP_block_x,
                            MOP_block_y - 1 + particleIndex * 0.5,
                            MOP_block_z,
                            (this.world.rand.nextFloat() - 0.5F) * 0.1,
                            this.world.rand.nextFloat() * 0.05,
                            (this.world.rand.nextFloat() - 0.5F) * 0.1
                        );
                }
                }
            } else {
                if (!this.world.isRemote) {
                    this.world.spawnEntity(new EntityElectricityLink(this.world, this, this.getDistance(MOP.entityHit)));
                }

                //RivalRebelsDamageSource damage = new RivalRebelsDamageSource(RivalRebelsDamageSource.RR_damage_sources.ELECTRICITY, this, this.shootingEntity);
                DamageSource damage = RivalRebelsDamageSource.charge;

                if (MOP.entityHit instanceof EntityPlayer) {
                    EntityPlayer entityPlayerHit = (EntityPlayer)MOP.entityHit;
                    NonNullList<ItemStack> armorSlots = entityPlayerHit.inventory.armorInventory;
                    int armorSlotIndex = this.world.rand.nextInt(4);
                    if (!armorSlots.get(armorSlotIndex).isEmpty()) {
                        armorSlots.get(armorSlotIndex).damageItem(14, entityPlayerHit);
                        entityPlayerHit.attackEntityFrom(damage, 1.0F);
                    } else {
                        entityPlayerHit.attackEntityFrom(
                            damage, RivalRebels.teslaDecay / ((int)MOP.entityHit.getDistance(this) + 1) / (armorSlotIndex + 1)
                        );
                    }
                } else if (false/*MOP.entityHit instanceof EntityB2Spirit*/) {
                    MOP.entityHit
                    .attackEntityFrom(damage, RivalRebels.teslaDecay / 1.5F / ((int)MOP.entityHit.getDistance(this) + 1));
                } else {
                    MOP.entityHit.attackEntityFrom(damage, RivalRebels.teslaDecay / ((int)MOP.entityHit.getDistance(this) + 1));
                }
            }
        } else if (!this.world.isRemote) {
            this.world.spawnEntity(new EntityElectricityLink(this.world, this, this.range));
        }

        this.setDead();
    }

    // I guess do not save Tesla electricity state if world closes mid use
    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
    }
}
