package com.rivalrebels.client.render;

import com.rivalrebels.RivalRebels;
import com.rivalrebels.client.model.ModelSteel;
import com.rivalrebels.common.tileentity.TileEntitySteel;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class SteelRenderer extends TileEntitySpecialRenderer<TileEntitySteel> {
    public ModelSteel model = new ModelSteel();
    public static ResourceLocation texture = new ResourceLocation(RivalRebels.modid, "textures/blocks/steel.png");
    BlockRendererDispatcher renderer = Minecraft.getMinecraft().getBlockRendererDispatcher();
    @Override
    public void render(TileEntitySteel te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        GL11.glEnable(GL11.GL_LIGHTING);
        BlockRendererDispatcher blockrenderer = Minecraft.getMinecraft().getBlockRendererDispatcher();

        World world = te.getWorld();

        if (te.getBlockType().getDefaultState() != null && te.getBlockType().getDefaultState().getRenderType() == EnumBlockRenderType.MODEL)
        {
            IBlockState block = te.getBlockType().getDefaultState();
            this.bindTexture(texture);
            GL11.glPushMatrix();
            GL11.glDisable(GL11.GL_LIGHTING);
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder buffer = tessellator.getBuffer();

            buffer.begin(7, DefaultVertexFormats.BLOCK);
            BlockPos pos = new BlockPos(te.getPos().getZ(), te.getRenderBoundingBox().maxY, te.getPos().getZ());
            GL11.glTranslated((float)(x - (double)pos.getX() - 0.5D), (float)(y - (double)pos.getY()), (float)(z - (double)pos.getZ() - 0.5D));
            IBakedModel model = Minecraft.getMinecraft().getBlockRendererDispatcher().getModelForState(block);
            blockrenderer.getBlockModelRenderer().renderModel(world, model, block, pos, buffer, true, MathHelper.getPositionRandom(te.getPos()));
            tessellator.draw();
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glPopMatrix();
        }
    }
}
