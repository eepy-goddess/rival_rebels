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
public class GuiKnob extends GuiButton {
    protected int degree;
    protected int maxdegreelimit = 360;
    protected int mindegreelimit = 0;
    protected boolean pressed;
    public static ResourceLocation button = new ResourceLocation(ModInfo.modid, "textures/gui/knob_button.png");

    public GuiKnob(int id, int x, int y, int minDegree, int maxDegree, int startDegree, boolean respectLimits, String par6Str)
    {
        super(id, x, y, 36, 36, par6Str);
        if (respectLimits) {
            this.maxdegreelimit = maxDegree;
            this.mindegreelimit = minDegree;
        }

        this.degree = startDegree;
    }

    /**
     * Draws this button to the screen.
     */
    @Override
    public void drawButton(Minecraft par1Minecraft, int par2, int par3, float partialTicks)
    {
        this.mouseDragged(par1Minecraft, par2, par3);

        if (this.degree > this.maxdegreelimit) {
            this.degree = this.maxdegreelimit;
        }

        if (this.degree < this.mindegreelimit) {
            this.degree = this.mindegreelimit;
        }

        par1Minecraft.renderEngine.bindTexture(button);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        int state = 0;
        if (this.pressed || this.mousePressed(par1Minecraft, par2, par3)) {
            state = 36;
        }
        GL11.glPushMatrix();
        GL11.glTranslatef(this.x + this.width / 2.0F, this.y + this.height / 2.0F, 0.0F);
        GL11.glRotatef(this.degree, 0.0F, 0.0F, 1.0F);
        GL11.glTranslatef(-(this.x + this.width / 2.0F), -(this.y + this.height / 2.0F), 0.0F);
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
            if (this.mousePressed(par1Minecraft, par2, par3)) {
                this.pressed = true;
            }

            if (this.pressed) {
                this.degree = (
                        (int)(
                            Math.atan2(this.y - par3 + this.height / 2, this.x - par2 + this.width / 2)
                                * 180.0
                                / Math.PI
                            )
                            + 450
                    )
                    % 360
                - 180;
            }
        }
        else
        {
            this.pressed = false;
            float movement = -Mouse.getDWheel() * 0.375F;
            this.degree = (int)(this.degree + movement);
        }
    }

    @Override
    public void mouseReleased(int par2, int par3)
    {
        pressed = false;
    }

    public int getDegree()
    {
        return degree;
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
