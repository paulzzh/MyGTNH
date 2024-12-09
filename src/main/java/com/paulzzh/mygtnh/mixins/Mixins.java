package com.paulzzh.mygtnh.mixins;

import com.paulzzh.mygtnh.MyGTNH;
import com.paulzzh.mygtnh.config.MyGTNHConfig;
import cpw.mods.fml.relauncher.FMLLaunchHandler;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public enum Mixins {
    GS_CAPE(new Builder("禁用galaxyspace赞助披风").addTargetedMod(TargetedMod.GALAXYSPACE).setSide(Side.BOTH)
        .setPhase(Phase.LATE).addMixinClasses("galaxyspace.ClientProxyMixin")
        .setApplyIf(() -> MyGTNHConfig.gs_cape)
    ),
    GC_CAPE(new Builder("禁用galacticraft赞助披风").addTargetedMod(TargetedMod.GALACTICRAFT).setSide(Side.BOTH)
        .setPhase(Phase.LATE).addMixinClasses("galacticraft.ClientProxyCoreMixin")
        .setApplyIf(() -> MyGTNHConfig.gc_cape)
    ),
    BOP_TRAIL(new Builder("禁用biomesoplenty赞助列表").addTargetedMod(TargetedMod.BIOMESOPLENTY).setSide(Side.BOTH)
        .setPhase(Phase.LATE).addMixinClasses("biomesoplenty.TrailManagerMixin")
        .setApplyIf(() -> MyGTNHConfig.bop_trail)
    ),
    GC_ARMOR(new Builder("禁用galacticraft护甲渲染").addTargetedMod(TargetedMod.GALACTICRAFT).setSide(Side.BOTH)
        .setPhase(Phase.LATE).addMixinClasses("galacticraft.RenderPlayerGCMixin")
        .setApplyIf(() -> MyGTNHConfig.gc_armor)
    ),
    GG_MEME(new Builder("修改goodgenerator烂梗合成表").addTargetedMod(TargetedMod.GREGTECH).setSide(Side.BOTH)
        .setPhase(Phase.LATE).addMixinClasses("gregtech.RecipeLoader2Mixin")
        .setApplyIf(() -> MyGTNHConfig.gg_meme)
    ),
    WARP_EFF(new Builder("屏蔽神秘扭曲效果").addTargetedMod(TargetedMod.WARPTHEORY).setSide(Side.BOTH)
        .setPhase(Phase.LATE).addMixinClasses("warptheory.WarpHandlerMixin", "thaumcraft.WarpEventsMixin")
        .setApplyIf(() -> MyGTNHConfig.warp_eff)
    ),
    GT_POLLUTE(new Builder("屏蔽GT污染渲染").addTargetedMod(TargetedMod.GREGTECH).setSide(Side.BOTH)
        .setPhase(Phase.LATE).addMixinClasses("gregtech.PollutionRendererMixin")
        .setApplyIf(() -> MyGTNHConfig.gt_pollute)
    ),
    TANK_DEBUFF(new Builder("缩短GT超级缸负面效果").addTargetedMod(TargetedMod.GREGTECH).setSide(Side.BOTH)
        .setPhase(Phase.LATE).addMixinClasses("gregtech.ItemMachinesMixin")
        .setApplyIf(() -> MyGTNHConfig.tank_debuff)
    ),
    MULTI_NOTIFY(new Builder("GT多方块故障提醒").addTargetedMod(TargetedMod.GREGTECH).setSide(Side.BOTH)
        .setPhase(Phase.LATE).addMixinClasses("gregtech.MTEMultiBlockBaseMixin")
        .setApplyIf(() -> MyGTNHConfig.multi_notify)
    ),
    NU_NOR(new Builder("屏蔽营养学舔斧子归一50").addTargetedMod(TargetedMod.NUTRITION).setSide(Side.BOTH)
        .setPhase(Phase.LATE).addMixinClasses("nutrition.NormalizeMixin")
        .setApplyIf(() -> MyGTNHConfig.nu_nor)
    ),
    GT_CAPE(new Builder("修改GT/GTNH赞助披风").addTargetedMod(TargetedMod.GREGTECH).setSide(Side.BOTH)
        .setPhase(Phase.LATE).addMixinClasses("gregtech.GTClientMixin", "gregtech.GTCapeRendererMixin")
        .setApplyIf(() -> MyGTNHConfig.gt_cape)
    ),
    DE_CON(new Builder("禁用draconicevolution贡献").addTargetedMod(TargetedMod.DRACONIC).setSide(Side.BOTH)
        .setPhase(Phase.LATE).addMixinClasses("draconicevolution.ContributorHandlerMixin")
        .setApplyIf(() -> MyGTNHConfig.de_con)
    ),
    DE_PIC(new Builder("禁用draconicevolution图片").addTargetedMod(TargetedMod.DRACONIC).setSide(Side.BOTH)
        .setPhase(Phase.LATE).addMixinClasses("draconicevolution.DownloadThreadMixin")
        .setApplyIf(() -> MyGTNHConfig.de_pic)
    ),
    BOT_CON(new Builder("禁用botania贡献").addTargetedMod(TargetedMod.BOTANIA).setSide(Side.BOTH)
        .setPhase(Phase.LATE).addMixinClasses("botania.ThreadContributorListLoaderMixin")
        .setApplyIf(() -> MyGTNHConfig.bot_con)
    ),
    AROMA_CAPE(new Builder("禁用Aroma1997Core赞助披风").addTargetedMod(TargetedMod.AROMA1997CORE).setSide(Side.BOTH)
        .setPhase(Phase.LATE).addMixinClasses("aroma1997.MiscStuffMixin")
        .setApplyIf(() -> MyGTNHConfig.aroma_cape)
    ),
    JOURNEY_STAT(new Builder("禁用journeymap统计").addTargetedMod(TargetedMod.JOURNEYMAP).setSide(Side.BOTH)
        .setPhase(Phase.LATE).addMixinClasses("journeymap.ClientMixin")
        .setApplyIf(() -> MyGTNHConfig.journey_stat)
    ),
    MC_STAT(new Builder("禁用minecraft数据上报").addTargetedMod(TargetedMod.VANILLA).setSide(Side.BOTH)
        .setPhase(Phase.EARLY).addMixinClasses("minecraft.MinecraftMixin", "minecraft.MinecraftServerMixin", "minecraft.DedicatedServerMixin", "minecraft.IntegratedServerMixin")
        .setApplyIf(() -> MyGTNHConfig.mc_stat)
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
