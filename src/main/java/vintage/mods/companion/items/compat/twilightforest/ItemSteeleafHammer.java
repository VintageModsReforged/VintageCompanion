package vintage.mods.companion.items.compat.twilightforest;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import vintage.mods.companion.items.Materials;
import vintage.mods.companion.items.base.ItemBaseHammer;
import vintage.mods.companion.items.compat.ItemRefs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ItemSteeleafHammer extends ItemBaseHammer {

    public ItemSteeleafHammer(int id) {
        super(id, Materials.STEELEAF.getToolMaterial(), Materials.STEELEAF.getName());
    }

    @SuppressWarnings("all")
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(int id, CreativeTabs tabs, List items) {
        ItemStack stack = new ItemStack(this, 1, 0);
        stack.addEnchantment(Enchantment.fortune, 2);
        items.add(stack);
    }

    @Override
    public boolean getIsRepairable(ItemStack tool, ItemStack repair) {
        ItemStack steeleafIngot = ItemRefs.getSteeleafStack();
        if (steeleafIngot != null) {
            return repair.itemID == steeleafIngot.itemID;
        } else return super.getIsRepairable(tool, repair);
    }
}
