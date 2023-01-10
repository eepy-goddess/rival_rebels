package com.rivalrebels.client.render;

import com.rivalrebels.ModInfo;
import com.rivalrebels.client.model.ModelSteel;
import com.rivalrebels.common.tileentity.TileEntitySteel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class SteelRenderer extends TileEntitySpecialRenderer<TileEntitySteel> {
    public ModelSteel model = new ModelSteel();
    public static ResourceLocation texture = new ResourceLocation(ModInfo.modid, "textures/blocks/steel.png");
    BlockRendererDispatcher renderer = Minecraft.getMinecraft().getBlockRendererDispatcher();
    @Override
    public void render(TileEntitySteel te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5f, (float) y + 0.5f, (float) z + 0.5f);
        model.renderModel();
        GL11.glPopMatrix();
    }
}
