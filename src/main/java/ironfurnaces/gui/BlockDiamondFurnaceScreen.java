package ironfurnaces.gui;

import ironfurnaces.container.BlockDiamondFurnaceScreenHandler;
import ironfurnaces.init.Reference;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class BlockDiamondFurnaceScreen extends BlockIronFurnaceScreenBase<BlockDiamondFurnaceScreenHandler> {

    public BlockDiamondFurnaceScreen(BlockDiamondFurnaceScreenHandler container, PlayerInventory inv, Text name) {
        super(container, inv, name, new Identifier(Reference.MOD_ID,"textures/gui/furnace.png"));
    }
}
