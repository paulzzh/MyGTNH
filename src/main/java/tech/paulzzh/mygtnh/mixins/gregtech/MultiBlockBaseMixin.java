package tech.paulzzh.mygtnh.mixins.gregtech;

import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Output;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_MultiBlockBase;
import net.minecraftforge.fluids.FluidStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tech.paulzzh.mygtnh.mcmt.FJPool;

import java.util.List;

@Mixin(value = GT_MetaTileEntity_MultiBlockBase.class, remap = false, priority = 1)
public class MultiBlockBaseMixin {
    @Inject(method = "dumpFluid", at = @At("HEAD"))
    private static void head(List<GT_MetaTileEntity_Hatch_Output> aOutputHatches, FluidStack copiedFluidStack, boolean restrictiveHatchesOnly, CallbackInfoReturnable<Boolean> cir) {
        FJPool.GT_MultiBlockBase_dumpFluid.lock();
    }

    @Inject(method = "dumpFluid", at = @At("RETURN"))
    private static void tail(List<GT_MetaTileEntity_Hatch_Output> aOutputHatches, FluidStack copiedFluidStack, boolean restrictiveHatchesOnly, CallbackInfoReturnable<Boolean> cir) {
        FJPool.GT_MultiBlockBase_dumpFluid.unlock();
    }

    @Inject(method = "updateSlots", at = @At("HEAD"))
    public void head2(CallbackInfo ci) {
        FJPool.GT_MultiBlockBase_updateSlots.lock();
    }

    @Inject(method = "updateSlots", at = @At("RETURN"))
    public void tail2(CallbackInfo ci) {
        FJPool.GT_MultiBlockBase_updateSlots.unlock();
    }
}