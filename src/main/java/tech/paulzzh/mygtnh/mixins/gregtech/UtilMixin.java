package tech.paulzzh.mygtnh.mixins.gregtech;

import gregtech.api.interfaces.tileentity.IEnergyConnected;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tech.paulzzh.mygtnh.mcmt.FJPool;


@Mixin(targets = "gregtech.api.interfaces.tileentity.IEnergyConnected$Util", remap = false, priority = 1)
public final class UtilMixin {
    @Inject(method = "emitEnergyToNetwork", at = @At("HEAD"))
    private static void head(long aVoltage, long aAmperage, IEnergyConnected aEmitter, CallbackInfoReturnable<Long> cir) {
        FJPool.GT_IEnergyConnected_emitEnergyToNetwork.lock();
    }

    @Inject(method = "emitEnergyToNetwork", at = @At("RETURN"))
    private static void tail(long aVoltage, long aAmperage, IEnergyConnected aEmitter, CallbackInfoReturnable<Long> cir) {
        FJPool.GT_IEnergyConnected_emitEnergyToNetwork.unlock();
    }
}