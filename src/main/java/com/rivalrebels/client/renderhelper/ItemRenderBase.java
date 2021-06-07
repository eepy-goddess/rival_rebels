package com.rivalrebels.client.renderhelper;

import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoaderRegistry;

public class ItemRenderBase extends TileEntityItemStackRenderer {
    public IBakedModel model;
    public ItemCameraTransforms.TransformType type;
    //the variables below can be null
    public EntityLivingBase entity;
    public World world;

}
