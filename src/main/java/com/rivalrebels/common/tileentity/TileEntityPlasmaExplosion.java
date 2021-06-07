package com.rivalrebels.common.tileentity;

import com.rivalrebels.common.entity.EntityPlasmoid;
import com.rivalrebels.common.init.RRSounds;
import com.rivalrebels.common.init.RivalRebelsDamageSource;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;

import java.util.List;

public class TileEntityPlasmaExplosion extends TileEntity implements ITickable {
    public float	size		= 0;
    float			increment	= 0.3f;
    float			prevsize	= 0;

    public TileEntityPlasmaExplosion()
    {

    }

    @Override
    public void update() {
        prevsize = size;
        size += increment;
        if (prevsize == 0)
        {
            world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), RRSounds.plasma_explosion, SoundCategory.AMBIENT, 4, 1);
        }
        if (size > 3.1f)
        {
            size = 0f;
            world.setBlockState(pos, Blocks.AIR.getDefaultState());
            this.invalidate();
        }

        double fsize = Math.sin(size) * 5.9 * 2;
        double fsqr = fsize * fsize;
        List l = this.world.getEntitiesWithinAABBExcludingEntity(null, new AxisAlignedBB(pos.getX() - fsize + 0.5, pos.getY() - fsize + 0.5, pos.getZ() - fsize + 0.5, pos.getX() + fsize + 0.5, pos.getY() + fsize + 0.5, pos.getZ() + fsize + 0.5));
        for (int i = 0; i < l.size(); i++)
        {
            Entity e = (Entity) l.get(i);
            double var15 = e.posX - pos.getX();
            double var17 = e.posY + e.getEyeHeight() - pos.getY() + 1.5f;
            double var19 = e.posZ - pos.getZ();
            double dist = 0.5f/(MathHelper.sqrt(var15*var15+var17*var17+var19*var19)+0.01f);
            if (/*dist <= 0.5f && !(e instanceof EntityNuclearBlast) && */!(e instanceof EntityPlasmoid) /*&& !(e instanceof EntityTsarBlast) && !(e instanceof EntityRhodes)*/)
            {
                e.attackEntityFrom(RivalRebelsDamageSource.plasmaexplosion, 2);
                e.motionX += var15*dist;
                e.motionY += var17*dist;
                e.motionZ += var19*dist;
            }
        }
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return TileEntity.INFINITE_EXTENT_AABB;
    }

    @Override
    public double getMaxRenderDistanceSquared() {
        return 1600D;
    }

    @Override
    public void invalidate() {
        super.invalidate();
        IBlockState block = world.getBlockState(this.getPos());
        if(block != null){
            world.setBlockState(this.getPos(), block);
        }
    }
}
