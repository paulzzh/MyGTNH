package tech.paulzzh.mygtnh.mixins.gregtech;

import gregtech.api.metatileentity.BaseMetaTileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tech.paulzzh.mygtnh.mcmt.FJPool;

@Mixin(value = BaseMetaTileEntity.class, remap = false, priority = 1)
public class BaseMetaTileEntityMixin {
    @Inject(method = "generatePowerNodes", at = @At("HEAD"))
    public void head(CallbackInfo ci) {
        FJPool.GT_BaseMetaTileEntity_generatePowerNodes.lock();
    }

    @Inject(method = "generatePowerNodes", at = @At("RETURN"))
    public void tail(CallbackInfo ci) {
        FJPool.GT_BaseMetaTileEntity_generatePowerNodes.unlock();
    }
}