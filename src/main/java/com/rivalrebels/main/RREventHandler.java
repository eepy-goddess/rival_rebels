package com.rivalrebels.main;

import com.rivalrebels.blocks.RRBlock;
import com.rivalrebels.blocks.RRBlocks;
import com.rivalrebels.items.RRItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "rivalrebels", bus = Mod.EventBusSubscriber.Bus.MOD)
public class RREventHandler {
    //yes, i haven't moved on to using deferred registries. Cry about it LOL
    @SubscribeEvent
    public static void itemRegisterEvent(RegistryEvent.Register<Item> event){
        for(Item o : RRItems.itemList){
            event.getRegistry().register(o);
        }
        for(Block o : RRBlocks.block_list){
            RRBlock block = (RRBlock)o;
            event.getRegistry().register(new BlockItem(block, new Item.Properties().tab(block.getCreativeTab())).setRegistryName(block.getRegistryName()));
        }
    }
    @SubscribeEvent
    public static void blockRegisterEvent(RegistryEvent.Register<Block> event){
        for(Block o : RRBlocks.block_list){
            event.getRegistry().register(o);
        }
    }
}
