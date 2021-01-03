package ironfurnaces.container;

import ironfurnaces.init.Reference;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.PropertyDelegate;

public class BlockObsidianFurnaceScreenHandler extends BlockIronFurnaceScreenHandlerBase {


    public BlockObsidianFurnaceScreenHandler(int syncId, PlayerInventory playerInventory) {
        super(Reference.OBSIDIAN_FURNACE_SCREEN_HANDLER, syncId, playerInventory);
    }

    public BlockObsidianFurnaceScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(Reference.OBSIDIAN_FURNACE_SCREEN_HANDLER, syncId, playerInventory, inventory, propertyDelegate);
    }
}
