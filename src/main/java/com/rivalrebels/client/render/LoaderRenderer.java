package com.rivalrebels.client.render;

import com.rivalrebels.ModInfo;
import com.rivalrebels.client.model.LoaderModel;
import com.rivalrebels.client.oldstuff.AdvancedModelLoader;
import com.rivalrebels.client.oldstuff.IModelCustom;
import com.rivalrebels.common.tileentity.TileLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LoaderRenderer extends TileEntitySpecialRenderer<TileLoader> {
    public LoaderModel loaderModel;
    public IModelCustom tube;
    public static ResourceLocation tube_model_path = new ResourceLocation(ModInfo.modid, "models/obj/loader_tube.obj");
    public static ResourceLocation loader_texture = new ResourceLocation(ModInfo.modid, "textures/models/loader.png");
    public static ResourceLocation loader_tube_texture = new ResourceLocation(ModInfo.modid, "textures/models/loader_tube.png");
    public LoaderRenderer() {
        loaderModel = new LoaderModel();
        tube = AdvancedModelLoader.loadModel(tube_model_path);
    }
    @Override
    public boolean isGlobalRenderer(TileLoader te) {
        return true;
    }

    @Override
    public void render(TileLoader tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        GlStateManager.enableLighting();
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
        Minecraft.getMinecraft().renderEngine.bindTexture(loader_texture);
        int var9 = tile.getBlockMetadata();
        short var11 = 0;
        if (var9 == 0)
        {
            var11 = -90;
        }

        if (var9 == 1)
        {
            var11 = 180;
        }

        if (var9 == 2)
        {
            var11 = -270;
        }

        if (var9 == 3)
        {
            var11 = 0;
        }

        GL11.glRotatef(var11, 0.0F, 1.0F, 0.0F);
        loaderModel.renderA();
        loaderModel.renderB((float) tile.slide);
        GL11.glPopMatrix();
        for (int i = 0; i < tile.machines.getSize(); i++)
        {
            GL11.glPushMatrix();
            GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
            int xdif = tile.machines.get(i).getPos().getX() - tile.getPos().getX();
            int zdif = tile.machines.get(i).getPos().getZ() - tile.getPos().getZ();
            GL11.glRotated(-90 + (Math.atan2(xdif, zdif) / Math.PI) * 180, 0, 1, 0);
            GL11.glTranslatef(-1f, -0.40f, 0);
            Minecraft.getMinecraft().renderEngine.bindTexture(loader_tube_texture);
            GL11.glScaled(0.5, 0.15, 0.15);
            int dist = (int) Math.sqrt((xdif * xdif) + (zdif * zdif));
            for (int d = 0; d < dist; d++)
            {
                GL11.glTranslatef(2, 0, 0);
                tube.renderAll();
            }
            GL11.glPopMatrix();
        }
    }
}
