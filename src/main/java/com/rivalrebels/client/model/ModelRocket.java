package com.rivalrebels.client.model;

import com.rivalrebels.client.renderhelper.RenderHelper;
import com.rivalrebels.client.renderhelper.Vertice;
import org.lwjgl.opengl.GL11;

public class ModelRocket {
    Vertice	vy1		= new Vertice(0, 0, 0);
    Vertice	vy2		= new Vertice(0, 2.5f, 0);
    Vertice	vpx1	= new Vertice(0.5f, 0, 0);
    Vertice	vnx1	= new Vertice(-0.5f, 0, 0);
    Vertice vpz1	= new Vertice(0, 0, 0.5f);
    Vertice	vnz1	= new Vertice(0, 0, -0.5f);
    Vertice	vpxpz1	= new Vertice(0.3535533f, 0, 0.3535533f);
    Vertice	vpxnz1	= new Vertice(0.3535533f, 0, -0.3535533f);
    Vertice	vnxpz1	= new Vertice(-0.3535533f, 0, 0.3535533f);
    Vertice	vnxnz1	= new Vertice(-0.3535533f, 0, -0.3535533f);
    Vertice	vpx2	= new Vertice(0.5f, 2, 0);
    Vertice	vnx2	= new Vertice(-0.5f, 2, 0);
    Vertice	vpz2	= new Vertice(0, 2, 0.5f);
    Vertice	vnz2	= new Vertice(0, 2, -0.5f);
    Vertice	vpxpz2	= new Vertice(0.3535533f, 2, 0.3535533f);
    Vertice	vpxnz2	= new Vertice(0.3535533f, 2, -0.3535533f);
    Vertice	vnxpz2	= new Vertice(-0.3535533f, 2, 0.3535533f);
    Vertice	vnxnz2	= new Vertice(-0.3535533f, 2, -0.3535533f);
    Vertice	vpx3	= new Vertice(1, -0.2f, 0);
    Vertice	vnx3	= new Vertice(-1, -0.2f, 0);
    Vertice	vpz3	= new Vertice(0, -0.2f, 1);
    Vertice	vnz3	= new Vertice(0, -0.2f, -1);
    Vertice	vpx4	= new Vertice(1, 1f, 0);
    Vertice	vnx4	= new Vertice(-1, 1f, 0);
    Vertice	vpz4	= new Vertice(0, 1f, 1);
    Vertice	vnz4	= new Vertice(0, 1f, -1);
    float	tx1		= 0;
    float	tx2		= 0.28125f;
    float	tx3		= 0.375f;
    float	tx4		= 0.5625f;
    float	tx5		= 0.65625f;
    float	ty1		= 0;
    float	ty2		= 0.09375f;
    float	ty3		= 0.1875f;

    public void render(boolean fins)
    {
        GL11.glPushMatrix();
        GL11.glScalef(0.125f, 0.25f, 0.125f);

        RenderHelper.addFace(vpx1, vpx2, vpxpz2, vpxpz1, tx1, tx2, ty1, ty2);
        RenderHelper.addFace(vpxpz1, vpxpz2, vpz2, vpz1, tx1, tx2, ty1, ty2);
        RenderHelper.addFace(vpz1, vpz2, vnxpz2, vnxpz1, tx1, tx2, ty1, ty2);
        RenderHelper.addFace(vnxpz1, vnxpz2, vnx2, vnx1, tx1, tx2, ty1, ty2);
        RenderHelper.addFace(vnx1, vnx2, vnxnz2, vnxnz1, tx1, tx2, ty1, ty2);
        RenderHelper.addFace(vnxnz1, vnxnz2, vnz2, vnz1, tx1, tx2, ty1, ty2);
        RenderHelper.addFace(vnz1, vnz2, vpxnz2, vpxnz1, tx1, tx2, ty1, ty2);
        RenderHelper.addFace(vpxnz1, vpxnz2, vpx2, vpx1, tx1, tx2, ty1, ty2);

        RenderHelper.addFace(vpxpz2, vpx2, vy2, vpz2, tx2, tx3, ty1, ty2);
        RenderHelper.addFace(vnxpz2, vpz2, vy2, vnx2, tx2, tx3, ty1, ty2);
        RenderHelper.addFace(vnxnz2, vnx2, vy2, vnz2, tx2, tx3, ty1, ty2);
        RenderHelper.addFace(vpxnz2, vnz2, vy2, vpx2, tx2, tx3, ty1, ty2);

        RenderHelper.addFace(vpx1, vpxpz1, vpz1, vy1, tx4, tx5, ty1, ty2);
        RenderHelper.addFace(vpz1, vnxpz1, vnx1, vy1, tx4, tx5, ty1, ty2);
        RenderHelper.addFace(vnx1, vnxnz1, vnz1, vy1, tx4, tx5, ty1, ty2);
        RenderHelper.addFace(vnz1, vpxnz1, vpx1, vy1, tx4, tx5, ty1, ty2);

        if (fins)
        {
            RenderHelper.addFace(vnx3, vpx3, vpx4, vnx4, tx3, tx4, ty1, ty3);
            RenderHelper.addFace(vpx3, vnx3, vnx4, vpx4, tx3, tx4, ty1, ty3);
            RenderHelper.addFace(vnz3, vpz3, vpz4, vnz4, tx3, tx4, ty1, ty3);
            RenderHelper.addFace(vpz3, vnz3, vnz4, vpz4, tx3, tx4, ty1, ty3);
        }

        GL11.glPopMatrix();
    }
}
