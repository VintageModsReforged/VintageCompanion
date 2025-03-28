package vintage.mods.companion.items.base;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import vintage.mods.companion.Refs;
import vintage.mods.companion.items.Materials;

import java.util.HashSet;
import java.util.Set;

public class ItemBaseExcavator extends ItemBaseAOETool {

    public Set<Material> mineableBlockMaterials = new HashSet<Material>();

    public ItemBaseExcavator(int id, Materials material, String name) {
        super(id, material, name);
        this.setUnlocalizedName(name + ".excavator");
        MinecraftForge.setToolClass(this, "shovel", material.getToolMaterial().getHarvestLevel());
        init();
    }

    @Override
    public void init() {
        mineableBlockMaterials.add(Material.grass);
        mineableBlockMaterials.add(Material.sand);
        mineableBlockMaterials.add(Material.snow);
        mineableBlockMaterials.add(Material.craftedSnow);
        mineableBlockMaterials.add(Material.ground);
    }

    @Override
    public boolean canHarvest(Block block) {
        return mineableBlockMaterials.contains(block.blockMaterial);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister par1IconRegister) {
        this.itemIcon = par1IconRegister.registerIcon(Refs.id + ":excavator/" + this.name);
    }

    @Override
    public float getStrVsBlock(ItemStack stack, Block block, int meta) {
        for (Material mat : this.mineableBlockMaterials) {
            if (mat == block.blockMaterial) {
                int harvestLevel = MinecraftForge.getBlockHarvestLevel(block, meta, "shovel");
                if (harvestLevel <= this.toolMaterial.getHarvestLevel()) {
                    return this.toolMaterial.getEfficiencyOnProperMaterial();
                }
            }
        }
        return 0.2F;
    }
}
