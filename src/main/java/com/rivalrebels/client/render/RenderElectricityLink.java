package com.rivalrebels.client.render;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.rivalrebels.common.entity.EntityElectricityLink;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

public class RenderElectricityLink extends Render<EntityElectricityLink> {

    static float red = 0.65F;
    static float green = 0.75F;
    static float blue = 1.0F;
    public RenderElectricityLink(RenderManager renderManager) {
        super(renderManager);
    }

    public void renderElectricityLink(EntityElectricityLink ell, double x, double y, double z, float yaw, float pitch) {
        float segmentDistance = 2; //supposed to be configable: RivalRebels.teslasegments
        float distance = (float)ell.motionX * 100.0F;
        distance = 100.0F;
        if (distance > 0.0F) {
            Random rand = new Random(ell.randLong);
            float radius = 0.07F;
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder buffer = tessellator.getBuffer();
            GL11.glPushMatrix();
            GL11.glEnable(32826);
            GL11.glDisable(3553);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 1);
            GL11.glTranslatef((float)x, (float)y, (float)z);
            GL11.glRotatef(ell.rotationYaw, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-ell.rotationPitch, 1.0F, 0.0F, 0.0F);
            double AddedX = 0.0;
            double AddedY = 0.0;
            double prevAddedX = 0.0;
            double prevAddedY = 0.0;

            for (int addedZ = (int)distance; addedZ >= 0; addedZ = (int)(addedZ - segmentDistance)) {
                prevAddedX = AddedX;
                prevAddedY = AddedY;
                AddedX += (rand.nextFloat() - 0.5) * 2.0;
                AddedY += (rand.nextFloat() - 0.5) * 2.0;
                double dist = Math.sqrt(AddedX * AddedX + AddedY * AddedY) / 1.5;
                if (dist != 0.0) {
                double tempAddedX = AddedX / dist;
                double tempAddedY = AddedY / dist;
                if (Math.abs(tempAddedX) < Math.abs(AddedX)) {
                    AddedX = tempAddedX;
                }

                if (Math.abs(tempAddedY) < Math.abs(AddedY)) {
                    AddedY = tempAddedY;
                }
                }

                if (addedZ <= 0) {
                AddedY = 0.0;
                AddedX = 0.0;
                }

                for (float o = 0.0F; o <= radius; o += radius / 8.0F) {
                    buffer.begin(7, DefaultVertexFormats.POSITION_COLOR);


                    buffer.pos(AddedX + o, AddedY - o, addedZ)
                        .color(red, green, blue, 0.95F)
                        .endVertex();
                    buffer.pos(AddedX + o, AddedY + o, addedZ)
                        .color(red, green, blue, 0.95F)
                        .endVertex();
                    buffer.pos(prevAddedX + o, prevAddedY + o, addedZ + segmentDistance)
                        .color(red, green, blue, 0.95F)
                        .endVertex();
                    buffer.pos(prevAddedX + o, prevAddedY - o, addedZ + segmentDistance)
                        .color(red, green, blue, 0.95F)
                        .endVertex();


                    buffer.pos(AddedX - o, AddedY - o, addedZ)
                        .color(red, green, blue, 0.95F)
                        .endVertex();
                    buffer.pos(AddedX + o, AddedY - o, addedZ)
                        .color(red, green, blue, 0.95F)
                        .endVertex();
                    buffer.pos(prevAddedX + o, prevAddedY - o, addedZ + segmentDistance)
                        .color(red, green, blue, 0.95F)
                        .endVertex();
                    buffer.pos(prevAddedX - o, prevAddedY - o, addedZ + segmentDistance)
                        .color(red, green, blue, 0.95F)
                        .endVertex();


                    buffer.pos(AddedX - o, AddedY + o, addedZ)
                        .color(red, green, blue, 0.95F)
                        .endVertex();
                    buffer.pos(AddedX - o, AddedY - o, addedZ)
                        .color(red, green, blue, 0.95F)
                        .endVertex();
                    buffer.pos(prevAddedX - o, prevAddedY - o, addedZ + segmentDistance)
                        .color(red, green, blue, 0.95F)
                        .endVertex();
                    buffer.pos(prevAddedX - o, prevAddedY + o, addedZ + segmentDistance)
                        .color(red, green, blue, 0.95F)
                        .endVertex();



                    buffer.pos(AddedX + o, AddedY + o, addedZ)
                        .color(red, green, blue, 0.95F)
                        .endVertex();
                    buffer.pos(AddedX - o, AddedY + o, addedZ)
                        .color(red, green, blue, 0.95F)
                        .endVertex();
                    buffer.pos(prevAddedX - o, prevAddedY + o, addedZ + segmentDistance)
                        .color(red, green, blue, 0.95F)
                        .endVertex();
                    buffer.pos(prevAddedX + o, prevAddedY + o, addedZ + segmentDistance)
                        .color(red, green, blue, 0.95F)
                        .endVertex();

                    
                    tessellator.draw();
                }
            }

            GL11.glDisable(3042);
            GL11.glEnable(3553);
            GL11.glDisable(32826);
            GL11.glPopMatrix();
        }
    }

    public void doRender(EntityElectricityLink entityElectricityLink, double x, double y, double z, float yaw, float pitch) {
        this.renderElectricityLink((EntityElectricityLink)entityElectricityLink, x, y, z, yaw, pitch);
    }

    protected ResourceLocation getEntityTexture(EntityElectricityLink entity) {
        return null;
    }
    
}
