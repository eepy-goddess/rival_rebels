package com.rivalrebels.common.items;

public class Pliers extends RRItem{
    public Pliers(String name) {
        super(name);
        setContainerItem(this);
        setMaxStackSize(1);
    }
}
