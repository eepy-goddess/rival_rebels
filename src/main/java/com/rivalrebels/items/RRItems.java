package com.rivalrebels.items;

import net.minecraft.world.item.Item;

import java.util.List;
import java.util.ArrayList;

public class RRItems {
    public static List<Item> itemList = new ArrayList<>();

    public static Item ROD;
    public static void init(){
        ROD = new RRItem(new Item.Properties(), "rod");
    }
}
