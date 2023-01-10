package com.rivalrebels.common.items;

import com.rivalrebels.common.blocks.Explosives;
import com.rivalrebels.common.init.RRBlocks;
import com.rivalrebels.common.init.RRSounds;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class Remote extends RRItem {
    public Remote(String name) {
        super(name);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = player.getHeldItem(hand);
        if(worldIn.getBlockState(pos.add(0, 1, 0)) == Blocks.AIR.getDefaultState()) {
            if (player.capabilities.isCreativeMode || player.inventory.hasItemStack(new ItemStack(RRBlocks.plastic_explosives))) {
                stack.setTagCompound(new NBTTagCompound());
                if (stack.getTagCompound() != null) {
                    stack.getTagCompound().setInteger("remotex", pos.getX());
                    stack.getTagCompound().setInteger("remotey", pos.getY() + 1);
                    stack.getTagCompound().setInteger("remotez", pos.getZ());
                }
                if (!worldIn.isRemote) {
                    player.sendStatusMessage(new TextComponentString("§7[".substring(1) + "§4Orders".substring(1) + "§7]".substring(1) + "§cShift-click (Sneak) to detonate.".substring(1)), false);
                }
                worldIn.playSound(null, pos, RRSounds.explosives_place, SoundCategory.AMBIENT, 1f, 1f);
                if (!player.capabilities.isCreativeMode) {
                    player.inventory.getStackInSlot(player.inventory.findSlotMatchingUnusedItem(new ItemStack(RRBlocks.plastic_explosives))).shrink(1);
                }
                worldIn.setBlockState(pos.add(0, 1, 0), RRBlocks.plastic_explosives.getDefaultState());
            }
        }
        return EnumActionResult.SUCCESS;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (stack.getTagCompound() != null) {
            int x = stack.getTagCompound().getInteger("remotex");
            int y = stack.getTagCompound().getInteger("remotey");
            int z = stack.getTagCompound().getInteger("remotez");
            tooltip.add("Detonating at: " + x + ", " + y + ", " + z);
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);

        if(playerIn.isSneaking()) {
            if (stack.getTagCompound() != null) {
                int x = stack.getTagCompound().getInteger("remotex");
                int y = stack.getTagCompound().getInteger("remotey");
                int z = stack.getTagCompound().getInteger("remotez");
                if (worldIn.getBlockState(new BlockPos(x, y, z)) == RRBlocks.plastic_explosives.getDefaultState()) {
                    worldIn.playSound(null, x, y, z, RRSounds.weird_noise, SoundCategory.AMBIENT, 1f, 1f);
                    Explosives.explode(worldIn, new BlockPos(x, y, z));
                }
            }
        }
        return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
    }
}
