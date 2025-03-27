package dev.crossvas.vintagecompanion.items.compat;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemRefs {

    public static final String TWILIGHT_FOREST_ITEM_CLASS = "twilightforest.item.TFItems";
    public static final String THAUMCRAFT_ITEM_CLASS = "thaumcraft.common.Config";
    public static final String EXU_ITEM_CLASS = "extrautils.ExtraUtils";

    public static Class<?> MOD_CLASS;

    public static ItemStack getUnstableStack() {
        Item placeholder = getItem(EXU_ITEM_CLASS, "unstableIngot");
        if (placeholder == null) {
            return null;
        } else return new ItemStack(placeholder);
    }

    public static ItemStack getSteeleafStack() {
        Item placeholder = getItem(TWILIGHT_FOREST_ITEM_CLASS, "steeleafIngot");
        if (placeholder == null) {
            return null;
        } else return new ItemStack(placeholder);
    }

    public static ItemStack getIronwoodStack() {
        Item placeholder = getItem(TWILIGHT_FOREST_ITEM_CLASS, "ironwoodIngot");
        if (placeholder == null) {
            return null;
        } else return new ItemStack(placeholder);
    }

    public static ItemStack getFieryStack() {
        Item placeholder = getItem(TWILIGHT_FOREST_ITEM_CLASS, "fieryIngot");
        if (placeholder == null) {
            return null;
        } else return new ItemStack(placeholder);
    }

    public static ItemStack getThaumiumStack() {
        Item placeholder = getItem(THAUMCRAFT_ITEM_CLASS, "itemResource");
        if (placeholder == null) {
            return null;
        } else return new ItemStack(placeholder, 1, 2);
    }

    public static Item getItem(String path, String name) {
        try {
            MOD_CLASS = Class.forName(path);
            Object ret = MOD_CLASS.getField(name).get(null);
            return ret instanceof Item ? (Item) ret : (Item) null;
        } catch (Exception var2) {
            return (Item) null;
        }
    }
}
