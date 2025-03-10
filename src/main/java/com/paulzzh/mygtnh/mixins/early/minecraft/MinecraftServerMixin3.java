package com.paulzzh.mygtnh.mixins.early.minecraft;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static com.paulzzh.mygtnh.MyGTNH.tickTime;

@Mixin(value = MinecraftServer.class)
public abstract class MinecraftServerMixin3 {
    @Redirect(method = "run()V", at = @At(value = "INVOKE", target = "Lio/github/crucible/CrucibleConfigs;getTickTime()I", remap = false), require = 0)
    public int inject(int original) {
        if (tickTime != 0) {
            return tickTime;
        }
        return original;
    }
}
