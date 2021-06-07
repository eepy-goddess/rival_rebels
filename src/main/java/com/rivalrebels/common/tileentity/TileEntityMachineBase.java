package com.rivalrebels.common.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public abstract class TileEntityMachineBase extends TileEntity implements ITickable {
    public float	pInM		= 0;
    public float	pInR		= 0;
    public float	edist		= 0;
    public float	decay		= 0;
    public float	powerGiven	= 0;
    public int		x			= 0;
    public int		y			= 0;
    public int		z			= 0;

    @Override
    public void update() {
        if (pInR > 0) pInR = powered(pInR, edist);
        pInR -= decay;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        x = nbt.getInteger("rx");
        y = nbt.getInteger("ry");
        z = nbt.getInteger("rz");
        edist = nbt.getFloat("edist");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setInteger("rx", x);
        nbt.setInteger("ry", y);
        nbt.setInteger("rz", z);
        nbt.setFloat("edist", edist);
        return nbt;
    }

    @Override
    public void invalidate()
    {
        super.invalidate();
        TileEntity connectedTo = world.getTileEntity(new BlockPos(x, y, z));
        //if (connectedTo != null && connectedTo instanceof TileEntityReactor) ((TileEntityReactor)connectedTo).machines.remove(this);
    }

    abstract public float powered(float power, float distance);
}
