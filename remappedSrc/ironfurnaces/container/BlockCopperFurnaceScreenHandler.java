package ironfurnaces.container;

import ironfurnaces.init.Reference;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.screen.PropertyDelegate;

public class BlockCopperFurnaceScreenHandler extends BlockIronFurnaceScreenHandlerBase {


    public BlockCopperFurnaceScreenHandler(int syncId, PlayerInventory playerInventory) {
        super(Reference.COPPER_FURNACE_SCREEN_HANDLER, syncId, playerInventory);
    }

    public BlockCopperFurnaceScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(Reference.COPPER_FURNACE_SCREEN_HANDLER, syncId, playerInventory, inventory, propertyDelegate);
    }

}
