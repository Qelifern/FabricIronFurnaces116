package ironfurnaces.container;

import ironfurnaces.init.Reference;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.PropertyDelegate;

public class BlockDiamondFurnaceScreenHandler extends BlockIronFurnaceScreenHandlerBase {


    public BlockDiamondFurnaceScreenHandler(int syncId, PlayerInventory playerInventory) {
        super(Reference.DIAMOND_FURNACE_SCREEN_HANDLER, syncId, playerInventory);
    }

    public BlockDiamondFurnaceScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(Reference.DIAMOND_FURNACE_SCREEN_HANDLER, syncId, playerInventory, inventory, propertyDelegate);
    }
}
