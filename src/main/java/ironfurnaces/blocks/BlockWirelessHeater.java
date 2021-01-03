package ironfurnaces.blocks;

import ironfurnaces.init.Reference;
import ironfurnaces.tileentity.BlockIronFurnaceTileBase;
import ironfurnaces.tileentity.BlockWirelessHeaterTile;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import team.reborn.energy.Energy;

import java.util.Random;

public class BlockWirelessHeater extends Block implements BlockEntityProvider {

    public static final String HEATER = "block_heater";

    public BlockWirelessHeater() {
        super(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK));
    }


    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new BlockWirelessHeaterTile();
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof BlockWirelessHeaterTile) {
            if (itemStack.hasCustomName()) {
                ((BlockWirelessHeaterTile)blockEntity).setCustomName(itemStack.getName());
            }
            if (itemStack.hasTag()) {
                ((BlockWirelessHeaterTile)blockEntity).setStored(itemStack.getTag().getInt("energy"));
            }
        }

    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack stack = player.getActiveItem().copy();
        if (world.isClient) {
            return ActionResult.SUCCESS;
        } else {
            this.openScreen(world, pos, player);
            return ActionResult.CONSUME;
        }
    }

    protected void openScreen(World world, BlockPos pos, PlayerEntity player) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof BlockWirelessHeaterTile) {
            player.openHandledScreen((NamedScreenHandlerFactory)blockEntity);
        }

    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.isOf(newState.getBlock())) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof BlockIronFurnaceTileBase) {
                ItemScatterer.spawn(world, (BlockPos)pos, (Inventory)((BlockWirelessHeaterTile)blockEntity));
                world.updateComparators(pos, this);
            }
        }
        if (this.hasBlockEntity() && !state.isOf(newState.getBlock())) {
            if (!world.isClient)
            {
                if (world.getBlockEntity(pos) != null)
                {
                    if (world.getBlockEntity(pos) instanceof BlockWirelessHeaterTile)
                    {
                        ItemStack stack = new ItemStack(Reference.WIRELESS_HEATER);
                        stack.getOrCreateTag().putInt("energy", (int)Energy.of(world.getBlockEntity(pos)).getEnergy());
                        Random rand = new Random();
                        world.spawnEntity(new ItemEntity(world, pos.getX() + rand.nextInt(1), pos.getY() + rand.nextInt(1), pos.getZ() + rand.nextInt(1), stack));
                    }
                }
            }
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return ScreenHandler.calculateComparatorOutput(world.getBlockEntity(pos));
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

}
