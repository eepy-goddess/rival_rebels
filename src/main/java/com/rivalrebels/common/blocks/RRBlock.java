package com.rivalrebels.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class RRBlock extends Block {
    public static List<Block> list = new ArrayList<>();
    public RRBlock(String name, Material material){
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        list.add(this);
    }

    @Override
    public Block setSoundType(SoundType sound) {
        return super.setSoundType(sound);
    }
}
