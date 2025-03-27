package vintage.mods.companion.items.base;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.MinecraftForge;
import vintage.mods.companion.Refs;
import vintage.mods.companion.VintageCompanion;

public class ItemBaseSword extends ItemSword {

    public String name;

    public ItemBaseSword(int id, EnumToolMaterial material, String name) {
        super(id, material);
        this.name = name;
        this.setMaxStackSize(1);
        this.setUnlocalizedName(name + ".sword");
        MinecraftForge.setToolClass(this, "sword", material.getHarvestLevel());
        this.setCreativeTab(VintageCompanion.TAB);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister registry) {
        this.itemIcon = registry.registerIcon(Refs.id + ":sword/" + this.name);
    }
}
