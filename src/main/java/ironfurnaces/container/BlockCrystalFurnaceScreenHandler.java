package ironfurnaces.container;

import ironfurnaces.init.Reference;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.PropertyDelegate;

public class BlockCrystalFurnaceScreenHandler extends BlockIronFurnaceScreenHandlerBase {


    public BlockCrystalFurnaceScreenHandler(int syncId, PlayerInventory playerInventory) {
        super(Reference.CRYSTAL_FURNACE_SCREEN_HANDLER, syncId, playerInventory);
    }

    public BlockCrystalFurnaceScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(Reference.CRYSTAL_FURNACE_SCREEN_HANDLER, syncId, playerInventory, inventory, propertyDelegate);
    }
}
