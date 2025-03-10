package com.paulzzh.mygtnh.mixins.late.worldedit;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.sk89q.worldedit.LocalSession;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.logging.Logger;

@Mixin(value = LocalSession.class)
public class LocalSessionMixin {
    @WrapWithCondition(
        method = "handleCUIInitializationMessage",
        at = @At(
            value = "INVOKE",
            target = "Ljava/util/logging/Logger;warning(Ljava/lang/String;)V"
        ),
        remap = false
    )
    private boolean inject(Logger instance, String msg) {
        return false;
    }
}
