package com.paulzzh.mygtnh.mixins.early.minecraft;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.util.ChatComponentText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.paulzzh.mygtnh.MyGTNH.*;

@Mixin(value = MinecraftServer.class)
public abstract class MinecraftServerMixin2 {
    @Shadow
    private ServerConfigurationManager serverConfigManager;

    @Shadow
    public abstract void tick();

    @ModifyConstant(method = "run()V", constant = @Constant(longValue = 50L))
    private long inject3(long original) {
        if (tickTime != 0) {
            return tickTime / 1000000;
        }
        return original;
    }

    @Inject(method = "run()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;tick()V", shift = At.Shift.AFTER))
    private void inject4(CallbackInfo ci) {
        if (tickWarp > 0) {
            boolean b = autoSave;
            autoSave = false;
            int t = 0;
            while (tickWarp > 0) {
                tick();
                t++;
                tickWarp--;
            }
            autoSave = b;
            serverConfigManager.sendChatMsg(new ChatComponentText(String.format("warp %d tick(s)", t)));
        }
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
