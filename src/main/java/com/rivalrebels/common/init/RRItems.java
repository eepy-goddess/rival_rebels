package com.rivalrebels.common.init;

import com.rivalrebels.ModInfo;
import com.rivalrebels.common.creativetabs.HydroRR;
import com.rivalrebels.common.creativetabs.NuclearRR;
import com.rivalrebels.common.items.*;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class RRItems {
    public static CreativeTabs nuclear_rr = new NuclearRR("rival_rebels");
    public static CreativeTabs hydro_rr = new HydroRR("rival_rebels2");

    public static Item rod;
    public static Item nuclear_rod;
    public static Item battery;
    public static Item hydro_rod;
    public static Item redstone_rod;
    public static Item lord_vertice_lemonade;
    public static Item plasma_cannon;
    public static Item pliers;
    public static Item fuse;
    public static Item antennae;
    public static Item gas_grenade;
    public static Item nuclear_lemonade;
    public static Item detonator;
    public static Item fuel;
    public static Item rocket;
    public static Item knife;
    public static Item flamethrower;
    //Item blocks go below here
    public static Item i_loader;
    public static Item i_mine;


    public static void initItems() {
        rod = new RRItem("rod").setModelName(ModInfo.modid + ":rod").setCreativeTab(nuclear_rr);
        nuclear_rod = new NukeRod("nuke_rod").setModelName(ModInfo.modid + ":nuke_rod").setCreativeTab(nuclear_rr).setContainerItem(rod).setMaxStackSize(1);
        hydro_rod = new RRItem("hydrogen_rod").setModelName(ModInfo.modid + ":hydrogen_rod").setContainerItem(rod).setCreativeTab(nuclear_rr).setMaxStackSize(1);
        redstone_rod = new RRItem("redstone_rod").setModelName(ModInfo.modid + ":redstone_rod").setContainerItem(rod).setCreativeTab(nuclear_rr).setMaxStackSize(1);
        battery = new BatteryItem("battery").setModelName(ModInfo.modid + ":" + "battery").setCreativeTab(nuclear_rr);
        lord_vertice_lemonade = new VerticeLemonade("vertice_lemonade").setModelName(ModInfo.modid + ":lemonade").setCreativeTab(nuclear_rr);
        nuclear_lemonade = new RRItem("nuclear_lemonade").setModelName(ModInfo.modid + ":nuke_lemon").setCreativeTab(nuclear_rr);
        plasma_cannon = new PlasmaCannonItem("plasma_cannon").setCreativeTab(nuclear_rr).setFull3D();
        pliers = new Pliers("pliers").setModelName(ModInfo.modid + ":pliers").setCreativeTab(nuclear_rr);
        fuse = new RRItem("fuse").setModelName(ModInfo.modid + ":fuse").setCreativeTab(nuclear_rr);
        antennae = new RRItem("antennae").setModelName(ModInfo.modid + ":radio").setCreativeTab(nuclear_rr);
        detonator = new Remote("detonator").setModelName(ModInfo.modid + ":detonator").setCreativeTab(nuclear_rr);
        fuel = new RRItem("fuel").setModelName(ModInfo.modid + ":fuel").setCreativeTab(nuclear_rr);
        rocket = new RRItem("rocket").setModelName(ModInfo.modid + ":rocket").setCreativeTab(nuclear_rr);
        knife = new Cuchillo("knife").setCreativeTab(nuclear_rr).setFull3D();
        flamethrower = new FlameThrower("flamethrower").setModelName(ModInfo.modid + ":flamethrower").setCreativeTab(nuclear_rr).setMaxStackSize(1);
        gas_grenade = new GasGrenade("gas_grenade").setModelName(ModInfo.modid + ":gas_grenade").setCreativeTab(nuclear_rr);

        /* ------------------------------------ Item-Blocks ----------------------------------------------- */

        i_loader = new RRItemBlock(RRBlocks.loader, "loader").setModelName(ModInfo.modid + ":loader").setCreativeTab(nuclear_rr);
        i_mine = new RRItemBlock(RRBlocks.mine, "mine").setModelName(ModInfo.modid + ":mine").setCreativeTab(nuclear_rr);
    }
}
