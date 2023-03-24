package tech.paulzzh.mygtnh.mixins.gregtech;

import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_MultiBlockBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tech.paulzzh.mygtnh.mcmt.FJPool;

@Mixin(value = GT_MetaTileEntity_MultiBlockBase.class, remap = false, priority = 1)
public class MultiBlockBaseMixin {
    @Inject(method = "onPostTick", at = @At("HEAD"))
    private void onPostTick(IGregTechTileEntity aBaseMetaTileEntity, long aTick, CallbackInfo ci) {
        FJPool.unlocks();
    }
}
