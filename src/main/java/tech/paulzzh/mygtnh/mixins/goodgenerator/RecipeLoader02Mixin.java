package tech.paulzzh.mygtnh.mixins.goodgenerator;

import goodgenerator.loader.RecipeLoader_02;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = RecipeLoader_02.class, remap = false)
public class RecipeLoader02Mixin {
    @ModifyConstant(method = "RecipeLoad()V", constant = @Constant(intValue = 114514))
    private static int injected1(int value) {
        return 111111;
    }

    @ModifyConstant(method = "RecipeLoad()V", constant = @Constant(intValue = 1919810))
    private static int injected2(int value) {
        return 1900000;
    }
}
