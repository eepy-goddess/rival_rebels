package com.rivalrebels.client.render;

import com.rivalrebels.common.entity.EntityDebris;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nullable;

public class RenderDebris extends Render<EntityDebris> {
    public RenderDebris(RenderManager renderManager) {
        super(renderManager);
        shadowSize = 0.5F;
    }

    @Override
    public void doRender(EntityDebris entity, double x, double y, double z, float entityYaw, float partialTicks) {
        IBlockState state = entity.block;
        if(state != null) {
            bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
            GL11.glPushMatrix();
            GL11.glDisable(GL11.GL_LIGHTING);
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder builder = tessellator.getBuffer();
            builder.begin(7, DefaultVertexFormats.BLOCK);
            BlockPos pos = new BlockPos(entity.posX, entity.posY, entity.posZ);
            GL11.glTranslatef((float) x - (float) pos.getX(), (float) y - (float) pos.getY(), (float) z - (float) pos.getZ());
            BlockRendererDispatcher renderer = Minecraft.getMinecraft().getBlockRendererDispatcher();
            renderer.getBlockModelRenderer().renderModel(entity.world, renderer.getModelForState(state), state, pos, builder, false, MathHelper.getPositionRandom(pos));
            tessellator.draw();
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glPopMatrix();
        }
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityDebris entity) {
        return TextureMap.LOCATION_BLOCKS_TEXTURE;
    }
}
