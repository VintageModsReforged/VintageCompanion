package vintage.mods.companion.items.compat;

import mods.vintage.core.utils.LazyEntry;
import mods.vintage.core.utils.function.Supplier;
import net.minecraft.item.Item;

public class ItemRefs {

    public static final String TWILIGHT_FOREST_ITEM_CLASS = "twilightforest.item.TFItems";
    public static final String THAUMCRAFT_ITEM_CLASS = "thaumcraft.common.Config";
    public static final String EXU_ITEM_CLASS = "extrautils.ExtraUtils";

    public static Class<?> MOD_CLASS;

    public static LazyEntry<Item> unstableStack() {
        final Item placeholder = getItem(EXU_ITEM_CLASS, "unstableIngot");
        return new LazyEntry<Item>(new Supplier<Item>() {
            @Override
            public Item get() {
                return placeholder;
            }
        });
    }

    public static LazyEntry<Item> steeleafStack() {
        final Item placeholder = getItem(TWILIGHT_FOREST_ITEM_CLASS, "steeleafIngot");
        return new LazyEntry<Item>(new Supplier<Item>() {
            @Override
            public Item get() {
                return placeholder;
            }
        });
    }

    public static LazyEntry<Item> ironwoodStack() {
        final Item placeholder = getItem(TWILIGHT_FOREST_ITEM_CLASS, "ironwoodIngot");
        return new LazyEntry<Item>(new Supplier<Item>() {
            @Override
            public Item get() {
                return placeholder;
            }
        });
    }

    public static LazyEntry<Item> fieryStack() {
        final Item placeholder = getItem(TWILIGHT_FOREST_ITEM_CLASS, "fieryIngot");
        return new LazyEntry<Item>(new Supplier<Item>() {
            @Override
            public Item get() {
                return placeholder;
            }
        });
    }

    public static LazyEntry<Item> thaumiumStack() {
        final Item placeholder = getItem(THAUMCRAFT_ITEM_CLASS, "itemResource");
        return new LazyEntry<Item>(new Supplier<Item>() {
            @Override
            public Item get() {
                return placeholder;
            }
        });
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
