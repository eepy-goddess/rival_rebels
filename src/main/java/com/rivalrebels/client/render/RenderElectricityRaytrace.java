package com.rivalrebels.client.render;

import com.rivalrebels.common.entity.EntityElectricityRaytrace;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderElectricityRaytrace extends Render<EntityElectricityRaytrace> {

    public RenderElectricityRaytrace(RenderManager renderManager) {
        super(renderManager);
    }

    @Override
    public void doRender(EntityElectricityRaytrace entity, double x, double y, double z,
                         float entityYaw, float partialTicks) {
        // Intentionally render nothing.
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityElectricityRaytrace entity) {
        return null;
    }
}