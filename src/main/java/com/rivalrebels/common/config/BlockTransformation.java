package com.rivalrebels.common.config;

import net.minecraft.block.Block;

// basically a record
public class BlockTransformation {
    public final Block inputBlock;
    public final Block outputBlock;
    public final int heatResistance;

    public BlockTransformation(Block input, Block output, int resistance) {
        this.inputBlock = input;
        this.outputBlock = output;
        this.heatResistance = resistance;
    }
}
