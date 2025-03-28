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
    // sword
    Item getSwordItem();
    // shovel
    Item getShovelItem();
    // hoe
    Item getHoeItem();
    // axe
    Item getAxeItem();
    // shears
    Item getShearsItem();
    // sickle
    Item getSickleItem();
}
