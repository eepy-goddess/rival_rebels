package com.rivalrebels.client.render;

import com.rivalrebels.RivalRebels;
import com.rivalrebels.client.model.RodModel;
import com.rivalrebels.client.oldstuff.AdvancedModelLoader;
import com.rivalrebels.client.oldstuff.IModelCustom;
import com.rivalrebels.client.renderhelper.ItemRenderBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderPlasmaCannon extends ItemRenderBase {
    RodModel md2;
    RodModel md3;
    public IModelCustom modelCannon;
    public static ResourceLocation texture = new ResourceLocation(RivalRebels.modid, "textures/models/plasma_cannon.png");
    public RenderPlasmaCannon(){
        md2 = new RodModel();
        md2.rendersecondcap = false;
        md3 = new RodModel();
        modelCannon = AdvancedModelLoader.loadModel(new ResourceLocation(RivalRebels.modid, "models/item/obj/plasma_cannon.obj"));
    }
    @Override
    public void renderByItem(ItemStack itemStackIn) {
        if(type != ItemCameraTransforms.TransformType.GUI) {
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glPushMatrix();
            GL11.glTranslatef(-0.1f, 0f, 0f);
            Minecraft.getMinecraft().renderEngine.bindTexture(texture);
            GL11.glPushMatrix();
            if(type == ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND) {
                GL11.glTranslatef(0.5f, 0.2f, -0.03f);
                GL11.glRotatef(35, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(180f, 0, 1, 0);
                GL11.glRotatef(90f, 0, 0, 1);
            }
                GL11.glScalef(0.03125f, 0.03125f, 0.03125f);

            GL11.glPushMatrix();

            modelCannon.renderAll();

            GL11.glPopMatrix();
            GL11.glPopMatrix();

            Minecraft.getMinecraft().renderEngine.bindTexture(RenderHydrogenRod.texture);
            GL11.glPushMatrix();
            if(type == ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND) {
                GL11.glTranslatef(0.5f, 0.2f, -0.03f);
                GL11.glRotatef(35, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(180f, 0, 1, 0);
                GL11.glRotatef(90f, 0, 0, 1);
            }
            GL11.glPushMatrix();
            GL11.glRotatef(225, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(-0.5f, 0.5f, 0.0f);
            GL11.glScalef(0.25f, 0.5f, 0.25f);
            md2.render();
            GL11.glPopMatrix();
            GL11.glPopMatrix();

            GL11.glPushMatrix();
            if(type == ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND) {
                GL11.glTranslatef(0.5f, 0.2f, -0.03f);
                GL11.glRotatef(35, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(180f, 0, 1, 0);
                GL11.glRotatef(90f, 0, 0, 1);
            }
            GL11.glPushMatrix();
            GL11.glRotatef(247.5f, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(-0.175f, 0.1f, 0.0f);
            GL11.glScalef(0.25f, 0.5f, 0.25f);
            md3.render();
            GL11.glPopMatrix();
            GL11.glPopMatrix();
            GL11.glPopMatrix();
        } else{
            GL11.glPushMatrix();
            GL11.glTranslatef(0.5F, 0.5F, 0.5F);
            Minecraft.getMinecraft().getRenderItem().renderItem(itemStackIn, this.model);
            GL11.glPopMatrix();
        }
    }
}
