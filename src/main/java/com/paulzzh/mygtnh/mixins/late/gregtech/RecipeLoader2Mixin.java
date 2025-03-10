package com.paulzzh.mygtnh.mixins.late.gregtech;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import goodgenerator.loader.RecipeLoader2;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = RecipeLoader2.class)
public class RecipeLoader2Mixin {
    @ModifyExpressionValue(method = "RecipeLoad()V", at = @At(value = "CONSTANT", args = "intValue=114514"), remap = false)
    private static int injected1(int value) {
        return 111111;
    }
}
