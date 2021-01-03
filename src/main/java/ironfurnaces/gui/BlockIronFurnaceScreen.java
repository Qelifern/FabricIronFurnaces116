package ironfurnaces.gui;

import ironfurnaces.container.BlockIronFurnaceScreenHandler;
import ironfurnaces.init.Reference;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class BlockIronFurnaceScreen extends BlockIronFurnaceScreenBase<BlockIronFurnaceScreenHandler> {

    public BlockIronFurnaceScreen(BlockIronFurnaceScreenHandler container, PlayerInventory inv, Text name) {
        super(container, inv, name, new Identifier(Reference.MOD_ID,"textures/gui/furnace.png"));
    }
}
