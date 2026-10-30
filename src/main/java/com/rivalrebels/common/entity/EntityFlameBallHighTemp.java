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

public class EntityFlameBallHighTemp extends EntityFlameBall {
    public boolean	gonnadie;

    public EntityFlameBallHighTemp(World world)
    {
        super(world);
    }

    public EntityFlameBallHighTemp(World world, double x, double y, double z)
    {
        super(world, x, y, z);
    }

    public EntityFlameBallHighTemp(World world, EntityPlayer player, float speed)
    {
        super(world, player, speed);
    }

    public EntityFlameBallHighTemp(World world, double x, double y, double z, double motionX, double motionY, double motionZ)
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
        sequence++;
        if (sequence > 15/* > RivalRebels.flamethrowerDecay */) setDead();
        if (ticksExisted > 5 && Math.random() > 0.5) gonnadie = true;

        if (gonnadie && sequence < 15) sequence++;

        Vec3d start = new Vec3d(posX, posY, posZ);
        Vec3d end = new Vec3d(posX + motionX, posY + motionY, posZ + motionZ);
        RayTraceResult mop = world.rayTraceBlocks(start, end);
        start = new Vec3d(posX, posY, posZ);
        end = new Vec3d(posX + motionX, posY + motionY, posZ + motionZ);

        if (mop != null) end = new Vec3d(mop.hitVec.x, mop.hitVec.y, mop.hitVec.z);

