package vintage.mods.companion.items.base;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemShears;
import net.minecraftforge.common.MinecraftForge;
import vintage.mods.companion.Refs;
import vintage.mods.companion.VintageCompanion;

public class ItemBaseShears extends ItemShears {

    public String name;

    public ItemBaseShears(int id, EnumToolMaterial material, String name) {
        super(id);
        this.name = name;
        this.setMaxStackSize(1);
        this.setMaxDamage(material.getMaxUses());
        this.setUnlocalizedName(name + ".shear");
        MinecraftForge.setToolClass(this, "shear", material.getHarvestLevel());
        this.setCreativeTab(VintageCompanion.TAB);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister registry) {
        this.itemIcon = registry.registerIcon(Refs.id + ":shear/" + this.name);
    }
}
