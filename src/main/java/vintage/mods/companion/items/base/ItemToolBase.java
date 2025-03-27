package vintage.mods.companion.items.base;

import vintage.mods.companion.CompanionConfig;
import vintage.mods.companion.VintageCompanion;
import mods.vintage.core.helpers.BlockHelper;
import mods.vintage.core.helpers.ToolHelper;
import mods.vintage.core.helpers.pos.BlockPos;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

import java.util.Locale;

public class ItemToolBase extends ItemTool {

    public String name;

    public ItemToolBase(int id, EnumToolMaterial material, String name) {
        super(id, 0, material, new Block[0]);
        this.name = name;
        this.setMaxStackSize(1);
        this.setCreativeTab(VintageCompanion.TAB);
    }

    public void init() {}

    public boolean canHarvest(Block block) {
        return false;
    }

    @Override
    public boolean canHarvestBlock(Block block) {
        return canHarvest(block);
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float xOffset, float yOffset, float zOffset) {
        for (int i = 0; i < player.inventory.mainInventory.length; i++) {
            ItemStack check = player.inventory.mainInventory[i];
            if (check != null) {
                if(check.getDisplayName().toLowerCase(Locale.ENGLISH).contains("torch")) {
                    Item item = check.getItem();
                    if (item instanceof net.minecraft.item.ItemBlock) {
                        int oldMeta = check.getItemDamage();
                        int oldSize = check.stackSize;
                        boolean result = check.tryPlaceItemIntoWorld(player, world, x, y, z, side, xOffset,
                                yOffset, zOffset);
                        if (player.capabilities.isCreativeMode) {
                            check.setItemDamage(oldMeta);
                            check.stackSize = oldSize;
                        } else if (check.stackSize <= 0) {
                            ForgeEventFactory.onPlayerDestroyItem(player, check);
                            player.inventory.mainInventory[i] = null;
                        }
                        if (result)
                            return true;
                    }
                }
            }
        }
        return super.onItemUse(stack, player, world, x, y, z, side, xOffset, yOffset, zOffset);
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
                            if ((getStrVsBlock(stack, adjBlock, world.getBlockMetadata(pos.getX(), pos.getY(), pos.getZ())) > 0.0F && canHarvestBlock(adjBlock)) && ToolHelper.harvestBlock(world, pos.getX(), pos.getY(), pos.getZ(), player)) {
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
        }
        return false;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World world, int id, int x, int y, int z, EntityLiving entity) {
        return false;
    }
}
