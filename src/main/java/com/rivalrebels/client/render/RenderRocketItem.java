package com.rivalrebels.client.render;

import com.rivalrebels.RivalRebels;
import com.rivalrebels.client.model.ModelRocket;
import com.rivalrebels.client.renderhelper.ItemRenderBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderRocketItem extends ItemRenderBase {
    ModelRocket rock;
    public static ResourceLocation texture = new ResourceLocation(RivalRebels.modid, "textures/models/rocket.png");
    public RenderRocketItem(){
        rock = new ModelRocket();
    }
    @Override
    public void renderByItem(ItemStack itemStackIn) {
        if(type != ItemCameraTransforms.TransformType.GUI){
            GL11.glEnable(GL11.GL_LIGHTING);
            Minecraft.getMinecraft().renderEngine.bindTexture(texture);
            GL11.glPushMatrix();
            GL11.glTranslatef(0.7f, 0.5f, 0.5f);
            GL11.glScaled(2, 2, 2);

            rock.render(true);

            GL11.glPopMatrix();
        } else{
            GL11.glPushMatrix();
            GL11.glTranslatef(0.5f, 0.5f, 0.5f);
            Minecraft.getMinecraft().getRenderItem().renderItem(itemStackIn, model);
            GL11.glPopMatrix();
        }
    }
}
