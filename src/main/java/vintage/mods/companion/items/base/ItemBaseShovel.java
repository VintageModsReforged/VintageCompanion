package vintage.mods.companion.items.base;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import vintage.mods.companion.Refs;

import java.util.HashSet;
import java.util.Set;

public class ItemBaseShovel extends ItemBaseMiningTool {

    public Set<Material> mineableBlockMaterials = new HashSet<Material>();

    public ItemBaseShovel(int id, EnumToolMaterial material, String name) {
        super(id, 1, material, name);
        this.setUnlocalizedName(name + ".shovel");
        MinecraftForge.setToolClass(this, "shovel", material.getHarvestLevel());
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
    public void registerIcons(IconRegister registry) {
        this.itemIcon = registry.registerIcon(Refs.id + ":shovel/" + this.name);
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
