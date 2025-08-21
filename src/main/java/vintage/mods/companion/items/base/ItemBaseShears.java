package vintage.mods.companion.items.base;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.vintage.core.helpers.StackHelper;
import mods.vintage.core.platform.lang.Translator;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import vintage.mods.companion.Refs;
import vintage.mods.companion.VintageCompanion;
import vintage.mods.companion.items.Materials;

import java.util.List;

public class ItemBaseShears extends ItemShears {

    public String name;
    private final Materials.Ingredient repairs;

    public ItemBaseShears(int id, Materials material, String name) {
        super(id);
        this.name = name;
        this.setMaxStackSize(1);
        this.setMaxDamage(material.getToolMaterial().getMaxUses());
        this.setUnlocalizedName(name + ".shear");
        MinecraftForge.setToolClass(this, "shear", material.getToolMaterial().getHarvestLevel());
        this.setCreativeTab(VintageCompanion.TAB);
        this.repairs = material.getIngredients();
    }

    @SuppressWarnings("all")
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean debug) {
        super.addInformation(stack, player, list, debug);
        list.add(Translator.GRAY.format("tooltips.info.durability", Translator.AQUA.literal((stack.getMaxDamage() - stack.getItemDamage()) + " / " + stack.getMaxDamage())));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister registry) {
        this.itemIcon = registry.registerIcon(Refs.id + ":shear/" + this.name);
    }

    @Override
    public boolean getIsRepairable(ItemStack tool, ItemStack repair) {
        for (String in : this.repairs.getItems()) {
            for (ItemStack validRepair : StackHelper.getStackFromOre(in)) {
                if (StackHelper.areStacksEqual(validRepair, repair)) {
                    return true;
                }
            }
        }
        return super.getIsRepairable(tool, repair);
    }
}
