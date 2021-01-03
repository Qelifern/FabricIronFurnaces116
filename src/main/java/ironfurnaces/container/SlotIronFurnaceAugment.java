package ironfurnaces.container;

import ironfurnaces.items.ItemAugment;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class SlotIronFurnaceAugment extends Slot {

    private final BlockIronFurnaceScreenHandlerBase handler;

    public SlotIronFurnaceAugment(BlockIronFurnaceScreenHandlerBase handler, Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
        this.handler = handler;
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return stack.getItem() instanceof ItemAugment;
    }

    @Override
    public int getMaxItemCount(ItemStack stack) {
        return 1;
    }
}
