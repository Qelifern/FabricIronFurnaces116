package ironfurnaces.tileentity;

import ironfurnaces.container.BlockWirelessHeaterScreenHandler;
import ironfurnaces.init.Reference;
import ironfurnaces.items.ItemHeater;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.Direction;
import team.reborn.energy.EnergySide;
import team.reborn.energy.EnergyStorage;
import team.reborn.energy.EnergyTier;

public class BlockWirelessHeaterTile extends TileEntityInventory implements Tickable, ExtendedScreenHandlerFactory, EnergyStorage {

    private int energy;
    private int capacity = 32000;

    public BlockWirelessHeaterTile() {
        super(Reference.WIRELESS_HEATER_TILE, 1);
    }


    private final PropertyDelegate propertyDelegate = new PropertyDelegate() {
        @Override
        public int get(int index) {
            return (int)BlockWirelessHeaterTile.this.getStored(EnergySide.UNKNOWN);
        }

        @Override
        public void set(int index, int value) {
            BlockWirelessHeaterTile.this.setStored(value);
        }

        //this is supposed to return the amount of integers you have in your delegate, in our example only one
        @Override
        public int size() {
            return 1;
        }
    };

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
        this.energy = compound.getInt("energy");
    }

    @Override
    public CompoundTag toTag(CompoundTag compound) {
        super.toTag(compound);
        compound.putInt("energy", this.energy);
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
        return new BlockWirelessHeaterScreenHandler(i, playerInventory, this, propertyDelegate);
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
        energy = (int)amount;
        if (energy > getMaxStoredPower())
        {
            energy = (int)getMaxStoredPower();
        }
        if (energy < 0)
        {
            energy = 0;
        }
    }

    @Override
    public double getMaxStoredPower() {
        return capacity;
    }

    @Override
    public EnergyTier getTier() {
        return EnergyTier.INFINITE;
    }

}
