package com.rivalrebels.common.items;

import net.minecraft.item.Item;
import com.rivalrebels.common.init.RRItems;

public class RRItem extends Item implements IRRItem {
    private String model_name;
    //public static List<Item> itemList = new ArrayList<>();

    public RRItem(String name){ // I don't like this constructor
        setUnlocalizedName(name);
        setRegistryName(name);
        RRItems.itemListx.add(this);
    }

    @Override
    /**Basically the successor of setTextureName from 1.7.10, except it takes json models instead of pngs
     * @param modelName the name of the model file, it already points to the models/item directory, it just needs the modid and the name of the file*/
    public RRItem setModelName(String modelName){
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
