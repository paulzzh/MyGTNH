package com.paulzzh.mygtnh;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class Config {

    public static String greeting = "Hello World";
    public static boolean gc_armor = true;
    public static boolean gg_meme = true;
    public static boolean warp_eff = true;
    public static boolean bop_trail = true;
    public static boolean gs_cape = true;
    public static boolean gc_cape = true;
    public static boolean gtpp_cape = true;
    public static boolean gt_cape = true;
    public static boolean de_con = true;
    public static boolean de_pic = true;
    public static boolean bot_con = true;
    public static boolean aroma_cape = true;
    public static boolean mc_stat = true;
    public static boolean journey_stat = true;
    public static boolean gt_pollute = true;
    public static boolean nu_nor = true;

    public static void synchronizeConfiguration(File configFile) {
        Configuration configuration = new Configuration(configFile);

        greeting = configuration.getString("greeting", Configuration.CATEGORY_GENERAL, greeting, "How shall I greet?");
        gc_armor = configuration.getBoolean("gc_armor", Configuration.CATEGORY_GENERAL, gc_armor, "禁用galacticraft护甲渲染");
        gg_meme = configuration.getBoolean("gg_meme", Configuration.CATEGORY_GENERAL, gg_meme, "修改goodgenerator烂梗合成表");
        warp_eff = configuration.getBoolean("warp_eff", Configuration.CATEGORY_GENERAL, warp_eff, "屏蔽神秘扭曲效果");
        gt_pollute = configuration.getBoolean("gt_pollute", Configuration.CATEGORY_GENERAL, gt_pollute, "屏蔽GT污染渲染");
        nu_nor = configuration.getBoolean("nu_nor", Configuration.CATEGORY_GENERAL, nu_nor, "屏蔽营养学舔斧子归一50");
        bop_trail = configuration.getBoolean("bop_trail", Configuration.CATEGORY_GENERAL, bop_trail, "阻塞/禁用biomesoplenty赞助列表");
        gs_cape = configuration.getBoolean("gs_cape", Configuration.CATEGORY_GENERAL, gs_cape, "阻塞/禁用galaxyspace赞助披风");
        gc_cape = configuration.getBoolean("gc_cape", Configuration.CATEGORY_GENERAL, gc_cape, "异步/禁用galacticraft赞助披风");
        gtpp_cape = configuration.getBoolean("gtpp_cape", Configuration.CATEGORY_GENERAL, gtpp_cape, "异步/禁用GT++赞助披风");
        gt_cape = configuration.getBoolean("gt_cape", Configuration.CATEGORY_GENERAL, gt_cape, "异步/禁用GT/GTNH赞助披风");
        de_con = configuration.getBoolean("de_con", Configuration.CATEGORY_GENERAL, de_con, "异步/禁用draconicevolution贡献");
        de_pic = configuration.getBoolean("de_pic", Configuration.CATEGORY_GENERAL, de_pic, "异步/禁用draconicevolution图片");
        bot_con = configuration.getBoolean("bot_con", Configuration.CATEGORY_GENERAL, bot_con, "异步/禁用botania贡献");
        aroma_cape = configuration.getBoolean("aroma_cape", Configuration.CATEGORY_GENERAL, aroma_cape, "异步/禁用Aroma1997Core赞助披风");
        journey_stat = configuration.getBoolean("journey_stat", Configuration.CATEGORY_GENERAL, journey_stat, "异步/禁用journeymap统计");
        mc_stat = configuration.getBoolean("mc_stat", Configuration.CATEGORY_GENERAL, mc_stat, "异步/禁用minecraft数据上报");
        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}
