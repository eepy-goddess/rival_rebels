package com.rivalrebels.client.render.items;

import org.lwjgl.opengl.GL11;

import com.rivalrebels.ModInfo;
import com.rivalrebels.client.oldstuff.AdvancedModelLoader;
import com.rivalrebels.client.oldstuff.IModelCustom;
import com.rivalrebels.client.renderhelper.ItemRenderBase;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RenderTesla extends ItemRenderBase {
    int spin = 0;

    public static ResourceLocation texture = new ResourceLocation(ModInfo.modid, "textures/models/tesla.png");
    public static ResourceLocation teslaModelLoc = new ResourceLocation(ModInfo.modid, "models/obj/tesla.obj");
    public static ResourceLocation dynamoModelLoc = new ResourceLocation(ModInfo.modid, "models/obj/dynamo.obj");

    public IModelCustom teslaModell = AdvancedModelLoader.loadModel(teslaModelLoc);
    public IModelCustom dynamol = AdvancedModelLoader.loadModel(dynamoModelLoc);


    public boolean handleRenderType(ItemStack item, TransformType type) {
        return type == TransformType.FIRST_PERSON_RIGHT_HAND
            || type == TransformType.FIRST_PERSON_LEFT_HAND
            || type == TransformType.GROUND;
    }

    public boolean shouldUseRenderHelper(TransformType type, ItemStack item, TransformType helper) {
        return false;
    }

    public int getDegree(ItemStack item) {
        return (!item.hasTagCompound()) ? 0 : item.getTagCompound().getInteger("dial") + 90;
    }

    @Override
    //ItemStack type, ItemStack item, Object... data
    public void renderByItem(ItemStack item) {
        if (!item.isItemEnchanted() || 2==2) {

            if (type == ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND
                || type == ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND
            ) {

                Minecraft.getMinecraft().renderEngine.bindTexture(texture);
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glPushMatrix();


                int degree = this.getDegree(item);
                this.spin = (int)(this.spin + (5.0F + degree / 36.0F));

                GL11.glScalef(0.12F, 0.12F, 0.12F);


                this.teslaModell.renderAll();
                // This will be not oriented in the hand correctly, TODO: find the correct json coordinates the model


                GL11.glRotatef(this.spin, 1.0F, 0.0F, 0.0F);
                this.dynamol.renderAll();
                GL11.glPopMatrix();
            } else if (type == ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND || type == ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND) {
                Minecraft.getMinecraft().renderEngine.bindTexture(texture);
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glPushMatrix();


                int degree = this.getDegree(item);
                this.spin = (int)(this.spin + (5.0F + degree / 36.0F));
                GL11.glTranslatef(0.5f, 0.1f, 0.2f);
                GL11.glRotatef(180, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(0.12F, 0.12F, 0.12F);
                this.teslaModell.renderAll();


                GL11.glRotatef(this.spin, 1.0F, 0.0F, 0.0F);
                this.dynamol.renderAll();
                GL11.glPopMatrix();
            } else if (type == ItemCameraTransforms.TransformType.GUI) {
                GL11.glPushMatrix();
                GL11.glTranslatef(0.5f, 0.5f, 0.5f);
                Minecraft.getMinecraft().getRenderItem().renderItem(item, baked_model);
                GL11.glPopMatrix();
            } else if (type == ItemCameraTransforms.TransformType.GROUND){
                Minecraft.getMinecraft().renderEngine.bindTexture(texture);
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glPushMatrix();

                GL11.glScalef(0.12F, 0.12F, 0.12F);


                int degree = this.getDegree(item);
                this.spin = (int)(this.spin + (5.0F + degree / 36.0F));
                this.teslaModell.renderAll();


                GL11.glRotatef(this.spin, 1.0F, 0.0F, 0.0F);
                this.dynamol.renderAll();
                GL11.glPopMatrix();
                
            }


        } else {
            if (type != ItemCameraTransforms.TransformType.GROUND) {
                GL11.glPopMatrix();
            }

            /*
            GL11.glPushMatrix();
            Tessellator t = Tessellator.getInstance();
            GL11.glBindTexture(
                3553, TileEntityForceFieldNodeRenderer.id[(int)(TileEntityForceFieldNodeRenderer.getTime() / 100L % TileEntityForceFieldNodeRenderer.frames)]
            );
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glScalef(1.01F, 1.01F, 1.01F);
            GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScalef(0.6F, 0.2F, 0.2F);
            GL11.glTranslatef(-0.99F, 0.5F, 0.0F);
            GL11.glBlendFunc(770, 1);
            GL11.glDisable(2896);
            GL11.glEnable(2884);
            t.startDrawingQuads();
            t.addVertexWithUV(-1.0, -1.0, -1.0, 0.0, 0.0);
            t.addVertexWithUV(-1.0, 1.0, -1.0, 1.0, 0.0);
            t.addVertexWithUV(-1.0, 1.0, 1.0, 1.0, 1.0);
            t.addVertexWithUV(-1.0, -1.0, 1.0, 0.0, 1.0);
            t.addVertexWithUV(1.0, -1.0, -1.0, 0.0, 0.0);
            t.addVertexWithUV(1.0, -1.0, 1.0, 0.0, 1.0);
            t.addVertexWithUV(1.0, 1.0, 1.0, 1.0, 1.0);
            t.addVertexWithUV(1.0, 1.0, -1.0, 1.0, 0.0);
            t.addVertexWithUV(-1.0, -1.0, -1.0, 0.0, 0.0);
            t.addVertexWithUV(-1.0, -1.0, 1.0, 0.0, 1.0);
            t.addVertexWithUV(1.0, -1.0, 1.0, 3.0, 1.0);
            t.addVertexWithUV(1.0, -1.0, -1.0, 3.0, 0.0);
            t.addVertexWithUV(-1.0, 1.0, -1.0, 0.0, 0.0);
            t.addVertexWithUV(1.0, 1.0, -1.0, 3.0, 0.0);
            t.addVertexWithUV(1.0, 1.0, 1.0, 3.0, 1.0);
            t.addVertexWithUV(-1.0, 1.0, 1.0, 0.0, 1.0);
            t.addVertexWithUV(-1.0, -1.0, -1.0, 0.0, 0.0);
            t.addVertexWithUV(1.0, -1.0, -1.0, 3.0, 0.0);
            t.addVertexWithUV(1.0, 1.0, -1.0, 3.0, 1.0);
            t.addVertexWithUV(-1.0, 1.0, -1.0, 0.0, 1.0);
            t.addVertexWithUV(-1.0, -1.0, 1.0, 0.0, 0.0);
            t.addVertexWithUV(-1.0, 1.0, 1.0, 0.0, 1.0);
            t.addVertexWithUV(1.0, 1.0, 1.0, 3.0, 1.0);
            t.addVertexWithUV(1.0, -1.0, 1.0, 3.0, 0.0);
            t.addVertexWithUV(-1.0, -1.0, -1.0, 0.0, 0.0);
            t.addVertexWithUV(-1.0, 1.0, -1.0, 1.0, 0.0);
            t.addVertexWithUV(-1.0, 1.0, 1.0, 1.0, 1.0);
            t.addVertexWithUV(-1.0, -1.0, 1.0, 0.0, 1.0);
            t.addVertexWithUV(1.0, -1.0, -1.0, 0.0, 0.0);
            t.addVertexWithUV(1.0, -1.0, 1.0, 0.0, 1.0);
            t.addVertexWithUV(1.0, 1.0, 1.0, 1.0, 1.0);
            t.addVertexWithUV(1.0, 1.0, -1.0, 1.0, 0.0);
            t.addVertexWithUV(-1.0, -1.0, -1.0, 0.0, 0.0);
            t.addVertexWithUV(-1.0, -1.0, 1.0, 0.0, 1.0);
            t.addVertexWithUV(1.0, -1.0, 1.0, 3.0, 1.0);
            t.addVertexWithUV(1.0, -1.0, -1.0, 3.0, 0.0);
            t.addVertexWithUV(-1.0, 1.0, -1.0, 0.0, 0.0);
            t.addVertexWithUV(1.0, 1.0, -1.0, 3.0, 0.0);
            t.addVertexWithUV(1.0, 1.0, 1.0, 3.0, 1.0);
            t.addVertexWithUV(-1.0, 1.0, 1.0, 0.0, 1.0);
            t.addVertexWithUV(-1.0, -1.0, -1.0, 0.0, 0.0);
            t.addVertexWithUV(1.0, -1.0, -1.0, 3.0, 0.0);
            t.addVertexWithUV(1.0, 1.0, -1.0, 3.0, 1.0);
            t.addVertexWithUV(-1.0, 1.0, -1.0, 0.0, 1.0);
            t.addVertexWithUV(-1.0, -1.0, 1.0, 0.0, 0.0);
            t.addVertexWithUV(-1.0, 1.0, 1.0, 0.0, 1.0);
            t.addVertexWithUV(1.0, 1.0, 1.0, 3.0, 1.0);
            t.addVertexWithUV(1.0, -1.0, 1.0, 3.0, 0.0);
            t.draw();
            GL11.glDisable(3042);
            GL11.glEnable(2896);
            GL11.glDisable(2884);
            GL11.glPopMatrix();
            if (type != ItemCameraTransforms.TransformType.GROUND) {
                GL11.glPushMatrix();
            }
             */
        }
    }
}
