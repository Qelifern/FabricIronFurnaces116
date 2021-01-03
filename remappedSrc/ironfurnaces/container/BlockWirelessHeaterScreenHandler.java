package ironfurnaces.container;

import ironfurnaces.init.Reference;
import ironfurnaces.tileentity.BlockWirelessHeaterTile;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import team.reborn.energy.Energy;


public class BlockWirelessHeaterScreenHandler extends ScreenHandler {

    private final Inventory inventory;
    private BlockPos pos;
    protected final World world;
    private BlockWirelessHeaterTile te;
    private double energy;
    private double capacity;

    public BlockWirelessHeaterScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        this(syncId, playerInventory, new SimpleInventory(1));
        pos = buf.readBlockPos();
    }

    public BlockWirelessHeaterScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(Reference.WIRELESS_HEATER_SCREEN_HANDLER, syncId);
        pos = BlockPos.ORIGIN;
        checkSize(inventory, 1);
        this.inventory = inventory;
        this.world = playerInventory.player.world;
        this.te = (BlockWirelessHeaterTile) world.getBlockEntity(pos);
        this.addSlot(new SlotHeater(te, 0, 80, 37));

        int k;
        for(k = 0; k < 3; ++k) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + k * 9 + 9, 8 + j * 18, 84 + k * 18));
            }
        }

        for(k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
        }

    }

    @Environment(EnvType.CLIENT)
    public int getEnergyScaled(int pixels) {
        double i = this.getEnergy();
        double j = this.getCapacity();
        return (int) (j != 0 && i != 0 ? i * pixels / j : 0);
    }

    @Environment(EnvType.CLIENT)
    public double getEnergy() {
        return energy;
    }

    @Environment(EnvType.CLIENT)
    public double getCapacity() {
        return capacity;
    }

    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    @Override
    public void sendContentUpdates() {
        super.sendContentUpdates();
        if (energy != Energy.of(te).getEnergy())
        {
            energy = Energy.of(te).getEnergy();
        }
        if (capacity != Energy.of(te).getMaxStored())
        {
            capacity = Energy.of(te).getMaxStored();
        }
    }

    @Override
    public ItemStack transferSlot(PlayerEntity player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index < 1) {
                if (!this.insertItem(itemstack1, 1, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(itemstack1, 0, 1, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return itemstack;
    }
}
