package ironfurnaces.tileentity;

import ironfurnaces.config.IronFurnacesConfig;
import ironfurnaces.container.BlockEmeraldFurnaceScreenHandler;
import ironfurnaces.init.Reference;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;

public class BlockEmeraldFurnaceTile extends BlockIronFurnaceTileBase {
    public BlockEmeraldFurnaceTile() {
        super(Reference.EMERALD_FURNACE_TILE);
    }

    @Override
    protected int getCookTimeConfig() {
        return IronFurnacesConfig.emeraldFurnaceSpeed;
    }

    @Override
    public String IgetName() {
        return "container.ironfurnaces.emerald_furnace";
    }

    @Override
    public ScreenHandler IcreateMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new BlockEmeraldFurnaceScreenHandler(i, playerInventory, this, this.propertyDelegate);
    }

}
