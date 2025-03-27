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
        initPickaxes();
        initShovels();
        initSwords();
        initHoes();
    }

    public static void initSwords() {
        for (Materials material : VALUES) {
            for (String itemID : material.getIngredients().getItems()) {
                if (shouldAdd(itemID)) {
                    if (material.getToolType().hasSword()) {
                        addSword(material.getSwordItem(), itemID);
                    }
                }
            }
        }
    }

    public static void initHoes() {
        for (Materials material : VALUES) {
            for (String itemID : material.getIngredients().getItems()) {
                if (shouldAdd(itemID)) {
                    if (material.getToolType().hasHoe()) {
                        addHoe(material.getHoeItem(), itemID);
                    }
                }
            }
        }
    }

    public static void initShovels() {
        for (Materials material : VALUES) {
            for (String itemID : material.getIngredients().getItems()) {
                if (shouldAdd(itemID)) {
                    if (material.getToolType().hasShovel()) {
                        addShovel(material.getShovelItem(), itemID);
                    }
                }
            }
        }
    }

    public static void initPickaxes() {
        for (Materials material : VALUES) {
            for (String itemID : material.getIngredients().getItems()) {
                if (shouldAdd(itemID)) {
                    if (material.getToolType().hasPickaxe()) {
                        addPick(material.getPickaxeItem(), itemID);
                    }
                }
            }
        }
    }

    public static void initHammers() {
        for (Materials material : VALUES) {
            for (String itemID : material.getIngredients().getItems()) {
                if (shouldAdd(itemID)) {
                    if (material != STEELEAF && material != IRONWOOD && material != FIERY && material != UNSTABLE) {
                        if (material.getToolType().hasHammer()) {
                            addHammer(material.getHammerItem(), itemID);
                        }
                    }
                }
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
        for (Materials material : VALUES) {
            for (String itemID : material.getIngredients().getItems()) {
                if (shouldAdd(itemID)) {
                    if (material != STEELEAF && material != IRONWOOD && material != FIERY && material != UNSTABLE) {
                        if (material.getToolType().hasExcavator()) {
                            addExcavator(material.getExcavatorItem(), itemID);
                        }
                    }
                }
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

    public static void addSword(Item pick, Object material) {
        GameRegistry.addRecipe(new ShapedOreRecipe(pick,
                " X ", " X ", " P ",
                'X', material,
                'P', Item.stick));
    }

    public static void addHoe(Item pick, Object material) {
        GameRegistry.addRecipe(new ShapedOreRecipe(pick,
                "XX ", " P ", " P ",
                'X', material,
                'P', Item.stick));

        GameRegistry.addRecipe(new ShapedOreRecipe(pick,
                " XX", " P ", " P ",
                'X', material,
                'P', Item.stick));
    }

    public static void addShovel(Item pick, Object material) {
        GameRegistry.addRecipe(new ShapedOreRecipe(pick,
                " X ", " P ", " P ",
                'X', material,
                'P', Item.stick));
    }

    public static void addPick(Item pick, Object material) {
        GameRegistry.addRecipe(new ShapedOreRecipe(pick,
                "XXX", " P ", " P ",
                'X', material,
                'P', Item.stick));
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
