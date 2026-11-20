package com.rivalrebels.common.entity;

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

public class EntityFlameBallLowTemp extends EntityFlameBall {
    public EntityFlameBallLowTemp(World world)
    {
        super(world);
    }

    public EntityFlameBallLowTemp(World world, double x, double y, double z)
    {
        super(world, x, y, z);
    }

    public EntityFlameBallLowTemp(World world, EntityPlayer player, float speed)
    {
        super(world, player, speed);
    }

    public EntityFlameBallLowTemp(World world, double x, double y, double z, double motionX, double motionY, double motionZ)
    {
        super(world, x, y, z, motionX, motionY, motionZ);
    }

    public EntityFlameBallLowTemp(World world, double x, double y, double z, double motionX, double motionY, double motionZ, double distance, double spread)
    {
        super(world);
        setPosition(x + motionX * distance + rand.nextGaussian() * spread,
                y + motionY * distance + rand.nextGaussian() * spread,
                z + motionZ * distance + rand.nextGaussian() * spread);
        this.motionX = motionX;
        this.motionY = motionY;
        this.motionZ = motionZ;
    }

    @Override
    public void onUpdate()
    {
        lastTickPosX = posX;
        lastTickPosY = posY;
        lastTickPosZ = posZ;
        super.onUpdate();

        if (!hasLeftOwner && owner != null) {
            if (!getEntityBoundingBox().intersects(owner.getEntityBoundingBox())) {
                hasLeftOwner = true;
            }
        }

        ticksExisted++;
        if (ticksExisted % 3 == 0) sequence++;
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
                        double var13 = start.distanceTo(entity_intercept.hitVec);

                        if (var13 < closest_hit_distance || closest_hit_distance == 0.0D)
                        {
                            hitEntity = current_entity;
                            closest_hit_distance = var13;
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
            if (mop.entityHit != null && (hasLeftOwner || mop.entityHit != owner))
            {
                mop.entityHit.setFire(3);
                mop.entityHit.attackEntityFrom(RivalRebelsDamageSource.cooked, 12);
                if (mop.entityHit != null && mop.entityHit instanceof EntityPlayer)
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
        }

        posX += motionX;
        posY += motionY;
        posZ += motionZ;

        rotation += motionr;
        motionr *= 1.06f;

        if (isInWater()) setDead();
        float airFriction = 0.96F;
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
                        if (id == Blocks.AIR || id == Blocks.SNOW || id == Blocks.ICE || id == Blocks.LEAVES || id == Blocks.LEAVES2) world.setBlockState(new BlockPos((int) posX + x, (int) posY + y, (int) posZ + z), Blocks.FIRE.getDefaultState());
                    }
                }
            }
        }
    }
}
