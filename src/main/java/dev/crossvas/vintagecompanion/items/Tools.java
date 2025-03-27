package dev.crossvas.vintagecompanion.items;

import cpw.mods.fml.common.registry.GameRegistry;
import dev.crossvas.vintagecompanion.items.base.ItemBaseExcavator;
import dev.crossvas.vintagecompanion.items.base.ItemBaseHammer;
import dev.crossvas.vintagecompanion.items.compat.exu.ItemUnstableExcavator;
import dev.crossvas.vintagecompanion.items.compat.exu.ItemUnstableHammer;
import dev.crossvas.vintagecompanion.items.compat.twilightforest.ItemIronWoodExcavator;
import dev.crossvas.vintagecompanion.items.compat.twilightforest.ItemIronWoodHammer;
import dev.crossvas.vintagecompanion.items.compat.twilightforest.ItemSteeleafExcavator;
import dev.crossvas.vintagecompanion.items.compat.twilightforest.ItemSteeleafHammer;
import dev.crossvas.vintagecompanion.items.compat.twilightforest.fiery.ItemFieryExcavator;
import dev.crossvas.vintagecompanion.items.compat.twilightforest.fiery.ItemFieryHammer;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

import static dev.crossvas.vintagecompanion.Refs.shouldAdd;
import static dev.crossvas.vintagecompanion.Refs.shouldRegister;
import static dev.crossvas.vintagecompanion.items.Materials.*;

public class Tools {

    // HAMMERS
    // Vanilla
    public static Item wood_hammer;
    public static Item stone_hammer;
    public static Item gold_hammer;
    public static Item iron_hammer;
    public static Item diamond_hammer;
    // Base Metals
    public static Item alum_hammer;
    public static Item bronze_hammer;
    public static Item constantan_hammer;
    public static Item copper_hammer;
    public static Item electrum_hammer;
    public static Item invar_hammer;
    public static Item lead_hammer;
    public static Item nickel_hammer;
    public static Item platinum_hammer;
    public static Item silver_hammer;
    public static Item steel_hammer;
    public static Item tin_hammer;
    // Applied Energistics
    public static Item quartz_hammer;
    public static Item certus_quartz_hammer;
    // GEMS
    public static Item ruby_hammer;
    public static Item sapphire_hammer;
    public static Item green_sapphire_hammer;
    // Compat: Mods
    public static Item thaumium_hammer;
    public static Item steeleaf_hammer;
    public static Item ironwood_hammer;
    public static Item fiery_hammer;
    public static Item unstable_hammer;

    // EXCAVATORS
    // Vanilla
    public static Item wood_excavator;
    public static Item stone_excavator;
    public static Item gold_excavator;
    public static Item iron_excavator;
    public static Item diamond_excavator;
    // Base Metals
    public static Item alum_excavator;
    public static Item bronze_excavator;
    public static Item constantan_excavator;
    public static Item copper_excavator;
    public static Item electrum_excavator;
    public static Item invar_excavator;
    public static Item lead_excavator;
    public static Item nickel_excavator;
    public static Item platinum_excavator;
    public static Item silver_excavator;
    public static Item steel_excavator;
    public static Item tin_excavator;
    // Applied Energistics
    public static Item quartz_excavator;
    public static Item certus_quartz_excavator;
    // GEMS
    public static Item ruby_excavator;
    public static Item sapphire_excavator;
    public static Item green_sapphire_excavator;
    // Compat: Mods
    public static Item thaumium_excavator;
    public static Item steeleaf_excavator;
    public static Item ironwood_excavator;
    public static Item fiery_excavator;
    public static Item unstable_excavator;

    public static void init() {
        initHammers();
        initExcavators();
    }

