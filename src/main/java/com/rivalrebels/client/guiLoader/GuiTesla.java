package com.rivalrebels.client.guiLoader;

import com.rivalrebels.ModInfo;
import com.rivalrebels.RivalRebels;
import com.rivalrebels.client.guihelper.GuiKnob;
import com.rivalrebels.client.oldstuff.Tessellator;
import com.rivalrebels.common.init.RRConfigOptions;
import com.rivalrebels.common.items.weapons.Tesla;
import com.rivalrebels.common.packet.ItemUpdate;
import com.rivalrebels.common.packet.PacketDispatcher;

import java.util.Objects;

import org.lwjgl.input.Keyboard;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public class GuiTesla extends GuiScreen {
    private final int xSizeOfTexture = 256;
    private final int ySizeOfTexture = 256;
    private int posX;
    private int posY;
    private GuiKnob knob;
    private int s = 0;
    public static ResourceLocation texture = new ResourceLocation(ModInfo.modid, "textures/gui/flamethrower_knob.png");

    public GuiTesla(int start) {
        this.s = start - 90;
    }

    public void initGui() {
        this.posX = (this.width - 256) / 2;
        this.posY = (this.height - 256) / 2;
        this.buttonList.clear();
        this.knob = new GuiKnob(0, this.posX + 108, this.posY + 176, -90, 90, this.s, true, "Knob");
        this.buttonList.add(this.knob);
    }

    public boolean doesGuiPauseGame() {
        return false;
    }

    public void updateScreen() {
    }

    public void drawScreen(int x, int y, float d) {
        Tessellator tessellator = Tessellator.instance;
        float f = 0.00390625F;
        this.mc = Minecraft.getMinecraft();
        this.mc.renderEngine.bindTexture(texture);
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(this.posX, this.posY + 256, this.zLevel, 0.0, 256.0F * f);
        tessellator.addVertexWithUV(this.posX + 256, this.posY + 256, this.zLevel, 256.0F * f, 256.0F * f);
        tessellator.addVertexWithUV(this.posX + 256, this.posY, this.zLevel, 256.0F * f, 0.0);
        tessellator.addVertexWithUV(this.posX, this.posY, this.zLevel, 0.0, 0.0);
        tessellator.draw();
        super.drawScreen(x, y, d);
        if (!(RRConfigOptions.alt_R_key?Keyboard.isKeyDown(Keyboard.KEY_F):Keyboard.isKeyDown(Keyboard.KEY_R))) {
            this.mc.displayGuiScreen((GuiScreen)null);
            this.mc.setIngameFocus();
            PacketDispatcher.wrapper.sendToServer(new ItemUpdate(mc.player.inventory.currentItem, knob.getDegree()));
            ItemStack itemstack = this.mc.player.inventory.getStackInSlot(this.mc.player.inventory.currentItem);
            if (!itemstack.hasTagCompound()) itemstack.setTagCompound(new NBTTagCompound());
            Objects.requireNonNull(itemstack.getTagCompound()).setInteger("dial", knob.getDegree());
        }
    }
}
