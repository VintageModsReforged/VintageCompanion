package extrautils.item;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

/**
 * Defines methods for items that support multiple transparency layers in rendering.
 * Implementing classes should provide multiple icons and corresponding transparency values
 * for different rendering passes.
 */
public interface IItemMultiTransparency {

    /**
     * Gets the number of icons used for rendering the item with transparency effects.
     *
     * @param stack The item stack for which the number of icons is queried.
     * @return The number of icons used in rendering.
     */
    int numIcons(ItemStack stack);

    /**
     * Retrieves the icon for rendering at a specific transparency pass.
     *
     * @param stack The item stack being rendered.
     * @param pass The rendering pass index.
     * @return The icon to be used for the given pass.
     */
    Icon getIconForTransparentRender(ItemStack stack, int pass);

    /**
     * Gets the transparency level for a specific rendering pass.
     *
     * @param stack The item stack being rendered.
     * @param pass The rendering pass index.
     * @return A float value representing the transparency level (0.0F = fully transparent, 1.0F = fully opaque).
     */
    float getIconTransparency(ItemStack stack, int pass);
}
