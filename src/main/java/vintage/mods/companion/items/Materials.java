package vintage.mods.companion.items;

import net.minecraft.item.Item;
import vintage.mods.companion.CompanionConfig;
import net.minecraft.item.EnumToolMaterial;
import vintage.mods.companion.items.base.ItemBaseExcavator;
import vintage.mods.companion.items.base.ItemBaseHammer;
import vintage.mods.companion.items.compat.exu.ItemUnstableExcavator;
import vintage.mods.companion.items.compat.exu.ItemUnstableHammer;
import vintage.mods.companion.items.compat.twilightforest.ItemIronWoodExcavator;
import vintage.mods.companion.items.compat.twilightforest.ItemIronWoodHammer;
import vintage.mods.companion.items.compat.twilightforest.ItemSteeleafExcavator;
import vintage.mods.companion.items.compat.twilightforest.ItemSteeleafHammer;
import vintage.mods.companion.items.compat.twilightforest.fiery.ItemFieryExcavator;
import vintage.mods.companion.items.compat.twilightforest.fiery.ItemFieryHammer;

import java.util.Locale;

public enum Materials implements IToolsProvider {
    // vanilla
    WOOD(ToolMaterials.WOOD),
    STONE(ToolMaterials.STONE),
    GOLD(ToolMaterials.GOLD),
    IRON(ToolMaterials.IRON),
    DIAMOND(ToolMaterials.DIAMOND),
    // Applied Energistics
    NETHER_QUARTZ(ToolMaterials.NETHER_QUARTZ),
    CERTUS_QUARTZ(ToolMaterials.CERTUS_QUARTZ),
    // Gems: Ruby, Sapphire, Green Sapphire
    RUBY(ToolMaterials.GEM),
    SAPPHIRE(ToolMaterials.GEM),
    GREEN_SAPPHIRE(ToolMaterials.GEM),
    // Base Metals
    ALUM(ToolMaterials.ALUM),
    BRONZE(ToolMaterials.BRONZE),
    CONSTANTAN(ToolMaterials.CONSTANTAN),
    COPPER(ToolMaterials.COPPER),
    ELECTRUM(ToolMaterials.ELECTRUM),
    INVAR(ToolMaterials.INVAR),
    LEAD(ToolMaterials.LEAD),
    NICKEL(ToolMaterials.NICKEL),
    PLATINUM(ToolMaterials.PLATINUM),
    SILVER(ToolMaterials.SILVER),
    STEEL(ToolMaterials.STEEL),
    TIN(ToolMaterials.TIN),
    // Compat: Mods
    THAUMIUM(ToolMaterials.THAUMIUM),
//    ELEMENTAL(ToolMaterials.ELEMENTAL),
    STEELEAF(ToolMaterials.STEELEAF),
    IRONWOOD(ToolMaterials.IRONWOOD),
    FIERY(ToolMaterials.FIERY),
//    KNIGHTLY(ToolMaterials.KNIGHTLY),
    UNSTABLE(ToolMaterials.UNSTABLE);

    private final String NAME;
    private final EnumToolMaterial MATERIAL;
    public static final Materials[] VALUES = values();

    private final LazyEntry<Item> hammerItem;
    private final LazyEntry<Item> excavatorItem;

    Materials(final EnumToolMaterial material) {
        this.NAME = name().toLowerCase(Locale.ROOT);
        this.MATERIAL = material;
        this.hammerItem = new LazyEntry<Item>(new LazyEntry.Supplier<Item>() {
            @Override
            public Item get() {
                switch (getMaterial()) {
                    case UNSTABLE:
                        return new ItemUnstableHammer();
                    case FIERY:
                        return new ItemFieryHammer();
                    case IRONWOOD:
                        return new ItemIronWoodHammer();
                    case STEELEAF:
                        return new ItemSteeleafHammer();
                    default:
                        return new ItemBaseHammer(hammerId(), material, getName());
                }
            }
        });

        this.excavatorItem = new LazyEntry<Item>(new LazyEntry.Supplier<Item>() {
            @Override
            public Item get() {
                switch (getMaterial()) {
                    case UNSTABLE:
                        return new ItemUnstableExcavator();
                    case FIERY:
                        return new ItemFieryExcavator();
                    case IRONWOOD:
                        return new ItemIronWoodExcavator();
                    case STEELEAF:
                        return new ItemSteeleafExcavator();
                    default:
                        return new ItemBaseExcavator(excavatorId(), material, getName());
                }
            }
        });
    }

    public EnumToolMaterial getToolMaterial() {
        return this.MATERIAL;
    }

    Materials getMaterial() {
        return this;
    }

    public int hammerId() {
        return CompanionConfig.toolsIdStartIndex + ordinal();
    }

    public int excavatorId() {
        return CompanionConfig.toolsIdStartIndex + VALUES.length + ordinal();
    }

    @Override
    public String getName() {
        return this.NAME;
    }

    @Override
    public Item getExcavatorItem() {
        return this.excavatorItem.get();
    }

    @Override
    public Item getHammerItem() {
        return this.hammerItem.get();
    }
}
