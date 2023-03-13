package tech.paulzzh.mygtnh.mixins.appeng;

import appeng.api.networking.events.MENetworkCellArrayUpdate;
import appeng.me.cache.GridStorageCache;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tech.paulzzh.mygtnh.mcmt.FJPool;

@Mixin(value = GridStorageCache.class, remap = false, priority = 1)
public class GridStorageCacheMixin {
    @Inject(method = "cellUpdate", at = @At("HEAD"))
    public void head(MENetworkCellArrayUpdate ev, CallbackInfo ci) {
        FJPool.AE2_GridStorageCache_cellUpdate.lock();
    }

    @Inject(method = "cellUpdate", at = @At("RETURN"))
    public void tail(MENetworkCellArrayUpdate ev, CallbackInfo ci) {
        FJPool.AE2_GridStorageCache_cellUpdate.unlock();
    }
}