package ironfurnaces.config;

public class IronFurnacesConfig {

    @Config(config = "ironfurnaces", category = "speeds", key = "iron_furnace_speed",
            comment = " Number of ticks to complete one smelting operation.\n 200 ticks is what a regular furnace takes.\n Default: 160")
    public static int ironFurnaceSpeed = 160;

    @Config(config = "ironfurnaces", category = "speeds", key = "gold_furnace_speed",
            comment = " Number of ticks to complete one smelting operation.\n 200 ticks is what a regular furnace takes.\n Default: 120")
    public static int goldFurnaceSpeed = 120;

    @Config(config = "ironfurnaces", category = "speeds", key = "diamond_furnace_speed",
            comment = " Number of ticks to complete one smelting operation.\n 200 ticks is what a regular furnace takes.\n Default: 80")
    public static int diamondFurnaceSpeed = 80;

    @Config(config = "ironfurnaces", category = "speeds", key = "emerald_furnace_speed",
            comment = " Number of ticks to complete one smelting operation.\n 200 ticks is what a regular furnace takes.\n Default: 40")
    public static int emeraldFurnaceSpeed = 40;

    @Config(config = "ironfurnaces", category = "speeds", key = "obsidian_furnace_speed",
            comment = " Number of ticks to complete one smelting operation.\n 200 ticks is what a regular furnace takes.\n Default: 20")
    public static int obsidianFurnaceSpeed = 20;

    @Config(config = "ironfurnaces", category = "speeds", key = "crystal_furnace_speed",
            comment = " Number of ticks to complete one smelting operation.\n 200 ticks is what a regular furnace takes.\n Default: 40")
    public static int crystalFurnaceSpeed = 40;

    @Config(config = "ironfurnaces", category = "speeds", key = "netherite_furnace_speed",
            comment = " Number of ticks to complete one smelting operation.\n 200 ticks is what a regular furnace takes.\n Default: 5")
    public static int netheriteFurnaceSpeed = 5;

    @Config(config = "ironfurnaces", category = "speeds", key = "copper_furnace_speed",
            comment = " Number of ticks to complete one smelting operation.\n 200 ticks is what a regular furnace takes.\n Default: 180")
    public static int copperFurnaceSpeed = 180;

    @Config(config = "ironfurnaces", category = "speeds", key = "silver_furnace_speed",
            comment = " Number of ticks to complete one smelting operation.\n 200 ticks is what a regular furnace takes.\n Default: 140")
    public static int silverFurnaceSpeed = 140;

    @Config(config = "ironfurnaces", category = "rei", key = "enable_catalysts",
            comment = " Enables or disables the recpie catalysts in REI.\n Default: true")
    public static boolean enableCatalysts = true;

    @Config(config = "ironfurnaces", category = "rei", key = "enable_click_area",
            comment = " Enables or disables the click area in the furance gui for REI.\n Default: true")
    public static boolean enableClickArea = true;

}
