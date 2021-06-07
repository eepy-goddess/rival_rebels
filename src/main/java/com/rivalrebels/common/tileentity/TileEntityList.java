package com.rivalrebels.common.tileentity;

import net.minecraft.tileentity.TileEntity;

import java.util.Arrays;

public class TileEntityList {
    private int				size	= 0;
    private TileEntity[]	list	= new TileEntity[0];

    public TileEntityList()
    {
    }

    public int getSize()
    {
        return size;
    }

    public int getFFSize()
    {
        int result = 0;
        for (int i = 0; i < list.length; i++)
        {
          //  if (list[i] != null && !(list[i] instanceof TileEntityReactive)) result++;
        }
        return result;
    }

    public TileEntity[] getArray()
    {
        return list;
    }

    public void add(TileEntity o)
    {
        int index = get(o);
        if (index == -1)
        {
            size++;
            if (size <= list.length) list[size - 1] = o;
            else
            {
                int nsize = ((list.length * 3) / 2) + 1;
                if (nsize < size) nsize = size;
                list = Arrays.copyOf(list, nsize);
                list[size - 1] = o;
            }
        }
        else
        {
            list[index] = o;
        }
    }

    public TileEntity get(int index)
    {
        return list[index];
    }

    public int get(TileEntity tile)
    {
        for (int i = 0; i < size; i++)
        {
            if (list[i].equals(tile))
            {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString()
    {
        StringBuilder strB = new StringBuilder();
        strB.append("tileentities:\n");
        for (int i = 0; i < size; i++)
        {
            strB.append(list[i].getPos().getX() + ", " + list[i].getPos().getY() + ", " + list[i].getPos().getZ() + ", " + list[i].toString());
            strB.append(",\n");
        }
        return strB.toString();
    }

    public void remove(TileEntityMachineBase temb)
    {
        remove(get(temb));
    }

    public void remove(int index)
    {
        if (index != -1)
        {
            while (index < size)
                list[index] = ++index == size ? null : list[index];
            --size;
        }
    }

    public void clear()
    {
        size = 0;
        list = new TileEntity[0];
    }
}
