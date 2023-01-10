package com.rivalrebels.client.guiLoader;

import com.rivalrebels.ModInfo;
import com.rivalrebels.common.container.LoaderContainer;
import com.rivalrebels.common.tileentity.TileLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class LoaderGui extends GuiContainer {
    public TileLoader te;
    private int inventoryRows = 0;
    public static ResourceLocation texture = new ResourceLocation(ModInfo.modid, "textures/gui/loader.png");
    public LoaderGui(IInventory player, TileLoader tileentity) {
        super(new LoaderContainer(player, tileentity));
        this.te = tileentity;
        this.allowUserInput = false;
        short var3 = 222;
        int var4 = var3 - 108;
        this.inventoryRows = tileentity.inventory.getSlots() / 9;
        this.ySize = var4 + this.inventoryRows * 18;
        this.xSize = 256;
    }
    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        GL11.glPushMatrix();
        GL11.glRotated(-13, 0, 0, 1);
        fontRenderer.drawString("Loader", 165, 237, 0x444444);
        GL11.glPopMatrix();
        int mousex = par1;
        int mousey = par2;
        int posx = (width - xSize) / 2;
        int posy = (height - ySize) / 2;
        int coordx = posx + 92;
        int coordy = posy + 202;
        int widthx = 72;
        int widthy = 8;
        if (mousex > coordx && mousey > coordy && mousex < coordx + widthx && mousey < coordy + widthy)
        {
            mousex -= posx;
            mousey -= posy;
            drawGradientRect(mousex, mousey, mousex + fontRenderer.getStringWidth("rivalrebels.com") + 3, mousey + 12, 0xaa111111, 0xaa111111);
            fontRenderer.drawString("rivalrebels.com", mousex + 2, mousey + 2, 0xFFFFFF);
            if (Desktop.isDesktopSupported() && !buttondown && Mouse.isButtonDown(0))
            {
                try
                {
                    Desktop.getDesktop().browse(new URI("http://rivalrebels.com"));
                }
                catch (IOException | URISyntaxException e)
                {
                    e.printStackTrace();
                }
            }
        }
        buttondown = Mouse.isButtonDown(0);
    }

    boolean	buttondown;

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.drawDefaultBackground();
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        this.drawTexturedModalRect(width / 2 - 128, height / 2 - 103, 0, 0, 256, 210);
    }
}
