package com.paulzzh.mygtnh.mixins.early.minecraft;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import static com.paulzzh.mygtnh.MyGTNH.autoSave;
import static com.paulzzh.mygtnh.MyGTNH.tickTime;

@Mixin(value = MinecraftServer.class)
public class MinecraftServerMixin2 {
    @ModifyConstant(method = "run()V", constant = @Constant(longValue=50L))
    private static long inject3(long original) {
        if (tickTime != 0) {
            return tickTime / 1000000;
        }
        return original;
    }

    @WrapWithCondition(method = "tick()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;saveAllWorlds(Z)V"))
    private boolean inject1(MinecraftServer mc, boolean dontLog) {
        return autoSave;
    }

    @WrapWithCondition(method = "tick()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/management/ServerConfigurationManager;saveAllPlayerData()V"))
    private boolean inject2(ServerConfigurationManager manager) {
        return autoSave;
    }
}
