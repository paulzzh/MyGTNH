package com.paulzzh.mygtnh.mixins.late.gregtech;

import com.google.common.collect.Iterables;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.implementations.MTEExtendedPowerMultiBlockBase;
import gregtech.common.pollution.Pollution;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import tectech.thing.metaTileEntity.hatch.MTEHatchEnergyMulti;
import tectech.thing.metaTileEntity.multi.base.TTMultiblockBase;

import java.util.ArrayList;

@Mixin(value = TTMultiblockBase.class)
public abstract class TTMultiblockBaseMixin extends MTEExtendedPowerMultiBlockBase<TTMultiblockBase> {

    protected TTMultiblockBaseMixin(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    @Unique
    private boolean myGTNH$explodedThisTick = false;

    @Shadow
    protected ArrayList<MTEHatchEnergyMulti> eEnergyMulti;
    @Shadow
    protected ArrayList<MTEHatchEnergyMulti> eUncertainHatches;
    @Shadow
    protected ArrayList<MTEHatchEnergyMulti> eDynamoMulti;
    @Shadow
    protected ArrayList<MTEHatchEnergyMulti> eInputData;
    @Shadow
    protected ArrayList<MTEHatchEnergyMulti> eOutputData;

    /**
     * @author Paulzzh
     * @reason readd explode
     */
    @Overwrite(remap = false)
    public final void explodeMultiblock() {
        if (myGTNH$explodedThisTick) {
            return;
        }
        myGTNH$explodedThisTick = true;
        Pollution.addPollution(getBaseMetaTileEntity(), 600000);
        Iterable<MetaTileEntity> allHatches = Iterables.concat(
            mInputBusses,
            mOutputBusses,
            mInputHatches,
            mOutputHatches,
            mDynamoHatches,
            mMufflerHatches,
            mEnergyHatches,
            mMaintenanceHatches,
            eEnergyMulti,
            eUncertainHatches,
            eDynamoMulti,
            eInputData,
            eOutputData);
        for (MetaTileEntity tTileEntity : allHatches) {
            if (tTileEntity != null && tTileEntity.getBaseMetaTileEntity() != null) {
                tTileEntity.getBaseMetaTileEntity()
                    .doExplosion(1);
            }
        }
        getBaseMetaTileEntity().doExplosion(1);
    }

    /**
     * @author Paulzzh
     * @reason readd explode
     */
    @Overwrite(remap = false)
    public void doExplosion(long aExplosionPower) {
        explodeMultiblock();
        super.doExplosion(aExplosionPower);
    }
}
