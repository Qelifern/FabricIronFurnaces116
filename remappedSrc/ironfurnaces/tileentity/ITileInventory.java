package ironfurnaces.tileentity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.math.Direction;

public interface ITileInventory {

    public int[] IgetSlotsForFace(Direction side);

    public boolean IcanExtractItem(int index, ItemStack stack, Direction direction);

    public String IgetName();

    public boolean IisItemValidForSlot(int index, ItemStack stack);

    public ScreenHandler IcreateMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity);


}
