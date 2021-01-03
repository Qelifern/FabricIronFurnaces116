package ironfurnaces.container;

import ironfurnaces.init.Reference;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.PropertyDelegate;

public class BlockIronFurnaceScreenHandler extends BlockIronFurnaceScreenHandlerBase {


    public BlockIronFurnaceScreenHandler(int syncId, PlayerInventory playerInventory) {
        super(Reference.IRON_FURNACE_SCREEN_HANDLER, syncId, playerInventory);
    }

    public BlockIronFurnaceScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(Reference.IRON_FURNACE_SCREEN_HANDLER, syncId, playerInventory, inventory, propertyDelegate);
    }
}
