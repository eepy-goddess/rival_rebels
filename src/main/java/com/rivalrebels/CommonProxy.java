package com.rivalrebels;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
    public void registerRenderStuff(FMLPreInitializationEvent event){}
    public void registerModel(Item item, int meta, String variant) {}
    public void registerCustomModelName(Item item, String name, int meta, String variant){}
    public void closeGui(){}
    public void flamethrowerGui(int i){}
}
