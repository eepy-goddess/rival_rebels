package com.rivalrebels.common.entity;

import com.rivalrebels.common.packet.EntityDebrisPacket;
import com.rivalrebels.common.packet.PacketDispatcher;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

public class EntityDebris extends Entity {
    public IBlockState			block;
    //This variable is no longer needed as the block metadata system was replaced by the block state system
    //TODO: Remove the metadata variable and all statements that call or initialize it
    public int				metadata;
    public int				ticksExisted;
    public boolean			grounded;
    public NBTTagCompound	tileEntityData;
    protected static final DataParameter<BlockPos> origin = EntityDataManager.<BlockPos>createKey(EntityDebris.class, DataSerializers.BLOCK_POS);

    public EntityDebris(World w)
    {
        super(w);
    }

    public EntityDebris(World w, int x, int y, int z)
    {
        super(w);
        block = w.getBlockState(new BlockPos(x, y, z));
        w.setBlockState(new BlockPos(x, y, z), Blocks.AIR.getDefaultState());
        setSize(1F, 1F);
        setPosition(x + 0.5f, y + 0.5f, z + 0.5f);
        prevPosX = x + 0.5f;
        prevPosY = y + 0.5f;
        prevPosZ = z + 0.5f;
    }
    public EntityDebris(World w, double x, double y, double z, double mx, double my, double mz, IBlockState b)
    {
        super(w);
        block = b;
        metadata = 0;
        setSize(1f, 1F);
        setPosition(x, y, z);
        prevPosX = x;
        prevPosY = y;
        prevPosZ = z;
        motionX = mx;
        motionY = my;
        motionZ = mz;
        setOrigin(new BlockPos(this));
    }

    public void setOrigin(BlockPos pos) {
        this.dataManager.set(origin, pos);
    }

    public BlockPos getOrigin() {
        return (BlockPos) this.dataManager.get(origin);
    }

    @Override
    protected void entityInit()
    {
        this.dataManager.register(origin, BlockPos.ORIGIN);
    }

    @Override
    public void onUpdate()
    {
        if (ticksExisted == 0 && FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) PacketDispatcher.wrapper.sendToAll(new EntityDebrisPacket(this));
        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;
        ++ticksExisted;
        motionY -= 0.04;
        motionX *= 0.98;
        motionY *= 0.98;
        motionZ *= 0.98;
        posX += motionX;
        posY += motionY;
        posZ += motionZ;

        if (!world.isRemote && world.getBlockState(new BlockPos(MathHelper.floor(posX), MathHelper.floor(posY), MathHelper.floor(posZ))).isOpaqueCube()) die(prevPosX, prevPosY, prevPosZ);
    }

    public void die(double X, double Y, double Z)
    {
        int x = MathHelper.floor(X);
        int y = MathHelper.floor(Y);
        int z = MathHelper.floor(Z);
        setDead();
        world.setBlockState(new BlockPos(x, y, z), block, 3);
        if (block.getBlock() instanceof BlockFalling) ((BlockFalling) block.getBlock()).breakBlock(world, new BlockPos(x, y, z), block);
        if (tileEntityData != null && block.getBlock() instanceof ITileEntityProvider)
        {
            TileEntity tileentity = world.getTileEntity(new BlockPos(x, y, z));
            if (tileentity != null)
            {
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                tileentity.writeToNBT(nbttagcompound);
                for (String s : tileEntityData.getKeySet()) {
                    NBTBase nbtbase = tileEntityData.getTag(s);
                    if (!s.equals("x") && !s.equals("y") && !s.equals("z")) {
                        nbttagcompound.setTag(s, nbtbase.copy());
                    }
                }
                tileentity.readFromNBT(nbttagcompound);
                tileentity.markDirty();
            }
        }
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbt)
    {
        nbt.setByte("Tile", (byte) Block.getIdFromBlock(block.getBlock()));
        nbt.setInteger("TileID", Block.getIdFromBlock(block.getBlock()));
        nbt.setByte("Data", (byte) metadata);
        nbt.setByte("Time", (byte) ticksExisted);
        if (tileEntityData != null) nbt.setTag("TileEntityData", tileEntityData);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbt)
    {
        if (nbt.hasKey("TileID", 99)) block = Block.getBlockById(nbt.getInteger("TileID")).getDefaultState();
        else block = Block.getBlockById(nbt.getByte("Tile") & 255).getDefaultState();
        metadata = nbt.getByte("Data") & 255;
        ticksExisted = nbt.getByte("Time") & 255;
        if (nbt.hasKey("TileEntityData", 10)) tileEntityData = nbt.getCompoundTag("TileEntityData");
    }

    @Override
    public void addEntityCrashInfo(CrashReportCategory crash)
    {
        super.addEntityCrashInfo(crash);
        crash.addCrashSection("Immitating block ID", Block.getIdFromBlock(block.getBlock()));
    }

    @Override
    public int getBrightnessForRender() {
        return 10;
    }
}
