package com.rivalrebels.common.explosion;

import com.rivalrebels.RivalRebels;
import com.rivalrebels.common.entity.EntityDebris;
import com.rivalrebels.common.init.RRSounds;
import com.rivalrebels.common.init.RivalRebelsDamageSource;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class RivalRebelsExplosion {
    public RivalRebelsExplosion(World world, double x, double y, double z, int strength, boolean fire, boolean crater, DamageSource dmgsrc)
    {
        world.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, x, y, z, 0, 0, 0);
        if (!world.isRemote)
        {
            if (fire)
            {
                fireSpread(world, x, y, z, strength);
            }
            else
            {
                createHole(world, x, y, z, strength * strength, crater, 4);
            }
            pushAndHurtEntities(world, x, y, z, strength, dmgsrc);
        }
    }

    private void fireSpread(World world, double x, double y, double z, int radius)
    {
        Random rand = new Random();
        int halfradius = radius / 2;
        int tworadius = radius * 2;
        for (int X = -tworadius; X <= tworadius; X++)
        {
            for (int Y = -tworadius; Y <= tworadius; Y++)
            {
                for (int Z = -tworadius; Z <= tworadius; Z++)
                {
                    int xx = (int) x + X;
                    int yy = (int) y + Y;
                    int zz = (int) z + Z;
                    Block block = world.getBlockState(new BlockPos(xx, yy, zz)).getBlock();
                    if (block == Blocks.AIR)
                    {
                        int dist = (int) Math.sqrt(X * X + Y * Y + Z * Z);
                        if (dist < radius)
                        {
                            int varrand = 1 + dist - halfradius;
                            if (dist < halfradius)
                            {
                                world.setBlockState(new BlockPos(xx, yy, zz), Blocks.FIRE.getDefaultState());
                            }
                            else if (varrand > 0)
                            {
                                if (rand.nextInt(varrand) == 0 || rand.nextInt(varrand / 2 + 1) == 0)
                                {
                                    world.setBlockState(new BlockPos(xx, yy, zz), Blocks.FIRE.getDefaultState());
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void createHole(World world, double x, double y, double z, int radius, boolean crater, int delete)
    {
        Random rand = new Random();
        int halfradius = radius >> 2;
        int tworadius = radius << 2;
        for (int X = -tworadius; X <= tworadius; X++)
        {
            int xx = (int) x + X;
            for (int Y = -tworadius; Y <= tworadius; Y++)
            {
                int yy = (int) y + Y;
                for (int Z = -tworadius; Z <= tworadius; Z++)
                {
                    int zz = (int) z + Z;
                    Block block = world.getBlockState(new BlockPos(xx, yy, zz)).getBlock();
                    if (block != null && block != Blocks.AIR && block != Blocks.BEDROCK)
                    {
                        int dist = X * X + Y * Y + Z * Z;
                        if (dist <= delete && block == RivalRebels.green_camo && block == RivalRebels.brown_camo && block == RivalRebels.grey_camo)
                        {
                            world.setBlockToAir(new BlockPos(xx, yy, zz));
                        }
                        if (dist < radius)
                        {
                            int varrand = 1 + dist - halfradius;
                            if (dist < halfradius)
                            {
                                breakBlock(world, xx, yy, zz, radius, x, y, z);
                            }
                            else if (varrand > 0)
                            {
                                if ((rand.nextInt(varrand) == 0 || rand.nextInt(varrand / 2 + 1) == 0))
                                {
                                    breakBlock(world, xx, yy, zz, radius, x, y, z);
                                }
                            }
                        }
                        else if (dist < tworadius)
                        {
                            if ((Y >= 2 || (dist < radius * 1.5 && Y >= 1)) && crater)
                            {
                                breakBlock(world, xx, yy, zz, radius, x, y, z);
                            }
                        }
                    }
                }
            }
        }
    }

    private void breakBlock(World world, int xx, int yy, int zz, int strength, double x, double y, double z)
    {
        Block block = world.getBlockState(new BlockPos(xx, yy, zz)).getBlock();
        EntityDebris e = new EntityDebris(world, xx, yy, zz);
        double xmo = x - xx;
        double ymo = y - yy;
        double zmo = z - zz;
        e.addVelocity(xmo * 0.2f, ymo * 0.2f, zmo * 0.2f);
        world.spawnEntity(e);
        if (block == RivalRebels.plastic_explosives)
        {
            world.setBlockToAir(new BlockPos(xx, yy, zz));
            world.playSound(null, xx, yy, zz, RRSounds.generic_explosion, SoundCategory.AMBIENT, 0.5f, 0.3f);
            new RivalRebelsExplosion(world, x + 0.5f, y + 0.5f, z + 0.5f, 5, false, false, RivalRebelsDamageSource.charge);
            return;
        }
        if (/*block == RivalRebels.toxicgas ||*/ block == Blocks.CHEST || block == Blocks.VINE || block == Blocks.TALLGRASS /*|| block == RivalRebels.flare || block == RivalRebels.light || block == RivalRebels.light2 || block == RivalRebels.reactive || block == RivalRebels.timedbomb*/)
        {
            world.setBlockState(new BlockPos(xx, yy, zz), Blocks.AIR.getDefaultState());
            return;
        }
        if (block == Blocks.OAK_STAIRS || block == Blocks.ACACIA_STAIRS || block == Blocks.DARK_OAK_STAIRS || block == Blocks.BIRCH_STAIRS) world.setBlockState(new BlockPos(xx, yy, zz), Blocks.PLANKS.getDefaultState());
        /*if (block == RivalRebels.camo1 || block == RivalRebels.camo2 || block == RivalRebels.camo3 || block == RivalRebels.conduit)
        {
            if (world.rand.nextInt(20) != 0) return;
        }*/
        //TODO: create the rival rebels blackList class
        /*if (BlackList.explosion(block))
        {
            return;
        }*/
    }

    private void pushAndHurtEntities(World world, double x, double y, double z, int radius, DamageSource dmgsrc)
    {
        int var3 = MathHelper.floor(x - radius - 1.0D);
        int var4 = MathHelper.floor(x + radius + 1.0D);
        int var5 = MathHelper.floor(y - radius - 1.0D);
        int var28 = MathHelper.floor(y + radius + 1.0D);
        int var7 = MathHelper.floor(z - radius - 1.0D);
        int var29 = MathHelper.floor(z + radius + 1.0D);
        List var9 = world.getEntitiesWithinAABBExcludingEntity(null, new AxisAlignedBB(var3, var5, var7, var4, var28, var29));
        Vec3d var30 = new Vec3d(x, y, z);

        radius *= 4;

        for (int var11 = 0; var11 < var9.size(); ++var11)
        {
            Entity var31 = (Entity) var9.get(var11);
            if (!(var31 instanceof EntityDebris)/*&&!(var31 instanceof EntityFlameBall)&&!(var31 instanceof EntityRhodes)*/)
            {
                double var13 = var31.getDistance(x, y, z) / radius;

                if (var13 <= 1.0D)
                {
                    double var15 = var31.posX - x;
                    double var17 = var31.posY + var31.getEyeHeight() - y;
                    double var19 = var31.posZ - z;
                    double var33 = MathHelper.sqrt(var15 * var15 + var17 * var17 + var19 * var19);

                    if (var33 != 0.0D)
                    {
                        var15 /= var33;
                        var17 /= var33;
                        var19 /= var33;
                        double var32 = world.getBlockDensity(var30, var31.getEntityBoundingBox());
                        double var34 = (1.0D - var13) * var32;
                        var31.attackEntityFrom(dmgsrc, (int) ((var34 * var34 + var34) / 2.0D * radius + 1.0D));
                        var31.motionX += var15 * var34;
                        var31.motionY += var17 * var34;
                        var31.motionZ += var19 * var34;
                    }
                }
            }
        }
    }
}
