package vintage.mods.companion;

import cpw.mods.fml.common.Loader;
import mods.vintage.core.helpers.StackHelper;

public class Refs {

    public static final String id = "vintagecompanion";
    public static final String name = "Vintage: Companion";

    public static boolean shouldAdd(String oreDictValue) {
        return !StackHelper.getStackFromOre(oreDictValue).isEmpty();
    }

    public static boolean shouldRegister(String modid) {
        return Loader.isModLoaded(modid);
    }
}
