package ironfurnaces.rei;

import ironfurnaces.config.IronFurnacesConfig;
import ironfurnaces.gui.*;
import ironfurnaces.init.Reference;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.EntryStack;
import me.shedaniel.rei.api.RecipeHelper;
import me.shedaniel.rei.api.plugins.REIPluginV0;
import net.minecraft.util.Identifier;

public class REIPlugin implements REIPluginV0 {

    @Override
    public Identifier getPluginIdentifier() {
        return new Identifier(Reference.MOD_ID, "ironfurnaces_plugin");
    }

    @Override
    public void registerOthers(RecipeHelper recipeHelper) {
        if (IronFurnacesConfig.enableCatalysts) {
            recipeHelper.registerWorkingStations(new Identifier("minecraft", "plugins/smelting"), EntryStack.create(Reference.IRON_FURNACE));
            recipeHelper.registerWorkingStations(new Identifier("minecraft", "plugins/smelting"), EntryStack.create(Reference.GOLD_FURNACE));
            recipeHelper.registerWorkingStations(new Identifier("minecraft", "plugins/smelting"), EntryStack.create(Reference.DIAMOND_FURNACE));
            recipeHelper.registerWorkingStations(new Identifier("minecraft", "plugins/smelting"), EntryStack.create(Reference.EMERALD_FURNACE));
            recipeHelper.registerWorkingStations(new Identifier("minecraft", "plugins/smelting"), EntryStack.create(Reference.CRYSTAL_FURNACE));
            recipeHelper.registerWorkingStations(new Identifier("minecraft", "plugins/smelting"), EntryStack.create(Reference.OBSIDIAN_FURNACE));
            recipeHelper.registerWorkingStations(new Identifier("minecraft", "plugins/smelting"), EntryStack.create(Reference.NETHERITE_FURNACE));
            recipeHelper.registerWorkingStations(new Identifier("minecraft", "plugins/smelting"), EntryStack.create(Reference.COPPER_FURNACE));
            recipeHelper.registerWorkingStations(new Identifier("minecraft", "plugins/smelting"), EntryStack.create(Reference.SILVER_FURNACE));

            recipeHelper.registerWorkingStations(new Identifier("minecraft", "plugins/fuel"), EntryStack.create(Reference.IRON_FURNACE));
            recipeHelper.registerWorkingStations(new Identifier("minecraft", "plugins/fuel"), EntryStack.create(Reference.GOLD_FURNACE));
            recipeHelper.registerWorkingStations(new Identifier("minecraft", "plugins/fuel"), EntryStack.create(Reference.DIAMOND_FURNACE));
            recipeHelper.registerWorkingStations(new Identifier("minecraft", "plugins/fuel"), EntryStack.create(Reference.EMERALD_FURNACE));
            recipeHelper.registerWorkingStations(new Identifier("minecraft", "plugins/fuel"), EntryStack.create(Reference.CRYSTAL_FURNACE));
            recipeHelper.registerWorkingStations(new Identifier("minecraft", "plugins/fuel"), EntryStack.create(Reference.OBSIDIAN_FURNACE));
            recipeHelper.registerWorkingStations(new Identifier("minecraft", "plugins/fuel"), EntryStack.create(Reference.NETHERITE_FURNACE));
            recipeHelper.registerWorkingStations(new Identifier("minecraft", "plugins/fuel"), EntryStack.create(Reference.COPPER_FURNACE));
            recipeHelper.registerWorkingStations(new Identifier("minecraft", "plugins/fuel"), EntryStack.create(Reference.SILVER_FURNACE));

            recipeHelper.registerWorkingStations(new Identifier("minecraft", "plugins/blasting"), EntryStack.create(Reference.BLASTING_AUGMENT));
            recipeHelper.registerWorkingStations(new Identifier("minecraft", "plugins/smoking"), EntryStack.create(Reference.SMOKING_AUGMENT));
        }
    }

    @Override
    public void registerRecipeDisplays(RecipeHelper recipeHelper) {
        if (IronFurnacesConfig.enableClickArea)
        {
            recipeHelper.registerContainerClickArea(new Rectangle(79, 35, 24, 17), BlockIronFurnaceScreen.class, new Identifier("minecraft", "plugins/smelting"));
            recipeHelper.registerContainerClickArea(new Rectangle(79, 35, 24, 17), BlockGoldFurnaceScreen.class, new Identifier("minecraft", "plugins/smelting"));
            recipeHelper.registerContainerClickArea(new Rectangle(79, 35, 24, 17), BlockDiamondFurnaceScreen.class, new Identifier("minecraft", "plugins/smelting"));
            recipeHelper.registerContainerClickArea(new Rectangle(79, 35, 24, 17), BlockEmeraldFurnaceScreen.class, new Identifier("minecraft", "plugins/smelting"));
            recipeHelper.registerContainerClickArea(new Rectangle(79, 35, 24, 17), BlockCrystalFurnaceScreen.class, new Identifier("minecraft", "plugins/smelting"));
            recipeHelper.registerContainerClickArea(new Rectangle(79, 35, 24, 17), BlockObsidianFurnaceScreen.class, new Identifier("minecraft", "plugins/smelting"));
            recipeHelper.registerContainerClickArea(new Rectangle(79, 35, 24, 17), BlockNetheriteFurnaceScreen.class, new Identifier("minecraft", "plugins/smelting"));
            recipeHelper.registerContainerClickArea(new Rectangle(79, 35, 24, 17), BlockCopperFurnaceScreen.class, new Identifier("minecraft", "plugins/smelting"));
            recipeHelper.registerContainerClickArea(new Rectangle(79, 35, 24, 17), BlockSilverFurnaceScreen.class, new Identifier("minecraft", "plugins/smelting"));
        }
    }

}
