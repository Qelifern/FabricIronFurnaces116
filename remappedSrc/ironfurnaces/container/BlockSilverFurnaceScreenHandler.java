package ironfurnaces.container;

import ironfurnaces.init.Reference;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.PropertyDelegate;

public class BlockSilverFurnaceScreenHandler extends BlockIronFurnaceScreenHandlerBase {


    public BlockSilverFurnaceScreenHandler(int syncId, PlayerInventory playerInventory) {
        super(Reference.SILVER_FURNACE_SCREEN_HANDLER, syncId, playerInventory);
    }

    public BlockSilverFurnaceScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(Reference.SILVER_FURNACE_SCREEN_HANDLER, syncId, playerInventory, inventory, propertyDelegate);
    }
}
