package tech.paulzzh.mygtnh.mixins.gregtech;

import gregtech.api.metatileentity.BaseMetaTileEntity;
import org.spongepowered.asm.lib.Opcodes;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tech.paulzzh.mygtnh.MixinIntrinsic;
import tech.paulzzh.mygtnh.mcmt.FJPool;

@Mixin(value = BaseMetaTileEntity.class, remap = false, priority = 1)
@Implements(@Interface(iface = MixinIntrinsic.class, prefix = "proxy$", remap = Interface.Remap.NONE))
public abstract class BaseMetaTileEntityMixin {
    @Shadow
    public abstract void generatePowerNodes();

    @Shadow
    public abstract long getAverageElectricInput();

    @Shadow
    public abstract long injectEnergyUnits(byte aSide, long aVoltage, long aAmperage);

    @Shadow
    public abstract String[] getInfoData();

    @Intrinsic(displace = true)
    public void proxy$generatePowerNodes() {
        try {
            FJPool.GT_BaseMetaTileEntity_generatePowerNodes.lock();
            this.generatePowerNodes();
        } finally {
            FJPool.GT_BaseMetaTileEntity_generatePowerNodes.unlock();
        }
    }

    @Inject(method = "updateEntity()V", at = @At(value = "FIELD", opcode = Opcodes.PUTFIELD, target = "Lgregtech/api/metatileentity/BaseMetaTileEntity;mAverageEUInputIndex:I"))
    public void head(CallbackInfo ci) {
        FJPool.GT_BaseMetaTileEntity_mAverageEUInputIndex.lock();
        FJPool.GT_BaseMetaTileEntity_mAverageEUInputIndex.unlock();
    }

    @Intrinsic(displace = true)
    public long proxy$getAverageElectricInput() {
        try {
            FJPool.GT_BaseMetaTileEntity_mAverageEUInputIndex.lock();
            return this.getAverageElectricInput();
        } finally {
            FJPool.GT_BaseMetaTileEntity_mAverageEUInputIndex.unlock();
        }
    }

    @Intrinsic(displace = true)
    public long proxy$injectEnergyUnits(byte aSide, long aVoltage, long aAmperage) {
        try {
            FJPool.GT_BaseMetaTileEntity_mAverageEUInputIndex.lock();
            return this.injectEnergyUnits(aSide, aVoltage, aAmperage);
        } finally {
            FJPool.GT_BaseMetaTileEntity_mAverageEUInputIndex.unlock();
        }
    }

    @Intrinsic(displace = true)
    public String[] proxy$getInfoData() {
        try {
            FJPool.GT_BaseMetaTileEntity_mAverageEUInputIndex.lock();
            return this.getInfoData();
        } finally {
            FJPool.GT_BaseMetaTileEntity_mAverageEUInputIndex.unlock();
        }
    }
}