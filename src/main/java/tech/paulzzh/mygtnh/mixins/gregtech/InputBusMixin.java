package tech.paulzzh.mygtnh.mixins.gregtech;

import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_InputBus;
import org.spongepowered.asm.mixin.*;
import tech.paulzzh.mygtnh.MixinIntrinsic;
import tech.paulzzh.mygtnh.mcmt.FJPool;

@Mixin(value = GT_MetaTileEntity_Hatch_InputBus.class, remap = false, priority = 1)
@Implements(@Interface(iface = MixinIntrinsic.class, prefix = "proxy$", remap = Interface.Remap.NONE))
public abstract class InputBusMixin {
    @Shadow
    protected abstract void fillStacksIntoFirstSlots();

    @Intrinsic(displace = true)
    public void proxy$fillStacksIntoFirstSlots() {
        try {
            FJPool.GT_InputBus_fillStacksIntoFirstSlots.lock();
            this.fillStacksIntoFirstSlots();
        } finally {
            FJPool.GT_InputBus_fillStacksIntoFirstSlots.unlock();
        }
    }
}
