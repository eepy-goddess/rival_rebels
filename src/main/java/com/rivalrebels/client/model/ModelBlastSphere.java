package com.rivalrebels.client.model;

import com.rivalrebels.client.oldstuff.Tessellator;
import com.rivalrebels.client.renderhelper.Vertice;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;

public class ModelBlastSphere {
    Vertice	vx	= new Vertice(1, 0, 0).normalize();
    Vertice	vy	= new Vertice(0, 1, 0).normalize();
    Vertice	vz	= new Vertice(0, 0, 1).normalize();
    Vertice	vxy	= new Vertice(0.5f, 0.5f, 0).normalize();
    Vertice	vyz	= new Vertice(0, 0.5f, 0.5f).normalize();
    Vertice	vxz	= new Vertice(0.5f, 0, 0.5f).normalize();
    Vertice	vx1	= new Vertice(0.75f, 0.25f, 0).normalize();
    Vertice	vx2	= new Vertice(0.5f, 0.25f, 0.25f).normalize();
    Vertice	vx3	= new Vertice(0.75f, 0, 0.25f).normalize();
    Vertice	vy1	= new Vertice(0, 0.75f, 0.25f).normalize();
    Vertice	vy2	= new Vertice(0.25f, 0.5f, 0.25f).normalize();
    Vertice	vy3	= new Vertice(0.25f, 0.75f, 0).normalize();
    Vertice	vz1	= new Vertice(0.25f, 0, 0.75f).normalize();
    Vertice	vz2	= new Vertice(0.25f, 0.25f, 0.5f).normalize();
    Vertice	vz3	= new Vertice(0, 0.25f, 0.75f).normalize();

    public void renderModel(float size, float red, float green, float blue, float alpha)
    {
        renderModel(size, red, green, blue, alpha, true);
    }

    public void renderModel(float size, float red, float green, float blue, float alpha, boolean blend)
    {
        GL11.glPushMatrix();
        GL11.glColor4f(red, green, blue, alpha);
        GL11.glScalef(size, size, size);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_LIGHTING);
        if (blend)
        {
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        }
        for (int i = 0; i < 2; i++)
        {
            GL11.glRotatef(i * 180, 0, 0, 1);
            for (int p = 0; p < 4; p++)
            {
                GL11.glPushMatrix();
                GL11.glRotatef(p * 90, 0, 1, 0);

                PlasmoidRenderHelper.addTri(vy, vy1, vy3);
                PlasmoidRenderHelper.addTri(vy1, vyz, vy2);
                PlasmoidRenderHelper.addTri(vy3, vy2, vxy);
                PlasmoidRenderHelper.addTri(vy1, vy2, vy3);

                PlasmoidRenderHelper.addTri(vx, vx1, vx3);
                PlasmoidRenderHelper.addTri(vx1, vxy, vx2);
                PlasmoidRenderHelper.addTri(vx3, vx2, vxz);
                PlasmoidRenderHelper.addTri(vx1, vx2, vx3);

                PlasmoidRenderHelper.addTri(vz, vz1, vz3);
                PlasmoidRenderHelper.addTri(vz1, vxz, vz2);
                PlasmoidRenderHelper.addTri(vz3, vz2, vyz);
                PlasmoidRenderHelper.addTri(vz1, vz2, vz3);

                PlasmoidRenderHelper.addTri(vyz, vz2, vy2);
                PlasmoidRenderHelper.addTri(vxy, vy2, vx2);
                PlasmoidRenderHelper.addTri(vxz, vx2, vz2);
                PlasmoidRenderHelper.addTri(vx2, vy2, vz2);

                GL11.glPopMatrix();
            }
        }
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }
    //I have to draw the triangles in the POSITION vertex format, so I am creating this subclass... WHY, WHY MUST VERTEX FORMATS BE A THING, NOT POG
    public static class PlasmoidRenderHelper{
        public static void addTri(Vertice v1, Vertice v2, Vertice v3){
            Tessellator t = Tessellator.instance;
            net.minecraft.client.renderer.Tessellator.getInstance().getBuffer().begin(6, DefaultVertexFormats.POSITION);
            addVertice(v3);
            addVertice(v1);
            addVertice(v2);
            t.draw();
        }
        public static void addVertice(Vertice v, float x, float y)
        {
            Tessellator tessellator = Tessellator.instance;
            tessellator.addVertexWithUV(v.x, v.y, v.z, x, y);
        }

        public static void addVertice(Vertice v)
        {
            Tessellator tessellator = Tessellator.instance;
            tessellator.addVertex(v.x, v.y, v.z);
        }
    }
}
