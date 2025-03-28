package extrautils.item;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public interface IItemMultiTransparency {

    int numIcons(ItemStack stack);
    Icon getIconForTransparentRender(ItemStack stack, int pass);
    float getIconTransparency(ItemStack stack, int pass);
}
