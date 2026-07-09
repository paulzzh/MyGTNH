package com.paulzzh.mygtnh.mixins.late.etfuturum;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import makamys.mclib.ext.assetdirector.AssetFetcher;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.io.File;

@Mixin(value = AssetFetcher.class)
public class AssetFetcherMixin {
    @WrapOperation(
        method = "downloadVersionIndex",
        at = @At(
            value = "INVOKE",
            target = "Lmakamys/mclib/ext/assetdirector/AssetFetcher;downloadJson(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;",
            remap = false
        ),
        remap = false
    )
    private <T> T downloadJson(AssetFetcher instance, String urlStr, Class<T> classOfT, Operation<T> original) {
        if (urlStr.equals("https://launchermeta.mojang.com/mc/game/version_manifest.json")) {
            return original.call(instance, "https://bmclapi2.bangbang93.com/mc/game/version_manifest.json", classOfT);
        }
        return original.call(instance, urlStr, classOfT);
    }

    @WrapOperation(
        method = "downloadAssetByHash",
        at = @At(
            value = "INVOKE",
            target = "Lmakamys/mclib/ext/assetdirector/AssetFetcher;copyURLToFile(Ljava/lang/String;Ljava/io/File;)V",
            remap = false
        ),
        remap = false
    )
    private void copyURLToFile(AssetFetcher instance, String source, File destination, Operation<Void> original) {
        if (source.startsWith("https://resources.download.minecraft.net")) {
            original.call(instance, source.replace("https://resources.download.minecraft.net",
                "https://bmclapi2.bangbang93.com/assets"), destination);
        } else {
            original.call(instance, source, destination);
        }
    }
}
