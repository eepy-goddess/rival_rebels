package com.rivalrebels.common.packet;

import com.rivalrebels.common.entity.EntityDebris;

import io.netty.buffer.ByteBuf;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class EntityDebrisPacket implements IMessage {
    IBlockState block;
    int id = 0;
    public EntityDebrisPacket(){

    }
    public EntityDebrisPacket(EntityDebris entity){
        id = entity.getEntityId();
        block = entity.block;
    }
    @Override
    public void fromBytes(ByteBuf buf) {
        id = buf.readInt();
        block = Block.getStateById(buf.readInt());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(id);
        buf.writeInt(Block.getStateId(block));
    }
    public static class Handler implements IMessageHandler<EntityDebrisPacket, IMessage> {

        @Override
        public IMessage onMessage(EntityDebrisPacket message, MessageContext ctx) {
            try {
                for (Entity e : Minecraft.getMinecraft().world.loadedEntityList) {
                    if (e.getEntityId() == message.id && e instanceof EntityDebris) {
                        EntityDebris ed = (EntityDebris) e;
                        ed.block = message.block;
                        break;
                    }
                }
            } catch(Exception e){}
            return null;
        }
    }
}
