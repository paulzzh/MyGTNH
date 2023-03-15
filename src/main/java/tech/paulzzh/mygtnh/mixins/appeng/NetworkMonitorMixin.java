package tech.paulzzh.mygtnh.mixins.appeng;

import appeng.api.config.Actionable;
import appeng.api.networking.security.BaseActionSource;
import appeng.api.storage.data.IAEStack;
import appeng.me.cache.NetworkMonitor;
import org.spongepowered.asm.mixin.*;
import tech.paulzzh.mygtnh.MixinIntrinsic;
import tech.paulzzh.mygtnh.mcmt.FJPool;

@Mixin(value = NetworkMonitor.class, remap = false, priority = 1)
@Implements(@Interface(iface = MixinIntrinsic.class, prefix = "proxy$", remap = Interface.Remap.NONE))
public abstract class NetworkMonitorMixin<T extends IAEStack<T>> {
    @Shadow
    protected abstract void postChange(final boolean add, final Iterable<T> changes, final BaseActionSource src);

    @Shadow
    public abstract T injectItems(final T input, final Actionable mode, final BaseActionSource src);

    @Shadow
    public abstract T extractItems(final T input, final Actionable mode, final BaseActionSource src);

    @Intrinsic(displace = true)
    public void proxy$postChange(final boolean add, final Iterable<T> changes, final BaseActionSource src) {
        try {
            FJPool.AE2_NetworkMonitor_postChange.lock();
            this.postChange(add, changes, src);
        } finally {
            FJPool.AE2_NetworkMonitor_postChange.unlock();
        }
    }

    @Intrinsic(displace = true)
    public T proxy$injectItems(final T input, final Actionable mode, final BaseActionSource src) {
        try {
            FJPool.AE2_NetworkMonitor_injectItems.lock();
            return this.injectItems(input, mode, src);
        } finally {
            FJPool.AE2_NetworkMonitor_injectItems.unlock();
        }
    }

    @Intrinsic(displace = true)
    public T proxy$extractItems(final T input, final Actionable mode, final BaseActionSource src) {
        try {
            FJPool.AE2_NetworkMonitor_extractItems.lock();
            return this.extractItems(input, mode, src);
        } finally {
            FJPool.AE2_NetworkMonitor_extractItems.unlock();
        }
    }
}
