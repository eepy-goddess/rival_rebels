package com.rivalrebels;

import com.rivalrebels.client.guiLoader.GuiFlamethrower;
import com.rivalrebels.client.render.*;
import com.rivalrebels.common.entity.*;
import com.rivalrebels.common.init.RRItems;
import com.rivalrebels.common.tileentity.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ClientProxy extends CommonProxy{
    @Override
    public void registerRenderStuff(FMLPreInitializationEvent event) {
        OBJLoader.INSTANCE.addDomain(ModInfo.modid);
        RRItems.battery.setTileEntityItemStackRenderer(new RenderBattery());
        RRItems.nuclear_rod.setTileEntityItemStackRenderer(new RenderNuclearRod());
        RRItems.rod.setTileEntityItemStackRenderer(new RenderRod());
        RRItems.redstone_rod.setTileEntityItemStackRenderer(new RenderRedstoneRod());
        RRItems.hydro_rod.setTileEntityItemStackRenderer(new RenderHydrogenRod());
        RRItems.i_loader.setTileEntityItemStackRenderer(new RenderItemLoader());
        RRItems.plasma_cannon.setTileEntityItemStackRenderer(new RenderPlasmaCannon());
        RRItems.rocket.setTileEntityItemStackRenderer(new RenderRocketItem());
        RRItems.fuel.setTileEntityItemStackRenderer(new RenderFuel());
        RRItems.flamethrower.setTileEntityItemStackRenderer(new RenderFlamethrower());
        GameRegistry.registerTileEntity(TileNuke.class, new ResourceLocation(ModInfo.modid, "tile_nuke"));
        ClientRegistry.bindTileEntitySpecialRenderer(TileNuke.class, new RenderNuke());
        GameRegistry.registerTileEntity(TileLoader.class, new ResourceLocation(ModInfo.modid, "tile_loader"));
        ClientRegistry.bindTileEntitySpecialRenderer(TileLoader.class, new LoaderRenderer());
        GameRegistry.registerTileEntity(TileNukeCrate.class, new ResourceLocation(ModInfo.modid, "tile_nuke_crate"));
        ClientRegistry.bindTileEntitySpecialRenderer(TileNukeCrate.class, new NukeCrateRenderer());
        GameRegistry.registerTileEntity(TileEntityPlasmaExplosion.class, new ResourceLocation(ModInfo.modid, "plasma_explosion"));
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPlasmaExplosion.class, new PlasmaExplosionRenderer());
        RenderingRegistry.registerEntityRenderingHandler(EntityPlasmoid.class, RenderPlasmoid::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityDebris.class, RenderDebris::new);
        GameRegistry.registerTileEntity(TileEntitySteel.class, new ResourceLocation(ModInfo.modid, "steel"));
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySteel.class, new SteelRenderer());
        GameRegistry.registerTileEntity(MineTileEntity.class, new ResourceLocation(ModInfo.modid, "mine"));
        ClientRegistry.bindTileEntitySpecialRenderer(MineTileEntity.class, new MineRenderer());
        GameRegistry.registerTileEntity(TileMario.class, new ResourceLocation(ModInfo.modid, "mario"));
        ClientRegistry.bindTileEntitySpecialRenderer(TileMario.class, new RenderMario());
        RenderingRegistry.registerEntityRenderingHandler(EntityCuchillo.class, RenderCuchillo::new);
        GameRegistry.registerTileEntity(TileQuickSand.class, new ResourceLocation(ModInfo.modid, "quicksand"));
        ClientRegistry.bindTileEntitySpecialRenderer(TileQuickSand.class, new RenderQuicksand());
        RenderingRegistry.registerEntityRenderingHandler(EntityFlameBall.class, RenderFlameball::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityFlameBall1.class, RenderFlameBall1::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityFlameBall2.class, RenderFlameBall2::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGasGrenade.class, RenderGasGrenade::new);
    }
    public void registerModel(Item item, int meta, String variant){
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), variant));
    }

    @Override
    public void registerCustomModelName(Item item, String name, int meta, String variant) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(name, variant));
    }

    @Override
    public void closeGui() {
        Minecraft.getMinecraft().displayGuiScreen(null);
    }

    @Override
    public void flamethrowerGui(int i) {
        Minecraft.getMinecraft().displayGuiScreen(new GuiFlamethrower(i));
    }
}
