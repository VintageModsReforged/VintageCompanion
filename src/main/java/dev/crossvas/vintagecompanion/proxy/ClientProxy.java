package dev.crossvas.vintagecompanion.proxy;

import cpw.mods.fml.common.Loader;
import dev.crossvas.vintagecompanion.items.Tools;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerRenderInformation() {
        if (Loader.isModLoaded("ExtraUtilities")) {
            MinecraftForgeClient.registerItemRenderer(Tools.unstable_hammer.itemID, extrautils.ExtraUtilsClient.renderItemMultiTransparency);
            MinecraftForgeClient.registerItemRenderer(Tools.unstable_excavator.itemID, extrautils.ExtraUtilsClient.renderItemMultiTransparency);
        }
    }
}
