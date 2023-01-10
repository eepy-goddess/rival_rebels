package com.rivalrebels.client.render;

import com.rivalrebels.ModInfo;
import com.rivalrebels.client.oldstuff.Tessellator;
import com.rivalrebels.common.entity.EntityFlameBall1;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;

import javax.annotation.Nullable;

public class RenderFlameBall1 extends Render<EntityFlameBall1> {
    public static ResourceLocation texture = new ResourceLocation(ModInfo.modid, "textures/entity/flame_reblu.png");
    public RenderFlameBall1(RenderManager renderManager) {
        super(renderManager);
    }

    @Override
    public void doRender(EntityFlameBall1 ell, double x, double y, double z, float entityYaw, float partialTicks) {
        if (ell.ticksExisted < 3) return;
        GL11.glPushMatrix();
        GL11.glDepthMask(false);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_BLEND);
        // GL11.glBlendEquationSeparate(GL14.GL_FUNC_ADD, GL14.GL_FUNC_ADD);
        GL14.glBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        GL14.glBlendEquation(GL14.GL_FUNC_ADD);
        GL11.glColor4f(1f, 1f, 1f, 1f);
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);

        GL11.glPushMatrix();
        float X = (ell.sequence % 4) / 4f;
        float Y = (ell.sequence - (ell.sequence % 4)) / 16f;
        float size = 0.0550f * ell.ticksExisted;
        size *= size;
        // if (size >= 0.5) size = 0.5f;
        size += 0.05;
        Tessellator t = Tessellator.instance;
        GL11.glTranslated(x, y, z);
        GL11.glRotatef(180 - Minecraft.getMinecraft().player.rotationYaw, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(90 - Minecraft.getMinecraft().player.rotationPitch, 1.0F, 0.0F, 0.0F);
        GL11.glPushMatrix();
        GL11.glRotatef(ell.rotation, 0.0F, 1.0F, 0.0F);
        net.minecraft.client.renderer.Tessellator.getInstance().getBuffer().begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);
        t.setNormal(0.0F, 1.0F, 0.0F);
        t.addVertexWithUV(-size, 0, -size, X, Y);
        t.addVertexWithUV(size, 0, -size, X + 0.25f, Y);
        t.addVertexWithUV(size, 0, size, X + 0.25f, Y + 0.25f);
        t.addVertexWithUV(-size, 0, size, X, Y + 0.25f);
        t.draw();
        GL11.glPopMatrix();
        GL11.glPopMatrix();

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDepthMask(true);
        GL11.glPopMatrix();
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityFlameBall1 entity) {
        return null;
    }
}
