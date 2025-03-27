package vintage.mods.companion.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

import static vintage.mods.companion.Refs.shouldAdd;
import static vintage.mods.companion.Refs.shouldRegister;
import static vintage.mods.companion.items.Materials.*;

public class Tools {

    public static void init() {
        initHammers();
        initExcavators();
    }

    public static void initHammers() {
        addHammer(WOOD.getHammerItem(), "plankWood");
        addHammer(STONE.getHammerItem(), Block.cobblestone);
        addHammer(GOLD.getHammerItem(), Item.ingotGold);
        addHammer(IRON.getHammerItem(), Item.ingotIron);
        addHammer(DIAMOND.getHammerItem(), Item.diamond);

        if (shouldAdd("ingotAluminium") || shouldAdd("ingotAluminum")) {
            Item alum_hammer = ALUM.getHammerItem();
            if (shouldAdd("ingotAluminium")) {
                addHammer(ALUM.getHammerItem(), "ingotAluminium");
            }
            if (shouldAdd("ingotAluminum")) {
                addHammer(alum_hammer, "ingotAluminum");
            }
        }
        if (shouldAdd("ingotBronze")) {
            addHammer(BRONZE.getHammerItem(), "ingotBronze");
        }
        if (shouldAdd("ingotCupronickel") || shouldAdd("ingotConstantan")) {
            Item constantan_hammer = CONSTANTAN.getHammerItem();
            if (shouldAdd("ingotCupronickel")) {
                addHammer(constantan_hammer, "ingotCupronickel");
            }
            if (shouldAdd("ingotConstantan")) {
                addHammer(constantan_hammer, "ingotConstantan");
            }
        }
        if (shouldAdd("ingotCopper")) {
            addHammer(COPPER.getHammerItem(), "ingotCopper");
        }
        if (shouldAdd("ingotElectrum")) {
            addHammer(ELECTRUM.getHammerItem(), "ingotElectrum");
        }
        if (shouldAdd("ingotInvar")) {
            addHammer(INVAR.getHammerItem(), "ingotInvar");
        }
        if (shouldAdd("ingotLead")) {
            addHammer(LEAD.getHammerItem(), "ingotLead");
        }
        if (shouldAdd("ingotNickel")) {
            addHammer(NICKEL.getHammerItem(), "ingotNickel");
        }
        if (shouldAdd("ingotPlatinum")) {
            addHammer(PLATINUM.getHammerItem(), "ingotPlatinum");
        }
        if (shouldAdd("ingotSilver")) {
            addHammer(SILVER.getHammerItem(), "ingotSilver");
        }
        if (shouldAdd("ingotSteel")) {
            addHammer(STEEL.getHammerItem(), "ingotSteel");
        }
        if (shouldAdd("ingotTin")) {
            addHammer(TIN.getHammerItem(), "ingotTin");
        }

        addHammer(NETHER_QUARTZ.getHammerItem(), Item.netherQuartz);

        if (shouldAdd("crystalCertusQuartz")) {
            addHammer(CERTUS_QUARTZ.getHammerItem(), "crystalCertusQuartz");
        }

        if (shouldAdd("gemRuby")) {
            addHammer(RUBY.getHammerItem(), "gemRuby");
        }
        if (shouldAdd("gemSapphire")) {
            addHammer(SAPPHIRE.getHammerItem(), "gemSapphire");
        }
        if (shouldAdd("gemGreenSapphire")) {
            addHammer(GREEN_SAPPHIRE.getHammerItem(), "gemGreenSapphire");
        }

        if (shouldRegister("Thaumcraft")) {
            if (shouldAdd("ingotThaumium")) {
                addHammer(THAUMIUM.getHammerItem(), "ingotThaumium");
            }
        }
        if (shouldRegister("TwilightForest")) {
            if (shouldAdd("ingotSteeleaf")) {
                ItemStack hammerStack = new ItemStack(STEELEAF.getHammerItem());
                hammerStack.addEnchantment(Enchantment.fortune, 2);
                addHammer(hammerStack, "ingotSteeleaf");
            }
            if (shouldAdd("ingotIronwood")) {
                ItemStack hammerStack = new ItemStack(IRONWOOD.getHammerItem());
                hammerStack.addEnchantment(Enchantment.efficiency, 1);
                addHammer(hammerStack, "ingotIronwood");
            }
            if (shouldAdd("ingotFiery")) {
                GameRegistry.addRecipe(new ShapedOreRecipe(FIERY.getHammerItem(),
                        "XXX", "XPX", " P ",
                        'X', "ingotFiery",
                        'P', Item.blazeRod));
            }
        }
        if (shouldRegister("ExtraUtilities")) {
            if (shouldAdd("ingotUnstable")) {
                GameRegistry.addRecipe(new ShapedOreRecipe(UNSTABLE.getHammerItem(),
                        "XXX", "XPX", " P ",
                        'X', "ingotUnstable",
                        'P', Block.obsidian));
            }
        }
    }

