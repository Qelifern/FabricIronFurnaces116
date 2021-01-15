package ironfurnaces.blocks;

import ironfurnaces.tileentity.BlockCopperFurnaceTile;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.BlockView;

public class BlockCopperFurnace extends BlockIronFurnaceBase {

    public static final String COPPER_FURNACE = "copper_furnace";

    public BlockCopperFurnace() {
        super(FabricBlockSettings.copyOf(Blocks.GOLD_BLOCK));
    }

    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new BlockCopperFurnaceTile();
    }
}
