package com.paulzzh.mygtnh.mixins.early.minecraft;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

import static com.paulzzh.mygtnh.MyGTNH.autoSave;
import static com.paulzzh.mygtnh.MyGTNH.tickTime;

@Mixin(value = MinecraftServer.class)
public abstract class MinecraftServerMixin2 {
    @ModifyConstant(method = "run()V", constant = @Constant(longValue = 50L))
    private static long inject3(long value) {
        if (tickTime != 0) {
            return tickTime / 1000000;
        }
        return value;
    }

    @Shadow
    protected abstract void saveAllWorlds(boolean dontLog);

    @Redirect(method = "tick()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;saveAllWorlds(Z)V"))
    public void inject1(MinecraftServer mc, boolean dontLog) {
        if (autoSave) {
            saveAllWorlds(dontLog);
        }
    }

    @Redirect(method = "tick()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/management/ServerConfigurationManager;saveAllPlayerData()V"))
    public void inject2(ServerConfigurationManager manager) {
        if (autoSave) {
            manager.saveAllPlayerData();
        }
    }
}
