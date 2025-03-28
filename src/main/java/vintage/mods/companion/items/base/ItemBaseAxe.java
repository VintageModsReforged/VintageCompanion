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

public class ItemBaseAxe extends ItemBaseMiningTool {

    public Set<Material> mineableBlockMaterials = new HashSet<Material>();

    public ItemBaseAxe(int id, Materials material, String name) {
        super(id, material.getToolMaterial().getDamageVsEntity(), material, name);
        this.setUnlocalizedName(name + ".axe");
        MinecraftForge.setToolClass(this, "axe", material.getToolMaterial().getHarvestLevel());
        init();
    }

    @Override
    public void init() {
        this.mineableBlockMaterials.add(Material.cake);
        this.mineableBlockMaterials.add(Material.sponge);
        this.mineableBlockMaterials.add(Material.cactus);
        this.mineableBlockMaterials.add(Material.coral);
        this.mineableBlockMaterials.add(Material.pumpkin);
        this.mineableBlockMaterials.add(Material.wood);
    }

    @Override
    public boolean canHarvest(Block block) {
        return mineableBlockMaterials.contains(block.blockMaterial);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister registry) {
        this.itemIcon = registry.registerIcon(Refs.id + ":axe/" + this.name);
    }

    @Override
    public float getStrVsBlock(ItemStack stack, Block block, int meta) {
        for (Material mat : this.mineableBlockMaterials) {
            if (mat == block.blockMaterial) {
                int harvestLevel = MinecraftForge.getBlockHarvestLevel(block, meta, "axe");
                if (harvestLevel <= this.toolMaterial.getHarvestLevel()) {
                    return this.toolMaterial.getEfficiencyOnProperMaterial();
                }
            }
        }
        return 0.2F;
    }
}
