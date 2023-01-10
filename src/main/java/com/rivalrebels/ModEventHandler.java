package com.rivalrebels;

import com.rivalrebels.client.render.*;
import com.rivalrebels.client.renderhelper.BakedModelNoGui;
import com.rivalrebels.client.renderhelper.ItemRenderBase;
import com.rivalrebels.common.blocks.RRBlock;
import com.rivalrebels.common.blocks.Reactive;
import com.rivalrebels.common.init.RRBlocks;
import com.rivalrebels.common.init.RRItems;
import com.rivalrebels.common.init.RRSounds;
import com.rivalrebels.common.items.IHasModel;
import com.rivalrebels.common.items.RRItem;
import com.rivalrebels.common.items.RRItemBlock;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.registry.IRegistry;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Objects;

@SuppressWarnings(value = "unused")
@Mod.EventBusSubscriber(modid = ModInfo.modid)
public class ModEventHandler {

    @SuppressWarnings(value = "unused")
    @SubscribeEvent
    public static void itemRegisterStuff(RegistryEvent.Register<Item> event) {
        for(Item item : RRItem.itemList){
            if(item != null){
                event.getRegistry().register(item);
            }
        }

        for(Item item : RivalRebels.other_items){
            if(item != null){
                event.getRegistry().register(item);
            }
        }

        for(Block block : RRBlock.list){
            RRBlock blockIn = (RRBlock) block;
            if(blockIn != null) {
                event.getRegistry().register(new RRItemBlock(blockIn, blockIn.getUnlocalizedName().substring(5))
                        .setModelName(Objects.requireNonNull(blockIn.getRegistryName()).toString())
                        .setCreativeTab(blockIn.getCreativeTabToDisplayOn()));
            }
        }
        event.getRegistry().registerAll(RRItems.i_loader, RRItems.i_mine);
    }

    @SuppressWarnings(value = "unused")
    @SubscribeEvent
    public static void blockRegisterStuff(RegistryEvent.Register<Block> gameRegistry) {
        for(Block block : RRBlock.list){
            RRBlock rrBlocks = (RRBlock) block;
            if(rrBlocks != null){
                gameRegistry.getRegistry().register(rrBlocks);
            }
        }

        for(Block block : RivalRebels.other_blocks){
            if(block != null){
                gameRegistry.getRegistry().register(block);
            }
        }
    }

    @SuppressWarnings(value = "unused")
    @SubscribeEvent
    public static void modelStuff(ModelRegistryEvent event) {
        for(Item item : RRItem.itemList){
            RRItem rrItem = (RRItem) item;
            if(rrItem != null){
                RivalRebels.proxy.registerCustomModelName(rrItem, rrItem.getModelName(), 0, "inventory");
            }
        }
        for(Item item : RRItemBlock.items){
            RRItemBlock rrItem = (RRItemBlock) item;
            if(rrItem != null){
                RivalRebels.proxy.registerCustomModelName(rrItem, rrItem.getModelName(), 0, "inventory");
            }
        }
        for(Item item : RivalRebels.model_items){
            if(item instanceof IHasModel){
                RivalRebels.proxy.registerCustomModelName(item, ((IHasModel) item).getModelName(), 0, "inventory");
            }
        }
    }

