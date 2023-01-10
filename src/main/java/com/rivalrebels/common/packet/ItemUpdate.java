package com.rivalrebels.common.packet;

import com.rivalrebels.common.items.FlameThrower;

import io.netty.buffer.ByteBuf;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ItemUpdate implements IMessage {
    public int	item;
    public int	value;

    public ItemUpdate()
    {
    }

    public ItemUpdate(int currentItem, int i)
    {
        item = currentItem;
        value = i;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        item = buf.readByte();
        value = buf.readByte();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeByte(item);
        buf.writeByte(value);
    }
    public static class Handler implements IMessageHandler<ItemUpdate, IMessage> {

        @Override
        public IMessage onMessage(ItemUpdate message, MessageContext ctx)
        {
            ItemStack itemstack = ctx.getServerHandler().player.inventory.getStackInSlot(message.item);
            /*if (itemstack.getItem() instanceof ItemTesla)
            {
                if (!itemstack.hasTagCompound()) itemstack.setTagCompound(new NBTTagCompound());
                itemstack.stackTagCompound.setInteger("dial", message.value);
            }*/
            if (itemstack.getItem() instanceof FlameThrower)
            {
                if (!itemstack.hasTagCompound()) itemstack.setTagCompound(new NBTTagCompound());
                itemstack.getTagCompound().setInteger("mode", message.value);
            }
            return null;
        }
    }
}
