package vintage.mods.companion.proxy;

import cpw.mods.fml.common.Loader;
import net.minecraftforge.client.MinecraftForgeClient;
import vintage.mods.companion.items.Materials;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerRenderInformation() {
        if (Loader.isModLoaded("ExtraUtilities")) {
            MinecraftForgeClient.registerItemRenderer(Materials.UNSTABLE.getHammerItem().itemID, extrautils.ExtraUtilsClient.renderItemMultiTransparency);
            MinecraftForgeClient.registerItemRenderer(Materials.UNSTABLE.getExcavatorItem().itemID, extrautils.ExtraUtilsClient.renderItemMultiTransparency);
            MinecraftForgeClient.registerItemRenderer(Materials.UNSTABLE.getSickleItem().itemID, extrautils.ExtraUtilsClient.renderItemMultiTransparency);
        }
    }
}
