package com.rivalrebels;

import com.rivalrebels.client.oldstuff.AdvancedModelLoader;
import com.rivalrebels.client.oldstuff.IModelCustom;
import com.rivalrebels.client.oldstuff.IModelCustomLoader;
import com.rivalrebels.client.render.*;
import com.rivalrebels.client.renderhelper.ItemRenderBase;
import com.rivalrebels.common.entity.EntityCuchillo;
import com.rivalrebels.common.entity.EntityDebris;
import com.rivalrebels.common.entity.EntityPlasmoid;
import com.rivalrebels.common.tileentity.*;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.client.model.obj.OBJModel;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Map;

public class ClientProxy extends CommonProxy{
    @Override
    public void registerRenderStuff(FMLPreInitializationEvent event) {
        OBJLoader.INSTANCE.addDomain(RivalRebels.modid);
        RivalRebels.battery.setTileEntityItemStackRenderer(new RenderBattery());
        RivalRebels.nuclear_rod.setTileEntityItemStackRenderer(new RenderNuclearRod());
        RivalRebels.rod.setTileEntityItemStackRenderer(new RenderRod());
        RivalRebels.redstone_rod.setTileEntityItemStackRenderer(new RenderRedstoneRod());
        RivalRebels.hydro_rod.setTileEntityItemStackRenderer(new RenderHydrogenRod());
        RivalRebels.iLoader.setTileEntityItemStackRenderer(new RenderItemLoader());
        RivalRebels.plasma_cannon.setTileEntityItemStackRenderer(new RenderPlasmaCannon());
        RivalRebels.rocket.setTileEntityItemStackRenderer(new RenderRocketItem());
        RivalRebels.fuel.setTileEntityItemStackRenderer(new RenderFuel());
        GameRegistry.registerTileEntity(TileNuke.class, new ResourceLocation(RivalRebels.modid, "tile_nuke"));
        ClientRegistry.bindTileEntitySpecialRenderer(TileNuke.class, new RenderNuke());
        GameRegistry.registerTileEntity(TileLoader.class, new ResourceLocation(RivalRebels.modid, "tile_loader"));
        ClientRegistry.bindTileEntitySpecialRenderer(TileLoader.class, new LoaderRenderer());
        GameRegistry.registerTileEntity(TileNukeCrate.class, new ResourceLocation(RivalRebels.modid, "tile_nuke_crate"));
        ClientRegistry.bindTileEntitySpecialRenderer(TileNukeCrate.class, new NukeCrateRenderer());
        GameRegistry.registerTileEntity(TileEntityPlasmaExplosion.class, new ResourceLocation(RivalRebels.modid, "plasma_explosion"));
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPlasmaExplosion.class, new PlasmaExplosionRenderer());
        RenderingRegistry.registerEntityRenderingHandler(EntityPlasmoid.class, RenderPlasmoid::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityDebris.class, RenderDebris::new);
        GameRegistry.registerTileEntity(TileEntitySteel.class, new ResourceLocation(RivalRebels.modid, "steel"));
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySteel.class, new SteelRenderer());
        GameRegistry.registerTileEntity(MineTileEntity.class, new ResourceLocation(RivalRebels.modid, "mine"));
        ClientRegistry.bindTileEntitySpecialRenderer(MineTileEntity.class, new MineRenderer());
        GameRegistry.registerTileEntity(TileMario.class, new ResourceLocation(RivalRebels.modid, "mario"));
        ClientRegistry.bindTileEntitySpecialRenderer(TileMario.class, new RenderMario());
        RenderingRegistry.registerEntityRenderingHandler(EntityCuchillo.class, RenderCuchillo::new);
    }
    public void registerModel(Item item, int meta, String variant){
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), variant));
    }

    @Override
    public void registerCustomModelName(Item item, String name, int meta, String variant) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(name, variant));
    }
}
