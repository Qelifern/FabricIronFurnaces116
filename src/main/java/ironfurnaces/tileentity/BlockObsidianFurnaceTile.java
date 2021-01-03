package ironfurnaces.tileentity;

import ironfurnaces.config.IronFurnacesConfig;
import ironfurnaces.container.BlockObsidianFurnaceScreenHandler;
import ironfurnaces.init.Reference;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;

public class BlockObsidianFurnaceTile extends BlockIronFurnaceTileBase {
    public BlockObsidianFurnaceTile() {
        super(Reference.OBSIDIAN_FURNACE_TILE);
    }

    @Override
    protected int getCookTimeConfig() {
        return IronFurnacesConfig.obsidianFurnaceSpeed;
    }

    @Override
    public String IgetName() {
        return "container.ironfurnaces.obsidian_furnace";
    }

    @Override
    public ScreenHandler IcreateMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new BlockObsidianFurnaceScreenHandler(i, playerInventory, this, this.propertyDelegate);
    }

}
