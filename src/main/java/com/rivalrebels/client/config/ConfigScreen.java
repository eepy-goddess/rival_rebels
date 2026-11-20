package com.rivalrebels.client.config;

import com.rivalrebels.ModInfo;
import com.rivalrebels.common.config.RRConfigx;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.common.config.ConfigElement;

public class ConfigScreen extends GuiConfig {

    public ConfigScreen(GuiScreen parent) {
        super(
            parent,
            new ConfigElement(
                RRConfigx.config.getCategory("general")
            ).getChildElements(),
            ModInfo.modid,
            false,
            false,
            "Rival Rebels Mod Configuration"
        );
    }
}