    public static void initHammers() {
        wood_hammer = new ItemBaseHammer(WOOD);
        addHammer(wood_hammer, "plankWood");
        stone_hammer = new ItemBaseHammer(STONE);
        addHammer(stone_hammer, Block.cobblestone);
        gold_hammer = new ItemBaseHammer(GOLD);
        addHammer(gold_hammer, Item.ingotGold);
        iron_hammer = new ItemBaseHammer(IRON);
        addHammer(iron_hammer, Item.ingotIron);
        diamond_hammer = new ItemBaseHammer(DIAMOND);
        addHammer(diamond_hammer, Item.diamond);

        if (shouldAdd("ingotAluminium") || shouldAdd("ingotAluminum")) {
            alum_hammer = new ItemBaseHammer(ALUM);
            if (shouldAdd("ingotAluminium")) {
                addHammer(alum_hammer, "ingotAluminium");
            }
            if (shouldAdd("ingotAluminum")) {
                addHammer(alum_hammer, "ingotAluminum");
            }
        }
        if (shouldAdd("ingotBronze")) {
            bronze_hammer = new ItemBaseHammer(BRONZE);
            addHammer(bronze_hammer, "ingotBronze");
        }
        if (shouldAdd("ingotCupronickel") || shouldAdd("ingotConstantan")) {
            constantan_hammer = new ItemBaseHammer(CONSTANTAN);
            if (shouldAdd("ingotCupronickel")) {
                addHammer(constantan_hammer, "ingotCupronickel");
            }
            if (shouldAdd("ingotConstantan")) {
                addHammer(constantan_hammer, "ingotConstantan");
            }
        }
        if (shouldAdd("ingotCopper")) {
            copper_hammer = new ItemBaseHammer(COPPER);
            addHammer(copper_hammer, "ingotCopper");
        }
        if (shouldAdd("ingotElectrum")) {
            electrum_hammer = new ItemBaseHammer(ELECTRUM);
            addHammer(electrum_hammer, "ingotElectrum");
        }
        if (shouldAdd("ingotInvar")) {
            invar_hammer = new ItemBaseHammer(INVAR);
            addHammer(invar_hammer, "ingotInvar");
        }
        if (shouldAdd("ingotLead")) {
            lead_hammer = new ItemBaseHammer(LEAD);
            addHammer(lead_hammer, "ingotLead");
        }
        if (shouldAdd("ingotNickel")) {
            nickel_hammer = new ItemBaseHammer(NICKEL);
            addHammer(nickel_hammer, "ingotNickel");
        }
        if (shouldAdd("ingotPlatinum")) {
            platinum_hammer = new ItemBaseHammer(PLATINUM);
            addHammer(platinum_hammer, "ingotPlatinum");
        }
        if (shouldAdd("ingotSilver")) {
            silver_hammer = new ItemBaseHammer(SILVER);
            addHammer(silver_hammer, "ingotSilver");
        }
        if (shouldAdd("ingotSteel")) {
            steel_hammer = new ItemBaseHammer(STEEL);
            addHammer(steel_hammer, "ingotSteel");
        }
        if (shouldAdd("ingotTin")) {
            tin_hammer = new ItemBaseHammer(TIN);
            addHammer(tin_hammer, "ingotTin");
        }

        quartz_hammer = new ItemBaseHammer(NETHER_QUARTZ);
        addHammer(quartz_hammer, Item.netherQuartz);

        if (shouldAdd("crystalCertusQuartz")) {
            certus_quartz_hammer = new ItemBaseHammer(CERTUS_QUARTZ);
            addHammer(certus_quartz_hammer, "crystalCertusQuartz");
        }

        if (shouldAdd("gemRuby")) {
            ruby_hammer = new ItemBaseHammer(RUBY);
            addHammer(ruby_hammer, "gemRuby");
        }
        if (shouldAdd("gemSapphire")) {
            sapphire_hammer = new ItemBaseHammer(SAPPHIRE);
            addHammer(sapphire_hammer, "gemSapphire");
        }
        if (shouldAdd("gemGreenSapphire")) {
            green_sapphire_hammer = new ItemBaseHammer(GREEN_SAPPHIRE);
            addHammer(green_sapphire_hammer, "gemGreenSapphire");
        }

        if (shouldRegister("Thaumcraft")) {
            if (shouldAdd("ingotThaumium")) {
                thaumium_hammer = new ItemBaseHammer(THAUMIUM);
                addHammer(thaumium_hammer, "ingotThaumium");
            }
        }
        if (shouldRegister("TwilightForest")) {
            if (shouldAdd("ingotSteeleaf")) {
                steeleaf_hammer = new ItemSteeleafHammer();
                ItemStack hammerStack = new ItemStack(steeleaf_hammer);
                hammerStack.addEnchantment(Enchantment.fortune, 2);
                addHammer(hammerStack, "ingotSteeleaf");
            }
            if (shouldAdd("ingotIronwood")) {
                ironwood_hammer = new ItemIronWoodHammer();
                ItemStack hammerStack = new ItemStack(ironwood_hammer);
                hammerStack.addEnchantment(Enchantment.efficiency, 1);
                addHammer(hammerStack, "ingotIronwood");
            }
            if (shouldAdd("ingotFiery")) {
                fiery_hammer = new ItemFieryHammer();
                GameRegistry.addRecipe(new ShapedOreRecipe(fiery_hammer,
                        "XXX", "XPX", " P ",
                        'X', "ingotFiery",
                        'P', Item.blazeRod));
            }

        }
        if (shouldRegister("ExtraUtilities")) {
            if (shouldAdd("ingotUnstable")) {
                unstable_hammer = new ItemUnstableHammer();
                GameRegistry.addRecipe(new ShapedOreRecipe(unstable_hammer,
                        "XXX", "XPX", " P ",
                        'X', "ingotUnstable",
                        'P', Block.obsidian));
            }
        }
    }

