package tech.paulzzh.mygtnh.mixins.gregtech;

import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_InputBus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tech.paulzzh.mygtnh.mcmt.FJPool;

@Mixin(value = GT_MetaTileEntity_Hatch_InputBus.class, remap = false, priority = 1)
public class InputBusMixin {
    @Inject(method = "fillStacksIntoFirstSlots", at = @At("HEAD"))
    public void head(CallbackInfo ci) {
        FJPool.GT_InputBus_fillStacksIntoFirstSlots.lock();
    }

    @Inject(method = "fillStacksIntoFirstSlots", at = @At("RETURN"))
    public void tail(CallbackInfo ci) {
        FJPool.GT_InputBus_fillStacksIntoFirstSlots.unlock();
    }
}
