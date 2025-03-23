package com.paulzzh.mygtnh.mixins.late.gregtech;

import gregtech.api.metatileentity.implementations.MTEMultiBlockBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.paulzzh.mygtnh.MyGTNH.MTE_CACHE;
import static com.paulzzh.mygtnh.Utils.notifyMaintenance;

@Mixin(value = MTEMultiBlockBase.class)
public abstract class MTEMultiBlockBaseMixin {
    @Unique
    private boolean myGTNH$dirty;

    @Shadow(remap = false)
    public abstract int getRepairStatus();

    @Shadow(remap = false)
    public abstract int getIdealStatus();

    @Inject(method = "checkMaintenance", at = @At(value = "RETURN"), remap = false)
    private void checkMaintenance(CallbackInfo ci) {
        if (getRepairStatus() != getIdealStatus() && getRepairStatus() > 0) {
            MTE_CACHE.add((MTEMultiBlockBase) (Object) this);
            if (myGTNH$dirty) {
                myGTNH$dirty = false;
                notifyMaintenance((MTEMultiBlockBase) (Object) this, null);
            }
        }
    }

    @Inject(method = "causeMaintenanceIssue", at = @At(value = "RETURN"), remap = false)
    private void causeMaintenanceIssue(CallbackInfo ci) {
        myGTNH$dirty = true;
    }
}
