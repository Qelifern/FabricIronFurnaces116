package ironfurnaces.container;

import ironfurnaces.init.Reference;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.PropertyDelegate;

public class BlockGoldFurnaceScreenHandler extends BlockIronFurnaceScreenHandlerBase {


    public BlockGoldFurnaceScreenHandler(int syncId, PlayerInventory playerInventory) {
        super(Reference.GOLD_FURNACE_SCREEN_HANDLER, syncId, playerInventory);
    }

    public BlockGoldFurnaceScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(Reference.GOLD_FURNACE_SCREEN_HANDLER, syncId, playerInventory, inventory, propertyDelegate);
    }
}
