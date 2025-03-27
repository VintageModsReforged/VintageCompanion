package vintage.mods.companion.items;

import net.minecraft.item.Item;

public interface IToolsProvider {
    String getName();

    // hammer
    Item getHammerItem();
    // excavator
    Item getExcavatorItem();
    // pickaxe
    Item getPickaxeItem();
}