    public static void initExcavators() {
        wood_excavator = new ItemBaseExcavator(WOOD);
        addExcavator(wood_excavator, "plankWood");
        stone_excavator = new ItemBaseExcavator(STONE);
        addExcavator(stone_excavator, Block.cobblestone);
        gold_excavator = new ItemBaseExcavator(GOLD);
        addExcavator(gold_excavator, Item.ingotGold);
        iron_excavator = new ItemBaseExcavator(IRON);
        addExcavator(iron_excavator, Item.ingotIron);
        diamond_excavator = new ItemBaseExcavator(DIAMOND);
        addExcavator(diamond_excavator, Item.diamond);

        if (shouldAdd("ingotAluminium") || shouldAdd("ingotAluminum")) {
            alum_excavator = new ItemBaseExcavator(ALUM);
            if (shouldAdd("ingotAluminium")) {
                addExcavator(alum_excavator, "ingotAluminium");
            }
            if (shouldAdd("ingotAluminum")) {
                addExcavator(alum_excavator, "ingotAluminum");
            }
        }
        if (shouldAdd("ingotBronze")) {
            bronze_excavator = new ItemBaseExcavator(BRONZE);
            addExcavator(bronze_excavator, "ingotBronze");
        }
        if (shouldAdd("ingotCupronickel") || shouldAdd("ingotConstantan")) {
            constantan_excavator = new ItemBaseExcavator(CONSTANTAN);
            if (shouldAdd("ingotCupronickel")) {
                addExcavator(constantan_excavator, "ingotCupronickel");
            }
            if (shouldAdd("ingotConstantan")) {
                addExcavator(constantan_excavator, "ingotConstantan");
            }
        }
        if (shouldAdd("ingotCopper")) {
            copper_excavator = new ItemBaseExcavator(COPPER);
            addExcavator(copper_excavator, "ingotCopper");
        }
        if (shouldAdd("ingotElectrum")) {
            electrum_excavator = new ItemBaseExcavator(ELECTRUM);
            addExcavator(electrum_excavator, "ingotElectrum");
        }
        if (shouldAdd("ingotInvar")) {
            invar_excavator = new ItemBaseExcavator(INVAR);
            addExcavator(invar_excavator, "ingotInvar");
        }
        if (shouldAdd("ingotLead")) {
            lead_excavator = new ItemBaseExcavator(LEAD);
            addExcavator(lead_excavator, "ingotLead");
        }
        if (shouldAdd("ingotNickel")) {
            nickel_excavator = new ItemBaseExcavator(NICKEL);
            addExcavator(nickel_excavator, "ingotNickel");
        }
        if (shouldAdd("ingotPlatinum")) {
            platinum_excavator = new ItemBaseExcavator(PLATINUM);
            addExcavator(platinum_excavator, "ingotPlatinum");
        }
        if (shouldAdd("ingotSilver")) {
            silver_excavator = new ItemBaseExcavator(SILVER);
            addExcavator(silver_excavator, "ingotSilver");
        }
        if (shouldAdd("ingotSteel")) {
            steel_excavator = new ItemBaseExcavator(STEEL);
            addExcavator(steel_excavator, "ingotSteel");
        }
        if (shouldAdd("ingotTin")) {
            tin_excavator = new ItemBaseExcavator(TIN);
            addExcavator(tin_excavator, "ingotTin");
        }

        quartz_excavator = new ItemBaseExcavator(NETHER_QUARTZ);
        addExcavator(quartz_excavator, Item.netherQuartz);

        if (shouldAdd("crystalCertusQuartz")) {
            certus_quartz_excavator = new ItemBaseExcavator(CERTUS_QUARTZ);
            addHammer(certus_quartz_excavator, "crystalCertusQuartz");
        }

        if (shouldAdd("gemRuby")) {
            ruby_excavator = new ItemBaseExcavator(RUBY);
            addExcavator(ruby_excavator, "gemRuby");
        }
        if (shouldAdd("gemSapphire")) {
            sapphire_excavator = new ItemBaseExcavator(SAPPHIRE);
            addExcavator(sapphire_excavator, "gemSapphire");
        }
        if (shouldAdd("gemGreenSapphire")) {
            green_sapphire_excavator = new ItemBaseExcavator(GREEN_SAPPHIRE);
            addExcavator(green_sapphire_excavator, "gemGreenSapphire");
        }

        if (shouldRegister("Thaumcraft")) {
            if (shouldAdd("ingotThaumium")) {
                thaumium_excavator = new ItemBaseExcavator(THAUMIUM);
                addExcavator(thaumium_excavator, "ingotThaumium");
            }
        }
        if (shouldRegister("TwilightForest")) {
            if (shouldAdd("ingotSteeleaf")) {
                steeleaf_excavator = new ItemSteeleafExcavator();
                ItemStack excavatorStack = new ItemStack(steeleaf_excavator);
                excavatorStack.addEnchantment(Enchantment.efficiency, 2);
                addExcavator(excavatorStack, "ingotSteeleaf");
            }
            if (shouldAdd("ingotIronwood")) {
                ironwood_excavator = new ItemIronWoodExcavator();
                ItemStack excavatorStack = new ItemStack(ironwood_excavator);
                excavatorStack.addEnchantment(Enchantment.unbreaking, 1);
                addExcavator(excavatorStack, "ingotIronwood");
            }
            if (shouldAdd("ingotFiery")) {
                fiery_excavator = new ItemFieryExcavator();
                GameRegistry.addRecipe(new ShapedOreRecipe(fiery_excavator,
                        "XXX", "XPX", " P ",
                        'X', "ingotFiery",
                        'P', Item.blazeRod));
            }

        }
        if (shouldRegister("ExtraUtilities")) {
            if (shouldAdd("ingotUnstable")) {
                unstable_excavator = new ItemUnstableExcavator();
                GameRegistry.addRecipe(new ShapedOreRecipe(unstable_excavator,
                        "XXX", "XPX", " P ",
                        'X', "ingotUnstable",
                        'P', Block.obsidian));
            }
        }
    }

    public static void addHammer(Item hammer, Object material) {
        GameRegistry.addRecipe(new ShapedOreRecipe(hammer,
                "XXX", "XPX", " P ",
                'X', material,
                'P', Item.stick));
    }

    public static void addExcavator(Item excavator, Object material) {
        GameRegistry.addRecipe(new ShapedOreRecipe(excavator,
                " X ", "XPX", " P ",
                'X', material,
                'P', Item.stick));
    }

    public static void addHammer(ItemStack hammer, Object material) {
        GameRegistry.addRecipe(new ShapedOreRecipe(hammer,
                "XXX", "XPX", " P ",
                'X', material,
                'P', Item.stick));
    }

    public static void addExcavator(ItemStack excavator, Object material) {
        GameRegistry.addRecipe(new ShapedOreRecipe(excavator,
                " X ", "XPX", " P ",
                'X', material,
                'P', Item.stick));
    }
}
