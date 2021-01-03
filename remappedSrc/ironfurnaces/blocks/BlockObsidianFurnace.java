package ironfurnaces.blocks;

import ironfurnaces.init.Reference;
import ironfurnaces.tileentity.BlockGoldFurnaceTile;
import ironfurnaces.tileentity.BlockIronFurnaceTileBase;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class BlockObsidianFurnace extends BlockIronFurnaceBase {

    public static final String OBSIDIAN_FURNACE = "obsidian_furnace";

    public BlockObsidianFurnace() {
        super(FabricBlockSettings.copyOf(Blocks.OBSIDIAN).hardness(50.0F).resistance(6000.0F));
    }

    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new BlockGoldFurnaceTile();
    }
}
