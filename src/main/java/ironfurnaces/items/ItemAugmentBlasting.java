package ironfurnaces.items;

import ironfurnaces.IronFurnaces;
import ironfurnaces.init.Reference;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemAugmentBlasting extends ItemAugment {


    @Environment(EnvType.CLIENT)
    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        tooltip.add(new TranslatableText("tooltip." + Reference.MOD_ID + ".augment_blasting_pro").setStyle(Style.EMPTY.withFormatting((Formatting.GREEN))));
        tooltip.add(new TranslatableText("tooltip." + Reference.MOD_ID + ".augment_blasting_con").setStyle(Style.EMPTY.withFormatting(Formatting.DARK_RED)));

    }
}
