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

public class ItemBasePickaxe extends ItemBaseMiningTool {

    public Set<Material> mineableBlockMaterials = new HashSet<Material>();

    public ItemBasePickaxe(int id, EnumToolMaterial material, String name) {
        super(id, 2, material, name);
        this.setUnlocalizedName(name + ".pickaxe");
        MinecraftForge.setToolClass(this, "pickaxe", material.getHarvestLevel());
        init();
    }

    @Override
    public void init() {
        this.mineableBlockMaterials.add(Material.anvil);
        this.mineableBlockMaterials.add(Material.circuits);
        this.mineableBlockMaterials.add(Material.dragonEgg);
        this.mineableBlockMaterials.add(Material.glass);
        this.mineableBlockMaterials.add(Material.iron);
        this.mineableBlockMaterials.add(Material.piston);
        this.mineableBlockMaterials.add(Material.redstoneLight);
        this.mineableBlockMaterials.add(Material.rock);
    }

    @Override
    public boolean canHarvest(Block block) {
        return mineableBlockMaterials.contains(block.blockMaterial);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister registry) {
        this.itemIcon = registry.registerIcon(Refs.id + ":pickaxe/" + this.name);
    }

    @Override
    public float getStrVsBlock(ItemStack stack, Block block, int meta) {
        for (Material mat : this.mineableBlockMaterials) {
            if (mat == block.blockMaterial) {
                int harvestLevel = MinecraftForge.getBlockHarvestLevel(block, meta, "pickaxe");
                if (harvestLevel <= this.toolMaterial.getHarvestLevel()) {
                    return this.toolMaterial.getEfficiencyOnProperMaterial();
                }
            }
        }
        return 0.2F;
    }
}
