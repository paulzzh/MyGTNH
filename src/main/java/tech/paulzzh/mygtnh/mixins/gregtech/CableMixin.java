package tech.paulzzh.mygtnh.mixins.gregtech;

import gregtech.api.metatileentity.implementations.GT_MetaPipeEntity_Cable;
import net.minecraft.tileentity.TileEntity;
import org.spongepowered.asm.mixin.*;
import tech.paulzzh.mygtnh.MixinIntrinsic;
import tech.paulzzh.mygtnh.mcmt.FJPool;

import java.util.HashSet;

@Mixin(value = GT_MetaPipeEntity_Cable.class, remap = false, priority = 1)
@Implements(@Interface(iface = MixinIntrinsic.class, prefix = "proxy$", remap = Interface.Remap.NONE))
public abstract class CableMixin {
    @Shadow
    public abstract long transferElectricity(byte aSide, long aVoltage, long aAmperage, HashSet<TileEntity> aAlreadyPassedSet);

    @Intrinsic(displace = true)
    public long proxy$transferElectricity(byte aSide, long aVoltage, long aAmperage, HashSet<TileEntity> aAlreadyPassedSet) {
        try {
            FJPool.GT_Cable_Consumers.lock();
            return this.transferElectricity(aSide, aVoltage, aAmperage, aAlreadyPassedSet);
        } finally {
            FJPool.GT_Cable_Consumers.unlock();
        }
    }
}
