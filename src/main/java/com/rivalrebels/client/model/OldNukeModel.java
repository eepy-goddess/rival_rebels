package com.rivalrebels.client.model;

import com.rivalrebels.client.oldstuff.Tessellator;
import com.rivalrebels.client.renderhelper.Vertice;
import com.rivalrebels.common.init.RRConfigOptions;
import org.lwjgl.opengl.GL11;

public class OldNukeModel {
    float	s	= 0.5F;
    float	g	= 0.0625F;
    Vertice	v0	= new Vertice(0, g * 22, 0);

    Vertice	v1	= new Vertice(g * 3, g * 19, g * 3);
    Vertice	v2	= new Vertice(g * 3, g * 19, -g * 3);
    Vertice	v3	= new Vertice(-g * 3, g * 19, -g * 3);
    Vertice	v4	= new Vertice(-g * 3, g * 19, g * 3);

    Vertice	v5	= new Vertice(g * 7, g * 7, g * 7);
    Vertice	v6	= new Vertice(g * 7, g * 7, -g * 7);
    Vertice	v7	= new Vertice(-g * 7, g * 7, -g * 7);
    Vertice	v8	= new Vertice(-g * 7, g * 7, g * 7);

    Vertice	v21	= new Vertice(g * 7, -g * 8, g * 7);
    Vertice	v22	= new Vertice(g * 7, -g * 8, -g * 7);
    Vertice	v23	= new Vertice(-g * 7, -g * 8, -g * 7);
    Vertice	v24	= new Vertice(-g * 7, -g * 8, g * 7);

    Vertice	v9	= new Vertice(g * 4, -g * 18, g * 4);
    Vertice	v10	= new Vertice(g * 4, -g * 18, -g * 4);
    Vertice	v11	= new Vertice(-g * 4, -g * 18, -g * 4);
    Vertice	v12	= new Vertice(-g * 4, -g * 18, g * 4);

    Vertice v13	= new Vertice(s, -g * 7, s);
    Vertice	v14	= new Vertice(s, -g * 7, -s);
    Vertice	v15	= new Vertice(-s, -g * 7, -s);
    Vertice	v16	= new Vertice(-s, -g * 7, s);

    Vertice	v17	= new Vertice(s, -g * 24, s);
    Vertice	v18	= new Vertice(s, -g * 24, -s);
    Vertice	v19	= new Vertice(-s, -g * 24, -s);
    Vertice	v20	= new Vertice(-s, -g * 24, s);

