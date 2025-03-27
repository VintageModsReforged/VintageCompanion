package vintage.mods.companion.items;

import net.minecraft.item.Item;
import vintage.mods.companion.CompanionConfig;
import net.minecraft.item.EnumToolMaterial;
import vintage.mods.companion.items.base.*;
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
    WOOD(ToolMaterials.WOOD, new Ingredient("plankWood"), ToolType.EXCAVATOR_HAMMER),
    STONE(ToolMaterials.STONE, new Ingredient("cobblestone"), ToolType.EXCAVATOR_HAMMER),
    GOLD(ToolMaterials.GOLD, new Ingredient("ingotGold"), ToolType.EXCAVATOR_HAMMER),
    IRON(ToolMaterials.IRON, new Ingredient("ingotIron"), ToolType.EXCAVATOR_HAMMER),
    DIAMOND(ToolMaterials.DIAMOND, new Ingredient("gemDiamond"), ToolType.EXCAVATOR_HAMMER),
    // Applied Energistics
    NETHER_QUARTZ(ToolMaterials.NETHER_QUARTZ, new Ingredient("crystalNetherQuartz"), ToolType.ALL_TOOLS),
    CERTUS_QUARTZ(ToolMaterials.CERTUS_QUARTZ, new Ingredient("crystalCertusQuartz"), ToolType.EXCAVATOR_HAMMER),
    // Gems: Ruby, Sapphire, Green Sapphire
    RUBY(ToolMaterials.GEM, new Ingredient("gemRuby"), ToolType.ALL_TOOLS),
    SAPPHIRE(ToolMaterials.GEM, new Ingredient("gemSapphire"), ToolType.ALL_TOOLS),
    GREEN_SAPPHIRE(ToolMaterials.GEM, new Ingredient("gemGreenSapphire"), ToolType.ALL_TOOLS),
    // Base Metals
    ALUM(ToolMaterials.ALUM, new Ingredient("ingotAluminium", "ingotAluminum"), ToolType.ALL_TOOLS),
    BRONZE(ToolMaterials.BRONZE, new Ingredient("ingotBronze"), ToolType.ALL_TOOLS),
    CONSTANTAN(ToolMaterials.CONSTANTAN, new Ingredient("ingotCupronickel", "ingotConstantan"), ToolType.ALL_TOOLS),
    COPPER(ToolMaterials.COPPER, new Ingredient("ingotCopper"), ToolType.ALL_TOOLS),
    ELECTRUM(ToolMaterials.ELECTRUM, new Ingredient("ingotElectrum"), ToolType.ALL_TOOLS),
    INVAR(ToolMaterials.INVAR, new Ingredient("ingotInvar"), ToolType.ALL_TOOLS),
    LEAD(ToolMaterials.LEAD, new Ingredient("ingotLead"), ToolType.ALL_TOOLS),
    NICKEL(ToolMaterials.NICKEL, new Ingredient("ingotNickel"), ToolType.ALL_TOOLS),
    PLATINUM(ToolMaterials.PLATINUM, new Ingredient("ingotPlatinum"), ToolType.ALL_TOOLS),
    SILVER(ToolMaterials.SILVER, new Ingredient("ingotSilver"), ToolType.ALL_TOOLS),
    STEEL(ToolMaterials.STEEL, new Ingredient("ingotSteel"), ToolType.ALL_TOOLS),
    TIN(ToolMaterials.TIN, new Ingredient("ingotTin"), ToolType.ALL_TOOLS),
    // Compat: Mods
    THAUMIUM(ToolMaterials.THAUMIUM, new Ingredient("ingotThaumium"), ToolType.EXCAVATOR_HAMMER),
    STEELEAF(ToolMaterials.STEELEAF, new Ingredient("ingotSteeleaf"), ToolType.EXCAVATOR_HAMMER),
    IRONWOOD(ToolMaterials.IRONWOOD, new Ingredient("ingotIronwood"), ToolType.EXCAVATOR_HAMMER),
    FIERY(ToolMaterials.FIERY, new Ingredient("ingotFiery"), ToolType.EXCAVATOR_HAMMER),
    UNSTABLE(ToolMaterials.UNSTABLE, new Ingredient("ingotUnstable"), ToolType.EXCAVATOR_HAMMER);

    private static int totalToolCount = 0;
    private final ToolType toolType;
    private final Ingredient ingredients;

    private final String NAME;
    private final EnumToolMaterial MATERIAL;
    public static final Materials[] VALUES = values();

    private final LazyEntry<Item> hammerItem;
    private final LazyEntry<Item> excavatorItem;
    private final LazyEntry<Item> pickaxeItem;
    private final LazyEntry<Item> swordItem;
    private final LazyEntry<Item> shovelItem;
    private final LazyEntry<Item> hoeItem;

    Materials(final EnumToolMaterial material, Ingredient ingredients, final ToolType builder) {
        this.NAME = name().toLowerCase(Locale.ROOT);
        this.MATERIAL = material;
        this.ingredients = ingredients;
        this.toolType = builder;
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

        this.shovelItem = new LazyEntry<Item>(new LazyEntry.Supplier<Item>() {
            @Override
            public Item get() {
                if (builder.hasShovel()) {
                    int shovelId = getToolId(totalToolCount++);
                    return new ItemBaseShovel(shovelId, material, getName());
                }
                return null;
            }
        });

        this.hoeItem = new LazyEntry<Item>(new LazyEntry.Supplier<Item>() {
            @Override
            public Item get() {
                if (builder.hasHoe()) {
                    int hoeId = getToolId(totalToolCount++);
                    return new ItemBaseHoe(hoeId, material, getName());
                }
                return null;
            }
        });
    }

    private static int getToolId(int totalToolsAdded) {
        return CompanionConfig.toolsIdStartIndex + totalToolsAdded;
    }

    public ToolType getToolType() {
        return toolType;
    }

    public Ingredient getIngredients() {
        return this.ingredients;
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

    @Override
    public Item getShovelItem() {
        return shovelItem.get();
    }

    public Item getHoeItem() {
        return hoeItem.get();
    }

    public static class ToolType {
        // add these if needed
        private final boolean excavator;
        private final boolean hammer;
        private final boolean pickaxe;
        private final boolean sword;
        private final boolean shovel;
        private final boolean hoe;

        private static final ToolType EXCAVATOR_HAMMER = new ToolType.Builder().addExcavator().addHammer().build();
        private static final ToolType ALL_TOOLS = new ToolType.Builder().addExcavator().addHammer().addPickaxe().addSword().addShovel().addHoe().build();

        private ToolType(Builder builder) {
            this.excavator = builder.excavator;
            this.hammer = builder.hammer;
            this.pickaxe = builder.pickaxe;
            this.sword = builder.sword;
            this.shovel = builder.shovel;
            this.hoe = builder.hoe;
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

        public boolean hasShovel() {
            return shovel;
        }

        public boolean hasHoe() {
            return hoe;
        }

        public static class Builder {
            private boolean excavator;
            private boolean hammer;
            private boolean pickaxe;
            private boolean sword;
            private boolean shovel;
            private boolean hoe;

            public Builder() {
                this.excavator = false;
                this.hammer = false;
                this.pickaxe = false;
                this.sword = false;
                this.shovel = false;
                this.hoe = false;
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

            public Builder addShovel() {
                this.shovel = true;
                return this;
            }

            public Builder addHoe() {
                this.hoe = true;
                return this;
            }

            public ToolType build() {
                return new ToolType(this);
            }
        }
    }

    public static class Ingredient {
        public String[] items;

        public Ingredient(String... items) {
            this.items = items;
        }

        public String[] getItems() {
            return this.items;
        }
    }
}
