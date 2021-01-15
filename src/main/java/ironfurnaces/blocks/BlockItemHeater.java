package ironfurnaces.blocks;

import ironfurnaces.IronFurnacesClient;
import ironfurnaces.init.Reference;
import ironfurnaces.items.ItemEnergyDisplay;
import ironfurnaces.util.StringHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.List;

public class BlockItemHeater extends BlockItem implements ItemEnergyDisplay {
    public BlockItemHeater(Block block, Settings settings) {
        super(block, settings);
    }


    private final int capacity = 100000;

    @Override
    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        if (stack.hasTag())
        {
            tooltip.add(new LiteralText(StringHelper.displayEnergy(stack.getTag().getDouble("energy"), capacity).get(0)).setStyle(Style.EMPTY.withFormatting((Formatting.GOLD))));
        }
        if (IronFurnacesClient.isShiftKeyDown())
        {
            tooltip.add(new TranslatableText("tooltip." + Reference.MOD_ID + ".heater_block").setStyle(Style.EMPTY.withFormatting((Formatting.GRAY))));
            tooltip.add(new TranslatableText("tooltip." + Reference.MOD_ID + ".heater_block1").setStyle(Style.EMPTY.withFormatting((Formatting.GRAY))));
            tooltip.add(new TranslatableText("tooltip." + Reference.MOD_ID + ".heater_block2").setStyle(Style.EMPTY.withFormatting((Formatting.GRAY))));
            tooltip.add(new TranslatableText("tooltip." + Reference.MOD_ID + ".heater_block3").setStyle(Style.EMPTY.withFormatting((Formatting.GRAY))));
        }
        else
        {
            tooltip.add(StringHelper.getShiftInfoText());
        }

    }

    @Override
    public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
        if (!isIn(group)) {
            return;
        }
        ItemStack uncharged = new ItemStack(this);
        ItemStack charged = new ItemStack(this);

        uncharged.getOrCreateTag().putDouble("energy", 0);
        charged.getOrCreateTag().putDouble("energy", capacity);

        stacks.add(uncharged);
        stacks.add(charged);
    }

    @Override
    public double getEnergy(ItemStack stack) {
        if (!stack.hasTag())
        {
            return 0.0;
        }
        return Math.max(stack.getTag().getDouble("energy"), 0.0);
    }

    @Override
    public double getMaxEnergy(ItemStack stack) {
        return 100000;
    }

    @Override
    public boolean showEnergy(ItemStack stack) {
        return stack.hasTag();
    }

    @Override
    public int getEnergyColor(ItemStack stack) {
        return 0xFF800600;
    }

}
