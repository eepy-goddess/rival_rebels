package com.rivalrebels.common.entity;

import com.rivalrebels.common.init.RRBlocks;
import com.rivalrebels.common.init.RivalRebelsDamageSource;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.List;

public class EntityFlameBallMediumTemp extends EntityFlameBall {
    public EntityFlameBallMediumTemp(World world)
    {
        super(world);
    }

    public EntityFlameBallMediumTemp(World world, double x, double y, double z)
    {
        super(world, x, y, z);
    }

    public EntityFlameBallMediumTemp(World world, EntityPlayer player, float speed)
    {
        super(world, player, speed);
    }

    public EntityFlameBallMediumTemp(World world, double x, double y, double z, double motionX, double motionY, double motionZ)
    {
        super(world, x, y, z, motionX, motionY, motionZ);
    }

    @Override
    public void onUpdate()
    {
        lastTickPosX = posX;
        lastTickPosY = posY;
        lastTickPosZ = posZ;
        super.onUpdate();
        ticksExisted++;
        if (ticksExisted > 5) sequence++;
        if (sequence > 15/* > RivalRebels.flamethrowerDecay */) setDead();

        Vec3d start = new Vec3d(posX, posY, posZ);
        Vec3d end = new Vec3d(posX + motionX, posY + motionY, posZ + motionZ);
        RayTraceResult mop = world.rayTraceBlocks(start, end);
        start = new Vec3d(posX, posY, posZ);
        end = new Vec3d(posX + motionX, posY + motionY, posZ + motionZ);

        if (mop != null) end = new Vec3d(mop.hitVec.x, mop.hitVec.y, mop.hitVec.z);

        Entity hitEntity = null;
        List<Entity> nearby_entities = world.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().contract(motionX, motionY, motionZ).expand(1.0D, 1.0D, 1.0D));
        double closest_hit_distance = 0.0D;
        Iterator<Entity> entity_iterator = nearby_entities.iterator();

        if (!world.isRemote)
        {
            while (entity_iterator.hasNext())
            {
                Entity current_entity = (Entity) entity_iterator.next();

                if (current_entity.canBeCollidedWith())
                {
                    float collision_border_size = 0.3F;
                    AxisAlignedBB expanded_bounding_box = current_entity.getEntityBoundingBox().expand(collision_border_size, collision_border_size, collision_border_size);
                    RayTraceResult entity_intercept = expanded_bounding_box.calculateIntercept(start, end);

                    if (entity_intercept != null)
                    {
                        double hit_distance = start.distanceTo(entity_intercept.hitVec);

                        if (hit_distance < closest_hit_distance || closest_hit_distance == 0.0D)
                        {
                            hitEntity = current_entity;
                            closest_hit_distance = hit_distance;
                        }
                    }
                }
            }
        }

        if (hitEntity != null)
        {
            mop = new RayTraceResult(hitEntity);
        }

        if (mop != null && ticksExisted >= 5)
        {
            fire();
            setDead();
            if (mop.entityHit != null)
            {
                mop.entityHit.setFire(3);
                mop.entityHit.attackEntityFrom(RivalRebelsDamageSource.cooked, 12);
                if (mop.entityHit instanceof EntityPlayer)
                {
                    EntityPlayer player = (EntityPlayer) mop.entityHit;
                    ItemStack[] armorSlots = player.inventory.armorInventory.toArray(new ItemStack[0]);
                    int i = world.rand.nextInt(4);
                    if (armorSlots[i] != null && !world.isRemote)
                    {
                        armorSlots[i].damageItem(8, player);
                    }
                }
            }
            else if (mop.typeOfHit == RayTraceResult.Type.ENTITY)
            {
                //world.spawnEntityInWorld(new EntityLightningLink(worldObj, this, this.getDistanceToEntity(mop.entityHit)));
                if (mop.entityHit instanceof EntityPlayer)
                {
                    EntityPlayer entityPlayerHit = (EntityPlayer) mop.entityHit;
                    ItemStack[] armorSlots = entityPlayerHit.inventory.armorInventory.toArray(new ItemStack[0]);
                    int i = world.rand.nextInt(4);
                    if (armorSlots[i] != null)
                    {
                        armorSlots[i].damageItem(44, entityPlayerHit);
                    }
                    entityPlayerHit.attackEntityFrom(RivalRebelsDamageSource.cooked, 250 / ((int) mop.entityHit.getDistance(this) + 1));
                }
                /*else if (mop.entityHit instanceof EntityB2Spirit)
                {
                    mop.entityHit.attackEntityFrom(RivalRebelsDamageSource.cooked, 250 / ((int) mop.entityHit.getDistanceToEntity(this) + 1));
                }*/
                else if (mop.entityHit.canBeAttackedWithItem())
                {
                    mop.entityHit.attackEntityFrom(RivalRebelsDamageSource.cooked, 250 / ((int) mop.entityHit.getDistance(this) + 1));
                }
            }
        }

        posX += motionX;
        posY += motionY;
        posZ += motionZ;

        rotation += motionr;
        motionr *= 1.06f;

        if (isInWater()) setDead();
        float airFriction = 0.97F;
        float gravity = 0.01F;
        motionX *= airFriction;
        motionY *= airFriction;
        motionZ *= airFriction;
        motionY -= gravity;
        setPosition(posX, posY, posZ);
    }

    private void fire()
    {
        if (!world.isRemote)
        {
            for (int x = -1; x < 2; x++)
            {
                for (int y = -1; y < 2; y++)
                {
                    for (int z = -1; z < 2; z++)
                    {
                        Block id = world.getBlockState(new BlockPos((int) posX + x, (int) posY + y, (int) posZ + z)).getBlock();
                        if (id == Blocks.AIR || id == Blocks.SNOW || id == Blocks.ICE) world.setBlockState(new BlockPos((int) posX + x, (int) posY + y, (int) posZ + z), Blocks.FIRE.getDefaultState());
                        else if (id == Blocks.LEAVES || id == Blocks.LEAVES2) world.setBlockState(new BlockPos((int) posX + x, (int) posY + y, (int) posZ + z), Blocks.FIRE.getDefaultState());
                        else if (id == Blocks.GRASS && world.rand.nextInt(5) == 0) world.setBlockState(new BlockPos((int) posX + x, (int) posY + y, (int) posZ + z), Blocks.DIRT.getDefaultState());
                        else if (id == RRBlocks.flare) RRBlocks.flare.onBlockDestroyedByPlayer(world, new BlockPos((int) posX + x, (int) posY + y, (int) posZ + z), RRBlocks.flare.getDefaultState());
                    }
                }
            }
        }
    }
}
