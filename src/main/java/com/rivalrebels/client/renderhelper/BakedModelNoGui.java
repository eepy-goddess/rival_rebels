package com.rivalrebels.client.renderhelper;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.tuple.Pair;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import javax.vecmath.Matrix4f;
import java.util.Collections;
import java.util.List;

public class BakedModelNoGui implements IBakedModel {
    private ItemRenderBase renderer;

    public BakedModelNoGui(ItemRenderBase renderer) {
        this.renderer = renderer;
    }

    @Override
    public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) {
        return renderer.type == TransformType.GUI ? Collections.emptyList() : renderer.baked_model.getQuads(state, side, rand);
    }

    @Override
    public boolean isAmbientOcclusion() {
        return renderer.type != ItemCameraTransforms.TransformType.GUI ? false : renderer.baked_model.isAmbientOcclusion();
    }

    @Override
    public boolean isGui3d() {
        return false;
    }

    @Override
    public boolean isBuiltInRenderer() {
        return true;
    }

    @Override
    public TextureAtlasSprite getParticleTexture() {
        return renderer.baked_model.getParticleTexture();
    }

    @Override
    public ItemOverrideList getOverrides() {
        return renderer.type != TransformType.GUI ? new ItemOverrideList(Collections.emptyList()){
            @Override
            public IBakedModel handleItemState(IBakedModel originalModel, ItemStack stack, World world, EntityLivingBase entity) {
                renderer.entity = entity;
                renderer.world = world;
                return super.handleItemState(originalModel, stack, world, entity);
            }
        } : renderer.baked_model.getOverrides();
    }
    @Override
    public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType) {
        renderer.type = cameraTransformType;
        return Pair.of(this, renderer.baked_model.handlePerspective(cameraTransformType).getRight());
    }
}
