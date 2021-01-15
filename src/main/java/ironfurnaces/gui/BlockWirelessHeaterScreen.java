package ironfurnaces.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import ironfurnaces.container.BlockWirelessHeaterScreenHandler;
import ironfurnaces.init.Reference;
import ironfurnaces.tileentity.BlockWirelessHeaterTile;
import ironfurnaces.util.StringHelper;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Optional;

public class BlockWirelessHeaterScreen extends HandledScreen<BlockWirelessHeaterScreenHandler> {

    public static Identifier GUI = new Identifier(Reference.MOD_ID + ":" + "textures/gui/heater.png");
    PlayerInventory playerInv;
    Text name;
    /** The X size of the inventory window in pixels. */
    protected int xSize = 176;
    /** The Y size of the inventory window in pixels. */
    protected int ySize = 166;

    public BlockWirelessHeaterScreen(BlockWirelessHeaterScreenHandler handler, PlayerInventory inv, Text name) {
        super(handler, inv, name);
        playerInv = inv;
        this.name = name;
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(matrices, mouseX, mouseY);
    }

    @Override
    protected void drawForeground(MatrixStack matrices, int mouseX, int mouseY) {
        //drawString(Minecraft.getInstance().fontRenderer, "Energy: " + container.getEnergy(), 10, 10, 0xffffff);
        this.client.textRenderer.draw(matrices, this.playerInv.getDisplayName(), 7, this.ySize - 93, 4210752);
        this.client.textRenderer.draw(matrices, name, this.xSize / 2 - this.client.textRenderer.getWidth(name) / 2, 6, 4210752);

        int actualMouseX = mouseX - ((this.width - this.xSize) / 2);
        int actualMouseY = mouseY - ((this.height - this.ySize) / 2);
        if(actualMouseX >= 65 && actualMouseX <= 111 && actualMouseY >= 64 && actualMouseY <= 76) {
            double energy = this.getEnergy();
            int capacity = this.getCapacity();
            this.renderTooltip(matrices, new LiteralText(StringHelper.displayEnergy(energy, capacity).get(0)).setStyle(Style.EMPTY.withFormatting((Formatting.GOLD))), actualMouseX, actualMouseY);
        }
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.client.getTextureManager().bindTexture(this.GUI);
        int i = (this.width - this.backgroundWidth) / 2;
        int j = (this.height - this.backgroundHeight) / 2;
        this.drawTexture(matrices, i, j, 0, 0, this.backgroundWidth, this.backgroundHeight);
        /**
        int k;
        if (((BlockIronFurnaceScreenHandlerBase)this.handler).isBurning()) {
            k = ((BlockIronFurnaceScreenHandlerBase)this.handler).getFuelProgress();
            this.drawTexture(matrices, i + 56, j + 36 + 12 - k, 176, 12 - k, 14, k + 1);
        }

        k = ((BlockIronFurnaceScreenHandlerBase)this.handler).getCookProgress();
        this.drawTexture(matrices, i + 79, j + 34, 176, 14, k + 1, 16);
        **/
        int k;
        k = this.getEnergyScaled(46);
        this.drawTexture(matrices, i + 65, j + 64, 176, 0, k + 1, 12);

    }

    public int getEnergyScaled(int pixels) {
        double i = this.getEnergy();
        int j = this.getCapacity();
        return (int) (j != 0 && i != 0 ? i * pixels / j : 0);
    }

    public double getEnergy()
    {
        double energy = getEnergy(this.handler).get();
        return energy;
    }

    public int getCapacity()
    {
        int capacity = getCapacity(this.handler).get();
        return capacity;
    }

    private static Optional<Double> getEnergy(ScreenHandler handler) {
        if (handler instanceof BlockWirelessHeaterScreenHandler) {
            BlockPos pos = ((BlockWirelessHeaterScreenHandler) handler).getPos();
            World world = ((BlockWirelessHeaterScreenHandler) handler).getWorld();
            return pos != null ? Optional.of(((BlockWirelessHeaterTile) world.getBlockEntity(pos)).getEnergy()) : Optional.empty();
        } else {
            return Optional.empty();
        }
    }

    private static Optional<Integer> getCapacity(ScreenHandler handler) {
        if (handler instanceof BlockWirelessHeaterScreenHandler) {
            BlockPos pos = ((BlockWirelessHeaterScreenHandler) handler).getPos();
            World world = ((BlockWirelessHeaterScreenHandler) handler).getWorld();
            return pos != null ? Optional.of(((BlockWirelessHeaterTile) world.getBlockEntity(pos)).getCapacity()) : Optional.empty();
        } else {
            return Optional.empty();
        }
    }


}
