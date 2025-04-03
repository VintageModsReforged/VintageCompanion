package vintage.mods.companion.items.base;

import mods.vintage.core.helpers.BlockHelper;
import mods.vintage.core.helpers.ToolHelper;
import mods.vintage.core.helpers.pos.BlockPos;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import vintage.mods.companion.CompanionConfig;
import vintage.mods.companion.items.Materials;

public class ItemBaseAOETool extends ItemBaseMiningTool {

    public ItemBaseAOETool(int id, Materials material, String name) {
        super(id, 0, material, name);
    }

    @Override
    public boolean onBlockStartBreak(ItemStack stack, int x, int y, int z, EntityPlayer player) {
        if (!player.worldObj.isRemote) {
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
                            if ((getStrVsBlock(stack, adjBlock, world.getBlockMetadata(pos.getX(), pos.getY(), pos.getZ())) > 0.0F && canHarvestBlock(adjBlock)) && ToolHelper.harvestAndDrop(world, pos.getX(), pos.getY(), pos.getZ(), player, getToolAction())) {
                                mined++;
                            }
                        }
                    }
                }
                if (mined > 0) {
                    int damage = (int) (mined * CompanionConfig.durability_factor);
                    stack.damageItem(damage, player);
                }
            } else {
                return super.onBlockStartBreak(stack, x, y, z, player);
            }
        }
        return false;
    }

    /// We handle damaging for this one inside {@link net.minecraft.item.Item#onBlockStartBreak(ItemStack, int, int, int, EntityPlayer)}
    @Override
    public boolean onBlockDestroyed(ItemStack stack, World world, int id, int x, int y, int z, EntityLiving living) {
        return true;
    }
}
