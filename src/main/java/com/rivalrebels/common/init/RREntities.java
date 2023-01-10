package com.rivalrebels.common.init;

import com.rivalrebels.ModInfo;
import com.rivalrebels.RivalRebels;
import com.rivalrebels.common.entity.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class RREntities {
    public static void registerEntities() {
        EntityRegistry.registerModEntity(new ResourceLocation(ModInfo.modid, "plasmoid"), EntityPlasmoid.class, "plasmoid", 0, RivalRebels.instance, 100000, 100, true);
        EntityRegistry.registerModEntity(new ResourceLocation(ModInfo.modid, "debris"), EntityDebris.class, "debris", 1, RivalRebels.instance, 100000, 1000, true);
        EntityRegistry.registerModEntity(new ResourceLocation(ModInfo.modid, "cuchillo"), EntityCuchillo.class, "cuchillo", 2, RivalRebels.instance, 100000, 1000, true);
        EntityRegistry.registerModEntity(new ResourceLocation(ModInfo.modid, "flame_ball"), EntityFlameBall.class, "flameball", 3, RivalRebels.instance, 1000000, 1000, true);
        EntityRegistry.registerModEntity(new ResourceLocation(ModInfo.modid, "flame_ball_reblu"), EntityFlameBall1.class, "flameball_reblu", 4, RivalRebels.instance, 1000000, 1000, true);
        EntityRegistry.registerModEntity(new ResourceLocation(ModInfo.modid, "flame_ball_blue"), EntityFlameBall2.class, "flameball_blue", 5, RivalRebels.instance, 1000000, 1000, true);
        EntityRegistry.registerModEntity(new ResourceLocation(ModInfo.modid, "gas_grenade"), EntityGasGrenade.class, "gas_granade", 6, RivalRebels.instance, 1000000, 1000, true);

    }
}