    @SuppressWarnings(value = "unused")
    @SubscribeEvent
    public static void moreModelStuff(ModelBakeEvent event){
        IBakedModel model = event.getModelRegistry().getObject(new ModelResourceLocation(ModInfo.modid + ":" + RRItems.rod.getRegistryName(), "inventory"));

        TileEntityItemStackRenderer rod_renderer = RRItems.rod.getTileEntityItemStackRenderer();
        if (rod_renderer instanceof RenderRod) {
            rod_renderer.renderByItem(new ItemStack(RRItems.rod));
            swapModelsNoGui(RRItems.rod, event.getModelRegistry());
        }

        TileEntityItemStackRenderer battery_renderer = RRItems.battery.getTileEntityItemStackRenderer();
        if (battery_renderer instanceof RenderBattery) {
            battery_renderer.renderByItem(new ItemStack(RRItems.battery));
            swapModelsNoGui(RRItems.battery, event.getModelRegistry());
        }

        TileEntityItemStackRenderer nuclear_rod_renderer = RRItems.nuclear_rod.getTileEntityItemStackRenderer();
        if (nuclear_rod_renderer instanceof RenderNuclearRod) {
            nuclear_rod_renderer.renderByItem(new ItemStack(RRItems.nuclear_rod));
            swapModelsNoGui(RRItems.nuclear_rod, event.getModelRegistry());
        }

        TileEntityItemStackRenderer redstone_rod_renderer = RRItems.redstone_rod.getTileEntityItemStackRenderer();
        if (redstone_rod_renderer instanceof RenderRedstoneRod) {
            redstone_rod_renderer.renderByItem(new ItemStack(RRItems.redstone_rod));
            swapModelsNoGui(RRItems.redstone_rod, event.getModelRegistry());
        }

        TileEntityItemStackRenderer hydro_render = RRItems.hydro_rod.getTileEntityItemStackRenderer();
        if (hydro_render instanceof RenderHydrogenRod) {
            hydro_render.renderByItem(new ItemStack(RRItems.hydro_rod));
            swapModelsNoGui(RRItems.hydro_rod, event.getModelRegistry());
        }

        TileEntityItemStackRenderer loader_render = RRItems.i_loader.getTileEntityItemStackRenderer();
        if (loader_render instanceof RenderItemLoader) {
            loader_render.renderByItem(new ItemStack(RRItems.i_loader));
            swapModelsNoGui(RRItems.i_loader, event.getModelRegistry());
        }

        TileEntityItemStackRenderer cannon_renderer = RRItems.plasma_cannon.getTileEntityItemStackRenderer();
        if (cannon_renderer instanceof RenderPlasmaCannon) {
            cannon_renderer.renderByItem(new ItemStack(RRItems.plasma_cannon));
            swapModelsNoGui(RRItems.plasma_cannon, event.getModelRegistry());
        }

        TileEntityItemStackRenderer rocket_renderer = RRItems.rocket.getTileEntityItemStackRenderer();
        if (rocket_renderer instanceof RenderRocketItem) {
            rocket_renderer.renderByItem(new ItemStack(RRItems.rocket));
            swapModelsNoGui(RRItems.rocket, event.getModelRegistry());
        }

        TileEntityItemStackRenderer fuel_renderer = RRItems.fuel.getTileEntityItemStackRenderer();
        if (fuel_renderer instanceof RenderFuel) {
            fuel_renderer.renderByItem(new ItemStack(RRItems.fuel));
            swapModelsNoGui(RRItems.fuel, event.getModelRegistry());
        }

        TileEntityItemStackRenderer flamethrower_renderer = RRItems.flamethrower.getTileEntityItemStackRenderer();
        if (flamethrower_renderer instanceof RenderFlamethrower) {
            flamethrower_renderer.renderByItem(new ItemStack(RRItems.flamethrower));
            swapModelsNoGui(RRItems.flamethrower, event.getModelRegistry());
        }
    }

    public static void swapModelsNoGui(Item item, IRegistry<ModelResourceLocation, IBakedModel> reg) {
        ModelResourceLocation loc;
        if (item instanceof RRItem) {
            loc = new ModelResourceLocation(((RRItem)item).getModelName(), "inventory");
        } else {
            loc = new ModelResourceLocation(Objects.requireNonNull(item.getRegistryName()), "inventory");
        }

        IBakedModel model = reg.getObject(loc);
        TileEntityItemStackRenderer render = item.getTileEntityItemStackRenderer();
        if (render instanceof ItemRenderBase) {
            ((ItemRenderBase) render).baked_model = model;
            reg.putObject(loc, new BakedModelNoGui((ItemRenderBase) render));
        }
    }

    @SubscribeEvent
    public static void colorItem(ColorHandlerEvent.Item event){
        event.getItemColors().registerItemColorHandler((stack, tintIndex) ->
            ColorizerGrass.getGrassColor(0.2D, 0.6D), Item.getItemFromBlock(RRBlocks.mine));
    }

    @SubscribeEvent
    public static void colorBlock(ColorHandlerEvent.Block event){
        event.getBlockColors().registerBlockColorHandler((state, world, pos, tint) -> world != null && pos != null ? BiomeColorHelper
                .getGrassColorAtPos(world, pos) : ColorizerGrass
                .getGrassColor(0.5D, 1.0D), RRBlocks.mine);

        event.getBlockColors().registerBlockColorHandler((state, world, pos, tint) -> {
            assert world != null;
            assert pos != null;
            return (15 - state.getValue(Reactive.meta)) * 1118481;
            }, RRBlocks.reactive);
    }

    @SubscribeEvent
    public static void tooltipDraw(ItemTooltipEvent event){
        ItemStack item = event.getItemStack();
        if (item.getItem() == Items.EGG) {
            event.getToolTip().add(TextFormatting.DARK_BLUE + "E G G");
            event.getToolTip().add("");
            event.getToolTip().add(TextFormatting.DARK_BLUE + "give a weeb programmer the egg, she requires egg");
        }
    }
    @SubscribeEvent
    public static void soundStuff(RegistryEvent.Register<SoundEvent> event){
        event.getRegistry().registerAll(RRSounds.gulp, RRSounds.generic_explosion, RRSounds.plasma_explosion, RRSounds.plasma_fire, RRSounds.plasma_prime,
                RRSounds.toyguy, RRSounds.explosives_place, RRSounds.quicksand, RRSounds.glass_break, RRSounds.knife_throw, RRSounds.gas_fizz);
    }
}
