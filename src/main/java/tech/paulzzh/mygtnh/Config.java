package tech.paulzzh.mygtnh;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.io.File;

public class Config {

    public static String greeting = Defaults.greeting;
    public static Boolean bop_trail = Defaults.bop_trail;
    public static Boolean ftbu_warp = Defaults.ftbu_warp;
    public static Boolean gc_cape = Defaults.gc_cape;
    public static Boolean gc_armor = Defaults.gc_armor;
    public static Boolean gs_cape = Defaults.gs_cape;
    public static Boolean gg_meme = Defaults.gg_meme;
    public static Boolean gtpp_cape = Defaults.gtpp_cape;
    public static Boolean ae_debug = Defaults.ae_debug;
    public static Boolean mc_temt = Defaults.mc_temt;

    public Config(File file) {
        syncronizeConfiguration(file);
    }

    public static void syncronizeConfiguration(File configFile) {
        Configuration configuration = new Configuration(configFile);
        configuration.load();

        Property greetingProperty = configuration.get(Categories.general, "greeting", Defaults.greeting, "How shall I greet?");
        greeting = greetingProperty.getString();

        Property bop_trailProperty = configuration.get(Categories.general, "bop_trail", Defaults.bop_trail, "禁用biomesoplenty赞助列表");
        bop_trail = bop_trailProperty.getBoolean();

        Property ftbu_warpProperty = configuration.get(Categories.general, "ftbu_warp", Defaults.ftbu_warp, "启用FTBU/warpUI功能");
        ftbu_warp = ftbu_warpProperty.getBoolean();

        Property gc_capeProperty = configuration.get(Categories.general, "gc_cape", Defaults.gc_cape, "禁用galacticraft赞助披风");
        gc_cape = gc_capeProperty.getBoolean();

        Property gc_armorProperty = configuration.get(Categories.general, "gc_armor", Defaults.gc_armor, "禁用galacticraft护甲渲染");
        gc_armor = gc_armorProperty.getBoolean();

        Property gs_capeProperty = configuration.get(Categories.general, "gs_cape", Defaults.gs_cape, "禁用galaxyspace赞助披风");
        gs_cape = gs_capeProperty.getBoolean();

        Property gg_memeProperty = configuration.get(Categories.general, "gg_meme", Defaults.gg_meme, "禁用goodgenerator烂梗合成表");
        gg_meme = gg_memeProperty.getBoolean();

        Property gtpp_capeProperty = configuration.get(Categories.general, "gtpp_cape", Defaults.gtpp_cape, "禁用GT++赞助披风");
        gtpp_cape = gtpp_capeProperty.getBoolean();

        Property ae_debugProperty = configuration.get(Categories.general, "ae_debug", Defaults.ae_debug, "启用AE2相关debug");
        ae_debug = ae_debugProperty.getBoolean();

        Property mc_temtProperty = configuration.get(Categories.general, "mc_temt", Defaults.mc_temt, "启用线程池更新TE");
        mc_temt = mc_temtProperty.getBoolean();

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }

    private static class Defaults {
        public static final String greeting = "Hello World";
        public static final Boolean bop_trail = true;
        public static final Boolean ftbu_warp = true;
        public static final Boolean gc_cape = true;
        public static final Boolean gc_armor = true;
        public static final Boolean gs_cape = true;
        public static final Boolean gg_meme = true;
        public static final Boolean gtpp_cape = true;
        public static final Boolean ae_debug = false;
        public static final Boolean mc_temt = false;
    }

    private static class Categories {
        public static final String general = "general";
    }
}