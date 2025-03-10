package com.paulzzh.mygtnh.mixins.early.minecraft;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import net.minecraft.client.gui.FontRenderer;
import org.spongepowered.asm.lib.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = FontRenderer.class)
public class FontRendererMixin {

    @ModifyExpressionValue(method = "renderStringAtPos", at = @At(value = "INVOKE", target = "Ljava/lang/String;indexOf(I)I"))
    private int injected1(int original, @Share("j") LocalIntRef j) {
        j.set(original);
        return original;
    }

    @ModifyExpressionValue(method = "renderStringAtPos", at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/FontRenderer;unicodeFlag:Z", opcode = Opcodes.GETFIELD))
    private boolean injected2(boolean original, @Share("j") LocalIntRef j) {
        if (j.get() == -1) {
            return true;
        }
        return original;
    }
}
