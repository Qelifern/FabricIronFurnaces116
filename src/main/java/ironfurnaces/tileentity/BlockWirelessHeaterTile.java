package ironfurnaces.tileentity;

import ironfurnaces.container.BlockWirelessHeaterScreenHandler;
import ironfurnaces.init.Reference;
import ironfurnaces.items.ItemHeater;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.Direction;
import team.reborn.energy.EnergySide;
import team.reborn.energy.EnergyStorage;
import team.reborn.energy.EnergyTier;

public class BlockWirelessHeaterTile extends TileEntityInventory implements Tickable, ExtendedScreenHandlerFactory, EnergyStorage, BlockEntityClientSerializable {

    private double energy;
    private int capacity = 100000;



    public BlockWirelessHeaterTile() {
        super(Reference.WIRELESS_HEATER_TILE, 1);
    }


    public double getEnergy()
    {
        return this.energy;
    }

    public int getCapacity()
    {
        return this.capacity;
    }


    @Override
    public void fromClientTag(CompoundTag tag) {
        this.fromTag(world.getBlockState(pos), tag);
        world.updateListeners(pos, world.getBlockState(pos).getBlock().getDefaultState(), world.getBlockState(pos), 3);
        this.markDirty();
    }

    @Override
    public CompoundTag toClientTag(CompoundTag tag) {
        this.markDirty();
        return this.toTag(tag);
    }

    @Override
    public void tick() {
        if (!world.isClient)
        {
            ItemStack stack = this.getStack(0);
            if (!stack.isEmpty()) {
                CompoundTag nbt = new CompoundTag();
                stack.setTag(nbt);
                nbt.putInt("X", this.pos.getX());
                nbt.putInt("Y", this.pos.getY());
                nbt.putInt("Z", this.pos.getZ());
            }
        }
    }

    @Override
    public void fromTag(BlockState state, CompoundTag compound) {
        super.fromTag(state, compound);
        this.energy = compound.getDouble("energy");
        this.capacity = compound.getInt("capacity");
    }

    @Override
    public CompoundTag toTag(CompoundTag compound) {
        super.toTag(compound);
        compound.putDouble("energy", this.energy);
        compound.putInt("capacity", this.capacity);
        return compound;
    }

    @Override
    public int[] IgetSlotsForFace(Direction side) {
        return null;
    }

    @Override
    public boolean IcanExtractItem(int index, ItemStack stack, Direction direction) {
        return true;
    }

    @Override
    public String IgetName() {
        return "container.ironfurnaces.wireless_energy_heater";
    }

    @Override
    public boolean IisItemValidForSlot(int index, ItemStack stack) {
        return stack.getItem() instanceof ItemHeater;
    }

    @Override
    public ScreenHandler IcreateMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new BlockWirelessHeaterScreenHandler(i, playerEntity.inventory, this);
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(pos);
    }

    @Override
    public double getStored(EnergySide face) {
        return energy;
    }

    @Override
    public void setStored(double amount) {
        energy = amount;
        if (energy > getMaxStoredPower())
        {
            energy = getMaxStoredPower();
        }
        if (energy < 0)
        {
            energy = 0;
        }
        this.markDirty();
    }

    @Override
    public double getMaxStoredPower() {
        return capacity;
    }

    @Override
    public EnergyTier getTier() {
        return EnergyTier.INSANE;
    }

    @Override
    public void markDirty() {
        super.markDirty();
        if (hasWorld() && getWorld() instanceof ServerWorld) {
            sync();
        }
    }

}
