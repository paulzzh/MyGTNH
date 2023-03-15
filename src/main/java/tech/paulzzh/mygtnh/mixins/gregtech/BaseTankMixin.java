package tech.paulzzh.mygtnh.mixins.gregtech;

import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_BasicTank;
import net.minecraftforge.fluids.FluidStack;
import org.spongepowered.asm.mixin.*;
import tech.paulzzh.mygtnh.MixinIntrinsic;
import tech.paulzzh.mygtnh.mcmt.FJPool;


@Mixin(value = GT_MetaTileEntity_BasicTank.class, remap = false, priority = 1)
@Implements(@Interface(iface = MixinIntrinsic.class, prefix = "proxy$", remap = Interface.Remap.NONE))
public abstract class BaseTankMixin {
    @Shadow
    public abstract int fill(FluidStack aFluid, boolean doFill);

    @Intrinsic(displace = true)
    public int proxy$fill(FluidStack aFluid, boolean doFill) {
        try {
            FJPool.GT_BasicTank_fill.lock();
            return this.fill(aFluid, doFill);
        } finally {
            FJPool.GT_BasicTank_fill.unlock();
        }
    }
}