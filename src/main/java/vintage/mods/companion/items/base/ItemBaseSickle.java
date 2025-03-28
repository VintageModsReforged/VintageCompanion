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
        int mined = 0;
        World world = player.worldObj;
        Block block = BlockHelper.getBlock(world, x, y, z);
        if (!canHarvestBlock(block)) {
            if (!player.capabilities.isCreativeMode) {
                stack.damageItem(1, player);
            }
            return false;
        }

        world.playAuxSFXAtEntity(player, 2001, x, y, z, block.blockID | (world.getBlockMetadata(x, y, z) << 12));
        int radius = player.isSneaking() ? 0 : getRadius();
        for (int i = x - radius; i <= x + radius; i++) {
            for (int k = z - radius; k <= z + radius; k++) {
                if (!world.isAirBlock(i, y, k)) {
                    Block adjBlock = BlockHelper.getBlock(world, i, y, k);
                    if (canHarvestBlock(adjBlock) && ToolHelper.harvestAndDrop(world, i, y, k, player, getToolAction())) {
                        mined++;
                    }
                }
            }
        }
        if (mined > 0 && !player.capabilities.isCreativeMode) {
            stack.damageItem(mined, player);
        }
        return true;
    }

    public int getRadius() {
        switch (this.material) {
            case WOOD:
            case ELECTRUM:
            case GOLD:
                return 1;
            case STONE:
            case ALUMINIUM:
            case COPPER:
            case LEAD:
            case SILVER:
            case TIN:
                return 2;
            case IRON:
            case NETHER_QUARTZ:
            case CERTUS_QUARTZ:
            case RUBY:
            case SAPPHIRE:
            case GREEN_SAPPHIRE:
            case IRONWOOD:
            case BRONZE:
            case CONSTANTAN:
            case NICKEL:
            case STEEL:
            case INVAR:
                return 3;
            case DIAMOND:
            case THAUMIUM:
            case UNSTABLE:
            case STEELEAF:
                return 4;
            case FIERY:
            case PLATINUM:
                return 5;
            default:
                return 0;
        }
    }
}
