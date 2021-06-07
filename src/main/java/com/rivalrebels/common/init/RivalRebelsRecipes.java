package com.rivalrebels.common.init;

import com.rivalrebels.RivalRebels;
import net.minecraft.client.gui.recipebook.RecipeBookPage;
import net.minecraft.client.util.RecipeItemHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RivalRebelsRecipes {
    public static void init(){
        GameRegistry.addShapedRecipe(new ResourceLocation(RivalRebels.modid, "hydrogen_rod"), new ResourceLocation(RivalRebels.modid, "pliers"), new ItemStack(RivalRebels.hydro_rod), new Object[]{"W", "S", 'S', RivalRebels.rod, 'W', Items.WATER_BUCKET});
        GameRegistry.addShapedRecipe(new ResourceLocation(RivalRebels.modid, "pliers"), null, new ItemStack(RivalRebels.pliers), new Object[]{"s ", " s", 's', Blocks.COBBLESTONE});
        GameRegistry.addShapedRecipe(new ResourceLocation(RivalRebels.modid, "rod"), new ResourceLocation(RivalRebels.modid, "pliers"), new ItemStack(RivalRebels.rod), new Object[]{"sps", 's', RivalRebels.steel, 'p', RivalRebels.pliers});
        GameRegistry.addShapedRecipe(new ResourceLocation(RivalRebels.modid, "redstone_rod"), new ResourceLocation(RivalRebels.modid, "pliers"), new ItemStack(RivalRebels.redstone_rod), new Object[]{"r", "s", 's', RivalRebels.rod, 'r', Items.REDSTONE});

    }
}
