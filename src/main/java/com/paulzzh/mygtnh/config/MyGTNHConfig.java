package com.paulzzh.mygtnh.config;

import com.gtnewhorizon.gtnhlib.config.Config;

@Config(modid = "mygtnh")
public class MyGTNHConfig {
    @Config.Comment("How shall I greet?")
    @Config.DefaultString("Hello World")
    @Config.RequiresMcRestart
    public static String greeting;

    @Config.Comment("禁用galacticraft护甲渲染")
    @Config.DefaultBoolean(true)
    @Config.RequiresMcRestart
    public static boolean gc_armor;

    @Config.Comment("修改goodgenerator烂梗合成表")
    @Config.DefaultBoolean(true)
    @Config.RequiresMcRestart
    public static boolean gg_meme;

    @Config.Comment("屏蔽神秘扭曲效果")
    @Config.DefaultBoolean(true)
    @Config.RequiresMcRestart
    public static boolean warp_eff;

    @Config.Comment("屏蔽GT污染渲染")
    @Config.DefaultBoolean(true)
    @Config.RequiresMcRestart
    public static boolean gt_pollute;

    @Config.Comment("缩短GT超级缸负面效果")
    @Config.DefaultBoolean(true)
    @Config.RequiresMcRestart
    public static boolean tank_debuff;

    @Config.Comment("屏蔽营养学舔斧子归一50")
    @Config.DefaultBoolean(true)
    @Config.RequiresMcRestart
    public static boolean nu_nor;

    @Config.Comment("阻塞/禁用biomesoplenty赞助列表")
    @Config.DefaultBoolean(true)
    @Config.RequiresMcRestart
    public static boolean bop_trail;

    @Config.Comment("阻塞/禁用galaxyspace赞助披风")
    @Config.DefaultBoolean(true)
    @Config.RequiresMcRestart
    public static boolean gs_cape;

    @Config.Comment("异步/禁用galacticraft赞助披风")
    @Config.DefaultBoolean(true)
    @Config.RequiresMcRestart
    public static boolean gc_cape;

    @Config.Comment("异步/禁用GT++赞助披风")
    @Config.DefaultBoolean(true)
    @Config.RequiresMcRestart
    public static boolean gtpp_cape;

    @Config.Comment("异步/禁用GT/GTNH赞助披风")
    @Config.DefaultBoolean(true)
    @Config.RequiresMcRestart
    public static boolean gt_cape;

    @Config.Comment("异步/禁用draconicevolution贡献")
    @Config.DefaultBoolean(true)
    @Config.RequiresMcRestart
    public static boolean de_con;

    @Config.Comment("异步/禁用draconicevolution图片")
    @Config.DefaultBoolean(true)
    @Config.RequiresMcRestart
    public static boolean de_pic;

    @Config.Comment("异步/禁用botania贡献")
    @Config.DefaultBoolean(true)
    @Config.RequiresMcRestart
    public static boolean bot_con;

    @Config.Comment("异步/禁用Aroma1997Core赞助披风")
    @Config.DefaultBoolean(true)
    @Config.RequiresMcRestart
    public static boolean aroma_cape;

    @Config.Comment("异步/禁用minecraft数据上报")
    @Config.DefaultBoolean(true)
    @Config.RequiresMcRestart
    public static boolean mc_stat;

    @Config.Comment("异步/禁用journeymap统计")
    @Config.DefaultBoolean(true)
    @Config.RequiresMcRestart
    public static boolean journey_stat;
}
