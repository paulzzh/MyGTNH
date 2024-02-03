package com.paulzzh.mygtnh.mixins;

import com.paulzzh.mygtnh.Config;
import com.paulzzh.mygtnh.MyGTNH;
import cpw.mods.fml.relauncher.FMLLaunchHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public enum Mixins {
    GTPP_CAPE(new Builder("禁用GT++赞助披风").addTargetedMod(TargetedMod.GTPP).setSide(Side.BOTH)
        .setPhase(Phase.LATE).addMixinClasses("GTPP.CapeUtilsMixin")
        .setApplyIf(() -> Config.gtpp_cape)
    ),
    GS_CAPE(new Builder("禁用galaxyspace赞助披风").addTargetedMod(TargetedMod.GALAXYSPACE).setSide(Side.BOTH)
        .setPhase(Phase.LATE).addMixinClasses("galaxyspace.ClientProxyMixin")
        .setApplyIf(() -> Config.gs_cape)
    ),
    GC_CAPE(new Builder("禁用galacticraft赞助披风").addTargetedMod(TargetedMod.GALACTICRAFT).setSide(Side.BOTH)
        .setPhase(Phase.LATE).addMixinClasses("galacticraft.ClientProxyCoreMixin")
        .setApplyIf(() -> Config.gc_cape)
    ),
    BOP_TRAIL(new Builder("禁用biomesoplenty赞助列表").addTargetedMod(TargetedMod.BIOMESOPLENTY).setSide(Side.BOTH)
        .setPhase(Phase.LATE).addMixinClasses("biomesoplenty.TrailManagerMixin")
        .setApplyIf(() -> Config.bop_trail)
    ),
    GC_ARMOR(new Builder("禁用galacticraft护甲渲染").addTargetedMod(TargetedMod.GALACTICRAFT).setSide(Side.BOTH)
        .setPhase(Phase.LATE).addMixinClasses("galacticraft.RenderPlayerGCMixin")
        .setApplyIf(() -> Config.gc_armor)
    ),
    GG_MEME(new Builder("修改goodgenerator烂梗合成表").addTargetedMod(TargetedMod.GOODGENERATOR).setSide(Side.BOTH)
        .setPhase(Phase.LATE).addMixinClasses("goodgenerator.RecipeLoader02Mixin")
        .setApplyIf(() -> Config.gg_meme)
    ),
    WARP_EFF(new Builder("屏蔽神秘扭曲效果").addTargetedMod(TargetedMod.WARPTHEORY).setSide(Side.BOTH)
        .setPhase(Phase.LATE).addMixinClasses("warptheory.WarpHandlerMixin", "thaumcraft.WarpEventsMixin")
        .setApplyIf(() -> Config.warp_eff)
    ),
    GT_POLLUTE(new Builder("屏蔽GT污染渲染").addTargetedMod(TargetedMod.GREGTECH).setSide(Side.BOTH)
        .setPhase(Phase.LATE).addMixinClasses("gregtech.PollutionRendererMixin")
        .setApplyIf(() -> Config.gt_pollute)
    ),
    GT_CAPE(new Builder("禁用GT/GTNH赞助披风").addTargetedMod(TargetedMod.GREGTECH).setSide(Side.BOTH)
        .setPhase(Phase.LATE).addMixinClasses("gregtech.GTClientMixin")
        .setApplyIf(() -> Config.gt_cape)
    ),
    DE_CON(new Builder("禁用draconicevolution贡献").addTargetedMod(TargetedMod.DRACONIC).setSide(Side.BOTH)
        .setPhase(Phase.LATE).addMixinClasses("draconicevolution.ContributorHandlerMixin")
        .setApplyIf(() -> Config.de_con)
    ),
    DE_PIC(new Builder("禁用draconicevolution图片").addTargetedMod(TargetedMod.DRACONIC).setSide(Side.BOTH)
        .setPhase(Phase.LATE).addMixinClasses("draconicevolution.DownloadThreadMixin")
        .setApplyIf(() -> Config.de_pic)
    ),
    BOT_CON(new Builder("禁用botania贡献").addTargetedMod(TargetedMod.BOTANIA).setSide(Side.BOTH)
        .setPhase(Phase.LATE).addMixinClasses("botania.ThreadContributorListLoaderMixin")
        .setApplyIf(() -> Config.bot_con)
    ),
    AROMA_CAPE(new Builder("禁用Aroma1997Core赞助披风").addTargetedMod(TargetedMod.AROMA1997CORE).setSide(Side.BOTH)
        .setPhase(Phase.LATE).addMixinClasses("aroma1997.MiscStuffMixin")
        .setApplyIf(() -> Config.aroma_cape)
    ),
    JOURNEY_STAT(new Builder("禁用journeymap统计").addTargetedMod(TargetedMod.JOURNEYMAP).setSide(Side.BOTH)
        .setPhase(Phase.LATE).addMixinClasses("journeymap.ClientMixin")
        .setApplyIf(() -> Config.journey_stat)
    ),
    MC_STAT(new Builder("禁用minecraft数据上报").addTargetedMod(TargetedMod.VANILLA).setSide(Side.BOTH)
        .setPhase(Phase.EARLY).addMixinClasses("minecraft.PlayerUsageSnooperMixin")
        .setApplyIf(() -> Config.mc_stat)
    ),
    ;

    private final List<String> mixinClasses;
    private final Supplier<Boolean> applyIf;
    private final Phase phase;
    private final Side side;
    private final List<TargetedMod> targetedMods;
    private final List<TargetedMod> excludedMods;

    Mixins(Builder builder) {
        this.mixinClasses = builder.mixinClasses;
        this.applyIf = builder.applyIf;
        this.side = builder.side;
        this.targetedMods = builder.targetedMods;
        this.excludedMods = builder.excludedMods;
        this.phase = builder.phase;
        if (this.targetedMods.isEmpty()) {
            throw new RuntimeException("No targeted mods specified for " + this.name());
        }
        if (this.applyIf == null) {
            throw new RuntimeException("No ApplyIf function specified for " + this.name());
        }
    }

    public static List<String> getEarlyMixins(Set<String> loadedCoreMods) {
        final List<String> mixins = new ArrayList<>();
        final List<String> notLoading = new ArrayList<>();
        for (Mixins mixin : Mixins.values()) {
            if (mixin.phase == Mixins.Phase.EARLY) {
                if (mixin.shouldLoad(loadedCoreMods, Collections.emptySet())) {
                    mixins.addAll(mixin.mixinClasses);
                } else {
                    notLoading.addAll(mixin.mixinClasses);
                }
            }
        }
        MyGTNH.LOG.info("Not loading the following EARLY mixins: {}", notLoading);
        return mixins;
    }

    public static List<String> getLateMixins(Set<String> loadedMods) {
        final List<String> mixins = new ArrayList<>();
        final List<String> notLoading = new ArrayList<>();
        for (Mixins mixin : Mixins.values()) {
            if (mixin.phase == Mixins.Phase.LATE) {
                if (mixin.shouldLoad(Collections.emptySet(), loadedMods)) {
                    mixins.addAll(mixin.mixinClasses);
                } else {
                    notLoading.addAll(mixin.mixinClasses);
                }
            }
        }
        MyGTNH.LOG.info("Not loading the following LATE mixins: {}", notLoading.toString());
        return mixins;
    }

    @SuppressWarnings("SimplifyStreamApiCallChains")
    private static String[] addPrefix(String prefix, String... values) {
        return Arrays.stream(values)
            .map(s -> prefix + s)
            .collect(Collectors.toList())
            .toArray(new String[values.length]);
    }

    private boolean shouldLoadSide() {
        return side == Side.BOTH || (side == Side.SERVER && FMLLaunchHandler.side().isServer())
            || (side == Side.CLIENT && FMLLaunchHandler.side().isClient());
    }

    private boolean allModsLoaded(List<TargetedMod> targetedMods, Set<String> loadedCoreMods, Set<String> loadedMods) {
        if (targetedMods.isEmpty()) return false;

        for (TargetedMod target : targetedMods) {
            if (target == TargetedMod.VANILLA) continue;

            // Check coremod first
            if (!loadedCoreMods.isEmpty() && target.coreModClass != null
                && !loadedCoreMods.contains(target.coreModClass))
                return false;
            else if (!loadedMods.isEmpty() && target.modId != null && !loadedMods.contains(target.modId)) return false;
        }

        return true;
    }

    private boolean noModsLoaded(List<TargetedMod> targetedMods, Set<String> loadedCoreMods, Set<String> loadedMods) {
        if (targetedMods.isEmpty()) return true;

        for (TargetedMod target : targetedMods) {
            if (target == TargetedMod.VANILLA) continue;

            // Check coremod first
            if (!loadedCoreMods.isEmpty() && target.coreModClass != null
                && loadedCoreMods.contains(target.coreModClass))
                return false;
            else if (!loadedMods.isEmpty() && target.modId != null && loadedMods.contains(target.modId)) return false;
        }

        return true;
    }

    private boolean shouldLoad(Set<String> loadedCoreMods, Set<String> loadedMods) {
        return (shouldLoadSide() && applyIf.get()
            && allModsLoaded(targetedMods, loadedCoreMods, loadedMods)
            && noModsLoaded(excludedMods, loadedCoreMods, loadedMods));
    }

    private enum Side {
        BOTH,
        CLIENT,
        SERVER
    }

    private enum Phase {
        EARLY,
        LATE,
    }

    private static class Builder {

        private final List<String> mixinClasses = new ArrayList<>();
        private final List<TargetedMod> targetedMods = new ArrayList<>();
        private final List<TargetedMod> excludedMods = new ArrayList<>();
        private Supplier<Boolean> applyIf = () -> true;
        private Side side = Side.BOTH;
        private Phase phase = Phase.LATE;

        public Builder(@SuppressWarnings("unused") String description) {
        }

        public Builder addMixinClasses(String... mixinClasses) {
            this.mixinClasses.addAll(Arrays.asList(mixinClasses));
            return this;
        }

        public Builder setPhase(Phase phase) {
            this.phase = phase;
            return this;
        }

        public Builder setSide(Side side) {
            this.side = side;
            return this;
        }

        public Builder setApplyIf(Supplier<Boolean> applyIf) {
            this.applyIf = applyIf;
            return this;
        }

        public Builder addTargetedMod(TargetedMod mod) {
            this.targetedMods.add(mod);
            return this;
        }

        public Builder addExcludedMod(TargetedMod mod) {
            this.excludedMods.add(mod);
            return this;
        }
    }
}
