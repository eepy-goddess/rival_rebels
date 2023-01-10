package com.rivalrebels.client.guihelper;

import com.rivalrebels.ModInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiFTKnob extends GuiButton {
    protected int		mode;
    protected boolean	pressed;
    public static ResourceLocation button = new ResourceLocation(ModInfo.modid, "textures/gui/knob_button.png");

    public GuiFTKnob(int id, int x, int y, int minDegree, int maxDegree, int startDegree, boolean respectLimits, String par6Str)
    {
        super(id, x, y, 36, 36, par6Str);
        mode = startDegree;
    }

    /**
     * Draws this button to the screen.
     */
    @Override
    public void drawButton(Minecraft par1Minecraft, int par2, int par3, float partialTicks)
    {
        this.mouseDragged(par1Minecraft, par2, par3);
        if (mode > 2) mode = 2;
        if (mode < 0) mode = 0;
        par1Minecraft.renderEngine.bindTexture(button);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        int state = 0;
        if (pressed || mousePressed(par1Minecraft, par2, par3)) state = 36;
        GL11.glPushMatrix();
        GL11.glTranslatef(this.x + (width / 2f), this.y + (height / 2f), 0);
        GL11.glRotatef(mode * 90 - 90, 0, 0, 1);
        GL11.glTranslatef(-(this.x + (width / 2f)), -(this.y + (height / 2f)), 0);
        this.drawTexturedModalRect(this.x, this.y, 76 + state, 0, this.width, this.height);
        GL11.glPopMatrix();
    }

    /**
     * Fired when the mouse button is dragged. Equivalent of MouseListener.mouseDragged(MouseEvent e).
     */
    @Override
    protected void mouseDragged(Minecraft par1Minecraft, int par2, int par3)
    {
        if (Mouse.isButtonDown(0))
        {
            if (mousePressed(par1Minecraft, par2, par3)) pressed = true;
            if (pressed) mode = (((((int) (Math.atan2(y - par3 + (height / 2), x - par2 + (width / 2)) * 180 / Math.PI)) + 450) % 360) - 45) / 90;
        }
        else
        {
            pressed = false;
            int move = Mouse.getDWheel();
            mode += Integer.compare(0, move);
            while (mode < 0)
                mode += 3;
            mode %= 3;
        }
    }

    @Override
    public void mouseReleased(int par2, int par3)
    {
        pressed = false;
    }

    public int getDegree()
    {
        return mode;
    }

    /**
     * Returns true if the mouse has been pressed on this control. Equivalent of MouseListener.mousePressed(MouseEvent e).
     */
    @Override
    public boolean mousePressed(Minecraft par1Minecraft, int par2, int par3)
    {
        return this.enabled && this.visible && Math.sqrt(((x - par2 + (width / 2f)) * (x - par2 + (width / 2f))) + ((y - par3 + (height / 2f)) * (y - par3 + (height / 2f)))) <= (width / 2f);
    }
}
