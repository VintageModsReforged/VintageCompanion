package vintage.mods.companion.items.base;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemHoe;
import net.minecraftforge.common.MinecraftForge;
import vintage.mods.companion.Refs;
import vintage.mods.companion.VintageCompanion;

public class ItemBaseHoe extends ItemHoe {

    public String name;

    public ItemBaseHoe(int id, EnumToolMaterial material, String name) {
        super(id, material);
        this.name = name;
        this.setUnlocalizedName(name + ".hoe");
        MinecraftForge.setToolClass(this, "hoe", material.getHarvestLevel());
        this.setCreativeTab(VintageCompanion.TAB);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister registry) {
        this.itemIcon = registry.registerIcon(Refs.id + ":hoe/" + this.name);
    }
}
