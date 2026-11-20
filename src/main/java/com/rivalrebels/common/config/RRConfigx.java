package com.rivalrebels.common.config;
import net.minecraftforge.common.config.Configuration;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class RRConfigx {

    public static Configuration config;

    public static boolean infinite_ammo;
    public static boolean old_pill_design;
    public static int plasmoid_decay;
    public static int m202_explosion_size;
    public static int m202_rocket_decay;
    public static int timed_bomb_fuse_seconds;
    public static int timed_bomb_explosion_size;
    public static int bunker_radius;
    public static List<BlockTransformation> blockTransformations = new ArrayList<>();
    public static List<Block> autobuild_unmutable = new ArrayList<>();
    public static List<Block> explosion_unmovable = new ArrayList<>();
    private static String[] autobuildIrreplacableBlockIds = new String[0];
    private static String[] explosionUnmovableBlockIds = new String[0];
    private static boolean blockRegistryReady;

    public static void init(File configFile) {
        if (config == null) {
            config = new Configuration(configFile);
        }

        loadConfig();
    }

    public static void loadConfig() {
        config.load();

        infinite_ammo = config.getBoolean(
            "infinite_ammo",
            Configuration.CATEGORY_GENERAL,
            false,
            "Enables or disables infinite ammo on all RR guns"
        );

        // This is a very bad option right now it it's usable but it does that forge registry error
        old_pill_design = config.getBoolean(
            "old_pill_design",
            Configuration.CATEGORY_GENERAL,
            false,
            "Enables or disables the old pill designs over the new lemonade designs (requires restart)"
        );

        plasmoid_decay = config.getInt(
            "plasmoid_decay",
            Configuration.CATEGORY_GENERAL,
            70,
            20,
            100,
            "Max lifetime in ticks of plasma shots"
        );

        m202_explosion_size = config.getInt(
            "m202_explosion_size",
            Configuration.CATEGORY_GENERAL,
            4,
            1,
            100,
            "Explosion power of m202-rr rockets"
        );
        m202_rocket_decay = config.getInt(
            "m202_rocket_decay",
            Configuration.CATEGORY_GENERAL,
            200,
            1,
            10000000,
            "Max lifetime in ticks of m202-rr rockets"
        );

        timed_bomb_fuse_seconds = config.getInt(
            "timed_bomb_fuse_seconds",
            Configuration.CATEGORY_GENERAL,
            25,
            10,
            300,
            "Fuse duration in seconds for timed bombs"
        );

        timed_bomb_explosion_size = config.getInt(
            "timed_bomb_explosion_size",
            Configuration.CATEGORY_GENERAL,
            6,
            1,
            20,
            "Explosion strength of timed bombs"
        );
    
        bunker_radius = config.getInt(
            "bunker_radius",
            Configuration.CATEGORY_GENERAL,
            10,
            7,
            15,
            "Radius of bunker deployables"
        );

        autobuildIrreplacableBlockIds = config.getStringList(
            "autobuild_irreplacable_blocks",
            Configuration.CATEGORY_GENERAL,
            new String[] {
                //Tough blocks
                "minecraft:bedrock",
                "rivalrebels:reactive",

                // Protected blocks
                "minecraft:chest",
                "minecraft:trapped_chest"
            },
            "List of blocks autobuild is not allowed to overwrite"
        );

        explosionUnmovableBlockIds = config.getStringList(
            "explosion_unmovable",
            Configuration.CATEGORY_GENERAL,
            new String[] {
                "minecraft:bedrock"
            },
            "List of blocks Rival Rebels displacing explosions cannot move"
        );

        String[] transformations = config.getStringList(
            "blockTransformations",
            Configuration.CATEGORY_GENERAL,
            new String[] {
                // Basic / immediate transformations
                "minecraft:air,minecraft:fire,0",
                "minecraft:snow,minecraft:fire,0",
                "minecraft:snow_layer,minecraft:fire,0",
                "minecraft:ice,minecraft:fire,0",
                "minecraft:leaves,minecraft:fire,0",
                "minecraft:leaves2,minecraft:fire,0",
                "minecraft:grass,minecraft:dirt,0",
                "minecraft:mycelium,minecraft:dirt,0",
                "minecraft:dirt,minecraft:fire,0",
                "minecraft:wool,minecraft:fire,0",
                "minecraft:web,minecraft:fire,0",
                "minecraft:tripwire,minecraft:fire,0",
                "minecraft:lava,minecraft:air,0",

                // Tissue
                "minecraft:red_mushroom_block,minecraft:fire,20",
                "minecraft:brown_mushroom_block,minecraft:fire,20",
                "minecraft:sponge,minecraft:fire,20",
                "minecraft:melon_block,minecraft:fire,20",
                "minecraft:pumpkin,minecraft:fire,20",
                "minecraft:lit_pumpkin,minecraft:fire,20",

                // Wood
                "minecraft:crafting_table,minecraft:fire,20",
                "minecraft:noteblock,minecraft:fire,20",
                "minecraft:jukebox,minecraft:fire,20",
                "minecraft:bed,minecraft:fire,30",
                "minecraft:wooden_door,minecraft:fire,30",
                "minecraft:chest,minecraft:fire,30",
                "minecraft:trapped_chest,minecraft:fire,30",

                // Redstone
                "minecraft:observer,minecraft:lava,30",
                "minecraft:dispenser,minecraft:lava,30",
                "minecraft:dropper,minecraft:lava,30",

                // Sand and glass
                "minecraft:sand,minecraft:glass,60",
                "minecraft:glass,minecraft:obsidian,120",
                "minecraft:stained_glass,minecraft:obsidian,120",

                // High resistance blocks
                "minecraft:obsidian,minecraft:lava,90",
                "minecraft:enchanting_table,minecraft:lava,180",
                "minecraft:ender_chest,minecraft:lava,180",

                // Stone and ores
                "minecraft:stone,minecraft:lava,30",
                "minecraft:cobblestone,minecraft:lava,30",
                "minecraft:mossy_cobblestone,minecraft:lava,30",
                "minecraft:stonebrick,minecraft:lava,30",
                "minecraft:monster_egg,minecraft:lava,30",
                "minecraft:stone_stairs,minecraft:lava,30",
                "minecraft:stone_brick_stairs,minecraft:lava,30",
                "minecraft:cobblestone_wall,minecraft:lava,30",

                // Slabs (all variants included)
                "minecraft:stone_slab,minecraft:lava,30",
                "minecraft:double_stone_slab,minecraft:lava,30",
                "minecraft:stone_slab2,minecraft:lava,30",
                "minecraft:double_stone_slab2,minecraft:lava,30",

                // Ores
                "minecraft:iron_ore,minecraft:lava,30",
                "minecraft:redstone_ore,minecraft:lava,30",
                "minecraft:lit_redstone_ore,minecraft:lava,30",
                "minecraft:coal_ore,minecraft:lava,30",
                "minecraft:gold_ore,minecraft:lava,30",
                "minecraft:lapis_ore,minecraft:lava,30",
                "minecraft:emerald_ore,minecraft:lava,30",
                "minecraft:diamond_ore,minecraft:lava,30",

                // Bricks
                "minecraft:brick_block,minecraft:lava,30",
                "minecraft:brick_stairs,minecraft:lava,30",

                // Glazed terracotta
                "minecraft:red_glazed_terracotta,minecraft:lava,30",
                "minecraft:orange_glazed_terracotta,minecraft:lava,30",
                "minecraft:yellow_glazed_terracotta,minecraft:lava,30",
                "minecraft:lime_glazed_terracotta,minecraft:lava,30",
                "minecraft:green_glazed_terracotta,minecraft:lava,30",
                "minecraft:light_blue_glazed_terracotta,minecraft:lava,30",
                "minecraft:cyan_glazed_terracotta,minecraft:lava,30",
                "minecraft:blue_glazed_terracotta,minecraft:lava,30",
                "minecraft:purple_glazed_terracotta,minecraft:lava,30",
                "minecraft:magenta_glazed_terracotta,minecraft:lava,30",
                "minecraft:pink_glazed_terracotta,minecraft:lava,30",
                "minecraft:brown_glazed_terracotta,minecraft:lava,30",
                "minecraft:white_glazed_terracotta,minecraft:lava,30",
                "minecraft:silver_glazed_terracotta,minecraft:lava,30",
                "minecraft:gray_glazed_terracotta,minecraft:lava,30",
                "minecraft:black_glazed_terracotta,minecraft:lava,30",

                // Other stone materials
                "minecraft:gravel,minecraft:lava,30",
                "minecraft:sandstone,minecraft:lava,30",
                "minecraft:red_sandstone,minecraft:lava,30",
                "minecraft:sandstone_stairs,minecraft:lava,30",
                "minecraft:red_sandstone_stairs,minecraft:lava,30",

                // Clay
                "minecraft:clay,minecraft:stained_hardened_clay,30",
                "minecraft:stained_hardened_clay,minecraft:lava,30",

                // Metals
                "minecraft:anvil,minecraft:lava,50",
                "minecraft:iron_block,minecraft:lava,50",
                "minecraft:gold_block,minecraft:lava,50",

                // Bedrock
                "minecraft:bedrock,minecraft:obsidian,500"
            },
            "Flamethrower block transformations: inputBlock,outputBlock,heatResistance"
        );

        blockTransformations.clear();

        for (String entry : transformations) {
            try {
                String[] parts = entry.split(",");

                if (parts.length != 3) {
                    continue;
                }

                Block input = ForgeRegistries.BLOCKS.getValue(
                    new ResourceLocation(parts[0].trim())
                );

                Block output = ForgeRegistries.BLOCKS.getValue(
                    new ResourceLocation(parts[1].trim())
                );

                int resistance = Integer.parseInt(parts[2].trim());

                if (input != null && output != null && resistance >= 0) {
                    blockTransformations.add(
                        new BlockTransformation(input, output, resistance)
                    );
                }

            } catch (Exception e) {
                System.err.println(
                    "[Rival Rebels] Invalid block transformation: " + entry
                );
                e.printStackTrace();
            }
        }



        if (config.hasChanged()) {
            config.save();
        }

        if (blockRegistryReady) {
            resolveAutobuildUnmutable();
            resolveExplosionUnmovable();
        }
    }

    public static void onBlockRegistryReady() {
        blockRegistryReady = true;
        resolveAutobuildUnmutable();
        resolveExplosionUnmovable();
    }

    private static void resolveAutobuildUnmutable() {
        autobuild_unmutable.clear();

        for (String entry : autobuildIrreplacableBlockIds) {
            try {
                ResourceLocation id = new ResourceLocation(entry.trim());
                if (!ForgeRegistries.BLOCKS.containsKey(id)) {
                    System.err.println(
                        "[Rival Rebels] Unknown block in autobuild blacklist: " + entry
                    );
                    continue;
                }

                autobuild_unmutable.add(ForgeRegistries.BLOCKS.getValue(id));
            } catch (IllegalArgumentException e) {
                System.err.println(
                    "[Rival Rebels] Invalid block in autobuild blacklist: " + entry
                );
                e.printStackTrace();
            }
        }
    }

    private static void resolveExplosionUnmovable() {
        explosion_unmovable.clear();

        for (String entry : explosionUnmovableBlockIds) {
            try {
                ResourceLocation id = new ResourceLocation(entry.trim());
                if (!ForgeRegistries.BLOCKS.containsKey(id)) {
                    System.err.println(
                        "[Rival Rebels] Unknown block in explosion unmovable list: " + entry
                    );
                    continue;
                }

                explosion_unmovable.add(ForgeRegistries.BLOCKS.getValue(id));
            } catch (IllegalArgumentException e) {
                System.err.println(
                    "[Rival Rebels] Invalid block in explosion unmovable list: " + entry
                );
                e.printStackTrace();
            }
        }
    }
}