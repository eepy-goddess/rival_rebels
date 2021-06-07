package com.rivalrebels.client.renderhelper;


import com.rivalrebels.client.oldstuff.Tessellator;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class RenderHelper {
    public static void renderBox(float length, float height, float depth, float texLocX, float texLocY, float texXsize, float texYsize, float resolution)
    {
        GL11.glRotatef(90, 0.0F, 1.0F, 0.0F);
        texLocX /= texXsize;
        texLocY /= texYsize;
        float hl = (length / 2f) / resolution;
        float hh = (height / 2f) / resolution;
        float hd = (depth / 2f) / resolution;
        float xtd = (length / texXsize);// * resolution;
        // float xth = (height / texXsize);// * resolution;
        float xtl = (depth / texXsize);// * resolution;
        float ytd = (length / texYsize);// * resolution;
        float yth = (height / texYsize);// * resolution;
        // float ytl = (depth / texYsize) ;//* resolution;
        Vertice xpypzp = new Vertice(hl, hh, hd);
        Vertice xnypzp = new Vertice(-hl, hh, hd);
        Vertice xnypzn = new Vertice(-hl, hh, -hd);
        Vertice xpypzn = new Vertice(hl, hh, -hd);
        Vertice xpynzp = new Vertice(hl, -hh, hd);
        Vertice xnynzp = new Vertice(-hl, -hh, hd);
        Vertice xnynzn = new Vertice(-hl, -hh, -hd);
        Vertice xpynzn = new Vertice(hl, -hh, -hd);
        TextureVertice t1 = new TextureVertice(texLocX + xtd, texLocY + 0);
        TextureVertice t2 = new TextureVertice(texLocX + xtd + xtl, texLocY + 0);
        TextureVertice t3 = new TextureVertice(texLocX + xtd + xtl + xtl, texLocY + 0);
        TextureVertice t4 = new TextureVertice(texLocX + 0, texLocY + ytd);
        TextureVertice t5 = new TextureVertice(texLocX + xtd, texLocY + ytd);
        TextureVertice t6 = new TextureVertice(texLocX + xtd + xtl, texLocY + ytd);
        TextureVertice t7 = new TextureVertice(texLocX + xtd + xtl + xtd, texLocY + ytd);
        TextureVertice t8 = new TextureVertice(texLocX + xtd + xtl + xtl, texLocY + ytd);
        TextureVertice t9 = new TextureVertice(texLocX + xtd + xtl + xtl + xtd, texLocY + ytd);
        TextureVertice t10 = new TextureVertice(texLocX + 0, texLocY + ytd + yth);
        TextureVertice t11 = new TextureVertice(texLocX + xtd, texLocY + ytd + yth);
        TextureVertice t12 = new TextureVertice(texLocX + xtd + xtl, texLocY + ytd + yth);
        TextureVertice t13 = new TextureVertice(texLocX + xtd + xtl + xtd, texLocY + ytd + yth);
        TextureVertice t14 = new TextureVertice(texLocX + xtd + xtl + xtl + xtd, texLocY + ytd + yth);
        addFace(xpypzn, xnypzn, xnypzp, xpypzp, t6, t2, t1, t5); // top
        addFace(xpynzp, xnynzp, xnynzn, xpynzn, t6, t2, t3, t8); // bottom
        addFace(xnypzp, xnynzp, xpynzp, xpypzp, t4, t10, t11, t5); // right
        addFace(xpypzp, xpynzp, xpynzn, xpypzn, t5, t11, t12, t6); // front
        addFace(xpypzn, xpynzn, xnynzn, xnypzn, t6, t12, t13, t7); // left
        addFace(xnypzn, xnynzn, xnynzp, xnypzp, t7, t13, t14, t9); // back
    }

    public static void drawPoint(Vertice v1, float size)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef(v1.x, v1.y, v1.z);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        size /= 2;
        Tessellator t = Tessellator.instance;
        GL11.glRotatef(180 - Minecraft.getMinecraft().player.rotationYaw, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(90 - Minecraft.getMinecraft().player.rotationPitch, 1.0F, 0.0F, 0.0F);
        t.startDrawingQuads();
        t.setNormal(0.0F, 1.0F, 0.0F);
        t.addVertexWithUV(-size, 0, -size, 0, 0);
        t.addVertexWithUV(size, 0, -size, 1, 0);
        t.addVertexWithUV(size, 0, size, 1, 1);
        t.addVertexWithUV(-size, 0, size, 0, 1);
        t.draw();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
    }

    public static void drawLine(Vertice v1, float size1, Vertice v2, float size2)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef(v1.x, v1.y, v1.z);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        size1 /= 2;
        size2 /= 2;
        Tessellator t = Tessellator.instance;
        GL11.glRotatef(180 - Minecraft.getMinecraft().player.rotationYaw, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(90 - Minecraft.getMinecraft().player.rotationPitch, 1.0F, 0.0F, 0.0F);
        t.startDrawingQuads();
        t.setNormal(0.0F, 1.0F, 0.0F);
        t.addVertexWithUV(-size1, 0, -size1, 0, 0);
        t.addVertexWithUV(size1, 0, -size1, 1, 0);
        t.addVertexWithUV(size1, 0, size1, 1, 1);
        t.addVertexWithUV(-size1, 0, size1, 0, 1);
        t.draw();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
    }

    public static void renderBox(float length, float height, float depth)
    {
        GL11.glRotatef(90, 0.0F, 1.0F, 0.0F);
        float hl = (length / 2f);
        float hh = (height / 2f);
        float hd = (depth / 2f);
        Vertice xpypzp = new Vertice(hl, hh, hd);
        Vertice xnypzp = new Vertice(-hl, hh, hd);
        Vertice xnypzn = new Vertice(-hl, hh, -hd);
        Vertice xpypzn = new Vertice(hl, hh, -hd);
        Vertice xpynzp = new Vertice(hl, -hh, hd);
        Vertice xnynzp = new Vertice(-hl, -hh, hd);
        Vertice xnynzn = new Vertice(-hl, -hh, -hd);
        Vertice xpynzn = new Vertice(hl, -hh, -hd);
        addFace(xpypzn, xnypzn, xnypzp, xpypzp); // top
        addFace(xpynzp, xnynzp, xnynzn, xpynzn); // bottom
        addFace(xnypzp, xnynzp, xpynzp, xpypzp); // right
        addFace(xpypzp, xpynzp, xpynzn, xpypzn); // front
        addFace(xpypzn, xpynzn, xnynzn, xnypzn); // left
        addFace(xnypzn, xnynzn, xnynzp, xnypzp); // back
    }

    public static void addFace(Vertice v1, Vertice v2, Vertice v3, Vertice v4, TextureVertice t1, TextureVertice t2, TextureVertice t3, TextureVertice t4)
    {
        Tessellator t = Tessellator.instance;
        Vertice mv = new Vertice((v1.x + v2.x + v3.x + v4.x) / 4, (v1.y + v2.y + v3.y + v4.y) / 4, (v1.z + v2.z + v3.z + v4.z) / 4);
        TextureVertice mt = new TextureVertice((t1.x + t2.x + t3.x + t4.x) / 4, (t1.y + t2.y + t3.y + t4.y) / 4);
        t.startDrawing(GL11.GL_TRIANGLES);
        addVertice(v1, t1);
        addVertice(v2, t2);
        addVertice(mv, mt);
        t.draw();
        t.startDrawing(GL11.GL_TRIANGLES);
        addVertice(v2, t2);
        addVertice(v3, t3);
        addVertice(mv, mt);
        t.draw();
        t.startDrawing(GL11.GL_TRIANGLES);
        addVertice(v3, t3);
        addVertice(v4, t4);
        addVertice(mv, mt);
        t.draw();
        t.startDrawing(GL11.GL_TRIANGLES);
        addVertice(v4, t4);
        addVertice(v1, t1);
        addVertice(mv, mt);
        t.draw();
    }

    // private void addFace(Vertice v1, Vertice v2, Vertice v3, Vertice v4, TextureVertice t1, TextureVertice t2, TextureVertice t3, TextureVertice t4)
    // {
    // Tessellator t = Tessellator.instance;
    // t.startDrawing(GL11.GL_QUADS);
    // addVertice(v1, t1);
    // addVertice(v2, t2);
    // addVertice(v3);
    // addVertice(v4);
    // t.draw();
    // }

    public static void addFace(Vertice v1, Vertice v2, Vertice v3, Vertice v4)
    {
        Tessellator t = Tessellator.instance;
        t.startDrawing(GL11.GL_QUADS);
        addVertice(v1);
        addVertice(v2);
        addVertice(v3);
        addVertice(v4);
        t.draw();
    }

    public static void addFace(Vertice v1, Vertice v2, Vertice v3, Vertice v4, TextureFace t)
    {
        Tessellator tess = Tessellator.instance;
        tess.startDrawingQuads();
        addVertice(v1, t.v1);
        addVertice(v2, t.v2);
        addVertice(v3, t.v3);
        addVertice(v4, t.v4);
        tess.draw();
    }

    public static void addFace(Vertice v1, Vertice v2, Vertice v3, Vertice v4, float par5, float par6, float par7, float par8)
    {
        Tessellator t = Tessellator.instance;
        t.startDrawingQuads();
        addVertice(v1, par5, par8);
        addVertice(v2, par6, par8);
        addVertice(v3, par6, par7);
        addVertice(v4, par5, par7);
        t.draw();
    }

    public static void addTri(Vertice v1, Vertice v2, Vertice v3)
    {
        Tessellator t = Tessellator.instance;
        t.startDrawing(6);
        addVertice(v3);
        addVertice(v1);
        addVertice(v2);
        t.draw();
    }

    public static void addVertice(Vertice v, TextureVertice t)
    {
        Tessellator tessellator = Tessellator.instance;
        tessellator.addVertexWithUV(v.x, v.y, v.z, t.x, t.y);
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
