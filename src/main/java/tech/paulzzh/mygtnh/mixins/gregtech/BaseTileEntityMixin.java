package tech.paulzzh.mygtnh.mixins.gregtech;

import gregtech.api.metatileentity.BaseTileEntity;
import org.spongepowered.asm.mixin.*;
import tech.paulzzh.mygtnh.MixinIntrinsic;
import tech.paulzzh.mygtnh.mcmt.FJPool;

@Mixin(value = BaseTileEntity.class, remap = false, priority = 1)
@Implements(@Interface(iface = MixinIntrinsic.class, prefix = "proxy$", remap = Interface.Remap.NONE))
public abstract class BaseTileEntityMixin {
    @Shadow
    public abstract void updateNeighbours(int mStrongRedstone, int oStrongRedstone);

    @Intrinsic(displace = true)
    public void proxy$updateNeighbours(int mStrongRedstone, int oStrongRedstone) {
        try {
            FJPool.GT_BaseTileEntity_updateNeighbours.lock();
            this.updateNeighbours(mStrongRedstone, oStrongRedstone);
        } finally {
            FJPool.GT_BaseTileEntity_updateNeighbours.unlock();
        }
    }
}