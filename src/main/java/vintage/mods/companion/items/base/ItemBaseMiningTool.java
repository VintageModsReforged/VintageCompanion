package vintage.mods.companion.items.base;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.vintage.core.helpers.StackHelper;
import mods.vintage.core.helpers.ToolHelper;
import mods.vintage.core.platform.lang.FormattedTranslator;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import vintage.mods.companion.VintageCompanion;
import vintage.mods.companion.items.Materials;
import vintage.mods.companion.items.ToolAction;

import java.util.List;
import java.util.Locale;

public class ItemBaseMiningTool extends ItemTool {

    public String name;
    private final Materials.Ingredient repairs;
    protected final Materials material;

    public ItemBaseMiningTool(int id, int attack, Materials material, String name) {
        super(id, attack, material.getToolMaterial(), new Block[0]);
        this.name = name;
        this.setMaxStackSize(1);
        this.setCreativeTab(VintageCompanion.TAB);
        this.repairs = material.getIngredients();
        this.material = material;
    }

    @SuppressWarnings("all")
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean debug) {
        super.addInformation(stack, player, list, debug);
        if (this.material == Materials.UNSTABLE) {
            list.add(FormattedTranslator.GRAY.format("tooltips.info.durability", FormattedTranslator.RED.format("tooltips.info.durability.unbreakable")));
            list.add(FormattedTranslator.RED.format("tooltips.info.warning.craft"));
        } else {
            list.add(FormattedTranslator.GRAY.format("tooltips.info.durability", FormattedTranslator.AQUA.literal((stack.getMaxDamage() - stack.getItemDamage()) + " / " + stack.getMaxDamage())));
        }
        if (this.material == Materials.FIERY) {
            list.add(FormattedTranslator.YELLOW.format("tooltips.info.auto_smelting"));
        }
    }

    @Override
    public boolean getIsRepairable(ItemStack tool, ItemStack repair) {
        for (String in : this.repairs.getItems()) {
            for (ItemStack validRepair : StackHelper.getStackFromOre(in)) {
                if (StackHelper.areStacksEqual(validRepair, repair)) {
                    return true;
                }
            }
        }
        return super.getIsRepairable(tool, repair);
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
    public boolean onBlockDestroyed(ItemStack stack, World world, int id, int x, int y, int z, EntityLiving entity) {
        return true;
    }

    @SuppressWarnings("all")
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(int id, CreativeTabs creativeTabs, List list) {
        if (this.material == Materials.IRONWOOD) {
            ItemStack stack = new ItemStack(this, 1, 0);
            stack.addEnchantment(Enchantment.unbreaking, 1);
            list.add(stack);
        } else if (this.material == Materials.STEELEAF) {
            ItemStack stack = new ItemStack(this, 1, 0);
            stack.addEnchantment(Enchantment.efficiency, 2);
            list.add(stack);
        } else super.getSubItems(id, creativeTabs, list);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLiving target, EntityLiving player) {
        boolean result = super.hitEntity(stack, target, player);
        if (result && !target.isImmuneToFire() && this.material == Materials.FIERY) {
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

    public ToolHelper.IDropCallback getToolAction() {
        if (this.material == Materials.FIERY) {
            return ToolAction.smelt();
        } else return ToolHelper.simpleHarvest();
    }
}
