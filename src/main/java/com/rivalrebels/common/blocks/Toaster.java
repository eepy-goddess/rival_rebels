package com.rivalrebels.common.blocks;

import com.rivalrebels.common.init.RivalRebelsDamageSource;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import com.rivalrebels.common.explosion.RivalRebelsExplosion;
import net.minecraft.world.World;

public class Toaster extends RRBlock {
    //I know that the toaster never rotated in the original version, but something about it not rotating bugs me
    public static PropertyDirection facing = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    public Toaster(String name, Material mat){
        super(name, mat);
        this.setDefaultState(this.getBlockState().getBaseState().withProperty(facing, EnumFacing.NORTH));
    }

    @Override
    public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
        if(!playerIn.isSneaking()){
            EntityItem item = new EntityItem(worldIn, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, new ItemStack(Items.BREAD, 1));
            if(!worldIn.isRemote){
                worldIn.spawnEntity(item);
                if(worldIn.rand.nextInt(64) == 0) playerIn.sendStatusMessage(new TextComponentString("§7[".substring(1) + "§4Orders".substring(1) + "§7]".substring(1) + " " + "§cShift-click (Sneak) to pack up toaster.".substring(1)), false);
            }
        } else if(playerIn.isSneaking()){
            worldIn.setBlockToAir(pos);
            EntityItem item = new EntityItem(worldIn, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, new ItemStack(this));
            if(!worldIn.isRemote){
                worldIn.spawnEntity(item);
            }
        }
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(!playerIn.isSneaking()){
            if(!worldIn.isRemote){
                new RivalRebelsExplosion(worldIn, pos.getX() + 0.5f, pos.getY() + 0.5f, pos.getZ() + 0.5f, 4, false, true, RivalRebelsDamageSource.plasmaexplosion);
            }
            return true;

        }
        return false;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        if(placer instanceof EntityPlayer){
            worldIn.setBlockState(pos, this.getDefaultState().withProperty(facing, placer.getHorizontalFacing().getOpposite()));
        }
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{facing});
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing front = EnumFacing.getFront(meta);
        if(front.getAxis() == EnumFacing.Axis.Y){
            front = EnumFacing.NORTH;
        }
        return this.getDefaultState().withProperty(facing, front);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(facing).getIndex();
    }

    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot) {
        return state.withProperty(facing, rot.rotate(state.getValue(facing)));
    }

    @Override
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
        return state.withRotation(mirrorIn.toRotation(state.getValue(facing)));
    }

}
