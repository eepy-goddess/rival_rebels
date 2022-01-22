package com.rivalrebels.blocks;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class RRBlock extends Block {
    private CreativeModeTab tab;
    public RRBlock(BlockBehaviour.Properties prop, String registry_name){
        super(prop);
        this.setRegistryName(registry_name);
        RRBlocks.block_list.add(this);
    }
    public RRBlock setCreativeTab(CreativeModeTab tab){
        this.tab = tab;
        return this;
    }
    public CreativeModeTab getCreativeTab(){
        return this.tab;
    }
}
