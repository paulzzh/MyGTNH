package tech.paulzzh.gtnhfix.mixins.goodgenerator;

import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import goodgenerator.loader.RecipeLoader;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = RecipeLoader.class, remap = false)
public class RecipeLoaderMixin {
    @ModifyConstant(method = "RecipeLoad()V", constant = @Constant(intValue = 114514))
    private static int injected1(int value) {
        return 111111;
    }
    @ModifyConstant(method = "RecipeLoad()V", constant = @Constant(intValue = 1919810))
    private static int injected2(int value) {
        return 1900000;
    }
}
