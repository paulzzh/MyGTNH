package com.paulzzh.mygtnh.mixins.late.gregtech;

import gregtech.api.util.GTLanguageManager;
import gregtech.loaders.preload.GTPreLoad;
import net.minecraftforge.common.config.Configuration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;

import static com.paulzzh.mygtnh.config.MyGTNHConfig.gt_lang;
import static gregtech.GTMod.GT_FML_LOGGER;

@Mixin(value = GTPreLoad.class)
public class GTPreLoadMixin {
    @Inject(
        method = "initLocalization",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraftforge/common/config/Configuration;load()V"
        ),
        remap = false
    )
    private static void inject1(File languageDir, CallbackInfo ci) {
        File l10nFile = new File(languageDir, gt_lang);
        if (l10nFile.isFile()) {
            GT_FML_LOGGER.info("Loading l10n file: " + gt_lang);
            GTLanguageManager.isEN_US = false;
            GTLanguageManager.sEnglishFile = new Configuration(l10nFile);
        } else {
            GT_FML_LOGGER.info("Cannot find l10n file " + gt_lang + ", fallback to GregTech.lang");
            GTLanguageManager.isEN_US = true;
            GTLanguageManager.sEnglishFile = new Configuration(new File(languageDir, "GregTech.lang"));
        }
    }
}
