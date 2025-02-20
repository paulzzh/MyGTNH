package com.paulzzh.mygtnh.mixins.late.gregtech;

import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.implementations.MTEMultiBlockBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.paulzzh.mygtnh.MyGTNH.MTE_CACHE;
import static com.paulzzh.mygtnh.Utils.notifyMaintenance;

@Mixin(value = MTEMultiBlockBase.class)
public abstract class MTEMultiBlockBaseMixin {
    @Shadow(remap = false)
    public abstract int getRepairStatus();

    @Shadow(remap = false)
    public abstract int getIdealStatus();

    @Inject(method = "onPostTick", at = @At(value = "RETURN"), remap = false)
    public void onPostTick(IGregTechTileEntity aBaseMetaTileEntity, long aTick, CallbackInfo ci) {
        if (getRepairStatus() != getIdealStatus() && getRepairStatus() > 0) {
            MTE_CACHE.add((MTEMultiBlockBase) (Object) this);
        }
    }

    @Inject(method = "causeMaintenanceIssue", at = @At(value = "RETURN"), remap = false)
    public void causeMaintenanceIssue(CallbackInfo ci) {
        notifyMaintenance((MTEMultiBlockBase) (Object) this, null);
    }
}
