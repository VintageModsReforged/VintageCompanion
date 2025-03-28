package vintage.mods.companion.items.compat.twilightforest.fiery;

import vintage.mods.companion.CompanionConfig;
import vintage.mods.companion.items.Materials;
import vintage.mods.companion.items.ToolAction;
import vintage.mods.companion.items.base.ItemBaseExcavator;
import vintage.mods.companion.items.compat.ItemRefs;
import mods.vintage.core.helpers.BlockHelper;
import mods.vintage.core.helpers.ToolHelper;
import mods.vintage.core.helpers.pos.BlockPos;
import mods.vintage.core.platform.lang.FormattedTranslator;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public class ItemFieryExcavator extends ItemBaseExcavator {

    public ItemFieryExcavator(int id) {
        super(id, Materials.FIERY, Materials.FIERY.getName());
    }

    @SuppressWarnings("all")
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean debug) {
        super.addInformation(stack, player, list, debug);
        list.add(FormattedTranslator.YELLOW.format("tooltips.info.auto_smelting"));
    }

    @Override
    public boolean getIsRepairable(ItemStack tool, ItemStack repair) {
        ItemStack fieryStack = ItemRefs.getFieryStack();
        if (fieryStack != null) {
            return repair.itemID == fieryStack.itemID;
        } else return super.getIsRepairable(tool, repair);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLiving target, EntityLiving player) {
        boolean result = super.hitEntity(stack, target, player);
        if (result && !target.isImmuneToFire()) {
            if (target.worldObj.isRemote) {
                for (int i = 0; i < 20; ++i) {
                    double xPos = Item.itemRand.nextGaussian() * 0.02;
                    double yPos = Item.itemRand.nextGaussian() * 0.02;
                    double zPos = Item.itemRand.nextGaussian() * 0.02;
                    double magnitude = 10.0F;
                    target.worldObj.spawnParticle("flame", target.posX + (double) (Item.itemRand.nextFloat() * target.width * 2.0F) - (double) target.width - xPos * magnitude, target.posY + (double) (Item.itemRand.nextFloat() * target.height) - yPos * magnitude, target.posZ + (double) (Item.itemRand.nextFloat() * target.width * 2.0F) - (double) target.width - zPos * magnitude, xPos, yPos, zPos);
                }
            } else {
                target.setFire(15);
            }
        }

        return result;
    }

    @Override
    public boolean onBlockStartBreak(ItemStack stack, int x, int y, int z, EntityPlayer player) {
        int mined = 0;
        World world = player.worldObj;
        Block block = BlockHelper.getBlock(world, x, y, z);
        if (!canHarvestBlock(block))
            return false;
        int radius = player.isSneaking() ? 0 : 1;
        float refStrength = block.getBlockHardness(world, x, y, z);
        if (refStrength != 0.0D) {
            BlockPos origin = new BlockPos(x, y, z);
            for (BlockPos pos : ToolHelper.getAOE(player, origin, radius)) {
                Block adjBlock = BlockHelper.getBlock(world, pos);
                if (!BlockHelper.isAir(world, pos)) {
                    float strength = adjBlock.getBlockHardness(world, pos.getX(), pos.getY(), pos.getZ());
                    if (strength > 0f && strength / refStrength <= 8f) {
                        if ((getStrVsBlock(stack, adjBlock, world.getBlockMetadata(pos.getX(), pos.getY(), pos.getZ())) > 0.0F && canHarvestBlock(adjBlock)) &&
                                ToolHelper.harvestAndDrop(world, pos.getX(), pos.getY(), pos.getZ(), player, ToolAction.smelt())) {
                            mined++;
                        }
                    }
                }
            }
            if (mined > 0) {
                stack.damageItem((int) (mined * CompanionConfig.durability_factor), player);
            }
        } else {
            return super.onBlockStartBreak(stack, x, y, z, player);
        }
        return false;
    }
}
