package com.paulzzh.mygtnh.mixins.late.goodgenerator;

import goodgenerator.loader.RecipeLoader_02;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = RecipeLoader_02.class)
public class RecipeLoader02Mixin {
    @ModifyConstant(method = "RecipeLoad()V", constant = @Constant(intValue = 114514), remap = false)
    private static int injected1(int value) {
        return 111111;
    }
}