        Entity e = null;
        List<Entity> var5 = world.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().contract(motionX, motionY, motionZ).expand(1.0D, 1.0D, 1.0D));
        double var6 = 0.0D;
        Iterator<Entity> var8 = var5.iterator();

        if (!world.isRemote)
        {
            while (var8.hasNext())
            {
                Entity var9 = (Entity) var8.next();

                if (var9.canBeCollidedWith())
                {
                    float var10 = 0.3F;
                    AxisAlignedBB var11 = var9.getEntityBoundingBox().expand(var10, var10, var10);
                    RayTraceResult var12 = var11.calculateIntercept(start, end);

                    if (var12 != null)
                    {
                        double var13 = start.distanceTo(var12.hitVec);

                        if (var13 < var6 || var6 == 0.0D)
                        {
                            e = var9;
                            var6 = var13;
                        }
                    }
                }
            }
        }

        if (e != null)
        {
            mop = new RayTraceResult(e);
        }

        if (mop != null && ticksExisted >= 6)
        {
            posX -= 0.5f;
            posY -= 0.5f;
            posZ -= 0.5f;
            fire();
            posX++;
            fire();
            posX--;
            posX--;
            fire();
            posX++;
            posY++;
            fire();
            posY--;
            posY--;
            fire();
            posY++;
            posZ++;
            fire();
            posZ--;
            posZ--;
            fire();
            posZ++;
            posX += 0.5f;
            posY += 0.5f;
            posZ += 0.5f;
            setDead();
            if (mop.entityHit != null)
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
        float airFriction = 0.9F;
        if (gonnadie)
        {
            motionY += 0.05;
            airFriction = 0.7f;
        }
        motionX *= airFriction;
        motionY *= airFriction;
        motionZ *= airFriction;
        setPosition(posX, posY, posZ);
    }

    private void fire() {
        if (!world.isRemote)
        {
            Block id = world.getBlockState(new BlockPos((int) posX, (int) posY, (int) posZ)).getBlock();
            if (id == Blocks.AIR || id == Blocks.SNOW || id == Blocks.ICE) world.setBlockState(new BlockPos((int) posX, (int) posY, (int) posZ), Blocks.FIRE.getDefaultState());
            else if (id == Blocks.SAND && world.rand.nextInt(60) == 0) world.setBlockState(new BlockPos((int) posX, (int) posY, (int) posZ), Blocks.GLASS.getDefaultState());
            else if (id == Blocks.GLASS && world.rand.nextInt(120) == 0) world.setBlockState(new BlockPos((int) posX, (int) posY, (int) posZ), Blocks.OBSIDIAN.getDefaultState());
            else if (id == Blocks.OBSIDIAN && world.rand.nextInt(90) == 0) world.setBlockState(new BlockPos((int) posX, (int) posY, (int) posZ), Blocks.LAVA.getDefaultState());
            else if ((id == Blocks.STONE || id == Blocks.COBBLESTONE) && world.rand.nextInt(30) == 0) world.setBlockState(new BlockPos((int) posX, (int) posY, (int) posZ), Blocks.LAVA.getDefaultState());
            else if (id == Blocks.IRON_ORE && world.rand.nextInt(30) == 0) world.setBlockState(new BlockPos((int) posX, (int) posY, (int) posZ), Blocks.LAVA.getDefaultState());
            else if (id == Blocks.COAL_ORE && world.rand.nextInt(30) == 0) world.setBlockState(new BlockPos((int) posX, (int) posY, (int) posZ), Blocks.LAVA.getDefaultState());
            else if (id == Blocks.GOLD_ORE && world.rand.nextInt(30) == 0) world.setBlockState(new BlockPos((int) posX, (int) posY, (int) posZ), Blocks.LAVA.getDefaultState());
            else if (id == Blocks.LAPIS_ORE && world.rand.nextInt(30) == 0) world.setBlockState(new BlockPos((int) posX, (int) posY, (int) posZ), Blocks.LAVA.getDefaultState());
            else if (id == Blocks.GRAVEL && world.rand.nextInt(30) == 0) world.setBlockState(new BlockPos((int) posX, (int) posY, (int) posZ), Blocks.LAVA.getDefaultState());
            else if (id == Blocks.SANDSTONE && world.rand.nextInt(30) == 0) world.setBlockState(new BlockPos((int) posX, (int) posY, (int) posZ), Blocks.LAVA.getDefaultState());
            else if (id == Blocks.IRON_BLOCK && world.rand.nextInt(50) == 0) world.setBlockState(new BlockPos((int) posX, (int) posY, (int) posZ), Blocks.LAVA.getDefaultState());
            else if (id == Blocks.BEDROCK && world.rand.nextInt(500) == 0) world.setBlockState(new BlockPos((int) posX, (int) posY, (int) posZ), Blocks.OBSIDIAN.getDefaultState());
            else if (id == Blocks.LAVA) world.setBlockToAir(new BlockPos((int) posX, (int) posY, (int) posZ));
            else if (id == Blocks.LEAVES || id == Blocks.LEAVES2) world.setBlockState(new BlockPos((int) posX, (int) posY, (int) posZ), Blocks.FIRE.getDefaultState());
            else if (id == Blocks.GRASS) world.setBlockState(new BlockPos((int) posX, (int) posY, (int) posZ), Blocks.DIRT.getDefaultState());
            else if (id == Blocks.DIRT) world.setBlockState(new BlockPos((int) posX, (int) posY, (int) posZ), Blocks.FIRE.getDefaultState());
            else if (id == RRBlocks.flare) RRBlocks.flare.onBlockDestroyedByPlayer(world, new BlockPos((int) posX, (int) posY, (int) posZ), RRBlocks.flare.getDefaultState());
            //else if (id == RivalRebels.timedbomb) RivalRebels.timedbomb.onBlockDestroyedByPlayer(worldObj, (int) posX, (int) posY, (int) posZ, 0);
            else if (id == RRBlocks.plastic_explosives) RRBlocks.plastic_explosives.onBlockDestroyedByPlayer(world, new BlockPos((int) posX, (int) posY, (int) posZ), RRBlocks.plastic_explosives.getDefaultState());
            else if (id == RRBlocks.mine) RRBlocks.plastic_explosives.onBlockDestroyedByPlayer(world, new BlockPos((int) posX, (int) posY, (int) posZ), RRBlocks.plastic_explosives.getDefaultState());
            else if (id == Blocks.TNT)
            {
                world.setBlockToAir(new BlockPos((int) posX, (int) posY, (int) posZ));
                world.createExplosion(null, posX, posY, posZ, 4, false);
            }
            /*else if (id == RivalRebels.conduit) worldObj.setBlock((int) posX, (int) posY, (int) posZ, Blocks.fire);
            else if (id == RivalRebels.reactive && worldObj.rand.nextInt(20) == 0) worldObj.setBlock((int) posX, (int) posY, (int) posZ, Blocks.lava);
            else if ((id == RivalRebels.camo1 || id == RivalRebels.camo2 || id == RivalRebels.camo3) && worldObj.rand.nextInt(40) == 0) worldObj.setBlock((int) posX, (int) posY, (int) posZ, RivalRebels.steel);
            else if (id == RivalRebels.steel && worldObj.rand.nextInt(40) == 0) worldObj.setBlock((int) posX, (int) posY, (int) posZ, Blocks.fire);*/
        }
    }
}
