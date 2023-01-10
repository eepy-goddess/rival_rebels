package com.rivalrebels.common.init;

import com.rivalrebels.common.blocks.*;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class RRBlocks {
    public static Block steel;
    public static Block nuke;
    public static Block loader;
    public static Block nuke_crate_bottom;
    public static Block nuke_crate_top;
    public static Block plasma_explosion;
    public static Block toaster;
    public static Block plastic_explosives;
    public static Block green_camo;
    public static Block brown_camo;
    public static Block grey_camo;
    public static Block jumper;
    public static Block smart_camo;
    public static Block mine;
    public static Block mario;
    public static Block quicksand;
    public static Block reactive;
    public static Block flare;
    public static Block toxic_gas;

    public static void initBlocks() {
        steel = new Steel(Material.IRON).setSoundType(SoundType.METAL).setCreativeTab(RRItems.nuclear_rr).setHardness(2.0F).setResistance(2.0F);
        nuke = new NukeBlock(Material.IRON).setCreativeTab(RRItems.nuclear_rr).setHardness(2.0F);
        loader = new LoaderBlock(Material.IRON).setResistance(4.0F).setHardness(4.0F).setCreativeTab(RRItems.nuclear_rr);
        nuke_crate_top = new NukeCrate("nuke_crate_top", Material.WOOD).setSoundType(SoundType.WOOD).setCreativeTab(RRItems.nuclear_rr).setHardness(2.0F);
        nuke_crate_bottom = new NukeCrate("nuke_crate_bottom", Material.WOOD).setSoundType(SoundType.WOOD).setCreativeTab(RRItems.nuclear_rr).setHardness(2.0F);
        plasma_explosion = new PlasmaExplosion(Material.PORTAL, "plasma_explosion").setLightLevel(1.0F);
        toaster = new Toaster("toaster", Material.IRON).setSoundType(SoundType.ANVIL).setCreativeTab(RRItems.nuclear_rr).setHardness(4.0F);
        plastic_explosives = new Explosives("explosives", Material.IRON).setSoundType(SoundType.METAL).setCreativeTab(RRItems.nuclear_rr).setHardness(3.0F).setResistance(3.0F);
        green_camo = new RRBlock("green_camo", Material.CLAY).setSoundType(SoundType.CLOTH).setCreativeTab(RRItems.nuclear_rr).setHardness(6.0F).setResistance(6.0F);
        brown_camo = new RRBlock("brown_camo", Material.CLAY).setSoundType(SoundType.CLOTH).setCreativeTab(RRItems.nuclear_rr).setHardness(6.0F).setResistance(6.0F);
        grey_camo = new RRBlock("grey_camo", Material.CLAY).setSoundType(SoundType.CLOTH).setCreativeTab(RRItems.nuclear_rr).setHardness(6.0F).setResistance(6.0F);
        smart_camo = new SmartCamo("smart_camo", Material.CLAY).setSoundType(SoundType.CLOTH).setCreativeTab(RRItems.nuclear_rr).setHardness(6.0F).setResistance(6.0F);
        mine = new Mine("mine", Material.IRON).setCreativeTab(RRItems.nuclear_rr).setHardness(6.0F).setResistance(6.0F);
        mario = new Mario("mario", Material.GROUND).setSoundType(SoundType.GROUND).setCreativeTab(RRItems.nuclear_rr).setHardness(0.3f);
        quicksand = new Quicksand("quicksand", Material.SAND).setSoundType(SoundType.SAND).setCreativeTab(RRItems.nuclear_rr).setHardness(0.2f);
        reactive = new Reactive("reactive", Material.IRON).setSoundType(SoundType.METAL).setCreativeTab(RRItems.nuclear_rr).setHardness(9.0f);
        flare = new Flare("flare", Material.WOOD).setCreativeTab(RRItems.nuclear_rr).setLightLevel(0.5f);
        jumper = new Jumper("jumper", Material.ROCK).setSoundType(SoundType.METAL).setHardness(0.8F).setCreativeTab(RRItems.nuclear_rr);
        toxic_gas = new ToxicGas("toxic_gas", Material.CACTUS).setUnlocalizedName("toxic_gas").setBlockUnbreakable();
    }
}
