package vintage.mods.companion.items;

import net.minecraft.item.Item;
import vintage.mods.companion.CompanionConfig;
import net.minecraft.item.EnumToolMaterial;
import vintage.mods.companion.items.base.ItemBaseExcavator;
import vintage.mods.companion.items.base.ItemBaseHammer;
import vintage.mods.companion.items.base.ItemBasePickaxe;
import vintage.mods.companion.items.base.ItemBaseSword;
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
    WOOD(ToolMaterials.WOOD, ToolType.EXCAVATOR_HAMMER),
    STONE(ToolMaterials.STONE, ToolType.EXCAVATOR_HAMMER),
    GOLD(ToolMaterials.GOLD, ToolType.EXCAVATOR_HAMMER),
    IRON(ToolMaterials.IRON, ToolType.EXCAVATOR_HAMMER),
    DIAMOND(ToolMaterials.DIAMOND, ToolType.EXCAVATOR_HAMMER),
    // Applied Energistics
    NETHER_QUARTZ(ToolMaterials.NETHER_QUARTZ, ToolType.ALL_TOOLS),
    CERTUS_QUARTZ(ToolMaterials.CERTUS_QUARTZ, ToolType.EXCAVATOR_HAMMER),
    // Gems: Ruby, Sapphire, Green Sapphire
    RUBY(ToolMaterials.GEM, ToolType.ALL_TOOLS),
    SAPPHIRE(ToolMaterials.GEM, ToolType.ALL_TOOLS),
    GREEN_SAPPHIRE(ToolMaterials.GEM, ToolType.ALL_TOOLS),
    // Base Metals
    ALUM(ToolMaterials.ALUM, ToolType.ALL_TOOLS),
    BRONZE(ToolMaterials.BRONZE, ToolType.ALL_TOOLS),
    CONSTANTAN(ToolMaterials.CONSTANTAN, ToolType.ALL_TOOLS),
    COPPER(ToolMaterials.COPPER, ToolType.ALL_TOOLS),
    ELECTRUM(ToolMaterials.ELECTRUM, ToolType.ALL_TOOLS),
    INVAR(ToolMaterials.INVAR, ToolType.ALL_TOOLS),
    LEAD(ToolMaterials.LEAD, ToolType.ALL_TOOLS),
    NICKEL(ToolMaterials.NICKEL, ToolType.ALL_TOOLS),
    PLATINUM(ToolMaterials.PLATINUM, ToolType.ALL_TOOLS),
    SILVER(ToolMaterials.SILVER, ToolType.ALL_TOOLS),
    STEEL(ToolMaterials.STEEL, ToolType.ALL_TOOLS),
    TIN(ToolMaterials.TIN, ToolType.ALL_TOOLS),
    // Compat: Mods
    THAUMIUM(ToolMaterials.THAUMIUM, ToolType.EXCAVATOR_HAMMER),
    STEELEAF(ToolMaterials.STEELEAF, ToolType.EXCAVATOR_HAMMER),
    IRONWOOD(ToolMaterials.IRONWOOD, ToolType.EXCAVATOR_HAMMER),
    FIERY(ToolMaterials.FIERY, ToolType.EXCAVATOR_HAMMER),
    UNSTABLE(ToolMaterials.UNSTABLE, ToolType.EXCAVATOR_HAMMER);

    private static int totalToolCount = 0;

    private final String NAME;
    private final EnumToolMaterial MATERIAL;
    public static final Materials[] VALUES = values();

    private final LazyEntry<Item> hammerItem;
    private final LazyEntry<Item> excavatorItem;
    private final LazyEntry<Item> pickaxeItem;
    private final LazyEntry<Item> swordItem;

    Materials(final EnumToolMaterial material, final ToolType builder) {
        this.NAME = name().toLowerCase(Locale.ROOT);
        this.MATERIAL = material;
        this.hammerItem = new LazyEntry<Item>(new LazyEntry.Supplier<Item>() {
            @Override
            public Item get() {
                if (builder.hasHammer()) {
                    int hammerId = getToolId(totalToolCount++);
                    switch (getMaterial()) {
                        case UNSTABLE:
                            return new ItemUnstableHammer(hammerId);
                        case FIERY:
                            return new ItemFieryHammer(hammerId);
                        case IRONWOOD:
                            return new ItemIronWoodHammer(hammerId);
                        case STEELEAF:
                            return new ItemSteeleafHammer(hammerId);
                        default:
                            return new ItemBaseHammer(hammerId, material, getName());
                    }
                }
                return null;
            }
        });

        this.excavatorItem = new LazyEntry<Item>(new LazyEntry.Supplier<Item>() {
            @Override
            public Item get() {
                if (builder.hasExcavator()) {
                    int excavatorId = getToolId(totalToolCount++);
                    switch (getMaterial()) {
                        case UNSTABLE:
                            return new ItemUnstableExcavator(excavatorId);
                        case FIERY:
                            return new ItemFieryExcavator(excavatorId);
                        case IRONWOOD:
                            return new ItemIronWoodExcavator(excavatorId);
                        case STEELEAF:
                            return new ItemSteeleafExcavator(excavatorId);
                        default:
                            return new ItemBaseExcavator(excavatorId, material, getName());
                    }
                }
                return null;
            }
        });

        this.pickaxeItem = new LazyEntry<Item>(new LazyEntry.Supplier<Item>() {
            @Override
            public Item get() {
                if (builder.hasPickaxe()) {
                    int pickaxeId = getToolId(totalToolCount++);
                    return new ItemBasePickaxe(pickaxeId, material, getName());
                }
                return null;
            }
        });

        this.swordItem = new LazyEntry<Item>(new LazyEntry.Supplier<Item>() {
            @Override
            public Item get() {
                if (builder.hasSword()) {
                    int swordId = getToolId(totalToolCount++);
                    return new ItemBaseSword(swordId, material, getName());
                }
                return null;
            }
        });
    }

    private static int getToolId(int totalToolsAdded) {
        return CompanionConfig.toolsIdStartIndex + totalToolsAdded;
    }

    public EnumToolMaterial getToolMaterial() {
        return this.MATERIAL;
    }

    Materials getMaterial() {
        return this;
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

    @Override
    public Item getPickaxeItem() {
        return pickaxeItem.get();
    }

    @Override
    public Item getSwordItem() {
        return swordItem.get();
    }

    public static class ToolType {
        // add these if needed
        private final boolean excavator;
        private final boolean hammer;
        private final boolean pickaxe;
        private final boolean sword;

        private static final ToolType EXCAVATOR_HAMMER = new ToolType.Builder().addExcavator().addHammer().build();
        private static final ToolType ALL_TOOLS = new ToolType.Builder().addExcavator().addHammer().addPickaxe().addSword().build();

        private ToolType(Builder builder) {
            this.excavator = builder.excavator;
            this.hammer = builder.hammer;
            this.pickaxe = builder.pickaxe;
            this.sword = builder.sword;
        }

        public boolean hasExcavator() {
            return excavator;
        }

        public boolean hasHammer() {
            return hammer;
        }

        public boolean hasPickaxe() {
            return pickaxe;
        }

        public boolean hasSword() {
            return sword;
        }

        public static class Builder {
            private boolean excavator;
            private boolean hammer;
            private boolean pickaxe;
            private boolean sword;

            public Builder() {
                this.excavator = false;
                this.hammer = false;
                this.pickaxe = false;
                this.sword = false;
            }

            public Builder addExcavator() {
                this.excavator = true;
                return this;
            }

            public Builder addHammer() {
                this.hammer = true;
                return this;
            }

            public Builder addPickaxe() {
                this.pickaxe = true;
                return this;
            }

            public Builder addSword() {
                this.sword = true;
                return this;
            }

            public ToolType build() {
                return new ToolType(this);
            }
        }
    }
}
