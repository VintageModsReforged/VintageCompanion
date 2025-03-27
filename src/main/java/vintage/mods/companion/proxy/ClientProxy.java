package vintage.mods.companion.proxy;

import cpw.mods.fml.common.Loader;
import vintage.mods.companion.items.Materials;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerRenderInformation() {
        if (Loader.isModLoaded("ExtraUtilities")) {
            MinecraftForgeClient.registerItemRenderer(Materials.UNSTABLE.getHammerItem().itemID, extrautils.ExtraUtilsClient.renderItemMultiTransparency);
            MinecraftForgeClient.registerItemRenderer(Materials.UNSTABLE.getExcavatorItem().itemID, extrautils.ExtraUtilsClient.renderItemMultiTransparency);
        }
    }
}
