package vintage.mods.companion;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import vintage.mods.companion.items.Materials;
import vintage.mods.companion.items.Tools;
import vintage.mods.companion.items.compat.ItemRefs;
import vintage.mods.companion.proxy.CommonProxy;
import mods.vintage.core.platform.lang.ILangProvider;
import mods.vintage.core.platform.lang.LangManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Arrays;
import java.util.List;

@Mod(modid = Refs.id, name = Refs.name, dependencies = VintageCompanion.deps, useMetadata = true)
public class VintageCompanion implements ILangProvider {

    public static final String deps = "required-after:VintageCore;" +
            "after:ExtraUtilities;" +
            "after:TwilightForest;" +
            "after:Thaumcraft;" +
            "after:IC2;" +
            "after:Forestry;" +
            "after:GregTech_Addon;" +
            "after:NumiRP;" +
            "after:AppliedEnergistics";

    public static CreativeTabs TAB = new CreativeTabs(Refs.id) {

        @Override
        public Item getTabIconItem() {
            return Materials.DIAMOND.getHammerItem();
        }
    };

    @SidedProxy(clientSide = "vintage.mods.companion.proxy.ClientProxy", serverSide = "vintage.mods.companion.proxy.CommonProxy")
    public static CommonProxy PROXY;

    public VintageCompanion() {}

    @Mod.PreInit
    public void preInit(FMLPreInitializationEvent e) {
        CompanionConfig.init();
        LangManager.THIS.registerLangProvider(this);
        LangManager.THIS.loadCreativeTabName(Refs.id, Refs.name);
    }

    @Mod.Init
    public void init(FMLInitializationEvent e) {
        if (Loader.isModLoaded("ExtraUtilities")) {
            ItemStack unstableStack = ItemRefs.getUnstableStack();
            if (unstableStack != null) {
                OreDictionary.registerOre("ingotUnstable", unstableStack);
            }
        }
        if (Loader.isModLoaded("TwilightForest")) {
            ItemStack steeleafIngotStack = ItemRefs.getSteeleafStack();
            if (steeleafIngotStack != null) {
                OreDictionary.registerOre("ingotSteeleaf", steeleafIngotStack);
            }
            ItemStack ironwoodIngotStack = ItemRefs.getIronwoodStack();
            if (ironwoodIngotStack != null) {
                OreDictionary.registerOre("ingotIronwood", ironwoodIngotStack);
            }
            ItemStack fieryStack = ItemRefs.getFieryStack();
            if (fieryStack != null) {
                OreDictionary.registerOre("ingotFiery", fieryStack);
            }
        }
        if (Loader.isModLoaded("Thaumcraft")) {
            ItemStack thaumiumIngotStack = ItemRefs.getThaumiumStack();
            if (thaumiumIngotStack != null) {
                OreDictionary.registerOre("ingotThaumium", thaumiumIngotStack);
            }
        }
        Tools.init();
        PROXY.registerRenderInformation();
    }

    @Override
    public String getModid() {
        return Refs.id;
    }

    @Override
    public List<String> getLocalizationList() {
        return Arrays.asList(CompanionConfig.languages);
    }
}
