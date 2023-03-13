package tech.paulzzh.mygtnh.mixins.appeng;

import appeng.api.config.Actionable;
import appeng.api.networking.security.BaseActionSource;
import appeng.api.storage.data.IAEStack;
import appeng.me.cache.NetworkMonitor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tech.paulzzh.mygtnh.mcmt.FJPool;

@Mixin(value = NetworkMonitor.class, remap = false, priority = 1)
public class NetworkMonitorMixin<T extends IAEStack<T>> {
    @Inject(method = "postChange", at = @At("HEAD"))
    public void head(CallbackInfo ci) {
        FJPool.AE2_NetworkMonitor_postChange.lock();
    }

    @Inject(method = "postChange", at = @At("RETURN"))
    public void tail(CallbackInfo ci) {
        FJPool.AE2_NetworkMonitor_postChange.unlock();
    }

    @Inject(method = "injectItems", at = @At("HEAD"))
    public void head2(T input, Actionable mode, BaseActionSource src, CallbackInfoReturnable<T> cir) {
        FJPool.AE2_NetworkMonitor_injectItems.lock();
    }

    @Inject(method = "injectItems", at = @At("RETURN"))
    public void tail2(T input, Actionable mode, BaseActionSource src, CallbackInfoReturnable<T> cir) {
        FJPool.AE2_NetworkMonitor_injectItems.unlock();
    }

    @Inject(method = "extractItems", at = @At("HEAD"))
    public void head3(T input, Actionable mode, BaseActionSource src, CallbackInfoReturnable<T> cir) {
        FJPool.AE2_NetworkMonitor_extractItems.lock();
    }

    @Inject(method = "extractItems", at = @At("RETURN"))
    public void tail3(T input, Actionable mode, BaseActionSource src, CallbackInfoReturnable<T> cir) {
        FJPool.AE2_NetworkMonitor_extractItems.unlock();
    }

}
