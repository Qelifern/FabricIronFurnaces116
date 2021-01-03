package ironfurnaces.items;

import ironfurnaces.init.Reference;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemHeater extends Item {


    public ItemHeater() {
        super(new Settings().group(Reference.itemGroup));
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        if (stack.hasTag()) {
            tooltip.add(new TranslatableText("tooltip." + Reference.MOD_ID + ".heater").setStyle(Style.EMPTY.withFormatting((Formatting.GRAY))));
            tooltip.add(new TranslatableText("tooltip." + Reference.MOD_ID + ".heaterX").setStyle(Style.EMPTY.withFormatting((Formatting.GRAY))).append(new LiteralText("" + stack.getTag().getInt("X")).setStyle(Style.EMPTY.withFormatting((Formatting.GRAY)))));
            tooltip.add(new TranslatableText("tooltip." + Reference.MOD_ID + ".heaterY").setStyle(Style.EMPTY.withFormatting((Formatting.GRAY))).append(new LiteralText("" + stack.getTag().getInt("Y")).setStyle(Style.EMPTY.withFormatting((Formatting.GRAY)))));
            tooltip.add(new TranslatableText("tooltip." + Reference.MOD_ID + ".heaterZ").setStyle(Style.EMPTY.withFormatting((Formatting.GRAY))).append(new LiteralText("" + stack.getTag().getInt("Z")).setStyle(Style.EMPTY.withFormatting((Formatting.GRAY)))));
        } else {
            tooltip.add(new TranslatableText("tooltip." + Reference.MOD_ID + ".heater_not_bound").setStyle(Style.EMPTY.withFormatting((Formatting.GRAY))));
            tooltip.add(new TranslatableText("tooltip." + Reference.MOD_ID + ".heater_tip").setStyle(Style.EMPTY.withFormatting((Formatting.GRAY))));
            tooltip.add(new TranslatableText("tooltip." + Reference.MOD_ID + ".heater_tip1").setStyle(Style.EMPTY.withFormatting((Formatting.GRAY))));
        }
    }
}
