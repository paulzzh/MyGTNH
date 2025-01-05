package com.paulzzh.mygtnh.mixins.early.minecraft;

import net.minecraft.client.gui.FontRenderer;
import org.spongepowered.asm.lib.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = FontRenderer.class)
public class FontRendererMixin {
    @Shadow
    private boolean unicodeFlag;
    @Unique
    private int j;

    @Redirect(method = "renderStringAtPos", at = @At(value = "INVOKE", target = "Ljava/lang/String;indexOf(I)I"))
    private int injected1(String s, int ch) {
        j = s.indexOf(ch);
        return j;
    }

    @Redirect(method = "renderStringAtPos", at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/FontRenderer;unicodeFlag:Z", opcode = Opcodes.GETFIELD))
    public boolean injected2(FontRenderer font) {
        if (j == -1) {
            return true;
        }
        return unicodeFlag;
    }
}
