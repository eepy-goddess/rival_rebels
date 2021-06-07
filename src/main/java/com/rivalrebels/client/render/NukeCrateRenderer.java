package com.rivalrebels.client.render;

import com.rivalrebels.RivalRebels;
import com.rivalrebels.client.model.NukeCrateModel;
import com.rivalrebels.common.blocks.NukeCrate;
import com.rivalrebels.common.tileentity.TileNukeCrate;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.opengl.GL11;

public class NukeCrateRenderer extends TileEntitySpecialRenderer<TileNukeCrate> {
    public static ResourceLocation texture_top = new ResourceLocation(RivalRebels.modid, "textures/blocks/nuke_crate_top.png");
    public static ResourceLocation texture_bottom = new ResourceLocation(RivalRebels.modid, "textures/blocks/nuke_crate_bottom.png");
    public static ResourceLocation texture_crate = new ResourceLocation(RivalRebels.modid, "textures/blocks/crate.png");
    public NukeCrateModel model;
    public NukeCrateRenderer(){
        model = new NukeCrateModel();
    }
    @Override
    public void render(TileNukeCrate tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
        int metadata = tile.getBlockMetadata();
        if (metadata == 0)
        {
            GL11.glRotatef(180, 1, 0, 0);
        }

        if (metadata == 2)
        {
            GL11.glRotatef(-90, 1, 0, 0);
        }

        if (metadata == 3)
        {
            GL11.glRotatef(90, 1, 0, 0);
        }

        if (metadata == 4)
        {
            GL11.glRotatef(90, 0, 0, 1);
        }

        if (metadata == 5)
        {
            GL11.glRotatef(-90, 0, 0, 1);
        }
        if (tile.getWorld().getBlockState(tile.getPos()) == RivalRebels.nukeCrateBottom.getDefaultState().withProperty(NukeCrate.meta, tile.getBlockMetadata())) Minecraft.getMinecraft().renderEngine.bindTexture(texture_bottom);
        if (tile.getWorld().getBlockState(tile.getPos()) == RivalRebels.nukeCrateTop.getDefaultState().withProperty(NukeCrate.meta, tile.getBlockMetadata())) Minecraft.getMinecraft().renderEngine.bindTexture(texture_top);
        model.renderModelA();
        GL11.glPushMatrix();
        Minecraft.getMinecraft().renderEngine.bindTexture(texture_crate);
        model.renderModelB();
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }
}
