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
        for (Materials material : VALUES) {
            for (String itemID : material.getIngredients().getItems()) {
                if (shouldAdd(itemID)) {
                    if (material.getToolType().hasPickaxe()) {
                        addPick(material.getPickaxeItem(), itemID);
                    }
                    if (material.getToolType().hasShovel()) {
                        addShovel(material.getShovelItem(), itemID);
                    }
                    if (material.getToolType().hasSword()) {
                        addSword(material.getSwordItem(), itemID);
                    }
                    if (material.getToolType().hasHoe()) {
                        addHoe(material.getHoeItem(), itemID);
                    }
                    if (material.getToolType().hasAxe()) {
                        addAxe(material.getAxeItem(), itemID);
                    }
                    if (material.getToolType().hasShears()) {
                        addShears(material.getShearsItem(), itemID);
                    }
                    if (material != STEELEAF && material != IRONWOOD && material != FIERY && material != UNSTABLE) {
                        if (material.getToolType().hasHammer()) {
                            addHammer(material.getHammerItem(), itemID);
                        }
                        if (material.getToolType().hasExcavator()) {
                            addExcavator(material.getExcavatorItem(), itemID);
                        }
                        if (material.getToolType().hasSickle()) {
                            addSickle(material.getSickleItem(), itemID);
                        }
                    }
                    if (material != UNSTABLE) {
                        if (material.getToolType().hasBow()) {
                            addBow(material.getBowItem(), itemID);
                        }
                    }
                }
            }
        }
        initEXUTools();
        initTwilightForestTools();
    }

    public static void initTwilightForestTools() {
        if (shouldRegister("TwilightForest")) {
            if (shouldAdd("ingotSteeleaf")) {
                ItemStack hammerStack = new ItemStack(STEELEAF.getHammerItem());
                hammerStack.addEnchantment(Enchantment.fortune, 2);
                addHammer(hammerStack, "ingotSteeleaf");

                ItemStack excavatorStack = new ItemStack(STEELEAF.getExcavatorItem());
                excavatorStack.addEnchantment(Enchantment.efficiency, 2);
                addExcavator(excavatorStack, "ingotSteeleaf");

                ItemStack itemStack = new ItemStack(STEELEAF.getSickleItem());
                itemStack.addEnchantment(Enchantment.fortune, 2);
                addSickle(itemStack, "ingotSteeleaf");

            }
            if (shouldAdd("ingotIronwood")) {
                ItemStack hammerStack = new ItemStack(IRONWOOD.getHammerItem());
                hammerStack.addEnchantment(Enchantment.efficiency, 1);
                addHammer(hammerStack, "ingotIronwood");

                ItemStack excavatorStack = new ItemStack(IRONWOOD.getExcavatorItem());
                excavatorStack.addEnchantment(Enchantment.unbreaking, 1);
                addExcavator(excavatorStack, "ingotIronwood");

                ItemStack itemStack = new ItemStack(IRONWOOD.getSickleItem());
                itemStack.addEnchantment(Enchantment.efficiency, 1);
                addSickle(itemStack, "ingotIronwood");
            }
            if (shouldAdd("ingotFiery")) {
                GameRegistry.addRecipe(new ShapedOreRecipe(FIERY.getHammerItem(),
                        "XXX", "XPX", " P ",
                        'X', "ingotFiery",
                        'P', Item.blazeRod));

                GameRegistry.addRecipe(new ShapedOreRecipe(FIERY.getExcavatorItem(),
                        "XXX", "XPX", " P ",
                        'X', "ingotFiery",
                        'P', Item.blazeRod));

                GameRegistry.addRecipe(new ShapedOreRecipe(FIERY.getSickleItem(),
                        " X ", "  X", "PX ",
                        'X', "ingotFiery",
                        'P', Item.blazeRod));
            }
        }
    }

    public static void initEXUTools() {
        if (shouldRegister("ExtraUtilities")) {
            if (shouldAdd("ingotUnstable")) {
                GameRegistry.addRecipe(new ShapedOreRecipe(UNSTABLE.getHammerItem(),
                        "XXX", "XPX", " P ",
                        'X', "ingotUnstable",
                        'P', Block.obsidian));

                GameRegistry.addRecipe(new ShapedOreRecipe(UNSTABLE.getExcavatorItem(),
                        "XXX", "XPX", " P ",
                        'X', "ingotUnstable",
                        'P', Block.obsidian));

                GameRegistry.addRecipe(new ShapedOreRecipe(UNSTABLE.getSickleItem(),
                        " X ", "  X", "PX ",
                        'X', "ingotUnstable",
                        'P', Block.obsidian));

                GameRegistry.addRecipe(new ShapedOreRecipe(UNSTABLE.getBowItem(),
                        " XS", "P S", " XS",
                        'S', "ingotUnstable",
                        'X', Block.obsidian,
                        'P', Item.stick));
            }
        }
    }

    public static void addBow(Item pick, Object material) {
        GameRegistry.addRecipe(new ShapedOreRecipe(pick,
                " XS", "P S", " XS",
                'S', Item.silk,
                'X', material,
                'P', Item.stick));
    }

    public static void addSword(Item pick, Object material) {
        GameRegistry.addRecipe(new ShapedOreRecipe(pick,
                " X ", " X ", " P ",
                'X', material,
                'P', Item.stick));
    }

    public static void addAxe(Item pick, Object material) {
        GameRegistry.addRecipe(new ShapedOreRecipe(pick,
                "XX ", "XP ", " P ",
                'X', material,
                'P', Item.stick));

        GameRegistry.addRecipe(new ShapedOreRecipe(pick,
                " XX", " PX", " P ",
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

    public static void addShears(Item shears, Object material) {
        GameRegistry.addRecipe(new ShapedOreRecipe(shears,
                "X ", " X",
                'X', material));
    }

    public static void addSickle(Item hammer, Object material) {
        GameRegistry.addRecipe(new ShapedOreRecipe(hammer,
                " X ", "  X", "PX ",
                'X', material,
                'P', Item.stick));
    }

    public static void addSickle(ItemStack hammer, Object material) {
        GameRegistry.addRecipe(new ShapedOreRecipe(hammer,
                " X ", "  X", "PX ",
                'X', material,
                'P', Item.stick));
    }
}
