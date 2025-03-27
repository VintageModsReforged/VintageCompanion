package dev.crossvas.vintagecompanion.items;

import dev.crossvas.vintagecompanion.CompanionConfig;
import net.minecraft.item.EnumToolMaterial;

import java.util.Locale;

public enum Materials {
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

    public final String NAME;
    public final EnumToolMaterial MATERIAL;

    Materials(EnumToolMaterial material) {
        this.NAME = name().toLowerCase(Locale.ROOT);
        this.MATERIAL = material;
    }

    public int hammerId() {
        return CompanionConfig.toolsIdStartIndex + ordinal();
    }

    public int excavatorId() {
        return CompanionConfig.toolsIdStartIndex + values().length + ordinal();
    }
}
