package tech.paulzzh.mygtnh.mixins.appeng;

import appeng.api.networking.IGridHost;
import appeng.api.networking.IGridNode;
import appeng.api.networking.events.MENetworkCellArrayUpdate;
import appeng.api.storage.ICellProvider;
import appeng.api.storage.IMEInventoryHandler;
import appeng.api.storage.data.IAEFluidStack;
import appeng.api.storage.data.IAEItemStack;
import appeng.me.cache.GridStorageCache;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tech.paulzzh.mygtnh.MixinIntrinsic;
import tech.paulzzh.mygtnh.mcmt.FJPool;

@Mixin(value = GridStorageCache.class, remap = false, priority = 1)
@Implements(@Interface(iface = MixinIntrinsic.class, prefix = "proxy$", remap = Interface.Remap.NONE))
public abstract class GridStorageCacheMixin {

    @Shadow
    public abstract void removeNode(final IGridNode node, final IGridHost machine);

    @Shadow
    public abstract void addNode(final IGridNode node, final IGridHost machine);

    @Shadow
    public abstract void registerCellProvider(final ICellProvider provider);

    @Shadow
    public abstract void unregisterCellProvider(final ICellProvider provider);

    @Shadow
    abstract IMEInventoryHandler<IAEItemStack> getItemInventoryHandler();

    @Shadow
    abstract IMEInventoryHandler<IAEFluidStack> getFluidInventoryHandler();

    @Inject(method = "cellUpdate", at = @At("HEAD"))
    public void head(MENetworkCellArrayUpdate ev, CallbackInfo ci) {
        FJPool.AE2_GridStorageCache.lock();
    }

    @Inject(method = "cellUpdate", at = @At("RETURN"))
    public void tail(MENetworkCellArrayUpdate ev, CallbackInfo ci) {
        FJPool.AE2_GridStorageCache.unlock();
    }

    @Intrinsic(displace = true)
    public void proxy$removeNode(final IGridNode node, final IGridHost machine) {
        try {
            FJPool.AE2_GridStorageCache.lock();
            this.removeNode(node, machine);
        } finally {
            FJPool.AE2_GridStorageCache.unlock();
        }
    }

    @Intrinsic(displace = true)
    public void proxy$addNode(final IGridNode node, final IGridHost machine) {
        try {
            FJPool.AE2_GridStorageCache.lock();
            this.addNode(node, machine);
        } finally {
            FJPool.AE2_GridStorageCache.unlock();
        }
    }

    @Intrinsic(displace = true)
    public void proxy$registerCellProvider(final ICellProvider provider) {
        try {
            FJPool.AE2_GridStorageCache.lock();
            this.registerCellProvider(provider);
        } finally {
            FJPool.AE2_GridStorageCache.unlock();
        }
    }

    @Intrinsic(displace = true)
    public void proxy$unregisterCellProvider(final ICellProvider provider) {
        try {
            FJPool.AE2_GridStorageCache.lock();
            this.unregisterCellProvider(provider);
        } finally {
            FJPool.AE2_GridStorageCache.unlock();
        }
    }

    @Intrinsic(displace = true)
    public IMEInventoryHandler<IAEItemStack> proxy$getItemInventoryHandler() {
        try {
            FJPool.AE2_GridStorageCache.lock();
            return this.getItemInventoryHandler();
        } finally {
            FJPool.AE2_GridStorageCache.unlock();
        }
    }

    @Intrinsic(displace = true)
    public IMEInventoryHandler<IAEFluidStack> proxy$getFluidInventoryHandler() {
        try {
            FJPool.AE2_GridStorageCache.lock();
            return this.getFluidInventoryHandler();
        } finally {
            FJPool.AE2_GridStorageCache.unlock();
        }
    }
}