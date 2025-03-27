package dev.crossvas.vintagecompanion.items.compat.twilightforest;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dev.crossvas.vintagecompanion.items.Materials;
import dev.crossvas.vintagecompanion.items.base.ItemBaseHammer;
import dev.crossvas.vintagecompanion.items.compat.ItemRefs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ItemIronWoodHammer extends ItemBaseHammer {

    public ItemIronWoodHammer() {
        super(Materials.IRONWOOD);
    }

    @SuppressWarnings("all")
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(int id, CreativeTabs tabs, List items) {
        ItemStack stack = new ItemStack(this, 1, 0);
        stack.addEnchantment(Enchantment.efficiency, 1);
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
