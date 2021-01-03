package ironfurnaces.init;

import ironfurnaces.blocks.*;
import ironfurnaces.config.Configuration;
import ironfurnaces.config.IronFurnacesConfig;
import ironfurnaces.container.*;
import ironfurnaces.gui.*;
import ironfurnaces.items.ItemAugmentBlasting;
import ironfurnaces.items.ItemAugmentFuel;
import ironfurnaces.items.ItemAugmentSmoking;
import ironfurnaces.items.ItemAugmentSpeed;
import ironfurnaces.tileentity.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Reference {

    public static final String MOD_ID = "ironfurnaces";

    public static final BlockIronFurnace IRON_FURNACE = new BlockIronFurnace();
    public static BlockEntityType<BlockIronFurnaceTile> IRON_FURNACE_TILE;
    public static ScreenHandlerType<BlockIronFurnaceScreenHandler> IRON_FURNACE_SCREEN_HANDLER;

    public static final BlockGoldFurnace GOLD_FURNACE = new BlockGoldFurnace();
    public static BlockEntityType<BlockGoldFurnaceTile> GOLD_FURNACE_TILE;
    public static ScreenHandlerType<BlockGoldFurnaceScreenHandler> GOLD_FURNACE_SCREEN_HANDLER;

    public static final BlockDiamondFurnace DIAMOND_FURNACE = new BlockDiamondFurnace();
    public static BlockEntityType<BlockDiamondFurnaceTile> DIAMOND_FURNACE_TILE;
    public static ScreenHandlerType<BlockDiamondFurnaceScreenHandler> DIAMOND_FURNACE_SCREEN_HANDLER;

    public static final BlockEmeraldFurnace EMERALD_FURNACE = new BlockEmeraldFurnace();
    public static BlockEntityType<BlockEmeraldFurnaceTile> EMERALD_FURNACE_TILE;
    public static ScreenHandlerType<BlockEmeraldFurnaceScreenHandler> EMERALD_FURNACE_SCREEN_HANDLER;

    public static final BlockObsidianFurnace OBSIDIAN_FURNACE = new BlockObsidianFurnace();
    public static BlockEntityType<BlockObsidianFurnaceTile> OBSIDIAN_FURNACE_TILE;
    public static ScreenHandlerType<BlockObsidianFurnaceScreenHandler> OBSIDIAN_FURNACE_SCREEN_HANDLER;

    public static final BlockCrystalFurnace CRYSTAL_FURNACE = new BlockCrystalFurnace();
    public static BlockEntityType<BlockCrystalFurnaceTile> CRYSTAL_FURNACE_TILE;
    public static ScreenHandlerType<BlockCrystalFurnaceScreenHandler> CRYSTAL_FURNACE_SCREEN_HANDLER;

    public static final BlockNetheriteFurnace NETHERITE_FURNACE = new BlockNetheriteFurnace();
    public static BlockEntityType<BlockNetheriteFurnaceTile> NETHERITE_FURNACE_TILE;
    public static ScreenHandlerType<BlockNetheriteFurnaceScreenHandler> NETHERITE_FURNACE_SCREEN_HANDLER;

    public static final BlockCopperFurnace COPPER_FURNACE = new BlockCopperFurnace();
    public static BlockEntityType<BlockCopperFurnaceTile> COPPER_FURNACE_TILE;
    public static ScreenHandlerType<BlockCopperFurnaceScreenHandler> COPPER_FURNACE_SCREEN_HANDLER;

    public static final BlockSilverFurnace SILVER_FURNACE = new BlockSilverFurnace();
    public static BlockEntityType<BlockSilverFurnaceTile> SILVER_FURNACE_TILE;
    public static ScreenHandlerType<BlockSilverFurnaceScreenHandler> SILVER_FURNACE_SCREEN_HANDLER;

    public static final BlockWirelessHeater WIRELESS_HEATER = new BlockWirelessHeater();
    public static BlockEntityType<BlockWirelessHeaterTile> WIRELESS_HEATER_TILE;
    public static ScreenHandlerType<BlockWirelessHeaterScreenHandler> WIRELESS_HEATER_SCREEN_HANDLER;

    public static final ItemGroup itemGroup = FabricItemGroupBuilder.create(
            new Identifier(MOD_ID, "ironfurnaces"))
            .icon(() -> new ItemStack(IRON_FURNACE))
            .build();

    public static final ItemAugmentBlasting BLASTING_AUGMENT = new ItemAugmentBlasting();
    public static final ItemAugmentSmoking SMOKING_AUGMENT = new ItemAugmentSmoking();
    public static final ItemAugmentSpeed SPEED_AUGMENT = new ItemAugmentSpeed();
    public static final ItemAugmentFuel FUEL_AUGMENT = new ItemAugmentFuel();


    public static void init()
    {
        new Configuration(IronFurnacesConfig.class, MOD_ID);
        registration();

    }

    @Environment(EnvType.CLIENT)
    public static void initClient()
    {
        ScreenRegistry.register(IRON_FURNACE_SCREEN_HANDLER, BlockIronFurnaceScreen::new);
        ScreenRegistry.register(GOLD_FURNACE_SCREEN_HANDLER, BlockGoldFurnaceScreen::new);
        ScreenRegistry.register(DIAMOND_FURNACE_SCREEN_HANDLER, BlockDiamondFurnaceScreen::new);
        ScreenRegistry.register(EMERALD_FURNACE_SCREEN_HANDLER, BlockEmeraldFurnaceScreen::new);
        ScreenRegistry.register(OBSIDIAN_FURNACE_SCREEN_HANDLER, BlockObsidianFurnaceScreen::new);
        ScreenRegistry.register(CRYSTAL_FURNACE_SCREEN_HANDLER, BlockCrystalFurnaceScreen::new);
        ScreenRegistry.register(NETHERITE_FURNACE_SCREEN_HANDLER, BlockNetheriteFurnaceScreen::new);
        ScreenRegistry.register(COPPER_FURNACE_SCREEN_HANDLER, BlockCopperFurnaceScreen::new);
        ScreenRegistry.register(SILVER_FURNACE_SCREEN_HANDLER, BlockSilverFurnaceScreen::new);
        ScreenRegistry.register(WIRELESS_HEATER_SCREEN_HANDLER, BlockWirelessHeaterScreen::new);
    }

    public static void registration() {

        registerBlock(BlockIronFurnace.IRON_FURNACE, IRON_FURNACE);
        IRON_FURNACE_TILE = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(Reference.MOD_ID, BlockIronFurnace.IRON_FURNACE), BlockEntityType.Builder.create(BlockIronFurnaceTile::new, IRON_FURNACE).build(null));
        IRON_FURNACE_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier(Reference.MOD_ID, BlockIronFurnace.IRON_FURNACE), BlockIronFurnaceScreenHandler::new);

        registerBlock(BlockGoldFurnace.GOLD_FURNACE, GOLD_FURNACE);
        GOLD_FURNACE_TILE = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(Reference.MOD_ID, BlockGoldFurnace.GOLD_FURNACE), BlockEntityType.Builder.create(BlockGoldFurnaceTile::new, GOLD_FURNACE).build(null));
        GOLD_FURNACE_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier(Reference.MOD_ID, BlockGoldFurnace.GOLD_FURNACE), BlockGoldFurnaceScreenHandler::new);

        registerBlock(BlockDiamondFurnace.DIAMOND_FURNACE, DIAMOND_FURNACE);
        DIAMOND_FURNACE_TILE = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(Reference.MOD_ID, BlockDiamondFurnace.DIAMOND_FURNACE), BlockEntityType.Builder.create(BlockDiamondFurnaceTile::new, DIAMOND_FURNACE).build(null));
        DIAMOND_FURNACE_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier(Reference.MOD_ID, BlockDiamondFurnace.DIAMOND_FURNACE), BlockDiamondFurnaceScreenHandler::new);

        registerBlock(BlockEmeraldFurnace.EMERALD_FURNACE, EMERALD_FURNACE);
        EMERALD_FURNACE_TILE = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(Reference.MOD_ID, BlockEmeraldFurnace.EMERALD_FURNACE), BlockEntityType.Builder.create(BlockEmeraldFurnaceTile::new, EMERALD_FURNACE).build(null));
        EMERALD_FURNACE_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier(Reference.MOD_ID, BlockEmeraldFurnace.EMERALD_FURNACE), BlockEmeraldFurnaceScreenHandler::new);

        registerBlock(BlockCrystalFurnace.CRYSTAL_FURNACE, CRYSTAL_FURNACE);
        CRYSTAL_FURNACE_TILE = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(Reference.MOD_ID, BlockCrystalFurnace.CRYSTAL_FURNACE), BlockEntityType.Builder.create(BlockCrystalFurnaceTile::new, CRYSTAL_FURNACE).build(null));
        CRYSTAL_FURNACE_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier(Reference.MOD_ID, BlockCrystalFurnace.CRYSTAL_FURNACE), BlockCrystalFurnaceScreenHandler::new);

        registerBlock(BlockObsidianFurnace.OBSIDIAN_FURNACE, OBSIDIAN_FURNACE);
        OBSIDIAN_FURNACE_TILE = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(Reference.MOD_ID, BlockObsidianFurnace.OBSIDIAN_FURNACE), BlockEntityType.Builder.create(BlockObsidianFurnaceTile::new, OBSIDIAN_FURNACE).build(null));
        OBSIDIAN_FURNACE_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier(Reference.MOD_ID, BlockObsidianFurnace.OBSIDIAN_FURNACE), BlockObsidianFurnaceScreenHandler::new);

        registerBlock(BlockNetheriteFurnace.NETHERITE_FURNACE, NETHERITE_FURNACE);
        NETHERITE_FURNACE_TILE = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(Reference.MOD_ID, BlockNetheriteFurnace.NETHERITE_FURNACE), BlockEntityType.Builder.create(BlockNetheriteFurnaceTile::new, NETHERITE_FURNACE).build(null));
        NETHERITE_FURNACE_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier(Reference.MOD_ID, BlockNetheriteFurnace.NETHERITE_FURNACE), BlockNetheriteFurnaceScreenHandler::new);

        registerBlock(BlockCopperFurnace.COPPER_FURNACE, COPPER_FURNACE);
        COPPER_FURNACE_TILE = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(Reference.MOD_ID, BlockCopperFurnace.COPPER_FURNACE), BlockEntityType.Builder.create(BlockCopperFurnaceTile::new, COPPER_FURNACE).build(null));
        COPPER_FURNACE_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier(Reference.MOD_ID, BlockCopperFurnace.COPPER_FURNACE), BlockCopperFurnaceScreenHandler::new);

        registerBlock(BlockSilverFurnace.SILVER_FURNACE, SILVER_FURNACE);
        SILVER_FURNACE_TILE = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(Reference.MOD_ID, BlockSilverFurnace.SILVER_FURNACE), BlockEntityType.Builder.create(BlockSilverFurnaceTile::new, SILVER_FURNACE).build(null));
        SILVER_FURNACE_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier(Reference.MOD_ID, BlockSilverFurnace.SILVER_FURNACE), BlockSilverFurnaceScreenHandler::new);

        registerBlock(BlockWirelessHeater.HEATER, WIRELESS_HEATER);
        WIRELESS_HEATER_TILE = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(Reference.MOD_ID, BlockWirelessHeater.HEATER), BlockEntityType.Builder.create(BlockWirelessHeaterTile::new, WIRELESS_HEATER).build(null));
        WIRELESS_HEATER_SCREEN_HANDLER = ScreenHandlerRegistry.registerExtended(new Identifier(Reference.MOD_ID, BlockWirelessHeater.HEATER), BlockWirelessHeaterScreenHandler::new);

        registerItem("augment_blasting", BLASTING_AUGMENT);
        registerItem("augment_smoking", SMOKING_AUGMENT);
        registerItem("augment_speed", SPEED_AUGMENT);
        registerItem("augment_fuel", FUEL_AUGMENT);

    }

    public static void registerBlock(String regName, Block block) {
        Registry.register(Registry.BLOCK, new Identifier(Reference.MOD_ID, regName), block);
        Registry.register(Registry.ITEM, new Identifier(Reference.MOD_ID, regName), new BlockItem(block, new Item.Settings().group(itemGroup)));
    }
    public static void registerItem(String regName, Item item) {
        Registry.register(Registry.ITEM, new Identifier(Reference.MOD_ID, regName), item);
    }


}
