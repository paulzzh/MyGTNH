package com.paulzzh.mygtnh.mixins.late.worldedit;

import com.sk89q.worldedit.LocalSession;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.logging.Logger;

@Mixin(value = LocalSession.class)
public class LocalSessionMixin {
    @Redirect(
        method = "handleCUIInitializationMessage",
        at = @At(
            value = "INVOKE",
            target = "Ljava/util/logging/Logger;warning(Ljava/lang/String;)V"
        ),
        remap = false
    )
    private void inject(Logger instance, String msg) {
    }
}
