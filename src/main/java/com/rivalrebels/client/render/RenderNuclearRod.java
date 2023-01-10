package com.rivalrebels.client.render;

import com.rivalrebels.ModInfo;
import com.rivalrebels.client.model.RodModel;
import com.rivalrebels.client.renderhelper.ItemRenderBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderNuclearRod extends ItemRenderBase {
    public final RodModel md;
    public static ResourceLocation texture = new ResourceLocation(ModInfo.modid, "textures/models/nuclear_rod.png");

    public RenderNuclearRod(){
        md = new RodModel();
    }

    @Override
    public void renderByItem(ItemStack itemStackIn) {
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
            Minecraft.getMinecraft().getRenderItem().renderItem(itemStackIn, baked_model);
            GL11.glPopMatrix();
        }
    }
}
