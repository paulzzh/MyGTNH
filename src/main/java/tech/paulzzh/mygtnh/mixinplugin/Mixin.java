package tech.paulzzh.mygtnh.mixinplugin;

import cpw.mods.fml.relauncher.FMLLaunchHandler;

import java.util.Arrays;
import java.util.List;

import static tech.paulzzh.mygtnh.mixinplugin.MixinConfig.config;
import static tech.paulzzh.mygtnh.mixinplugin.TargetedMod.*;

public enum Mixin {

    //
    // IMPORTANT: Do not make any references to any mod from this file. This file is loaded quite early on and if
    // you refer to other mods you load them as well. The consequence is: You can't inject any previously loaded classes!
    // Exception: Tags.java, as long as it is used for Strings only!
    //

    // Replace with your own mixins:

    RecipeLoader02Mixin("goodgenerator.RecipeLoader02Mixin", config.gg_meme, GOODGENERATOR, VANILLA),

    RecipeLoaderMixin("goodgenerator.RecipeLoaderMixin", config.gg_meme, GOODGENERATOR, VANILLA),

    RenderPlayerGCMixin("galacticraft.RenderPlayerGCMixin", config.gc_armor, GALACTICRAFT, VANILLA),

    ClientProxyCoreMixin("galacticraft.ClientProxyCoreMixin", config.gc_cape, GALACTICRAFT, VANILLA),

    ClientProxyMixin("galaxyspace.ClientProxyMixin", config.gs_cape, GALAXYSPACE, VANILLA),

    CmdWarpMixin("FTBU.CmdWarpMixin", config.ftbu_warp, FTBU, VANILLA),

    TrailManagerMixin("biomesoplenty.TrailManagerMixin", config.bop_trail, BIOMESOPLENTY, VANILLA),

    CapeUtilsMixin("GTPP.CapeUtilsMixin", config.gtpp_cape, GTPP, VANILLA),

    PartExportBusMixin("appeng.PartExportBusMixin", config.ae_debug, APPENG, VANILLA),

    PartFluidExportBusMixin("ae2fc.PartFluidExportBusMixin", config.ae_debug, AE2FC, VANILLA),


    WorldMixin("minecraft.WorldMixin", config.mc_temt, Side.SERVER, VANILLA),
    WorldServerMixin("minecraft.WorldServerMixin", config.mc_temt, Side.SERVER, VANILLA),
    AE2PlatformMixin("appeng.PlatformMixin", config.mc_temt, Side.SERVER, APPENG, VANILLA),
    IC2PlatformMixin("ic2.PlatformMixin", config.mc_temt, Side.SERVER, IC2, VANILLA),
    NetworkMonitorMixin("appeng.NetworkMonitorMixin", config.mc_temt, Side.SERVER, APPENG, VANILLA),
    GridStorageCacheMixin("appeng.GridStorageCacheMixin", config.mc_temt, Side.SERVER, APPENG, VANILLA),
    CableMixin("gregtech.CableMixin", config.mc_temt, Side.SERVER, GREGTECH, VANILLA),
    UtilityMixin("gregtech.UtilityMixin", config.mc_temt, Side.SERVER, GREGTECH, VANILLA),
    MultiBlockBaseMixin("gregtech.MultiBlockBaseMixin", config.mc_temt, Side.SERVER, GREGTECH, VANILLA),
    InputBusMixin("gregtech.InputBusMixin", config.mc_temt, Side.SERVER, GREGTECH, VANILLA),
    BaseMetaTileEntityMixin("gregtech.BaseMetaTileEntityMixin", config.mc_temt, Side.SERVER, GREGTECH, VANILLA);
    //WirePropagatorMixin("projectred.WirePropagatorMixin", config.mc_temt, Side.SERVER, PROJECTRED, VANILLA);

    public final String mixinClass;
    public final List<TargetedMod> targetedMods;
    private final Side side;
    public Boolean enable;

    Mixin(String mixinClass, Side side, TargetedMod... targetedMods) {
        this.mixinClass = mixinClass;
        this.targetedMods = Arrays.asList(targetedMods);
        this.side = side;
    }

    Mixin(String mixinClass, Boolean enable, TargetedMod... targetedMods) {
        this.mixinClass = mixinClass;
        this.targetedMods = Arrays.asList(targetedMods);
        this.side = Side.BOTH;
        this.enable = enable;
    }

    Mixin(String mixinClass, Boolean enable, Side side, TargetedMod... targetedMods) {
        this.mixinClass = mixinClass;
        this.targetedMods = Arrays.asList(targetedMods);
        this.side = side;
        this.enable = enable;
    }

    public boolean shouldLoad(List<TargetedMod> loadedMods) {
        return enable && (side == Side.BOTH
                || side == Side.SERVER && FMLLaunchHandler.side().isServer()
                || side == Side.CLIENT && FMLLaunchHandler.side().isClient())
                && loadedMods.containsAll(targetedMods);
    }
}

enum Side {
    BOTH,
    CLIENT,
    SERVER;
}
