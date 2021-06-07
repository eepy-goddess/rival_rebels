package com.rivalrebels.client.render;

import com.rivalrebels.RivalRebels;
import com.rivalrebels.client.model.OldNukeModel;
import com.rivalrebels.client.oldstuff.AdvancedModelLoader;
import com.rivalrebels.client.oldstuff.IModelCustom;
import com.rivalrebels.common.tileentity.TileNuke;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderNuke extends TileEntitySpecialRenderer<TileNuke> {
    //Fragments of the past UwU
    public static ResourceLocation texture = new ResourceLocation(RivalRebels.modid, "textures/models/wack_nuke.png");
    public static ResourceLocation model_loc = new ResourceLocation(RivalRebels.modid, "models/obj/wacknuke.obj");
    public static ResourceLocation old_nuke_texture = new ResourceLocation(RivalRebels.modid, "textures/models/old_nuke.png");
    public static IModelCustom model = AdvancedModelLoader.loadModel(model_loc);
    public OldNukeModel md;
    public RenderNuke(){
        md = new OldNukeModel();
    }
    @Override
    public boolean isGlobalRenderer(TileNuke te) {
        return true;
    }
    @Override
    public void render(TileNuke te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5, y + 0.5, z + 0.5);
        GlStateManager.enableLighting();
        GlStateManager.enableCull();
        int meta = te.getBlockMetadata();
        if (meta == 0)
        {
            GL11.glRotatef(90, 1, 0, 0);
        }

        if (meta == 1)
        {
            GL11.glRotatef(-90, 1, 0, 0);
        }

        if (meta == 2)
        {
            GL11.glRotatef(180, 0, 1, 0);
        }

        if (meta == 3)
        {
            GL11.glRotatef(0, 0, 1, 0);
        }

        if (meta == 4)
        {
            GL11.glRotatef(-90, 0, 1, 0);
        }

        if (meta == 5)
        {
            GL11.glRotatef(90, 0, 1, 0);
        }

        if(RivalRebels.render_old_nuke){
            this.bindTexture(old_nuke_texture);
            GL11.glRotatef(90F, 1.0F, 0, 0);
            md.renderModel(false);
        } else if(!RivalRebels.render_old_nuke) {
            this.bindTexture(texture);
            model.renderAll();
        }
        GlStateManager.enableCull();
        GL11.glPopMatrix();
    }



}
