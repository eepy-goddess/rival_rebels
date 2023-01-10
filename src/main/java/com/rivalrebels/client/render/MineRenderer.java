package com.rivalrebels.client.render;

import com.rivalrebels.common.tileentity.MineTileEntity;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class MineRenderer extends TileEntitySpecialRenderer<MineTileEntity> {
    @Override
    public void render(MineTileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        renderBlock(te, x, y, z, te.getWorld());
    }
    private void renderBlock(MineTileEntity te, double x, double y, double z, World world){
        IBlockState state = te.getBlockState();
            bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

            GL11.glPushMatrix();
            GL11.glDisable(GL11.GL_LIGHTING);
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder builder = tessellator.getBuffer();
            builder.begin(7, DefaultVertexFormats.BLOCK);
            BlockPos pos = te.getPos();
            GL11.glTranslatef((float) x - (float) pos.getX(), (float) y - (float) pos.getY(), (float) z - (float) pos.getZ());
            BlockRendererDispatcher renderer = Minecraft.getMinecraft().getBlockRendererDispatcher();
            renderer.getBlockModelRenderer().renderModel(world, renderer.getModelForState(state), state, pos, builder, false, MathHelper.getPositionRandom(te.getPos()));
            tessellator.draw();
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glPopMatrix();

    }
}
