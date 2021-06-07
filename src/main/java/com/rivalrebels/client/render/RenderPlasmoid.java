package com.rivalrebels.client.render;

import com.rivalrebels.client.model.ModelBlastSphere;
import com.rivalrebels.common.entity.EntityPlasmoid;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nullable;

public class RenderPlasmoid extends Render<EntityPlasmoid> {
    public ModelBlastSphere model = new ModelBlastSphere();
    public RenderPlasmoid(RenderManager renderManager) {
        super(renderManager);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityPlasmoid entity) {
        return null;
    }

    @Override
    public void doRender(EntityPlasmoid entity, double x, double y, double z, float entityYaw, float partialTicks) {
        renderPlasmoid(entity, x, y, z, entityYaw, partialTicks);
    }
    public void renderPlasmoid(EntityPlasmoid e, double x, double y, double z, float yaw, float ticks){
        GL11.glPushMatrix();
        GL11.glTranslated(x, y, z);
        GL11.glRotatef(e.rotationYaw - 90.0f, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(e.rotationPitch - 90.0f, 0.0F, 0.0F, 1.0F);
        GL11.glScalef(0.4f, 2.5f, 0.4f);
        GL11.glPushMatrix();

        GL11.glRotatef(e.rotation, 0.0F, 1.0F, 0.0F);
        model.renderModel(0.4f, 0.65f, 0.55f, 0.95f, 0.9f);
        GL11.glPushMatrix();
        GL11.glRotatef(e.rotation, 0.0F, 1.0F, 0.0F);
        model.renderModel(0.6f, 0.65f, 0.55f, 0.95f, 0.9f);
        GL11.glPushMatrix();
        GL11.glRotatef(e.rotation, 0.0F, 1.0F, 0.0F);
        model.renderModel(0.8f, 0.65f, 0.55f, 0.95f, 0.9f);
        GL11.glPushMatrix();
        GL11.glRotatef(e.rotation, 0.0F, 1.0F, 0.0F);
        model.renderModel(1f, 0.65f, 0.55f, 0.95f, 0.9f);
        GL11.glPushMatrix();
        GL11.glRotatef(e.rotation, 0.0F, 1.0F, 0.0F);
        model.renderModel(1.2f, 0.65f, 0.55f, 0.95f, 0.9f);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
        GL11.glPopMatrix();
        GL11.glPopMatrix();
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }
}
