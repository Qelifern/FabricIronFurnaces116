package ironfurnaces.container;

import ironfurnaces.items.ItemHeater;
import ironfurnaces.tileentity.BlockWirelessHeaterTile;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class SlotHeater extends Slot {

    private BlockWirelessHeaterTile te;

    public SlotHeater(BlockWirelessHeaterTile te, int slotIndex, int xPosition, int yPosition) {
        super(te, slotIndex, xPosition, yPosition);
    }

    public boolean isItemValid(ItemStack stack) {
        return stack.getItem() instanceof ItemHeater;
    }

}
