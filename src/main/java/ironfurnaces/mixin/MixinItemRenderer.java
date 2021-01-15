package ironfurnaces.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import ironfurnaces.items.ItemEnergyDisplay;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemRenderer.class)
public abstract class MixinItemRenderer {

    @Shadow
    protected abstract void renderGuiQuad(BufferBuilder bufferBuilder_1, int int_1, int int_2, int int_3, int int_4, int int_5, int int_6, int int_7, int int_8);

    @Inject(method = "renderGuiItemOverlay(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/item/ItemStack;IILjava/lang/String;)V", at = @At("HEAD"))
    private void renderGuiItemOverlay(TextRenderer textRenderer, ItemStack stack, int x, int y, @Nullable String string, CallbackInfo info) {
        if (stack.getItem() instanceof ItemEnergyDisplay) {
            ItemEnergyDisplay energyItem = (ItemEnergyDisplay) stack.getItem();
            if (!energyItem.showEnergy(stack)) {
                return;
            }
            RenderSystem.disableDepthTest();
            RenderSystem.disableTexture();
            RenderSystem.disableAlphaTest();
            RenderSystem.disableBlend();

            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder bufferBuilder = tessellator.getBuffer();
            float f = (float)energyItem.getEnergy(stack);
            float g = (float)energyItem.getMaxEnergy(stack);
            int durability = Math.round((f * 13.0F) / g);
            int color = energyItem.getEnergyColor(stack);

            this.renderGuiQuad(bufferBuilder, x + 2, y + 13, 13, 2, 0, 0, 0, 255);
            this.renderGuiQuad(bufferBuilder, x + 2, y + 13, durability, 1, color >> 16 & 255, color >> 8 & 255, color & 255, 255);

            RenderSystem.enableBlend();
            RenderSystem.enableAlphaTest();
            RenderSystem.enableTexture();
            RenderSystem.enableDepthTest();
        }
    }

}
