package vintage.mods.companion.items.base;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.vintage.core.helpers.StackHelper;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.MinecraftForge;
import vintage.mods.companion.Refs;
import vintage.mods.companion.VintageCompanion;
import vintage.mods.companion.items.Materials;

public class ItemBaseSword extends ItemSword {

    public String name;
    private final Materials.Ingredient repairs;

    public ItemBaseSword(int id, Materials material, String name) {
        super(id, material.getToolMaterial());
        this.name = name;
        this.setMaxStackSize(1);
        this.setUnlocalizedName(name + ".sword");
        MinecraftForge.setToolClass(this, "sword", material.getToolMaterial().getHarvestLevel());
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
        this.itemIcon = registry.registerIcon(Refs.id + ":sword/" + this.name);
    }
}
