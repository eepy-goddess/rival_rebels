package com.rivalrebels.client.render;

import com.rivalrebels.ModInfo;
import com.rivalrebels.client.model.LoaderModel;
import com.rivalrebels.client.renderhelper.ItemRenderBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderItemLoader extends ItemRenderBase {
    public LoaderModel ml;
    public static ResourceLocation texture = new ResourceLocation(ModInfo.modid, "textures/models/loader.png");
    public RenderItemLoader(){
        ml = new LoaderModel();
    }

    @Override
    public void renderByItem(ItemStack item) {
        if(type != ItemCameraTransforms.TransformType.GUI) {
            GlStateManager.enableLighting();
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0F, 0.05F, 0.0F);
            Minecraft.getMinecraft().renderEngine.bindTexture(texture);
            ml.renderA();
            ml.renderB(0);
            GL11.glPopMatrix();
        } else {
            GL11.glPushMatrix();
            GL11.glTranslated(0.5, 0.5, 0.5);
            Minecraft.getMinecraft().getRenderItem().renderItem(item, baked_model);
            GL11.glPopMatrix();
        }
    }
}
