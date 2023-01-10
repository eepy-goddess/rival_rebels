package com.rivalrebels.client.render;

import com.rivalrebels.ModInfo;
import com.rivalrebels.client.oldstuff.AdvancedModelLoader;
import com.rivalrebels.client.oldstuff.IModelCustom;
import com.rivalrebels.client.renderhelper.ItemRenderBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderBattery extends ItemRenderBase {
    public static IModelCustom battery;
    public static ResourceLocation texture = new ResourceLocation(ModInfo.modid, "textures/models/battery.png");
    public RenderBattery() {
        battery = AdvancedModelLoader.loadModel(new ResourceLocation(ModInfo.modid, "models/obj/battery.obj"));
    }

    @Override
    public void renderByItem(ItemStack item) {
        if(type != ItemCameraTransforms.TransformType.GUI && type != ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND){
            Minecraft.getMinecraft().renderEngine.bindTexture(texture);
            GlStateManager.enableLighting();
            GL11.glPushMatrix();
            GL11.glTranslatef(0.5f, 0.5f, 0.5f);
            GL11.glRotatef(35, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(90, 0.0F, 1.0F, 0.0F);
            GL11.glScalef(0.3f, 0.3f, 0.3f);

            battery.renderAll();

            GL11.glPopMatrix();
        } else if(type == ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND) {
            Minecraft.getMinecraft().renderEngine.bindTexture(texture);
            GL11.glPushMatrix();
            GL11.glTranslatef(0.5f, 0.5f, 0.5f);
            GL11.glRotatef(-90, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(180, 0.0F, 1.0F, 0.0F);
            GL11.glScalef(0.3f, 0.3f, 0.3f);
            battery.renderAll();
            GL11.glPopMatrix();
        }
        else{
            GL11.glPushMatrix();
            GL11.glTranslated(0.5, 0.5, 0.5);
            Minecraft.getMinecraft().getRenderItem().renderItem(item, baked_model);
            GL11.glPopMatrix();
        }
    }
}
