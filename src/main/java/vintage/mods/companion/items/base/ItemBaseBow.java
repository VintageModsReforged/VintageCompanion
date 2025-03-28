package vintage.mods.companion.items.base;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.vintage.core.helpers.StackHelper;
import mods.vintage.core.platform.lang.FormattedTranslator;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import vintage.mods.companion.Refs;
import vintage.mods.companion.VintageCompanion;
import vintage.mods.companion.items.Materials;

import java.util.List;

public class ItemBaseBow extends ItemBow {

    public static final String[] pullIconSuffixArray = new String[]{"_0", "_1", "_2"};

    @SideOnly(Side.CLIENT)
    private Icon[] icons;

    public String name;
    public Materials material;
    private final Materials.Ingredient repairs;

    public ItemBaseBow(int id, Materials material, String name) {
        super(id);
        this.setUnlocalizedName(name + ".bow");
        this.setMaxDamage(material == Materials.UNSTABLE ? -1 : (material.getToolMaterial().getMaxUses() + 325));
        this.setCreativeTab(VintageCompanion.TAB);
        this.name = name;
        this.material = material;
        this.repairs = material.getIngredients();
    }

    @Override
    public boolean isRepairable() {
        return this.material != Materials.UNSTABLE;
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
    }

    public Pair<Float, Float> getBowStats() {
        switch (material) {
            case STONE:
            case COPPER:
                return new Pair<Float, Float>(1.25f, 1.2f);
            case IRON:
            case BRONZE:
                return new Pair<Float, Float>(1.5f, 1.3f);
            case GOLD:
                return new Pair<Float, Float>(1.0f, 1.6f);
            case DIAMOND:
                return new Pair<Float, Float>(1.75f, 1.4f);
            case TIN:
                return new Pair<Float, Float>(1.25f, 1.23f);
            case SILVER:
                return new Pair<Float, Float>(1.25f, 1.3f);
            case LEAD:
                return new Pair<Float, Float>(1.25f, 1.25f);
            case ALUMINIUM:
                return new Pair<Float, Float>(1.25f, 1.5f);
            case NICKEL:
            case INVAR:
            case STEEL:
                return new Pair<Float, Float>(1.63f, 1.33f);
            case PLATINUM:
                return new Pair<Float, Float>(1.88f, 1.45f);
            case ELECTRUM:
                return new Pair<Float, Float>(1.13f, 1.7f);
            case CONSTANTAN:
                return new Pair<Float, Float>(1.38f, 1.3f);
            default:
                return new Pair<Float, Float>(1.25f, 2f);
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        ArrowNockEvent event = new ArrowNockEvent(player, stack);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled()) return event.result;
        if (player.capabilities.isCreativeMode || player.inventory.hasItem(Item.arrow.itemID) || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, stack) > 0) {
            player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
        }
        return stack;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int time) {
        int draw = this.getMaxItemUseDuration(stack) - time;
        ArrowLooseEvent event = new ArrowLooseEvent(player, stack, draw);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled()) return;

        draw = event.charge;
        boolean flag = player.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, stack) > 0;
        if (flag || player.inventory.hasItem(Item.arrow.itemID)) {
            float drawStrength = draw / 20.0F;
            drawStrength = (drawStrength * drawStrength + drawStrength * 2.0F) / 3.0F;

            if (drawStrength > 1.0F) {
                drawStrength = 1.0F;
            } else if (drawStrength < 0.1F) {
                return;
            }

            int enchantPower = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, stack);
            int enchantKnockback = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, stack);
            int enchantFire = EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, stack);

            Pair<Float, Float> stats = getBowStats();

            EntityArrow arrow = new EntityArrow(world, player, drawStrength * stats.getSpeed());
            double damage = arrow.getDamage() * stats.getDamage();
            arrow.setDamage(damage);

            if (drawStrength == 1.0F) {
                arrow.setIsCritical(true);
            }
            if (enchantPower > 0) {
                arrow.setDamage(damage + enchantPower * 0.5D + 0.5D);
            }
            if (enchantKnockback > 0) {
                arrow.setKnockbackStrength(enchantKnockback);
            }
            if (enchantFire > 0) {
                arrow.setFire(100);
            }
            if (flag) {
                arrow.canBePickedUp = 2;
            } else {
                player.inventory.consumeInventoryItem(Item.arrow.itemID);
            }
            world.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + drawStrength * 0.5F);
            if (!world.isRemote) {
                world.spawnEntityInWorld(arrow);
            }

            if (!player.capabilities.isCreativeMode) {
                stack.damageItem(1, player);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister registry) {
        this.itemIcon = registry.registerIcon(Refs.id + ":bow/" + this.name);
        this.icons = new Icon[pullIconSuffixArray.length];
        for (int i = 0; i < icons.length; i++) {
            this.icons[i] = registry.registerIcon(Refs.id + ":bow/" + this.name + pullIconSuffixArray[i]);
        }
    }

    @Override
    public Icon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
        if (useRemaining > 0) {
            int draw = stack.getMaxItemUseDuration() - useRemaining;
            if (draw > 17) {
                return this.icons[2];
            } else if (draw > 13) {
                return this.icons[1];
            } else if (draw > 0) {
                return this.icons[0];
            }
        }
        return itemIcon;
    }

    public static class Pair<DAMAGE, SPEED> {

        public DAMAGE damage;
        public SPEED speed;

        public Pair(DAMAGE damage, SPEED speed) {
            this.damage = damage;
            this.speed = speed;
        }

        public DAMAGE getDamage() {
            return this.damage;
        }

        public SPEED getSpeed() {
            return this.speed;
        }
    }
}
