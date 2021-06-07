package com.rivalrebels.client.render;

import com.rivalrebels.client.model.ModelBlastSphere;
import com.rivalrebels.common.tileentity.TileEntityPlasmaExplosion;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import org.lwjgl.opengl.GL11;

public class PlasmaExplosionRenderer extends TileEntitySpecialRenderer<TileEntityPlasmaExplosion> {
    public ModelBlastSphere model = new ModelBlastSphere();
    @Override
    public void render(TileEntityPlasmaExplosion tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        float fsize = (float) Math.sin(tile.size);
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);

        GL11.glPushMatrix();
        GL11.glRotatef(tile.size * 50, 0f, 1, 0f);
        model.renderModel(fsize * 5.5f, 0.45f, 0.45f, 0.65f, 0.4f);
        GL11.glRotatef(tile.size * 50, 0f, 1, 0f);
        model.renderModel(fsize * 5.6f, 0.45f, 0.35f, 0.65f, 0.4f);
        GL11.glRotatef(tile.size * 50, 0f, 1, 0f);
        model.renderModel(fsize * 5.7f, 0.45f, 0.35f, 0.95f, 0.4f);
        GL11.glRotatef(tile.size * 50, 0f, 1, 0f);
        model.renderModel(fsize * 5.8f, 0.45f, 0.35f, 0.65f, 0.4f);
        GL11.glPopMatrix();
        model.renderModel(fsize * 5.9f, 0.45f, 0.35f, 0.65f, 0.4f);
        GL11.glPopMatrix();
    }
}
