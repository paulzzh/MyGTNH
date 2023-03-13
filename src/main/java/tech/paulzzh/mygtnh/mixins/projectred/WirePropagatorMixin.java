package tech.paulzzh.mygtnh.mixins.projectred;

import mrtjp.projectred.transmission.WirePropagator$;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tech.paulzzh.mygtnh.mcmt.FJPool;

@Mixin(value = WirePropagator$.class, remap = false, priority = 1)
public class WirePropagatorMixin {
    @Inject(method = "propagateTo(Lmrtjp/projectred/transmission/IWirePart;Lcodechicken/multipart/TMultiPart;I)V", at = @At("HEAD"))
    public void head(CallbackInfo ci) {
        FJPool.PR_WirePropagator_propagateTo.lock();
    }

    @Inject(method = "propagateTo(Lmrtjp/projectred/transmission/IWirePart;Lcodechicken/multipart/TMultiPart;I)V", at = @At("TAIL"))
    public void tail(CallbackInfo ci) {
        FJPool.PR_WirePropagator_propagateTo.unlock();
    }
}
