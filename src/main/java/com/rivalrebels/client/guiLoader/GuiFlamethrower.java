package com.rivalrebels.client.guiLoader;

import com.rivalrebels.RivalRebels;
import com.rivalrebels.client.guihelper.GuiFTKnob;
import com.rivalrebels.client.oldstuff.Tessellator;
import com.rivalrebels.common.packet.ItemUpdate;
import com.rivalrebels.common.packet.PacketDispatcher;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;

public class GuiFlamethrower extends GuiScreen {
    private final int	xSizeOfTexture	= 256;
    private final int	ySizeOfTexture	= 256;
    private int			posX;
    private int			posY;
    private GuiFTKnob knob;
    private int			s				= 0;
    public static ResourceLocation texture = new ResourceLocation(RivalRebels.modid, "textures/gui/flamethrower_knob.png");

    public GuiFlamethrower(int start)
    {
        s = start;
    }

    @Override
    public void initGui()
    {
        posX = (width - xSizeOfTexture) / 2;
        posY = (height - ySizeOfTexture) / 2;
        buttonList.clear();
        knob = new GuiFTKnob(0, posX + 108, posY + 176, -90, 90, s, true, "Knob");
        buttonList.add(knob);
    }

    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }

    @Override
    public void updateScreen()
    {

    }

    @Override
    public void drawScreen(int x, int y, float d)
    {
        Tessellator tessellator = Tessellator.instance;
        float f = 0.00390625F;
        mc = Minecraft.getMinecraft();
        mc.renderEngine.bindTexture(texture);
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(posX, posY + ySizeOfTexture, zLevel, 0, ySizeOfTexture * f);
        tessellator.addVertexWithUV(posX + xSizeOfTexture, posY + ySizeOfTexture, zLevel, xSizeOfTexture * f, ySizeOfTexture * f);
        tessellator.addVertexWithUV(posX + xSizeOfTexture, posY, zLevel, xSizeOfTexture * f, 0);
        tessellator.addVertexWithUV(posX, posY, zLevel, 0, 0);
        tessellator.draw();
        super.drawScreen(x, y, d);
        if (!(RivalRebels.altRkey?Keyboard.isKeyDown(Keyboard.KEY_F):Keyboard.isKeyDown(Keyboard.KEY_R)))
        {
            mc.displayGuiScreen((GuiScreen) null);
            mc.setIngameFocus();
            ItemStack itemstack = mc.player.inventory.getStackInSlot(mc.player.inventory.currentItem);
            if (!itemstack.hasTagCompound()) itemstack.setTagCompound(new NBTTagCompound());
            itemstack.getTagCompound().setInteger("mode", knob.getDegree());
            PacketDispatcher.wrapper.sendToServer(new ItemUpdate(mc.player.inventory.currentItem, knob.getDegree()));
        }
    }
}
