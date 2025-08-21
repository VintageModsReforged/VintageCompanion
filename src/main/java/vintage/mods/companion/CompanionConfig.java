package vintage.mods.companion;

import mods.vintage.core.helpers.ConfigHelper;
import mods.vintage.core.platform.lang.LocalizationProvider;
import net.minecraftforge.common.Configuration;

@LocalizationProvider
public class CompanionConfig {

    public static Configuration main_config;

    @LocalizationProvider.List(modId = Refs.id)
    public static String[] languages;
    public static double durability_factor = 1.0D;

    public static int toolsIdStartIndex = 10236;

    public static void init() {
        main_config = ConfigHelper.getConfigFor("VintageCompanion");

        languages = ConfigHelper.getLocalizations(main_config, new String[] {"en_US"}, Refs.id);
        durability_factor = ConfigHelper.getDouble(main_config, "general", "durability_factor", 0.1, 1.0, 1.0, "Durability factor for Hammers.");
        toolsIdStartIndex = getId("toolsIdStartIndex", toolsIdStartIndex);

        if (main_config.hasChanged()) main_config.save();
    }

    public static int getId(String tag, int defaultValue) {
        return ConfigHelper.getId(main_config, "IDs", tag, defaultValue);
    }
}
