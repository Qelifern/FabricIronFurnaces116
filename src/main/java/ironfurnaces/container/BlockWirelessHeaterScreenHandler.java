package ironfurnaces.container;

import ironfurnaces.init.Reference;
import ironfurnaces.tileentity.BlockWirelessHeaterTile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class BlockWirelessHeaterScreenHandler extends ScreenHandler {

    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;
    private BlockPos pos;
    protected final World world;
    public BlockWirelessHeaterTile te;
    public int capacity = 32000;

    public BlockWirelessHeaterScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        this(syncId, playerInventory, new SimpleInventory(1), new ArrayPropertyDelegate(1));
        pos = buf.readBlockPos();
        te = (BlockWirelessHeaterTile)playerInventory.player.world.getBlockEntity(pos);
    }

    public BlockWirelessHeaterScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(Reference.WIRELESS_HEATER_SCREEN_HANDLER, syncId);
        pos = BlockPos.ORIGIN;
        checkSize(inventory, 1);
        this.inventory = inventory;
        checkDataCount(propertyDelegate, 1);
        this.propertyDelegate = propertyDelegate;
        this.world = playerInventory.player.world;

        this.addSlot(new SlotHeater(inventory,0, 80, 37));

        int k;
        for(k = 0; k < 3; ++k) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + k * 9 + 9, 8 + j * 18, 84 + k * 18));
            }
        }

        for(k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
        }
        this.addProperties(this.propertyDelegate);
    }

    public int getEnergyScaled(int pixels) {
        int i = this.getEnergy();
        int j = this.capacity;
        return j != 0 && i != 0 ? i * pixels / j : 0;
    }

    public int getEnergy()
    {
        return this.propertyDelegate.get(0);
    }

    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
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
