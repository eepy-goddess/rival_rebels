package com.rivalrebels;

import com.hbm.items.machine.ItemCassette;
import com.rivalrebels.common.blocks.*;
import com.rivalrebels.common.command.RRConfig;
import com.rivalrebels.common.creativetabs.HydroRR;
import com.rivalrebels.common.creativetabs.NuclearRR;
import com.rivalrebels.common.entity.EntityDebris;
import com.rivalrebels.common.entity.EntityPlasmoid;
import com.rivalrebels.common.init.RRSounds;
import com.rivalrebels.common.init.RivalRebelsGuiHandler;
import com.rivalrebels.common.init.RivalRebelsRecipes;
import com.rivalrebels.common.items.*;
import com.rivalrebels.common.packet.PacketDispatcher;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucketMilk;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import java.nio.file.AccessMode;
import java.util.ArrayList;
import java.util.List;


@Mod(modid = RivalRebels.modid, name = RivalRebels.name, version = RivalRebels.version)
public class RivalRebels {
    /*////////////////////////////////////////////////*/
    public static final String modid = "rivalrebels";
    public static final String name = "Rival Rebels";
    public static final String version = "1.12.2A";
    @Mod.Instance(modid)
    public static RivalRebels instance;
    /*/////////////////////////////////////////////////*/
    public static final String client = "com.rivalrebels.ClientProxy";
    public static final String server = "com.rivalrebels.CommonProxy";
    @SidedProxy(clientSide = client, serverSide = server)
    public static CommonProxy proxy;
    /*____________Items Start_________________*/
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
    public static Item nuclear_lemonade;
    public static Item detonator;
    public static Item fuel;
    public static Item rocket;
    public static Item knife;
    /*___________Blocks start_______________*/
    public static Block steel;
    public static Block nuke;
    public static Block loader;
    public static Item iLoader;
    public static Block nukeCrateBottom;
    public static Block nukeCrateTop;
    public static Block plasmaexplosion;
    public static Block toaster;
    public static Block plastic_explosives;
    public static Block green_camo;
    public static Block brown_camo;
    public static Block grey_camo;
    public static Block smart_camo;
    public static Block mine;
    public static Item iMine;
    public static Block mario;
    public static Block quicksand;
    /*______________________config start___________________________*/
    public Configuration config;
    public static float nukeScale = 1.0F;
    public static boolean render_old_nuke;
    public static int plasmoidDecay;
    //this is a list that contains all items implemented with the IHasModel interface
    public static List<Item> model_items = new ArrayList<>();
    public static List<Item> other_items = new ArrayList<>();
    public static List<Block> other_blocks = new ArrayList<>();
    @Mod.EventHandler
    public void preLoad(FMLPreInitializationEvent event){
        config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();
        nukeScale = (float) config.get("explosionsize", "nukeScale", 1.0F).getDouble(1.0F);
        render_old_nuke = config.get("misc", "enable_old_nuke", false).getBoolean(false);
        plasmoidDecay = config.get("decay", "plasmoid_decay", 70).getInt();
        config.save();
        instance = this;
        rod = new RRItem("rod").setModelName(modid + ":rod").setCreativeTab(nuclear_rr);
        nuclear_rod = new NukeRod("nuke_rod").setModelName(modid + ":nuke_rod").setCreativeTab(nuclear_rr).setContainerItem(rod).setMaxStackSize(1);
        hydro_rod = new RRItem("hydrogen_rod").setModelName(modid + ":hydrogen_rod").setContainerItem(rod).setCreativeTab(nuclear_rr).setMaxStackSize(1);
        redstone_rod = new RRItem("redstone_rod").setModelName(modid + ":redstone_rod").setContainerItem(rod).setCreativeTab(nuclear_rr).setMaxStackSize(1);
        steel = new Steel(Material.IRON).setSoundType(SoundType.METAL).setCreativeTab(nuclear_rr).setHardness(2.0F).setResistance(2.0F);
        nuke = new NukeBlock(Material.IRON).setCreativeTab(nuclear_rr).setHardness(2.0F);
        battery = new BatteryItem("battery").setModelName(modid + ":" + "battery").setCreativeTab(nuclear_rr);
        lord_vertice_lemonade = new VerticeLemonade("vertice_lemonade").setModelName(modid + ":lemonade").setCreativeTab(nuclear_rr);
        nuclear_lemonade = new RRItem("nuclear_lemonade").setModelName(modid + ":nuke_lemon").setCreativeTab(nuclear_rr);
        loader = new LoaderBlock(Material.IRON).setResistance(4.0F).setHardness(4.0F).setCreativeTab(nuclear_rr);
        iLoader = new RRItemBlock(loader, "loader").setModelName(modid + ":loader").setCreativeTab(nuclear_rr);
        plasma_cannon = new PlasmaCannonItem("plasma_cannon").setCreativeTab(nuclear_rr).setFull3D();
        nukeCrateTop = new NukeCrate("nuke_crate_top", Material.WOOD).setSoundType(SoundType.WOOD).setCreativeTab(nuclear_rr).setHardness(2.0F);
        nukeCrateBottom = new NukeCrate("nuke_crate_bottom", Material.WOOD).setSoundType(SoundType.WOOD).setCreativeTab(nuclear_rr).setHardness(2.0F);
        pliers = new Pliers("pliers").setModelName(modid + ":pliers").setCreativeTab(nuclear_rr);
        plasmaexplosion = new PlasmaExplosion(Material.PORTAL, "plasma_explosion").setLightLevel(1.0F);
        toaster = new Toaster("toaster", Material.IRON).setSoundType(SoundType.ANVIL).setCreativeTab(nuclear_rr).setHardness(4.0F);
        fuse = new RRItem("fuse").setModelName(modid + ":fuse").setCreativeTab(nuclear_rr);
        antennae = new RRItem("antennae").setModelName(modid + ":radio").setCreativeTab(nuclear_rr);
        detonator = new Remote("detonator").setModelName(modid + ":detonator").setCreativeTab(nuclear_rr);
        fuel = new RRItem("fuel").setModelName(modid + ":fuel").setCreativeTab(nuclear_rr);
        rocket = new RRItem("rocket").setModelName(modid + ":rocket").setCreativeTab(nuclear_rr);
        plastic_explosives = new Explosives("explosives", Material.IRON).setSoundType(SoundType.METAL).setCreativeTab(nuclear_rr).setHardness(3.0F).setResistance(3.0F);
        green_camo = new RRBlock("green_camo", Material.CLAY).setSoundType(SoundType.CLOTH).setCreativeTab(nuclear_rr).setHardness(6.0F).setResistance(6.0F);
        brown_camo = new RRBlock("brown_camo", Material.CLAY).setSoundType(SoundType.CLOTH).setCreativeTab(nuclear_rr).setHardness(6.0F).setResistance(6.0F);
        grey_camo = new RRBlock("grey_camo", Material.CLAY).setSoundType(SoundType.CLOTH).setCreativeTab(nuclear_rr).setHardness(6.0F).setResistance(6.0F);
        smart_camo = new SmartCamo("smart_camo", Material.CLAY).setSoundType(SoundType.CLOTH).setCreativeTab(nuclear_rr).setHardness(6.0F).setResistance(6.0F);
        mine = new Mine("mine", Material.IRON).setCreativeTab(nuclear_rr).setHardness(6.0F).setResistance(6.0F);
        iMine = new RRItemBlock(mine, "mine").setModelName(modid + ":mine").setCreativeTab(nuclear_rr);
        mario = new Mario("mario", Material.GROUND).setSoundType(SoundType.GROUND).setCreativeTab(nuclear_rr).setHardness(0.3f);
        quicksand = new Quicksand("quicksand", Material.SAND).setSoundType(SoundType.SAND).setCreativeTab(nuclear_rr).setHardness(0.2f);
        knife = new RRItem("knife").setModelName(modid + ":knife").setCreativeTab(nuclear_rr).setFull3D();
        EntityRegistry.registerModEntity(new ResourceLocation(modid, "plasmoid"), EntityPlasmoid.class, "plasmoid", 0, instance, 100000, 100, true);
        EntityRegistry.registerModEntity(new ResourceLocation(modid, "debris"), EntityDebris.class, "debris", 1, instance, 100000, 1000, true);
        proxy.registerRenderStuff(event);
        RRSounds.init();
        PacketDispatcher.registerPackets();
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new RivalRebelsGuiHandler());
    }
    @Mod.EventHandler
    public void load(FMLInitializationEvent event){
        RivalRebelsRecipes.init();

    }
    @Mod.EventHandler
    public void postLoad(FMLPostInitializationEvent event){

    }
    @Mod.EventHandler
    public void serverStart(FMLServerStartingEvent event){
        event.registerServerCommand(new RRConfig());
    }
    public static CreativeTabs nuclear_rr = new NuclearRR("rival_rebels");
    public static CreativeTabs hydro_rr = new HydroRR("rival_rebels2");
}
