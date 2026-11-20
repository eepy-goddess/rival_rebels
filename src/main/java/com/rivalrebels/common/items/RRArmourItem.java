package com.rivalrebels.common.items;

import com.rivalrebels.common.init.RRItems;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

public class RRArmourItem extends ItemArmor implements IRRItem {
    private String model_name;

    public RRArmourItem(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(materialIn, renderIndexIn, equipmentSlotIn);
        //TODO Auto-generated constructor stub
        setUnlocalizedName(name);
        setRegistryName(name);
        RRItems.itemListx.add(this);
    }


    @Override
    public RRArmourItem setModelName(String modelName) {
        this.model_name = modelName;
        return this;
    }

    @Override
    public String getModelName() {
        if(model_name == null){
            throw new NullPointerException("Tell that dummy developer known as a weeb programmer to set the model name for " + getRegistryName() + "! And if you are a weeb programmer, SET THE MODEL NAME, DUMMY!!");
        }
        return model_name;
    }
    
}
