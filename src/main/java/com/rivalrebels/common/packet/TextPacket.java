package com.rivalrebels.common.packet;

import com.rivalrebels.common.init.FileRW;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class TextPacket implements IMessage {
    public String text;
    public TextFormatting format;
    public boolean isFormatted = false;
    public TextPacket(){

    }
    public TextPacket(String text){
        this.text = text;
    }
    public TextPacket(TextFormatting formatting, String text){
        this.text = text;
        this.format = formatting;
        this.isFormatted = true;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        byte[] dst = new byte[buf.readInt()];
        buf.readBytes(dst);
        text = FileRW.getStringBytes(dst);
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(text.length());
        buf.writeBytes(FileRW.getBytesString(text));
    }
    public static class Handler implements IMessageHandler<TextPacket, IMessage> {

        @Override
        public IMessage onMessage(TextPacket m, MessageContext ctx) {
            if (m.text.startsWith("-t"))
            {
                String[] str = m.text.substring(2, m.text.length()).split("\n");
                for (int i = 0; i < str.length; i++) Minecraft.getMinecraft().player.sendStatusMessage(new TextComponentString(str[i]), false);
            }
            else
            {
                String[] s = m.text.split(" ");
                StringBuilder strb = new StringBuilder();
                for (int i = 0; i < s.length; i++) {
                    strb.append(I18n.translateToLocal(s[i]));
                    strb.append(" ");
                }
                if(m.isFormatted){
                    Minecraft.getMinecraft().player.sendStatusMessage(new TextComponentString(m.format + strb.toString()), false);
                } else {
                    Minecraft.getMinecraft().player.sendStatusMessage(new TextComponentString(strb.toString()), false);
                }
            }
            return null;
        }
    }
}
