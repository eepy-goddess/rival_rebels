package com.rivalrebels;

import com.rivalrebels.client.model.RodModel;
import com.rivalrebels.client.render.*;
import com.rivalrebels.client.renderhelper.BakedModelNoGui;
import com.rivalrebels.client.renderhelper.ItemRenderBase;
import com.rivalrebels.common.blocks.RRBlock;
import com.rivalrebels.common.init.RRSounds;
import com.rivalrebels.common.items.IHasModel;
import com.rivalrebels.common.items.RRItem;
import com.rivalrebels.common.items.RRItemBlock;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemOverride;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.BlockStateMapper;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.resources.Language;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.IRegistry;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber(modid = RivalRebels.modid)
public class ModEventHandler {
    @SubscribeEvent
    public static void itemRegisterStuff(RegistryEvent.Register<Item> event){
        //This little maneuver is gonna save me 5000 years
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
                event.getRegistry().register(new RRItemBlock(blockIn, blockIn.getUnlocalizedName().substring(5)).setModelName(blockIn.getRegistryName().toString()).setCreativeTab(blockIn.getCreativeTabToDisplayOn()));
            }
        }
        event.getRegistry().registerAll(RivalRebels.iLoader, RivalRebels.iMine);
    }
    @SubscribeEvent
    public static void blockRegisterStuff(RegistryEvent.Register<Block> gameRegistry){
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
    @SubscribeEvent
    public static void modelStuff(ModelRegistryEvent event){
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
    //Stupid baked models AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA BAKA BAKA BAKA BAKA BAKA BAKA BAKA BAKA BAKA BAKA
    @SubscribeEvent
    public static void moreModelStuff(ModelBakeEvent event){
        IBakedModel model = event.getModelRegistry().getObject(new ModelResourceLocation(RivalRebels.modid + ":" + RivalRebels.rod.getRegistryName(), "inventory"));
        TileEntityItemStackRenderer renderer = RivalRebels.rod.getTileEntityItemStackRenderer();
        if(renderer instanceof RenderRod){
            ((RenderRod)renderer).renderByItem(new ItemStack(RivalRebels.rod));
            swapModelsNoGui(RivalRebels.rod, event.getModelRegistry());
        }
        TileEntityItemStackRenderer renderer1 = RivalRebels.battery.getTileEntityItemStackRenderer();
        if(renderer1 instanceof RenderBattery){
            ((RenderBattery)renderer1).renderByItem(new ItemStack(RivalRebels.battery));
            swapModelsNoGui(RivalRebels.battery, event.getModelRegistry());
        }
        TileEntityItemStackRenderer nuclear_rod_renderer = RivalRebels.nuclear_rod.getTileEntityItemStackRenderer();
        if(nuclear_rod_renderer instanceof RenderNuclearRod){
            ((RenderNuclearRod)nuclear_rod_renderer).renderByItem(new ItemStack(RivalRebels.nuclear_rod));
            swapModelsNoGui(RivalRebels.nuclear_rod, event.getModelRegistry());
        }
        TileEntityItemStackRenderer redstone_rod_renderer = RivalRebels.redstone_rod.getTileEntityItemStackRenderer();
        if(redstone_rod_renderer instanceof RenderRedstoneRod){
            ((RenderRedstoneRod)redstone_rod_renderer).renderByItem(new ItemStack(RivalRebels.redstone_rod));
            swapModelsNoGui(RivalRebels.redstone_rod, event.getModelRegistry());
        }
        TileEntityItemStackRenderer hydro_render = RivalRebels.hydro_rod.getTileEntityItemStackRenderer();
        if(hydro_render instanceof RenderHydrogenRod){
            ((RenderHydrogenRod)hydro_render).renderByItem(new ItemStack(RivalRebels.hydro_rod));
            swapModelsNoGui(RivalRebels.hydro_rod, event.getModelRegistry());
        }
        TileEntityItemStackRenderer loader_render = RivalRebels.iLoader.getTileEntityItemStackRenderer();
        if(loader_render instanceof RenderItemLoader){
            ((RenderItemLoader)loader_render).renderByItem(new ItemStack(RivalRebels.iLoader));
            swapModelsNoGui(RivalRebels.iLoader, event.getModelRegistry());
        }
        TileEntityItemStackRenderer cannon_renderer = RivalRebels.plasma_cannon.getTileEntityItemStackRenderer();
        if(cannon_renderer instanceof RenderPlasmaCannon){
            ((RenderPlasmaCannon)cannon_renderer).renderByItem(new ItemStack(RivalRebels.plasma_cannon));
            swapModelsNoGui(RivalRebels.plasma_cannon, event.getModelRegistry());
        }
        TileEntityItemStackRenderer rocket_renderer = RivalRebels.rocket.getTileEntityItemStackRenderer();
        if(rocket_renderer instanceof RenderRocketItem){
            ((RenderRocketItem)rocket_renderer).renderByItem(new ItemStack(RivalRebels.rocket));
            swapModelsNoGui(RivalRebels.rocket, event.getModelRegistry());
        }
        TileEntityItemStackRenderer fuel_renderer = RivalRebels.fuel.getTileEntityItemStackRenderer();
        if(fuel_renderer instanceof RenderFuel){
            ((RenderFuel)fuel_renderer).renderByItem(new ItemStack(RivalRebels.fuel));
            swapModelsNoGui(RivalRebels.fuel, event.getModelRegistry());
        }
    }
    public static void swapModelsNoGui(Item item, IRegistry<ModelResourceLocation, IBakedModel> reg) {
        ModelResourceLocation loc;
        if(item instanceof RRItem){
            loc = new ModelResourceLocation(((RRItem)item).getModelName(), "inventory");
        } else {
            loc = new ModelResourceLocation(item.getRegistryName(), "inventory");
        }
        IBakedModel model = reg.getObject(loc);
        TileEntityItemStackRenderer render = item.getTileEntityItemStackRenderer();
        if(render instanceof ItemRenderBase) {
            ((ItemRenderBase) render).model = model;
            reg.putObject(loc, new BakedModelNoGui((ItemRenderBase) render));
        }

    }

    @SubscribeEvent
    public static void colorItem(ColorHandlerEvent.Item event){
        event.getItemColors().registerItemColorHandler((stack, tintIndex) -> {
            return ColorizerGrass.getGrassColor(0.2D, 0.6D);}, Item.getItemFromBlock(RivalRebels.mine));
    }
    @SubscribeEvent
    public static void colorBlock(ColorHandlerEvent.Block event){
        event.getBlockColors().registerBlockColorHandler((state, world, pos, tint) -> world != null && pos != null ? BiomeColorHelper.getGrassColorAtPos(world, pos) : ColorizerGrass.getGrassColor(0.5D, 1.0D), RivalRebels.mine);
    }
    @SubscribeEvent
    public static void tooltipDraw(ItemTooltipEvent event){
        ItemStack item = event.getItemStack();
        if(item.getItem() == Items.EGG){
            event.getToolTip().add(TextFormatting.DARK_BLUE + "E G G");
            event.getToolTip().add("");
            event.getToolTip().add(TextFormatting.DARK_BLUE + "give a weeb programmer the egg, he requires egg");

        }
    }
    @SubscribeEvent
    public static void soundStuff(RegistryEvent.Register<SoundEvent> event){
        event.getRegistry().registerAll(RRSounds.gulp, RRSounds.generic_explosion, RRSounds.plasma_explosion, RRSounds.plasma_fire, RRSounds.plasma_prime,
                RRSounds.toyguy, RRSounds.explosives_place, RRSounds.quicksand, RRSounds.glass_break, RRSounds.knife_throw);
    }
}
