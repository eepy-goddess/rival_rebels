package com.rivalrebels.common.entity;

import com.rivalrebels.common.init.RivalRebelsDamageSource;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.List;

public class EntityFlameBall extends Entity {
    public int		sequence;
    public float	rotation;
    public float	motionr;

    public EntityFlameBall(World par1World)
    {
        super(par1World);
        setSize(0.5F, 0.5F);
        rotation = (float) (Math.random() * 360);
        motionr = (float) (Math.random() - 0.5f) * 5;
    }

    public EntityFlameBall(World par1World, double par2, double par4, double par6)
    {
        super(par1World);
        setSize(0.5F, 0.5F);
        setPosition(par2, par4, par6);
        rotation = (float) (Math.random() * 360);
        motionr = (float) (Math.random() - 0.5f) * 5;
    }

    public EntityFlameBall(World par1World, EntityPlayer player, float par3)
    {
        super(par1World);
        setSize(0.5F, 0.5F);
        setPosition(player.posX, player.posY + player.getEyeHeight(), player.posZ);
        motionX = (-MathHelper.sin(player.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(player.rotationPitch / 180.0F * (float) Math.PI));
        motionZ = (MathHelper.cos(player.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(player.rotationPitch / 180.0F * (float) Math.PI));
        motionY = (-MathHelper.sin(player.rotationPitch / 180.0F * (float) Math.PI));
        posX -= (MathHelper.cos(player.rotationYaw / 180.0F * (float) Math.PI) * 0.2F);
        posY -= 0.13;
        posZ -= (MathHelper.sin(player.rotationYaw / 180.0F * (float) Math.PI) * 0.2F);
        motionX *= par3;
        motionY *= par3;
        motionZ *= par3;
        rotation = (float) (Math.random() * 360);
        motionr = (float) (Math.random() - 0.5f) * 5;
        // Side side = FMLCommonHandler.instance().getEffectiveSide();
        // if (side == Side.SERVER)
        // {
        // ByteArrayOutputStream bos = new ByteArrayOutputStream(9);
        // DataOutputStream outputStream = new DataOutputStream(bos);
        // try
        // {
        // outputStream.writeInt(24);
        // outputStream.writeInt(entityId);
        // outputStream.writeByte((byte)mode);
        // }
        // catch (Exception ex)
        // {
        // ex.printStackTrace();
        // }
        // finally
        // {
        // try
        // {
        // if (outputStream != null) outputStream.close();
        // }
        // catch (IOException error)
        // {
        //
        // }
        // }
        // Packet250CustomPayload packet = new Packet250CustomPayload();
        // packet.channel = "RodolRivalRebels";
        // packet.data = bos.toByteArray();
        // packet.length = bos.size();
        // PacketDispatcher.sendPacketToAllPlayers(packet);
        // }
    }

    /*public EntityFlameBall(World par1World, TileEntityReciever ter, float f)
    {
        super(par1World);
        rotationYaw = (float) (180 - ter.yaw);
        rotationPitch = (float) (-ter.pitch);
        setSize(0.5F, 0.5F);
        setPosition(ter.xCoord + ter.xO + 0.5, ter.yCoord + 0.5, ter.zCoord + ter.zO + 0.5);
        motionX = (-MathHelper.sin(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI));
        motionZ = (MathHelper.cos(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI));
        motionY = (-MathHelper.sin(rotationPitch / 180.0F * (float) Math.PI));
        motionX *= f;
        motionY *= f;
        motionZ *= f;
        rotation = (float) (Math.random() * 360);
        motionr = (float) (Math.random() - 0.5f) * 5;
    }*/

    public EntityFlameBall(World world, double x, double y, double z, double mx, double my, double mz)
    {
        super(world);
        setSize(0.5F, 0.5F);
        setPosition(x, y, z);
        motionX = mx;
        motionY = my;
        motionZ = mz;
        rotation = (float) (Math.random() * 360);
        motionr = (float) (Math.random() - 0.5f) * 5;
    }

    public EntityFlameBall(World world, double x, double y, double z, double mx, double my, double mz, double d, double r)
    {
        super(world);
        setSize(0.5F, 0.5F);
        setPosition(x+mx*d+rand.nextGaussian()*r, y+my*d+rand.nextGaussian()*r, z+mz*d+rand.nextGaussian()*r);
        motionX = mx;
        motionY = my;
        motionZ = mz;
        rotation = (float) (Math.random() * 360);
        motionr = (float) (Math.random() - 0.5f) * 5;
    }

    @Override
    public void onUpdate()
    {
        lastTickPosX = posX;
        lastTickPosY = posY;
        lastTickPosZ = posZ;
        super.onUpdate();
        ticksExisted++;
        if (ticksExisted % 3 == 0) sequence++;
        if (sequence > 15/* > RivalRebels.flamethrowerDecay */) setDead();

        Vec3d start = new Vec3d(posX, posY, posZ);
        Vec3d end = new Vec3d(posX + motionX, posY + motionY, posZ + motionZ);
        RayTraceResult mop = world.rayTraceBlocks(start, end);
        start = new Vec3d(posX, posY, posZ);
        end = new Vec3d(posX + motionX, posY + motionY, posZ + motionZ);

        if (mop != null) end = new Vec3d(mop.hitVec.x, mop.hitVec.y, mop.hitVec.z);

        Entity e = null;
        List var5 = world.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().contract(motionX, motionY, motionZ).expand(1.0D, 1.0D, 1.0D));
        double var6 = 0.0D;
        Iterator var8 = var5.iterator();

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

        if (mop != null && ticksExisted >= 5)
        {
            fire();
            setDead();
            if (mop.entityHit != null)
            {
                mop.entityHit.setFire(3);
                mop.entityHit.attackEntityFrom(RivalRebelsDamageSource.cooked, 12);
                if (mop.entityHit != null && mop.entityHit instanceof EntityPlayer)
                {
                    EntityPlayer player = (EntityPlayer) mop.entityHit;
                    ItemStack armorSlots[] = player.inventory.armorInventory.toArray(new ItemStack[0]);
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

    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
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
    public boolean isInRangeToRenderDist(double par1)
    {
        return true;
    }

    @Override
    public boolean canBeAttackedWithItem() {
        return false;
    }

    @Override
    public boolean shouldRenderInPass(int pass)
    {
        return true;
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
