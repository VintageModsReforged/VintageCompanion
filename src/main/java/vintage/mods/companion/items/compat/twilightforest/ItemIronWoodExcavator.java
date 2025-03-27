package vintage.mods.companion.items.compat.twilightforest;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import vintage.mods.companion.items.Materials;
import vintage.mods.companion.items.base.ItemBaseExcavator;
import vintage.mods.companion.items.compat.ItemRefs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ItemIronWoodExcavator extends ItemBaseExcavator {

    public ItemIronWoodExcavator() {
        super(Materials.IRONWOOD.excavatorId(), Materials.IRONWOOD.getToolMaterial(), Materials.IRONWOOD.getName());
    }

    @SuppressWarnings("all")
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(int id, CreativeTabs tabs, List items) {
        ItemStack stack = new ItemStack(this, 1, 0);
        stack.addEnchantment(Enchantment.unbreaking, 1);
        items.add(stack);
    }

    @Override
    public boolean getIsRepairable(ItemStack tool, ItemStack repair) {
        ItemStack ironwoodStack = ItemRefs.getIronwoodStack();
        if (ironwoodStack != null) {
            return repair.itemID == ironwoodStack.itemID;
        } else return super.getIsRepairable(tool, repair);
    }
}
