package com.rivalrebels.client.render;

import com.rivalrebels.client.oldstuff.AdvancedModelLoader;
import com.rivalrebels.client.oldstuff.IModelCustom;
import com.rivalrebels.client.renderhelper.ItemRenderBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import com.rivalrebels.RivalRebels;
import org.lwjgl.opengl.GL11;

public class RenderFuel extends ItemRenderBase {
    public static ResourceLocation texture = new ResourceLocation(RivalRebels.modid, "textures/models/flamethrower.png");
    public static ResourceLocation modelloc = new ResourceLocation(RivalRebels.modid, "models/obj/fuel.obj");
    public IModelCustom modell;
    public RenderFuel(){
        modell = AdvancedModelLoader.loadModel(modelloc);
    }

    @Override
    public void renderByItem(ItemStack itemStackIn) {
        if(type != ItemCameraTransforms.TransformType.GUI){
            Minecraft.getMinecraft().renderEngine.bindTexture(texture);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glPushMatrix();
            GL11.glTranslatef(0.5f, 0.5f, 0.5f);
            // GL11.glRotatef(35, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(-90, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(160, 0.0F, 0.0F, 1.0F);
            GL11.glScalef(0.15f, 0.15f, 0.15f);
            // GL11.glTranslatef(0.3f, 0.05f, -0.1f);

            modell.renderAll();

            GL11.glPopMatrix();
        } else{
            GL11.glPushMatrix();
            GL11.glTranslatef(0.5f, 0.5f, 0.5f);
            Minecraft.getMinecraft().getRenderItem().renderItem(itemStackIn, model);
            GL11.glPopMatrix();
        }
    }
}
