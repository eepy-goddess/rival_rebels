package com.rivalrebels.client.model;

import com.rivalrebels.client.renderhelper.RenderHelper;
import com.rivalrebels.client.renderhelper.TextureVertice;
import com.rivalrebels.client.renderhelper.Vertice;
import org.lwjgl.opengl.GL11;

public class LoaderModel {
    float			m			= 0.3125f;
    float			l			= 0.4375f;
    float			o			= 0.5f;

    Vertice			lloader1	= new Vertice(-m, o, o);
    Vertice			lloader2	= new Vertice(-l, l, o);
    Vertice			lloader3	= new Vertice(-o, m, o);
    Vertice			lloader4	= new Vertice(-o, -m, o);
    Vertice			lloader5	= new Vertice(-l, -l, o);
    Vertice lloader6	= new Vertice(-m, -o, o);
    Vertice			lloader7	= new Vertice(m, -o, o);
    Vertice			lloader8	= new Vertice(l, -l, o);
    Vertice			lloader9	= new Vertice(o, -m, o);
    Vertice			lloader10	= new Vertice(o, m, o);
    Vertice			lloader11	= new Vertice(l, l, o);
    Vertice			lloader12	= new Vertice(m, o, o);

    Vertice			rloader1	= new Vertice(-m, o, -o);
    Vertice			rloader2	= new Vertice(-l, l, -o);
    Vertice			rloader3	= new Vertice(-o, m, -o);
    Vertice			rloader4	= new Vertice(-o, -m, -o);
    Vertice			rloader5	= new Vertice(-l, -l, -o);
    Vertice			rloader6	= new Vertice(-m, -o, -o);
    Vertice			rloader7	= new Vertice(m, -o, -o);
    Vertice			rloader8	= new Vertice(l, -l, -o);
    Vertice			rloader9	= new Vertice(o, -m, -o);
    Vertice			rloader10	= new Vertice(o, m, -o);
    Vertice			rloader11	= new Vertice(l, l, -o);
    Vertice			rloader12	= new Vertice(m, o, -o);

    float			a			= 0f;
    float			b			= 0.09375f;
    float			c			= 0.1875f;
    float			d			= 0.5f;
    float			e			= 0.59375f;
    float			f			= 0.6875f;
    float			g			= 1f;
    float			h			= 0.25f;
    float			i			= 0.75f;

    TextureVertice	l1f			= new TextureVertice(i, f);
    TextureVertice	l1s			= new TextureVertice(i, c);
    TextureVertice	l2			= new TextureVertice(i, b);
    TextureVertice	l3f			= new TextureVertice(i, a);
    TextureVertice	l3s			= new TextureVertice(g, c);
    TextureVertice l4			= new TextureVertice(g, d);
    TextureVertice	l5			= new TextureVertice(g, e);
    TextureVertice	l6f			= new TextureVertice(g, f);
    TextureVertice	l6s			= new TextureVertice(h, g);
    TextureVertice	l7			= new TextureVertice(h, f);
    TextureVertice	l8			= new TextureVertice(h, e);
    TextureVertice	l9			= new TextureVertice(h, d);
    TextureVertice	l10			= new TextureVertice(h, c);
    TextureVertice	l11			= new TextureVertice(h, b);
    TextureVertice	l12f		= new TextureVertice(h, a);
    TextureVertice	l12s		= new TextureVertice(i, g);

    TextureVertice	r1f			= new TextureVertice(g, f);
    TextureVertice	r1s			= new TextureVertice(g, c);
    TextureVertice	r2			= new TextureVertice(g, b);
    TextureVertice	r3f			= new TextureVertice(g, a);
    TextureVertice	r3s			= new TextureVertice(i, c);
    TextureVertice	r4			= new TextureVertice(i, d);
    TextureVertice	r5			= new TextureVertice(i, e);
    TextureVertice	r6f			= new TextureVertice(i, f);
    TextureVertice	r6s			= new TextureVertice(d, g);
    TextureVertice	r7			= new TextureVertice(d, f);
    TextureVertice	r8			= new TextureVertice(d, e);
    TextureVertice	r9			= new TextureVertice(d, d);
    TextureVertice	r10			= new TextureVertice(d, c);
    TextureVertice	r11			= new TextureVertice(d, b);
    TextureVertice	r12f		= new TextureVertice(d, a);
    TextureVertice	r12s		= new TextureVertice(g, g);

    float			p			= 0.125f;
    float			q			= 0.1093755f;
    float			r			= 0.078125f;
    float			k			= 0.34375f;
    float			t			= 0.125f;

    TextureVertice	ls1			= new TextureVertice(t - r, k + p * 2);
    TextureVertice	ls2			= new TextureVertice(t - q, k + q * 2);
    TextureVertice	ls3			= new TextureVertice(t - p, k + r * 2);
    TextureVertice	ls4			= new TextureVertice(t - p, k - r * 2);
    TextureVertice	ls5			= new TextureVertice(t - q, k - q * 2);
    TextureVertice	ls6			= new TextureVertice(t - r, k - p * 2);
    TextureVertice	ls7			= new TextureVertice(t + r, k - p * 2);
    TextureVertice	ls8			= new TextureVertice(t + q, k - q * 2);
    TextureVertice	ls9			= new TextureVertice(t + p, k - r * 2);
    TextureVertice	ls10		= new TextureVertice(t + p, k + r * 2);
    TextureVertice	ls11		= new TextureVertice(t + q, k + q * 2);
    TextureVertice	ls12		= new TextureVertice(t + r, k + p * 2);

