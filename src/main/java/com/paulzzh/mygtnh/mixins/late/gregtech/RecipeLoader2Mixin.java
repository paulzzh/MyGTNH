package com.paulzzh.mygtnh.mixins.late.gregtech;

import goodgenerator.loader.RecipeLoader2;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = RecipeLoader2.class)
public class RecipeLoader2Mixin {
    @ModifyConstant(method = "RecipeLoad()V", constant = @Constant(intValue = 114514), remap = false)
    private static int injected1(int value) {
        return 111111;
    }
}
