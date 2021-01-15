package ironfurnaces.items;

import net.minecraft.item.ItemStack;

public interface ItemEnergyDisplay {

    default double getEnergy(ItemStack stack) {
        return 0;
    }

    default double getMaxEnergy(ItemStack stack) {
        return 0;
    }

    default boolean showEnergy(ItemStack stack) {
        return false;
    }

    default int getEnergyColor(ItemStack stack) {
        return 0;
    }

}
