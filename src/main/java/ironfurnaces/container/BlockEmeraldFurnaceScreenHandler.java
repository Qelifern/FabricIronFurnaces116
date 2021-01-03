package ironfurnaces.container;

import ironfurnaces.init.Reference;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.PropertyDelegate;

public class BlockEmeraldFurnaceScreenHandler extends BlockIronFurnaceScreenHandlerBase {


    public BlockEmeraldFurnaceScreenHandler(int syncId, PlayerInventory playerInventory) {
        super(Reference.EMERALD_FURNACE_SCREEN_HANDLER, syncId, playerInventory);
    }

    public BlockEmeraldFurnaceScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(Reference.EMERALD_FURNACE_SCREEN_HANDLER, syncId, playerInventory, inventory, propertyDelegate);
    }
}