    float			u			= 0.625f;

    TextureVertice	rs1			= new TextureVertice(u - r, k + p * 2);
    TextureVertice	rs2			= new TextureVertice(u - q, k + q * 2);
    TextureVertice	rs3			= new TextureVertice(u - p, k + r * 2);
    TextureVertice	rs4			= new TextureVertice(u - p, k - r * 2);
    TextureVertice	rs5			= new TextureVertice(u - q, k - q * 2);
    TextureVertice	rs6			= new TextureVertice(u - r, k - p * 2);
    TextureVertice	rs7			= new TextureVertice(u + r, k - p * 2);
    TextureVertice	rs8			= new TextureVertice(u + q, k - q * 2);
    TextureVertice	rs9			= new TextureVertice(u + p, k - r * 2);
    TextureVertice	rs10		= new TextureVertice(u + p, k + r * 2);
    TextureVertice	rs11		= new TextureVertice(u + q, k + q * 2);
    TextureVertice	rs12		= new TextureVertice(u + r, k + p * 2);

    public void renderA()
    {
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);
        RenderHelper.addFace(lloader1, lloader12, rloader12, rloader1, l1f, l12s, r12s, r1f);
        RenderHelper.addFace(lloader2, lloader1, rloader1, rloader2, l2, l1s, r1s, r2);
        RenderHelper.addFace(lloader3, lloader2, rloader2, rloader3, l3f, l2, r2, r3f);
        RenderHelper.addFace(lloader4, lloader3, rloader3, rloader4, l4, l3s, r3s, r4);
        RenderHelper.addFace(lloader5, lloader4, rloader4, rloader5, l5, l4, r4, r5);
        RenderHelper.addFace(lloader6, lloader5, rloader5, rloader6, l6f, l5, r5, r6f);
        RenderHelper.addFace(lloader7, lloader6, rloader6, rloader7, l7, l6s, r6s, r7);
        RenderHelper.addFace(lloader8, lloader7, rloader7, rloader8, l8, l7, r7, r8);
        RenderHelper.addFace(lloader9, lloader8, rloader8, rloader9, l9, l8, r8, r9);
        RenderHelper.addFace(lloader10, lloader9, rloader9, rloader10, l10, l9, r9, r10);
        RenderHelper.addFace(lloader11, lloader10, rloader10, rloader11, l11, l10, r10, r11);
        RenderHelper.addFace(lloader12, lloader11, rloader11, rloader12, l12f, l11, r11, r12f);
        RenderHelper.addFace(lloader1, lloader2, lloader11, lloader12, ls6, ls5, ls8, ls7);
        RenderHelper.addFace(lloader2, lloader3, lloader10, lloader11, ls5, ls4, ls9, ls8);
        RenderHelper.addFace(lloader3, lloader4, lloader9, lloader10, ls4, ls3, ls10, ls9);
        RenderHelper.addFace(lloader4, lloader5, lloader8, lloader9, ls3, ls2, ls11, ls10);
        RenderHelper.addFace(lloader5, lloader6, lloader7, lloader8, ls2, ls1, ls12, ls11);
        RenderHelper.addFace(rloader2, rloader1, rloader12, rloader11, rs8, rs7, rs6, rs5);
        RenderHelper.addFace(rloader3, rloader2, rloader11, rloader10, rs9, rs8, rs5, rs4);
        RenderHelper.addFace(rloader4, rloader3, rloader10, rloader9, rs10, rs9, rs4, rs3);
        RenderHelper.addFace(rloader5, rloader4, rloader9, rloader8, rs11, rs10, rs3, rs2);
        RenderHelper.addFace(rloader6, rloader5, rloader8, rloader7, rs12, rs11, rs2, rs1);
        GL11.glPopMatrix();
    }

    TextureVertice	front1	= new TextureVertice(70f / 256f, 24f / 128f);
    TextureVertice	front2	= new TextureVertice(70f / 256f, 64f / 128f);
    TextureVertice	front3	= new TextureVertice(122f / 256f, 64f / 128f);
    TextureVertice	front4	= new TextureVertice(122f / 256f, 24f / 128f);
    TextureVertice	front5	= new TextureVertice(70f / 256f, 12f / 128f);
    TextureVertice	front6	= new TextureVertice(122f / 256f, 12f / 128f);

    TextureVertice	panel1	= new TextureVertice(92f / 256f, 28f / 128f);
    TextureVertice	panel2	= new TextureVertice(92f / 256f, 60f / 128f);
    TextureVertice	panel3	= new TextureVertice(120f / 256f, 60f / 128f);
    TextureVertice	panel4	= new TextureVertice(120f / 256f, 28f / 128f);
    TextureVertice	panel5	= new TextureVertice(96f / 256f, 32f / 128f);
    TextureVertice	panel6	= new TextureVertice(96f / 256f, 44f / 128f);
    TextureVertice	panel7	= new TextureVertice(116f / 256f, 44f / 128f);
    TextureVertice	panel8	= new TextureVertice(116f / 256f, 32f / 128f);

    TextureVertice	cside1	= new TextureVertice(4f / 256f, 76f / 128f);
    TextureVertice	cside2	= new TextureVertice(0, 88f / 128f);
    TextureVertice	cside3	= new TextureVertice(0, 1);
    TextureVertice	cside4	= new TextureVertice(60f / 256f, 1);
    TextureVertice	cside5	= new TextureVertice(60f / 256f, 88f / 128f);
    TextureVertice	cside6	= new TextureVertice(60f / 256f, 76f / 128f);

    TextureVertice	ctop1	= new TextureVertice(0.5f, 76f / 128f);
    TextureVertice	ctop2	= new TextureVertice(0.5f, 1);
    TextureVertice	ctop3	= new TextureVertice(192f / 256f, 1);
    TextureVertice	ctop4	= new TextureVertice(192f / 256f, 76f / 128f);

    Vertice			vfront1	= new Vertice(0.5f, 0.3125f, 0.40625f);
    Vertice			vfront2	= new Vertice(0.5f, -0.3125f, 0.40625f);
    Vertice			vfront3	= new Vertice(0.5f, -0.3125f, -0.40625f);
    Vertice			vfront4	= new Vertice(0.5f, 0.3125f, -0.40625f);
    Vertice			vfront5	= new Vertice(0.4375f, 0.4375f, 0.40625f);
    Vertice			vfront6	= new Vertice(0.4375f, 0.4375f, -0.40625f);

    Vertice			vpanel1	= new Vertice(0.5f, 0.25f, 0.0625f);
    Vertice			vpanel2	= new Vertice(0.5f, -0.25f, 0.0625f);
    Vertice			vpanel3	= new Vertice(0.5f, -0.25f, -0.375f);
    Vertice			vpanel4	= new Vertice(0.5f, 0.25f, -0.375f);
    Vertice			vpanel5	= new Vertice(0.6f, 0.1875f, 0f);
    Vertice			vpanel6	= new Vertice(0.6f, 0f, 0f);
    Vertice			vpanel7	= new Vertice(0.6f, 0f, -0.3125f);
    Vertice			vpanel8	= new Vertice(0.6f, 0.1875f, -0.3125f);

    Vertice			vcside1	= new Vertice(-0.4375f, 0.4375f, 0.40625f);
    Vertice			vcside2	= new Vertice(-0.4375f, 0.3125f, 0.40625f);
    Vertice			vcside3	= new Vertice(-0.4375f, -0.3125f, 0.40625f);
    Vertice			vcside4	= new Vertice(-0.4375f, -0.3125f, -0.40625f);
    Vertice			vcside5	= new Vertice(-0.4375f, 0.3125f, -0.40625f);
    Vertice			vcside6	= new Vertice(-0.4375f, 0.4375f, -0.40625f);

    public void renderB(float slide)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef(slide * 0.9f, 0, 0);
        GL11.glDisable(GL11.GL_LIGHTING);
        RenderHelper.addFace(vfront1, vfront2, vfront3, vfront4, front1, front2, front3, front4);
        RenderHelper.addFace(vfront5, vfront1, vfront4, vfront6, front5, front1, front4, front6);
        RenderHelper.addFace(vpanel5, vpanel6, vpanel7, vpanel8, panel5, panel6, panel7, panel8);
        RenderHelper.addFace(vpanel1, vpanel5, vpanel8, vpanel4, panel1, panel5, panel8, panel4);
        RenderHelper.addFace(vpanel1, vpanel2, vpanel6, vpanel5, panel1, panel2, panel6, panel5);
        RenderHelper.addFace(vpanel6, vpanel2, vpanel3, vpanel7, panel6, panel2, panel3, panel7);
        RenderHelper.addFace(vpanel8, vpanel7, vpanel3, vpanel4, panel8, panel7, panel3, panel4);
        RenderHelper.addFace(vfront1, vfront5, vcside1, vcside2, cside2, cside1, cside6, cside5);
        RenderHelper.addFace(vfront6, vfront4, vcside5, vcside6, cside1, cside2, cside5, cside6);
        RenderHelper.addFace(vfront2, vfront1, vcside2, vcside3, cside3, cside2, cside5, cside4);
        RenderHelper.addFace(vfront4, vfront3, vcside4, vcside5, cside2, cside3, cside4, cside5);
        RenderHelper.addFace(vcside1, vfront5, vfront6, vcside6, ctop4, ctop1, ctop2, ctop3);
        RenderHelper.addFace(vfront2, vcside3, vcside4, vfront3, ctop2, ctop3, ctop4, ctop1);
        GL11.glPopMatrix();
    }
}
