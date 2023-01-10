package com.rivalrebels;

import com.rivalrebels.common.command.RRConfig;
import com.rivalrebels.common.init.*;
import com.rivalrebels.common.packet.PacketDispatcher;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import java.util.ArrayList;
import java.util.List;


@Mod(modid = ModInfo.modid, name = ModInfo.name, version = ModInfo.version)
public class RivalRebels {

    @Mod.Instance(ModInfo.modid)
    public static RivalRebels instance;

    public static final String client = "com.rivalrebels.ClientProxy";
    public static final String server = "com.rivalrebels.CommonProxy";
    @SidedProxy(clientSide = client, serverSide = server)
    public static CommonProxy proxy;
    //this is a list that contains all items implemented with the IHasModel interface
    public static List<Item> model_items = new ArrayList<>();
    public static List<Item> other_items = new ArrayList<>();
    public static List<Block> other_blocks = new ArrayList<>();

    @SuppressWarnings(value = "unused")
    @Mod.EventHandler
    public void preLoad(FMLPreInitializationEvent event) {
        RRConfigOptions.makeConfig(event);
        instance = this;
        RRSounds.init();
        RRBlocks.initBlocks();
        RRItems.initItems();
        RREntities.registerEntities();
        proxy.registerRenderStuff(event);
        PacketDispatcher.registerPackets();
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new RivalRebelsGuiHandler());
    }

    @SuppressWarnings(value = "unused")
    @Mod.EventHandler
    public void load(FMLInitializationEvent event) {
        RivalRebelsRecipes.init();

    }

    @SuppressWarnings(value = "unused")
    @Mod.EventHandler
    public void serverStart(FMLServerStartingEvent event) {
        event.registerServerCommand(new RRConfig());
    }
}
