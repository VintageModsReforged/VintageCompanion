package dev.crossvas.vintagecompanion.items;

import net.minecraft.item.EnumToolMaterial;
import net.minecraftforge.common.EnumHelper;

public class ToolMaterials {

    // vanilla
    public static final EnumToolMaterial WOOD = EnumToolMaterial.WOOD;
    public static final EnumToolMaterial STONE = EnumToolMaterial.STONE;
    public static final EnumToolMaterial GOLD = EnumToolMaterial.GOLD;
    public static final EnumToolMaterial IRON = EnumToolMaterial.IRON;
    public static final EnumToolMaterial DIAMOND = EnumToolMaterial.EMERALD;
    // Applied Energistics
    public static final EnumToolMaterial NETHER_QUARTZ = EnumToolMaterial.IRON;
    public static final EnumToolMaterial CERTUS_QUARTZ = EnumToolMaterial.IRON;
    // Ruby, Sapphire, Green Sapphire
    public static final EnumToolMaterial GEM = EnumHelper.addToolMaterial("gem", 2, 500, 8F, 3, 12);
    // Extra Utilities
    public static final EnumToolMaterial UNSTABLE = EnumToolMaterial.EMERALD;
    // Thaumcraft
    public static final EnumToolMaterial THAUMIUM = EnumHelper.addToolMaterial("thaumium", 3, 400, 7F, 2, 22);
//    public static final EnumToolMaterial ELEMENTAL = EnumHelper.addToolMaterial("elemental", 3, 1500, 10.0F, 3, 18);
    // Twilight Forest
    public static final EnumToolMaterial STEELEAF = EnumHelper.addToolMaterial("steeleaf", 3, 131, 8F, 3, 9);
    public static final EnumToolMaterial IRONWOOD = EnumHelper.addToolMaterial("ironwood", 2, 512, 6.5F, 2, 25);
    public static final EnumToolMaterial FIERY = EnumHelper.addToolMaterial("fiery", 4, 1024, 9F, 4, 10);
//    public static final EnumToolMaterial KNIGHTLY = EnumHelper.addToolMaterial("knight", 3, 512, 8F, 3, 8);
    // Base Metals
    public static final EnumToolMaterial ALUM = EnumHelper.addToolMaterial("alum", 1, 225, 10F, 1, 14);
    public static final EnumToolMaterial BRONZE = EnumHelper.addToolMaterial("bronze", 2, 325, 6.0F, 2, 10);
    public static final EnumToolMaterial CONSTANTAN = EnumHelper.addToolMaterial("constantan", 2, 275, 6.0F, 2, 12);
    public static final EnumToolMaterial COPPER = EnumHelper.addToolMaterial("copper", 1, 175, 4F, 1, 7);
    public static final EnumToolMaterial ELECTRUM = EnumHelper.addToolMaterial("electrum", 0, 100, 14.0F, 1, 30);
    public static final EnumToolMaterial INVAR = EnumHelper.addToolMaterial("invar", 2, 425, 6.5F, 3, 12);
    public static final EnumToolMaterial LEAD = EnumHelper.addToolMaterial("lead", 1, 100, 5F, 1, 9);
    public static final EnumToolMaterial NICKEL = EnumHelper.addToolMaterial("nickel", 2, 300, 6.5F, 3, 18);
    public static final EnumToolMaterial PLATINUM = EnumHelper.addToolMaterial("platinum", 4, 1400, 9F, 4, 16);
    public static final EnumToolMaterial SILVER = EnumHelper.addToolMaterial("silver", 1, 75, 6F, 1, 25);
    public static final EnumToolMaterial STEEL = EnumHelper.addToolMaterial("steel", 2, 400, 6.5F, 3, 10);
    public static final EnumToolMaterial TIN = EnumHelper.addToolMaterial("copper", 1, 150, 4.5F, 1, 7);
}
