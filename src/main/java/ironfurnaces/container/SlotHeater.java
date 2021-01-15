package ironfurnaces.container;

import ironfurnaces.items.ItemHeater;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class SlotHeater extends Slot {

    public SlotHeater(Inventory inv, int slotIndex, int xPosition, int yPosition) {
        super(inv, slotIndex, xPosition, yPosition);
    }


    @Override
    public boolean canInsert(ItemStack stack) {
        return stack.getItem() instanceof ItemHeater;
    }

}