    public void renderModel(boolean hasFuse)
    {
        GL11.glPushMatrix();
        GL11.glScalef(RRConfigOptions.nuke_scale, RRConfigOptions.nuke_scale, RRConfigOptions.nuke_scale);
        Tessellator tessellator = Tessellator.instance;
        float itemIcon = 39F;
        float var3 = (itemIcon % 16 * 16 + 0) / 256.0F;
        float var4 = (itemIcon % 16 * 16 + 16) / 256.0F;
        float var5 = (itemIcon / 16 * 16 + 0) / 256.0F;
        float var6 = (itemIcon / 16 * 16 + 16) / 256.0F;

        GL11.glPushMatrix();
        GL11.glScalef(1.01f, 1.01f, 1.01f);
        GL11.glDisable(GL11.GL_LIGHTING);

        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(v2.x, v2.y, v2.z, var3, var6);
        tessellator.addVertexWithUV(v1.x, v1.y, v1.z, var4, var6);
        tessellator.addVertexWithUV(v5.x, v5.y, v5.z, var4, var5);
        tessellator.addVertexWithUV(v6.x, v6.y, v6.z, var3, var5);
        tessellator.addVertexWithUV(v3.x, v3.y, v3.z, var3, var6);
        tessellator.addVertexWithUV(v2.x, v2.y, v2.z, var4, var6);
        tessellator.addVertexWithUV(v6.x, v6.y, v6.z, var4, var5);
        tessellator.addVertexWithUV(v7.x, v7.y, v7.z, var3, var5);
        tessellator.addVertexWithUV(v4.x, v4.y, v4.z, var3, var6);
        tessellator.addVertexWithUV(v3.x, v3.y, v3.z, var4, var6);
        tessellator.addVertexWithUV(v7.x, v7.y, v7.z, var4, var5);
        tessellator.addVertexWithUV(v8.x, v8.y, v8.z, var3, var5);
        tessellator.addVertexWithUV(v1.x, v1.y, v1.z, var3, var6);
        tessellator.addVertexWithUV(v4.x, v4.y, v4.z, var4, var6);
        tessellator.addVertexWithUV(v8.x, v8.y, v8.z, var4, var5);
        tessellator.addVertexWithUV(v5.x, v5.y, v5.z, var3, var5);

        itemIcon = 40;
        var3 = (itemIcon % 16 * 16 + 0) / 256.0F;
        var4 = (itemIcon % 16 * 16 + 16) / 256.0F;
        var5 = (itemIcon / 16 * 16 + 0) / 256.0F;
        var6 = (itemIcon / 16 * 16 + 16) / 256.0F;

        tessellator.addVertexWithUV(v6.x, v6.y, v6.z, var3, var6);
        tessellator.addVertexWithUV(v5.x, v5.y, v5.z, var4, var6);
        tessellator.addVertexWithUV(v21.x, v21.y, v21.z, var4, var5);
        tessellator.addVertexWithUV(v22.x, v22.y, v22.z, var3, var5);
        tessellator.addVertexWithUV(v7.x, v7.y, v7.z, var3, var6);
        tessellator.addVertexWithUV(v6.x, v6.y, v6.z, var4, var6);
        tessellator.addVertexWithUV(v22.x, v22.y, v22.z, var4, var5);
        tessellator.addVertexWithUV(v23.x, v23.y, v23.z, var3, var5);
        tessellator.addVertexWithUV(v8.x, v8.y, v8.z, var3, var6);
        tessellator.addVertexWithUV(v7.x, v7.y, v7.z, var4, var6);
        tessellator.addVertexWithUV(v23.x, v23.y, v23.z, var4, var5);
        tessellator.addVertexWithUV(v24.x, v24.y, v24.z, var3, var5);
        tessellator.addVertexWithUV(v5.x, v5.y, v5.z, var3, var6);
        tessellator.addVertexWithUV(v8.x, v8.y, v8.z, var4, var6);
        tessellator.addVertexWithUV(v24.x, v24.y, v24.z, var4, var5);
        tessellator.addVertexWithUV(v21.x, v21.y, v21.z, var3, var5);
        tessellator.addVertexWithUV(v22.x, v22.y, v22.z, var3, var6);
        tessellator.addVertexWithUV(v21.x, v21.y, v21.z, var4, var6);
        tessellator.addVertexWithUV(v9.x, v9.y, v9.z, var4, var5);
        tessellator.addVertexWithUV(v10.x, v10.y, v10.z, var3, var5);
        tessellator.addVertexWithUV(v23.x, v23.y, v23.z, var3, var6);
        tessellator.addVertexWithUV(v22.x, v22.y, v22.z, var4, var6);
        tessellator.addVertexWithUV(v10.x, v10.y, v10.z, var4, var5);
        tessellator.addVertexWithUV(v11.x, v11.y, v11.z, var3, var5);
        tessellator.addVertexWithUV(v24.x, v24.y, v24.z, var3, var6);
        tessellator.addVertexWithUV(v23.x, v23.y, v23.z, var4, var6);
        tessellator.addVertexWithUV(v11.x, v11.y, v11.z, var4, var5);
        tessellator.addVertexWithUV(v12.x, v12.y, v12.z, var3, var5);
        tessellator.addVertexWithUV(v21.x, v21.y, v21.z, var3, var6);
        tessellator.addVertexWithUV(v24.x, v24.y, v24.z, var4, var6);
        tessellator.addVertexWithUV(v12.x, v12.y, v12.z, var4, var5);
        tessellator.addVertexWithUV(v9.x, v9.y, v9.z, var3, var5);

        itemIcon = 38;
        var3 = (itemIcon % 16 * 16 + 0) / 256.0F;
        var4 = (itemIcon % 16 * 16 + 16) / 256.0F;
        var5 = (itemIcon / 16 * 16 + 0) / 256.0F;
        var6 = (itemIcon / 16 * 16 + 16) / 256.0F;

        tessellator.addVertexWithUV(v10.x, v10.y, v10.z, var3, var6);
        tessellator.addVertexWithUV(v9.x, v9.y, v9.z, var4, var6);
        tessellator.addVertexWithUV(v12.x, v12.y, v12.z, var4, var5);
        tessellator.addVertexWithUV(v11.x, v11.y, v11.z, var3, var5);

        itemIcon = 41;
        var3 = (itemIcon % 16 * 16 + 0) / 256.0F;
        var4 = (itemIcon % 16 * 16 + 16) / 256.0F;
        var5 = (itemIcon / 16 * 16 + 0) / 256.0F;
        var6 = (itemIcon / 16 * 16 + 16) / 256.0F;
        float o = 0.999F;

        tessellator.addVertexWithUV(v13.x * o, v13.y, v13.z * o, var3, var6);
        tessellator.addVertexWithUV(v14.x * o, v14.y, v14.z * o, var4, var6);
        tessellator.addVertexWithUV(v18.x * o, v18.y, v18.z * o, var4, var5);
        tessellator.addVertexWithUV(v17.x * o, v17.y, v17.z * o, var3, var5);
        tessellator.addVertexWithUV(v14.x * o, v14.y, v14.z * o, var3, var6);
        tessellator.addVertexWithUV(v15.x * o, v15.y, v15.z * o, var4, var6);
        tessellator.addVertexWithUV(v19.x * o, v19.y, v19.z * o, var4, var5);
        tessellator.addVertexWithUV(v18.x * o, v18.y, v18.z * o, var3, var5);
        tessellator.addVertexWithUV(v15.x * o, v15.y, v15.z * o, var3, var6);
        tessellator.addVertexWithUV(v16.x * o, v16.y, v16.z * o, var4, var6);
        tessellator.addVertexWithUV(v20.x * o, v20.y, v20.z * o, var4, var5);
        tessellator.addVertexWithUV(v19.x * o, v19.y, v19.z * o, var3, var5);
        tessellator.addVertexWithUV(v16.x * o, v16.y, v16.z * o, var3, var6);
        tessellator.addVertexWithUV(v13.x * o, v13.y, v13.z * o, var4, var6);
        tessellator.addVertexWithUV(v17.x * o, v17.y, v17.z * o, var4, var5);
        tessellator.addVertexWithUV(v20.x * o, v20.y, v20.z * o, var3, var5);
        tessellator.addVertexWithUV(v14.x, v14.y, v14.z, var3, var6);
        tessellator.addVertexWithUV(v13.x, v13.y, v13.z, var4, var6);
        tessellator.addVertexWithUV(v17.x, v17.y, v17.z, var4, var5);
        tessellator.addVertexWithUV(v18.x, v18.y, v18.z, var3, var5);
        tessellator.addVertexWithUV(v15.x, v15.y, v15.z, var3, var6);
        tessellator.addVertexWithUV(v14.x, v14.y, v14.z, var4, var6);
        tessellator.addVertexWithUV(v18.x, v18.y, v18.z, var4, var5);
        tessellator.addVertexWithUV(v19.x, v19.y, v19.z, var3, var5);
        tessellator.addVertexWithUV(v16.x, v16.y, v16.z, var3, var6);
        tessellator.addVertexWithUV(v15.x, v15.y, v15.z, var4, var6);
        tessellator.addVertexWithUV(v19.x, v19.y, v19.z, var4, var5);
        tessellator.addVertexWithUV(v20.x, v20.y, v20.z, var3, var5);
        tessellator.addVertexWithUV(v13.x, v13.y, v13.z, var3, var6);
        tessellator.addVertexWithUV(v16.x, v16.y, v16.z, var4, var6);
        tessellator.addVertexWithUV(v20.x, v20.y, v20.z, var4, var5);
        tessellator.addVertexWithUV(v17.x, v17.y, v17.z, var3, var5);

        itemIcon = 42;
        var3 = (itemIcon % 16 * 16 + 0) / 256.0F;
        var4 = (itemIcon % 16 * 16 + 16) / 256.0F;
        var5 = (itemIcon / 16 * 16 + 0) / 256.0F;
        var6 = (itemIcon / 16 * 16 + 16) / 256.0F;

        tessellator.addVertexWithUV(v13.x, v13.y, v13.z, var3, var6);
        tessellator.addVertexWithUV(v15.x, v15.y, v15.z, var4, var6);
        tessellator.addVertexWithUV(v19.x, v19.y, v19.z, var4, var5);
        tessellator.addVertexWithUV(v17.x, v17.y, v17.z, var3, var5);
        tessellator.addVertexWithUV(v16.x, v16.y, v16.z, var3, var6);
        tessellator.addVertexWithUV(v14.x, v14.y, v14.z, var4, var6);
        tessellator.addVertexWithUV(v18.x, v18.y, v18.z, var4, var5);
        tessellator.addVertexWithUV(v20.x, v20.y, v20.z, var3, var5);
        tessellator.addVertexWithUV(v15.x, v15.y, v15.z, var3, var6);
        tessellator.addVertexWithUV(v13.x, v13.y, v13.z, var4, var6);
        tessellator.addVertexWithUV(v17.x, v17.y, v17.z, var4, var5);
        tessellator.addVertexWithUV(v19.x, v19.y, v19.z, var3, var5);
        tessellator.addVertexWithUV(v14.x, v14.y, v14.z, var3, var6);
        tessellator.addVertexWithUV(v16.x, v16.y, v16.z, var4, var6);
        tessellator.addVertexWithUV(v20.x, v20.y, v20.z, var4, var5);
        tessellator.addVertexWithUV(v18.x, v18.y, v18.z, var3, var5);
        if (!hasFuse)
        {
            itemIcon = 37;
            var3 = (itemIcon % 16 * 16 + 0) / 256.0F;
            var4 = (itemIcon % 16 * 16 + 16) / 256.0F;
            var5 = (itemIcon / 16 * 16 + 0) / 256.0F;
            var6 = (itemIcon / 16 * 16 + 16) / 256.0F;

            tessellator.addVertexWithUV(v1.x, v1.y, v1.z, var3, var6);
            tessellator.addVertexWithUV(v2.x, v2.y, v2.z, var4, var6);
            tessellator.addVertexWithUV(v3.x, v3.y, v3.z, var4, var5);
            tessellator.addVertexWithUV(v4.x, v4.y, v4.z, var3, var5);
            tessellator.draw();
        }
        else
        {
            tessellator.draw();
            itemIcon = 43;
            var3 = (itemIcon % 16 * 16 + 0) / 256.0F;
            var4 = (itemIcon % 16 * 16 + 16) / 256.0F;
            var5 = (itemIcon / 16 * 16 + 0) / 256.0F;
            var6 = (itemIcon / 16 * 16 + 16) / 256.0F;

            tessellator.startDrawing(GL11.GL_TRIANGLES);
            tessellator.addVertexWithUV(v0.x, v0.y, v0.z, var3, var6);
            tessellator.addVertexWithUV(v1.x, v1.y, v1.z, var4, var6);
            tessellator.addVertexWithUV(v2.x, v2.y, v2.z, var4, var5);
            tessellator.addVertexWithUV(v0.x, v0.y, v0.z, var3, var6);
            tessellator.addVertexWithUV(v2.x, v2.y, v2.z, var4, var5);
            tessellator.addVertexWithUV(v3.x, v3.y, v3.z, var4, var6);
            tessellator.addVertexWithUV(v0.x, v0.y, v0.z, var3, var6);
            tessellator.addVertexWithUV(v3.x, v3.y, v3.z, var3, var5);
            tessellator.addVertexWithUV(v4.x, v4.y, v4.z, var4, var5);
            tessellator.addVertexWithUV(v0.x, v0.y, v0.z, var3, var6);
            tessellator.addVertexWithUV(v4.x, v4.y, v4.z, var4, var5);
            tessellator.addVertexWithUV(v1.x, v1.y, v1.z, var4, var6);
            tessellator.draw();
        }
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }
}
