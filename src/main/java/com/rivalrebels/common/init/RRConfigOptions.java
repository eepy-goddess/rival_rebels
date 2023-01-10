package com.rivalrebels.common.init;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class RRConfigOptions {
    public static float nuke_scale = 1.0F;
    public static boolean render_old_nuke;
    public static int plasmoid_decay;
    public static boolean alt_R_key;
    public static boolean infinite_ammo;
    public static boolean flare_explode;

    public static void makeConfig(FMLPreInitializationEvent event) {
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();
        nuke_scale = (float) config.get("explosionsize", "nukeScale", 1.0F).getDouble(1.0F);
        render_old_nuke = config.
                get("misc", "enable_old_nuke", false).getBoolean(false);
        plasmoid_decay = config.get("decay", "plasmoid_decay", 70).getInt();
        alt_R_key = config.get("misc", "useFkeyInsteadOfRkey", false).getBoolean(false);
        infinite_ammo = config.get("misc", "infinite_ammo", false).getBoolean(false);
        flare_explode = config.get("misc", "flare_explode", true).getBoolean(true);
        config.save();
    }
}
