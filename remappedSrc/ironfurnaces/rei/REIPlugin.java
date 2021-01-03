package ironfurnaces.rei;

import ironfurnaces.config.IronFurnacesConfig;
import ironfurnaces.gui.*;
import ironfurnaces.init.Reference;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.EntryStack;
import me.shedaniel.rei.api.RecipeHelper;
import me.shedaniel.rei.api.plugins.REIPluginV0;
import me.shedaniel.rei.plugin.DefaultPlugin;
import net.minecraft.util.Identifier;

public class REIPlugin implements REIPluginV0 {

    @Override
    public Identifier getPluginIdentifier() {
        return new Identifier(Reference.MOD_ID, "ironfurnaces_plugin");
    }

    @Override
    public void registerOthers(RecipeHelper recipeHelper) {
        if (IronFurnacesConfig.enableCatalysts) {
            recipeHelper.registerWorkingStations(DefaultPlugin.SMELTING, EntryStack.create(Reference.IRON_FURNACE));
            recipeHelper.registerWorkingStations(DefaultPlugin.SMELTING, EntryStack.create(Reference.GOLD_FURNACE));
            recipeHelper.registerWorkingStations(DefaultPlugin.SMELTING, EntryStack.create(Reference.DIAMOND_FURNACE));
            recipeHelper.registerWorkingStations(DefaultPlugin.SMELTING, EntryStack.create(Reference.EMERALD_FURNACE));
            recipeHelper.registerWorkingStations(DefaultPlugin.SMELTING, EntryStack.create(Reference.CRYSTAL_FURNACE));
            recipeHelper.registerWorkingStations(DefaultPlugin.SMELTING, EntryStack.create(Reference.OBSIDIAN_FURNACE));
            recipeHelper.registerWorkingStations(DefaultPlugin.SMELTING, EntryStack.create(Reference.NETHERITE_FURNACE));
            recipeHelper.registerWorkingStations(DefaultPlugin.SMELTING, EntryStack.create(Reference.COPPER_FURNACE));
            recipeHelper.registerWorkingStations(DefaultPlugin.SMELTING, EntryStack.create(Reference.SILVER_FURNACE));

            recipeHelper.registerWorkingStations(DefaultPlugin.FUEL, EntryStack.create(Reference.IRON_FURNACE));
            recipeHelper.registerWorkingStations(DefaultPlugin.FUEL, EntryStack.create(Reference.GOLD_FURNACE));
            recipeHelper.registerWorkingStations(DefaultPlugin.FUEL, EntryStack.create(Reference.DIAMOND_FURNACE));
            recipeHelper.registerWorkingStations(DefaultPlugin.FUEL, EntryStack.create(Reference.EMERALD_FURNACE));
            recipeHelper.registerWorkingStations(DefaultPlugin.FUEL, EntryStack.create(Reference.CRYSTAL_FURNACE));
            recipeHelper.registerWorkingStations(DefaultPlugin.FUEL, EntryStack.create(Reference.OBSIDIAN_FURNACE));
            recipeHelper.registerWorkingStations(DefaultPlugin.FUEL, EntryStack.create(Reference.NETHERITE_FURNACE));
            recipeHelper.registerWorkingStations(DefaultPlugin.FUEL, EntryStack.create(Reference.COPPER_FURNACE));
            recipeHelper.registerWorkingStations(DefaultPlugin.FUEL, EntryStack.create(Reference.SILVER_FURNACE));

            recipeHelper.registerWorkingStations(DefaultPlugin.BLASTING, EntryStack.create(Reference.BLASTING_AUGMENT));
            recipeHelper.registerWorkingStations(DefaultPlugin.SMOKING, EntryStack.create(Reference.SMOKING_AUGMENT));
        }
    }

    @Override
    public void registerRecipeDisplays(RecipeHelper recipeHelper) {
        if (IronFurnacesConfig.enableClickArea)
        {
            recipeHelper.registerContainerClickArea(new Rectangle(79, 35, 24, 17), BlockIronFurnaceScreen.class, DefaultPlugin.SMELTING);
            recipeHelper.registerContainerClickArea(new Rectangle(79, 35, 24, 17), BlockGoldFurnaceScreen.class, DefaultPlugin.SMELTING);
            recipeHelper.registerContainerClickArea(new Rectangle(79, 35, 24, 17), BlockDiamondFurnaceScreen.class, DefaultPlugin.SMELTING);
            recipeHelper.registerContainerClickArea(new Rectangle(79, 35, 24, 17), BlockEmeraldFurnaceScreen.class, DefaultPlugin.SMELTING);
            recipeHelper.registerContainerClickArea(new Rectangle(79, 35, 24, 17), BlockCrystalFurnaceScreen.class, DefaultPlugin.SMELTING);
            recipeHelper.registerContainerClickArea(new Rectangle(79, 35, 24, 17), BlockObsidianFurnaceScreen.class, DefaultPlugin.SMELTING);
            recipeHelper.registerContainerClickArea(new Rectangle(79, 35, 24, 17), BlockNetheriteFurnaceScreen.class, DefaultPlugin.SMELTING);
            recipeHelper.registerContainerClickArea(new Rectangle(79, 35, 24, 17), BlockCopperFurnaceScreen.class, DefaultPlugin.SMELTING);
            recipeHelper.registerContainerClickArea(new Rectangle(79, 35, 24, 17), BlockSilverFurnaceScreen.class, DefaultPlugin.SMELTING);
        }
    }

}
