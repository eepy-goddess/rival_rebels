package com.rivalrebels.items;

import net.minecraft.world.item.Item;

public class RRItem extends Item {
    public RRItem(Item.Properties settings, String registry_name){
        super(settings);
        this.setRegistryName(registry_name);
        RRItems.itemList.add(this);
    }
}
