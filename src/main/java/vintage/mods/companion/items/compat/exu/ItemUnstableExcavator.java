package vintage.mods.companion.items.compat.exu;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import vintage.mods.companion.Refs;
import vintage.mods.companion.items.Materials;
import vintage.mods.companion.items.base.ItemBaseExcavator;
import extrautils.item.IItemMultiTransparency;
import mods.vintage.core.helpers.BlockHelper;
import mods.vintage.core.platform.lang.FormattedTranslator;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

import java.util.List;

public class ItemUnstableExcavator extends ItemBaseExcavator implements IItemMultiTransparency {

    private Icon[] icons;

    public ItemUnstableExcavator(int id) {
        super(id, Materials.UNSTABLE, Materials.UNSTABLE.getName());
        this.setMaxDamage(-1);
    }

    @Override
    public boolean isRepairable() {
        return false;
    }

    @SuppressWarnings("all")
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean debug) {
        super.addInformation(stack, player, list, debug);
        list.add(FormattedTranslator.RED.format("tooltips.info.warning.craft"));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister registry) {
        this.icons = new Icon[2];
        this.icons[0] = registry.registerIcon(Refs.id + ":excavator/" + this.name);
        this.icons[1] = registry.registerIcon(Refs.id + ":excavator/" + this.name + "1");
        this.itemIcon = registry.registerIcon(Refs.id + ":excavator/" + this.name);
    }

    @Override
    public boolean onBlockStartBreak(ItemStack stack, int x, int y, int z, EntityPlayer player) {
        Block block = BlockHelper.getBlock(player.worldObj, x, y, z);
        if (!canHarvest(block)) {
            stack.damageItem(1, player);
        } else return super.onBlockStartBreak(stack, x, y, z, player);
        return false;
    }

    @Override
    public int numIcons(ItemStack stack) {
        return 2;
    }

    @Override
    public Icon getIconForTransparentRender(ItemStack stack, int pass) {
        return this.icons[pass];
    }

    @Override
    public float getIconTransparency(ItemStack stack, int pass) {
        return pass == 1 ? 0.5F : 1.0F;
    }

}
