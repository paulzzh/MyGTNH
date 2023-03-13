package tech.paulzzh.mygtnh.mixins.gregtech;

import gregtech.api.metatileentity.implementations.GT_MetaPipeEntity_Cable;
import net.minecraft.tileentity.TileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tech.paulzzh.mygtnh.mcmt.FJPool;

import java.util.HashSet;

@Mixin(value = GT_MetaPipeEntity_Cable.class, remap = false, priority = 1)
public class CableMixin {
    @Inject(method = "transferElectricity(BJJLjava/util/HashSet;)J", at = @At("HEAD"))
    public void head(byte aSide, long aVoltage, long aAmperage, HashSet<TileEntity> aAlreadyPassedSet, CallbackInfoReturnable<Long> cir) {
        FJPool.GT_Cable_transferElectricity.lock();
    }

    @Inject(method = "transferElectricity(BJJLjava/util/HashSet;)J", at = @At("RETURN"))
    public void tail(byte aSide, long aVoltage, long aAmperage, HashSet<TileEntity> aAlreadyPassedSet, CallbackInfoReturnable<Long> cir) {
        FJPool.GT_Cable_transferElectricity.unlock();
    }
}
