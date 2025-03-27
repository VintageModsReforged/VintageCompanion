package vintage.mods.companion.items.base;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import vintage.mods.companion.VintageCompanion;

import java.util.Locale;

public class ItemBasicMiningTool extends ItemTool {

    public String name;

    public ItemBasicMiningTool(int id, int attack, EnumToolMaterial material, String name) {
        super(id, attack, material, new Block[0]);
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
                if(check.getItemName().toLowerCase(Locale.ROOT).contains("torch") && !check.getItemName().toLowerCase(Locale.ROOT).contains("redstone")) {
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
    public boolean onBlockStartBreak(ItemStack stack, int X, int Y, int Z, EntityPlayer player) {
        return super.onBlockStartBreak(stack, X, Y, Z, player);
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World world, int id, int x, int y, int z, EntityLiving entity) {
        return super.onBlockDestroyed(stack, world, id, x, y, z, entity);
    }
}
