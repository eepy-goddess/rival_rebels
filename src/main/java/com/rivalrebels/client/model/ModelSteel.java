package com.rivalrebels.client.model;

import com.rivalrebels.client.oldstuff.Tessellator;
import com.rivalrebels.client.renderhelper.RenderHelper;
import com.rivalrebels.client.renderhelper.Vertice;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;

public class ModelSteel {
    Tessellator	tessellator	= Tessellator.instance;
    float		s			= 0.5F;

    Vertice		v1			= new Vertice(s, s, s).normalize();
    Vertice		v2			= new Vertice(s, s, -s).normalize();
    Vertice		v3			= new Vertice(-s, s, -s).normalize();
    Vertice		v4			= new Vertice(-s, s, s).normalize();

    Vertice		v5			= new Vertice(s, -s, s).normalize();
    Vertice		v6			= new Vertice(s, -s, -s).normalize();
    Vertice		v7			= new Vertice(-s, -s, -s).normalize();
    Vertice		v8			= new Vertice(-s, -s, s).normalize();

    public void renderModel()
    {
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);

        net.minecraft.client.renderer.Tessellator.getInstance().getBuffer().begin(7, DefaultVertexFormats.POSITION_TEX);
        addVertex(v1, 0, 0, true);
        addVertex(v5, 1, 0, true);
        addVertex(v8, 1, 1, true);
        addVertex(v4, 0, 1, true);
        tessellator.draw();

        net.minecraft.client.renderer.Tessellator.getInstance().getBuffer().begin(7, DefaultVertexFormats.POSITION_TEX);
        addVertex(v4, 0, 0, true);
        addVertex(v8, 1, 0, true);
        addVertex(v7, 1, 1, true);
        addVertex(v3, 0, 1, true);
        tessellator.draw();

        net.minecraft.client.renderer.Tessellator.getInstance().getBuffer().begin(7, DefaultVertexFormats.POSITION_TEX);
        addVertex(v3, 0, 0, true);
        addVertex(v7, 1, 0, true);
        addVertex(v6, 1, 1, true);
        addVertex(v2, 0, 1, true);
        tessellator.draw();

        net.minecraft.client.renderer.Tessellator.getInstance().getBuffer().begin(7, DefaultVertexFormats.POSITION_TEX);
        addVertex(v2, 0, 0, true);
        addVertex(v6, 1, 0, true);
        addVertex(v5, 1, 1, true);
        addVertex(v1, 0, 1, true);
        tessellator.draw();

        net.minecraft.client.renderer.Tessellator.getInstance().getBuffer().begin(7, DefaultVertexFormats.POSITION_TEX);
        addVertex(v4, 0, 0, true);
        addVertex(v3, 1, 0, true);
        addVertex(v2, 1, 1, true);
        addVertex(v1, 0, 1, true);
        tessellator.draw();

        net.minecraft.client.renderer.Tessellator.getInstance().getBuffer().begin(7, DefaultVertexFormats.POSITION_TEX);
        addVertex(v5, 0, 0, true);
        addVertex(v6, 1, 0, true);
        addVertex(v7, 1, 1, true);
        addVertex(v8, 0, 1, true);
        tessellator.draw();

        GL11.glPopMatrix();
    }

    private void addVertex(Vertice v, double t, double t2, boolean offset)
    {
        Tessellator tessellator = Tessellator.instance;
        tessellator.addVertexWithUV(v.x * 0.999, v.y * 0.999, v.z * 0.999, t, t2);
    }
}
