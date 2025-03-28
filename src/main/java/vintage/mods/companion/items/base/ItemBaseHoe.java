package vintage.mods.companion.items.base;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.vintage.core.helpers.StackHelper;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import vintage.mods.companion.Refs;
import vintage.mods.companion.VintageCompanion;
import vintage.mods.companion.items.Materials;

public class ItemBaseHoe extends ItemHoe {

    public String name;
    private final Materials.Ingredient repairs;

    public ItemBaseHoe(int id, Materials material, String name) {
        super(id, material.getToolMaterial());
        this.name = name;
        this.setUnlocalizedName(name + ".hoe");
        MinecraftForge.setToolClass(this, "hoe", material.getToolMaterial().getHarvestLevel());
        this.setCreativeTab(VintageCompanion.TAB);
        this.repairs = material.getIngredients();
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

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister registry) {
        this.itemIcon = registry.registerIcon(Refs.id + ":hoe/" + this.name);
    }
}
