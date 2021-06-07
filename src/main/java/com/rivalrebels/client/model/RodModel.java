package com.rivalrebels.client.model;


import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.rivalrebels.client.renderhelper.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.model.BakedItemModel;
import net.minecraftforge.client.model.obj.OBJModel;
import net.minecraftforge.common.model.TRSRTransformation;
import net.minecraft.client.renderer.GlStateManager;import javax.annotation.Nullable;
import java.util.List;

public class RodModel{
    public boolean	rendersecondcap	= true;

    int				numOfSegs		= 16;
    float			deg				= (float) Math.PI * 2 / numOfSegs;
    TextureVertice	t1				= new TextureVertice(0.03125f * 2, 0.03125f * 0);
    TextureVertice	t2				= new TextureVertice(0.03125f * 1, 0.03125f * 3);
    TextureVertice	t3				= new TextureVertice(0.03125f * 3, 0.03125f * 3);
    TextureVertice	t4				= new TextureVertice(0.03125f * 0, 0.03125f * 6);
    TextureVertice	t5				= new TextureVertice(0.03125f * 4, 0.03125f * 6);
    TextureVertice	t6				= new TextureVertice(0.03125f * 1, 0.03125f * 7);
    TextureVertice	t7				= new TextureVertice(0.03125f * 3, 0.03125f * 7);
    TextureVertice	t8				= new TextureVertice(0.03125f * 1, 0.03125f * 19);
    TextureVertice	t9				= new TextureVertice(0.03125f * 3, 0.03125f * 19);

    Vertice			v0				= new Vertice(0f, 0.03125f * 10, 0f);
    Vertice			v1				= new Vertice(0.03125f * 8, 0.03125f * 9, 0f);
    Vertice			v2				= new Vertice(0.03125f * 9, 0.03125f * 6, 0f);
    Vertice v3				= new Vertice(0.03125f * 8, 0.03125f * 5, 0f);
    Vertice			v4				= new Vertice(0.03125f * 8, 0.03125f * -5, 0f);
    Vertice			v5				= new Vertice(0.03125f * 9, 0.03125f * -6, 0f);
    Vertice			v6				= new Vertice(0.03125f * 8, 0.03125f * -9, 0f);
    Vertice			v7				= new Vertice(0f, 0.03125f * -10, 0f);

    Vertice			vd1				= new Vertice(0.03125f * 8 * (float) Math.cos(deg), 0.03125f * 9, 0.03125f * 8 * (float) Math.sin(deg));
    Vertice			vd2				= new Vertice(0.03125f * 9 * (float) Math.cos(deg), 0.03125f * 6, 0.03125f * 9 * (float) Math.sin(deg));
    Vertice			vd3				= new Vertice(0.03125f * 8 * (float) Math.cos(deg), 0.03125f * 5, 0.03125f * 8 * (float) Math.sin(deg));
    Vertice			vd4				= new Vertice(0.03125f * 8 * (float) Math.cos(deg), 0.03125f * -5, 0.03125f * 8 * (float) Math.sin(deg));
    Vertice			vd5				= new Vertice(0.03125f * 9 * (float) Math.cos(deg), 0.03125f * -6, 0.03125f * 9 * (float) Math.sin(deg));
    Vertice			vd6				= new Vertice(0.03125f * 8 * (float) Math.cos(deg), 0.03125f * -9, 0.03125f * 8 * (float) Math.sin(deg));



    public void render()
    {
        for (float i = 0; i < 360; i += 360 / numOfSegs)
        {
            GlStateManager.pushMatrix();
            {
                GlStateManager.disableLighting();
                GlStateManager.rotate(i, 0, 1, 0);
                RenderHelper.addFace(v0, vd1, v1, v0, t1, t3, t2, t1);
                RenderHelper.addFace(vd1, vd2, v2, v1, t2, t4, t5, t3);
                RenderHelper.addFace(vd2, vd3, v3, v2, t4, t6, t7, t5);
                RenderHelper.addFace(vd3, vd4, v4, v3, t6, t8, t9, t7);
                if (rendersecondcap) {
                    RenderHelper.addFace(v7, v6, vd6, v7, t1, t3, t2, t1);
                    RenderHelper.addFace(v6, v5, vd5, vd6, t2, t4, t5, t3);
                    RenderHelper.addFace(v5, v4, vd4, vd5, t4, t6, t7, t5);
                }
                GlStateManager.popMatrix();
            }
        }
    }


}
