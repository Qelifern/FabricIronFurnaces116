package ironfurnaces.tileentity;

import ironfurnaces.config.IronFurnacesConfig;
import ironfurnaces.container.BlockNetheriteFurnaceScreenHandler;
import ironfurnaces.init.Reference;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;

public class BlockNetheriteFurnaceTile extends BlockIronFurnaceTileBase {
    public BlockNetheriteFurnaceTile() {
        super(Reference.NETHERITE_FURNACE_TILE);
    }

    @Override
    protected int getCookTimeConfig() {
        return IronFurnacesConfig.netheriteFurnaceSpeed;
    }

    @Override
    public String IgetName() {
        return "container.ironfurnaces.netherite_furnace";
    }

    @Override
    public ScreenHandler IcreateMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new BlockNetheriteFurnaceScreenHandler(i, playerInventory, this, this.propertyDelegate);
    }

}
