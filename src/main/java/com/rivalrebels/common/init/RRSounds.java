package com.rivalrebels.common.init;

import com.rivalrebels.ModInfo;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class RRSounds {
    public static SoundEvent gulp;
    public static SoundEvent toyguy;
    public static SoundEvent plasma_explosion;
    public static SoundEvent plasma_prime;
    public static SoundEvent plasma_fire;
    public static SoundEvent generic_explosion;
    public static SoundEvent explosives_place;
    public static SoundEvent weird_noise;
    public static SoundEvent quicksand;
    public static SoundEvent glass_break;
    public static SoundEvent knife_throw;
    public static SoundEvent gas_fizz;
    public static SoundEvent flamethrower_fire;
    public static SoundEvent tesla_fire;
    public static SoundEvent m202_fire;
    public static SoundEvent m202_rocket_explosion;
    public static SoundEvent m202_rocket_water_splash;
    public static SoundEvent m202_rocket_water_explode;
    public static SoundEvent einsten_warmup;
    public static SoundEvent einsten_fire_A;
    public static SoundEvent einsten_fire_B;
    public static SoundEvent einsten_pop;
    public static SoundEvent autobuild_deploy;
    public static SoundEvent reactor_startup;
    public static SoundEvent reactor_hum;
    public static SoundEvent reactor_warning;

    public static void init() {
        gulp = new SoundEvent(new ResourceLocation(ModInfo.modid, "gulp")).setRegistryName("gulp");
        toyguy = new SoundEvent(new ResourceLocation(ModInfo.modid, "toyguy_song")).setRegistryName("toyguy_song");
        plasma_explosion = new SoundEvent(new ResourceLocation(ModInfo.modid, "plasma_explosion")).setRegistryName("plasma_explosion");
        plasma_prime = new SoundEvent(new ResourceLocation(ModInfo.modid, "plasma_prime")).setRegistryName("plasma_prime");
        plasma_fire = new SoundEvent(new ResourceLocation(ModInfo.modid, "plasma_fire")).setRegistryName("plasma_fire");
        generic_explosion = new SoundEvent(new ResourceLocation(ModInfo.modid, "generic_explosion")).setRegistryName("generic_explosion");
        explosives_place = new SoundEvent(new ResourceLocation(ModInfo.modid, "explosives_place")).setRegistryName("explosives_place");
        weird_noise = new SoundEvent(new ResourceLocation(ModInfo.modid, "weird_noise")).setRegistryName("weird_noise");
        quicksand = new SoundEvent(new ResourceLocation(ModInfo.modid, "quicksand")).setRegistryName("quicksand");
        glass_break = new SoundEvent(new ResourceLocation(ModInfo.modid, "glass_break")).setRegistryName("glass_break");
        knife_throw = new SoundEvent(new ResourceLocation(ModInfo.modid, "knife_throw")).setRegistryName("knife_throw");
        gas_fizz = new SoundEvent(new ResourceLocation(ModInfo.modid, "gas_fizz")).setRegistryName("gas_fizz");
        flamethrower_fire = new SoundEvent(new ResourceLocation(ModInfo.modid, "flamethrower_fire")).setRegistryName("flamethrower_fire");
        tesla_fire = new SoundEvent(new ResourceLocation(ModInfo.modid, "tesla_fire")).setRegistryName("tesla_fire");

        m202_fire = new SoundEvent(new ResourceLocation(ModInfo.modid, "m202_fire")).setRegistryName("m202_fire");
        m202_rocket_explosion = new SoundEvent(new ResourceLocation(ModInfo.modid, "m202_rocket_explosion")).setRegistryName("m202_rocket_explosion");
        m202_rocket_water_splash = new SoundEvent(new ResourceLocation(ModInfo.modid, "m202_rocket_water_splash")).setRegistryName("m202_rocket_water_splash");
        m202_rocket_water_explode = new SoundEvent(new ResourceLocation(ModInfo.modid, "m202_rocket_water_explode")).setRegistryName("m202_rocket_water_explode");

        einsten_warmup = new SoundEvent(new ResourceLocation(ModInfo.modid, "einsten_warmup")).setRegistryName("einsten_warmup");
        einsten_fire_A = new SoundEvent(new ResourceLocation(ModInfo.modid, "einsten_fire_a")).setRegistryName("einsten_fire_a");
        einsten_fire_B = new SoundEvent(new ResourceLocation(ModInfo.modid, "einsten_fire_b")).setRegistryName("einsten_fire_b");
        einsten_pop = new SoundEvent(new ResourceLocation(ModInfo.modid, "einsten_pop")).setRegistryName("einsten_pop");

        autobuild_deploy = new SoundEvent(new ResourceLocation(ModInfo.modid, "autobuild_deploy")).setRegistryName("autobuild_deploy");

        reactor_startup = new SoundEvent(new ResourceLocation(ModInfo.modid, "reactor_startup")).setRegistryName("reactor_startup");
        reactor_hum = new SoundEvent(new ResourceLocation(ModInfo.modid, "reactor_hum")).setRegistryName("reactor_hum");
        reactor_warning = new SoundEvent(new ResourceLocation(ModInfo.modid, "reactor_warning")).setRegistryName("reactor_warning");
    }
}
