package ironfurnaces.blocks;

import ironfurnaces.tileentity.BlockSilverFurnaceTile;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class BlockSilverFurnace extends BlockIronFurnaceBase {

    public static final String SILVER_FURNACE = "silver_furnace";

    public BlockSilverFurnace() {
        super(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK));
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity blockEntity, ItemStack stack) {
        super.afterBreak(world, player, pos, state, blockEntity, stack);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new BlockSilverFurnaceTile();
    }
}
