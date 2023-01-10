package com.rivalrebels.common.init;

import com.rivalrebels.ModInfo;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RivalRebelsRecipes {
    public static void init(){
        GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.modid, "hydrogen_rod"), new ResourceLocation(ModInfo.modid, "pliers"), new ItemStack(RRItems.hydro_rod), new Object[]{"W", "S", 'S', RRItems.rod, 'W', Items.WATER_BUCKET});
        GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.modid, "pliers"), null, new ItemStack(RRItems.pliers), new Object[]{"s ", " s", 's', Blocks.COBBLESTONE});
        GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.modid, "rod"), new ResourceLocation(ModInfo.modid, "pliers"), new ItemStack(RRItems.rod), new Object[]{"sps", 's', RRBlocks.steel, 'p', RRItems.pliers});
        GameRegistry.addShapedRecipe(new ResourceLocation(ModInfo.modid, "redstone_rod"), new ResourceLocation(ModInfo.modid, "pliers"), new ItemStack(RRItems.redstone_rod), new Object[]{"r", "s", 's', RRItems.rod, 'r', Items.REDSTONE});

    }
}
