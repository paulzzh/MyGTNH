package com.paulzzh.mygtnh.mixins.early.minecraft;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import static com.paulzzh.mygtnh.MyGTNH.tickTime;

@Mixin(value = MinecraftServer.class)
public class MinecraftServerMixin3 {
    @ModifyExpressionValue(method = "run()V", at = @At(value = "INVOKE", target = "Lio/github/crucible/CrucibleConfigs;getTickTime()I", remap = false))
    private int inject(int original) {
        if (tickTime != 0L) {
            return (int) tickTime;
        }
        return original;
    }
}
