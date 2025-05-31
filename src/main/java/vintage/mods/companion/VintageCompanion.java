package vintage.mods.companion;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.block.Block;
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
        OreDictionary.registerOre("cobblestone", Block.cobblestone);
        OreDictionary.registerOre("ingotGold", Item.ingotGold);
        OreDictionary.registerOre("ingotIron", Item.ingotIron);
        OreDictionary.registerOre("gemDiamond", Item.diamond);
        OreDictionary.registerOre("crystalNetherQuartz", Item.netherQuartz);
        if (Loader.isModLoaded("ExtraUtilities")) {
            Item unstableStack = ItemRefs.unstableStack().get();
            OreDictionary.registerOre("ingotUnstable", unstableStack);
        }
        if (Loader.isModLoaded("TwilightForest")) {
            Item steeleafIngotStack = ItemRefs.steeleafStack().get();
            OreDictionary.registerOre("ingotSteeleaf", steeleafIngotStack);

            Item ironwoodIngotStack = ItemRefs.ironwoodStack().get();
            OreDictionary.registerOre("ingotIronwood", ironwoodIngotStack);

            Item fieryStack = ItemRefs.fieryStack().get();
            OreDictionary.registerOre("ingotFiery", fieryStack);
        }
        if (Loader.isModLoaded("Thaumcraft")) {
            Item thaumiumIngotStack = ItemRefs.thaumiumStack().get();
            OreDictionary.registerOre("ingotThaumium", new ItemStack(thaumiumIngotStack, 1, 2));
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
