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

public class BlockGoldFurnace extends BlockIronFurnaceBase {

    public static final String GOLD_FURNACE = "gold_furnace";

    public BlockGoldFurnace() {
        super(FabricBlockSettings.copyOf(Blocks.GOLD_BLOCK));
    }

    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new BlockGoldFurnaceTile();
    }
}
