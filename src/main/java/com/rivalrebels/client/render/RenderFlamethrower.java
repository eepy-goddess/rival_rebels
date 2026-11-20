package com.rivalrebels.client.render;

import com.rivalrebels.ModInfo;
import com.rivalrebels.client.oldstuff.AdvancedModelLoader;
import com.rivalrebels.client.oldstuff.IModelCustom;
import com.rivalrebels.client.renderhelper.ItemRenderBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderFlamethrower extends ItemRenderBase {
    public static ResourceLocation texture = new ResourceLocation(ModInfo.modid, "textures/models/flamethrower.png");
    public static ResourceLocation modelLoc = new ResourceLocation(ModInfo.modid, "models/obj/flamethrower.obj");
    public IModelCustom modell = AdvancedModelLoader.loadModel(modelLoc);
    @Override
    public void renderByItem(ItemStack itemStackIn) {
        if(type == ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND || type == ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND){
            Minecraft.getMinecraft().renderEngine.bindTexture(texture);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glPushMatrix(); 


            // Good enough transforms
            GL11.glRotatef(35, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.7f, 0.1f, 0.2f);
            GL11.glRotatef(265, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(89, 1.0f, 0.0f, 0.0f);
            GL11.glRotatef(167, 0.0f, 0.0f, 1.0f);
            GL11.glScalef(0.18f, 0.18f, 0.18f);
            GL11.glTranslatef(-2.1f, -0.2f, 0.2f);
            GL11.glRotatef(-24, 1.0f, 0.0f, 0.0f);


            GL11.glTranslatef(0.6f, -0.1f, -0.5f);
            GL11.glRotatef(2.0f, 1.0f, 0.0f, 0.0f);
            GL11.glRotatef(1.0f, 0, 1, 0);
            GL11.glRotatef(6.0f, 0, 0, 1);

            ModelDebugTransform.apply();


            

            //Testing
            /* 
            GL11.glRotatef(35, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.7f, 0.1f, 0.2f);
            GL11.glRotatef(270, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(90, 1.0f, 0.0f, 0.0f);
            GL11.glRotatef(180, 0.0f, 0.0f, 1.0f);
            GL11.glScalef(0.18f, 0.18f, 0.18f);
            GL11.glTranslatef(-2.1f, -0.2f, -0.2f);
            GL11.glRotatef(-24, 1.0f, 0.0f, 0.0f);
            */


            modell.renderAll();

            GL11.glPopMatrix();

        }
        else if (type == ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND || type == ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND) {
            Minecraft.getMinecraft().renderEngine.bindTexture(texture);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glPushMatrix(); //  x+ is up in fpv
            GL11.glTranslatef(0.5f, 0.1f, 0.2f);
            GL11.glScalef(0.3f, 0.3f, 0.3f);

            modell.renderAll();

            GL11.glPopMatrix();
        
        } else if (type == ItemCameraTransforms.TransformType.GUI) {
            GL11.glPushMatrix();
            GL11.glTranslatef(0.5f, 0.5f, 0.5f);
            Minecraft.getMinecraft().getRenderItem().renderItem(itemStackIn, baked_model);
            GL11.glPopMatrix();
        } else {
            Minecraft.getMinecraft().renderEngine.bindTexture(texture);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glPushMatrix();
            GL11.glRotatef(35, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.7f, 0.1f, 0.2f);
            GL11.glRotatef(270, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(90, 1.0f, 0.0f, 0.0f);
            GL11.glRotatef(180, 0.0f, 0.0f, 1.0f);
            GL11.glScalef(0.18f, 0.18f, 0.18f);
            // GL11.glTranslatef(0.3f, 0.05f, -0.1f);

            modell.renderAll();

            GL11.glPopMatrix();
        }
    }
}