    public static void initExcavators() {
        addExcavator(WOOD.getExcavatorItem(), "plankWood");
        addExcavator(STONE.getExcavatorItem(), Block.cobblestone);
        addExcavator(GOLD.getExcavatorItem(), Item.ingotGold);
        addExcavator(IRON.getExcavatorItem(), Item.ingotIron);
        addExcavator(DIAMOND.getExcavatorItem(), Item.diamond);

        if (shouldAdd("ingotAluminium") || shouldAdd("ingotAluminum")) {
            Item alum_excavator = ALUM.getExcavatorItem();
            if (shouldAdd("ingotAluminium")) {
                addExcavator(alum_excavator, "ingotAluminium");
            }
            if (shouldAdd("ingotAluminum")) {
                addExcavator(alum_excavator, "ingotAluminum");
            }
        }
        if (shouldAdd("ingotBronze")) {
            addExcavator(BRONZE.getExcavatorItem(), "ingotBronze");
        }
        if (shouldAdd("ingotCupronickel") || shouldAdd("ingotConstantan")) {
            Item constantan_excavator = CONSTANTAN.getExcavatorItem();
            if (shouldAdd("ingotCupronickel")) {
                addExcavator(constantan_excavator, "ingotCupronickel");
            }
            if (shouldAdd("ingotConstantan")) {
                addExcavator(constantan_excavator, "ingotConstantan");
            }
        }
        if (shouldAdd("ingotCopper")) {
            addExcavator(COPPER.getExcavatorItem(), "ingotCopper");
        }
        if (shouldAdd("ingotElectrum")) {
            addExcavator(ELECTRUM.getExcavatorItem(), "ingotElectrum");
        }
        if (shouldAdd("ingotInvar")) {
            addExcavator(INVAR.getExcavatorItem(), "ingotInvar");
        }
        if (shouldAdd("ingotLead")) {
            addExcavator(LEAD.getExcavatorItem(), "ingotLead");
        }
        if (shouldAdd("ingotNickel")) {
            addExcavator(NICKEL.getExcavatorItem(), "ingotNickel");
        }
        if (shouldAdd("ingotPlatinum")) {
            addExcavator(PLATINUM.getExcavatorItem(), "ingotPlatinum");
        }
        if (shouldAdd("ingotSilver")) {
            addExcavator(SILVER.getExcavatorItem(), "ingotSilver");
        }
        if (shouldAdd("ingotSteel")) {
            addExcavator(STEEL.getExcavatorItem(), "ingotSteel");
        }
        if (shouldAdd("ingotTin")) {
            addExcavator(TIN.getExcavatorItem(), "ingotTin");
        }

        addExcavator(NETHER_QUARTZ.getExcavatorItem(), Item.netherQuartz);

        if (shouldAdd("crystalCertusQuartz")) {
            addHammer(CERTUS_QUARTZ.getExcavatorItem(), "crystalCertusQuartz");
        }

        if (shouldAdd("gemRuby")) {
            addExcavator(RUBY.getExcavatorItem(), "gemRuby");
        }
        if (shouldAdd("gemSapphire")) {
            addExcavator(SAPPHIRE.getExcavatorItem(), "gemSapphire");
        }
        if (shouldAdd("gemGreenSapphire")) {
            addExcavator(GREEN_SAPPHIRE.getExcavatorItem(), "gemGreenSapphire");
        }

        if (shouldRegister("Thaumcraft")) {
            if (shouldAdd("ingotThaumium")) {
                addExcavator(THAUMIUM.getExcavatorItem(), "ingotThaumium");
            }
        }
        if (shouldRegister("TwilightForest")) {
            if (shouldAdd("ingotSteeleaf")) {
                ItemStack excavatorStack = new ItemStack(STEELEAF.getExcavatorItem());
                excavatorStack.addEnchantment(Enchantment.efficiency, 2);
                addExcavator(excavatorStack, "ingotSteeleaf");
            }
            if (shouldAdd("ingotIronwood")) {
                ItemStack excavatorStack = new ItemStack(IRONWOOD.getExcavatorItem());
                excavatorStack.addEnchantment(Enchantment.unbreaking, 1);
                addExcavator(excavatorStack, "ingotIronwood");
            }
            if (shouldAdd("ingotFiery")) {
                GameRegistry.addRecipe(new ShapedOreRecipe(FIERY.getExcavatorItem(),
                        "XXX", "XPX", " P ",
                        'X', "ingotFiery",
                        'P', Item.blazeRod));
            }

        }
        if (shouldRegister("ExtraUtilities")) {
            if (shouldAdd("ingotUnstable")) {
                GameRegistry.addRecipe(new ShapedOreRecipe(UNSTABLE.getExcavatorItem(),
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
