package com.rivalrebels.common.init;

import com.rivalrebels.RivalRebels;
import net.minecraft.entity.passive.EntityRabbit;
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
    public static void init(){
        gulp = new SoundEvent(new ResourceLocation(RivalRebels.modid, "gulp")).setRegistryName("gulp");
        toyguy = new SoundEvent(new ResourceLocation(RivalRebels.modid, "toyguy_song")).setRegistryName("toyguy_song");
        plasma_explosion = new SoundEvent(new ResourceLocation(RivalRebels.modid, "plasma_explosion")).setRegistryName("plasma_explosion");
        plasma_prime = new SoundEvent(new ResourceLocation(RivalRebels.modid, "plasma_prime")).setRegistryName("plasma_prime");
        plasma_fire = new SoundEvent(new ResourceLocation(RivalRebels.modid, "plasma_fire")).setRegistryName("plasma_fire");
        generic_explosion = new SoundEvent(new ResourceLocation(RivalRebels.modid, "generic_explosion")).setRegistryName("generic_explosion");
        explosives_place = new SoundEvent(new ResourceLocation(RivalRebels.modid, "explosives_place")).setRegistryName("explosives_place");
        weird_noise = new SoundEvent(new ResourceLocation(RivalRebels.modid, "weird_noise")).setRegistryName("weird_noise");
        quicksand = new SoundEvent(new ResourceLocation(RivalRebels.modid, "quicksand")).setRegistryName("quicksand");

    }
}
