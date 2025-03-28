package vintage.mods.companion.items.base;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.vintage.core.helpers.BlockHelper;
import mods.vintage.core.helpers.ToolHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import vintage.mods.companion.Refs;
import vintage.mods.companion.items.Materials;

import java.util.HashSet;
import java.util.Set;

public class ItemBaseSickle extends ItemBaseMiningTool {

    public Set<Material> mineableBlockMaterials = new HashSet<Material>();
    int radius = 3;

    public ItemBaseSickle(int id, Materials material, String name) {
        super(id, 3, material, name);
        this.setUnlocalizedName(name + ".sickle");
        MinecraftForge.setToolClass(this, "sickle", material.getToolMaterial().getHarvestLevel());
        init();
    }

    @Override
    public void init() {
        this.mineableBlockMaterials.add(Material.leaves);
        this.mineableBlockMaterials.add(Material.plants);
        this.mineableBlockMaterials.add(Material.vine);
        this.mineableBlockMaterials.add(Material.web);
    }

    @Override
    public boolean canHarvest(Block block) {
        return mineableBlockMaterials.contains(block.blockMaterial) || block.blockID == Block.vine.blockID || block.blockID == Block.web.blockID;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister registry) {
        this.itemIcon = registry.registerIcon(Refs.id + ":sickle/" + this.name);
    }

    @Override
    public float getStrVsBlock(ItemStack stack, Block block, int meta) {
        for (Material mat : this.mineableBlockMaterials) {
            if (mat == block.blockMaterial) {
                int harvestLevel = MinecraftForge.getBlockHarvestLevel(block, meta, "sickle");
                if (harvestLevel <= this.toolMaterial.getHarvestLevel()) {
                    return this.toolMaterial.getEfficiencyOnProperMaterial();
                }
            }
        }
        return 0.2F;
    }

    @Override
    public boolean onBlockStartBreak(ItemStack stack, int x, int y, int z, EntityPlayer player) {
        World world = player.worldObj;
        Block block = BlockHelper.getBlock(world, x, y, z);
        if (!canHarvestBlock(block)) {
            if (!player.capabilities.isCreativeMode) {
                stack.damageItem(1, player);
            }
            return false;
        }
        boolean used = false;
        world.playAuxSFXAtEntity(player, 2001, x, y, z, block.blockID | (world.getBlockMetadata(x, y, z) << 12));
        for (int i = x - radius; i <= x + radius; i++) {
            for (int k = z - radius; k <= z + radius; k++) {
                used |= ToolHelper.harvestBlock(world, i, y, k, player);
            }
        }
        if (used && !player.capabilities.isCreativeMode) {
            stack.damageItem(1, player);
        }
        return true;
    }
}
