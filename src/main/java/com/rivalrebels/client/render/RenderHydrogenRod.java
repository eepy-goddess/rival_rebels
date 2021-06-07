package com.rivalrebels.client.render;

import com.rivalrebels.RivalRebels;
import com.rivalrebels.client.model.RodModel;
import com.rivalrebels.client.renderhelper.ItemRenderBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderHydrogenRod extends ItemRenderBase {
    public RodModel md;
    public static ResourceLocation texture = new ResourceLocation(RivalRebels.modid, "textures/models/hydro_rod.png");
    public RenderHydrogenRod(){
        md = new RodModel();
    }

    @Override
    public void renderByItem(ItemStack item) {
        if(type != ItemCameraTransforms.TransformType.GUI){
            Minecraft.getMinecraft().renderEngine.bindTexture(texture);
            GlStateManager.enableLighting();
            GL11.glPushMatrix();
            GlStateManager.disableCull();
            GL11.glTranslatef(0.5f, 0.5f, 0.4f);
            GL11.glRotatef(35, 0.0F, 0.0F, 1.0F);
            GL11.glScalef(0.5f, 1.25f, 0.5f);
            GL11.glPushMatrix();
            md.render();
            GL11.glPopMatrix();
            GL11.glPopMatrix();
        } else {
            GL11.glPushMatrix();
            GL11.glTranslated(0.5, 0.5, 0.5);
            Minecraft.getMinecraft().getRenderItem().renderItem(item, model);
            GL11.glPopMatrix();
        }
    }
}
