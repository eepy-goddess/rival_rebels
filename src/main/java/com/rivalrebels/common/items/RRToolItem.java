package com.rivalrebels.common.items;

import java.util.Set;

import com.rivalrebels.common.init.RRItems;

import net.minecraft.block.Block;
import net.minecraft.item.ItemTool;

public class RRToolItem extends ItemTool implements IRRItem  {
    private String model_name;

    protected RRToolItem(String name, float attackDamageIn, float attackSpeedIn, ToolMaterial materialIn,
            Set<Block> effectiveBlocksIn) {
        super(attackDamageIn, attackSpeedIn, materialIn, effectiveBlocksIn);
        setUnlocalizedName(name);
        setRegistryName(name);
        RRItems.itemListx.add(this);
    }

    protected RRToolItem(String name, ToolMaterial materialIn, Set<Block> effectiveBlocksIn) {
        super(materialIn, effectiveBlocksIn);
        setUnlocalizedName(name);
        setRegistryName(name);
        RRItems.itemListx.add(this);
    }

    public RRToolItem setModelName(String modelName){
        this.model_name = modelName;
        return this;
    }
    public String getModelName() {
        if(model_name == null){
            throw new NullPointerException("Tell that dummy developer known as a weeb programmer to set the model name for " + getRegistryName() + "! And if you are a weeb programmer, SET THE MODEL NAME, DUMMY!!");
        }
        return model_name;
    }
    
    
}
