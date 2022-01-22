package com.rivalrebels.main;

import com.rivalrebels.blocks.RRBlocks;
import com.rivalrebels.items.RRItems;
import net.minecraftforge.fml.common.Mod;

@Mod("rivalrebels")
public class RivalRebels {
    public RivalRebels(){
        RRBlocks.init();
        RRItems.init();
    }
}
