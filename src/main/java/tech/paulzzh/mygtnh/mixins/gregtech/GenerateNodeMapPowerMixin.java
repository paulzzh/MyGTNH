package tech.paulzzh.mygtnh.mixins.gregtech;

import gregtech.api.graphs.GenerateNodeMapPower;
import gregtech.api.graphs.consumers.ConsumerNode;
import net.minecraft.tileentity.TileEntity;
import org.spongepowered.asm.mixin.*;
import tech.paulzzh.mygtnh.MixinIntrinsic;
import tech.paulzzh.mygtnh.mcmt.FJPool;

import java.util.ArrayList;

@Mixin(value = GenerateNodeMapPower.class, remap = false, priority = 1)
@Implements(@Interface(iface = MixinIntrinsic.class, prefix = "proxy$", remap = Interface.Remap.NONE))
public abstract class GenerateNodeMapPowerMixin {
    @Shadow
    protected abstract boolean addConsumer(TileEntity aTileEntity, byte aSide, int aNodeValue,
                                           ArrayList<ConsumerNode> aConsumers);

    @Intrinsic(displace = true)
    public boolean proxy$addConsumer(TileEntity aTileEntity, byte aSide, int aNodeValue,
                                     ArrayList<ConsumerNode> aConsumers) {
        try {
            FJPool.GT_Cable_Consumers.lock();
            return this.addConsumer(aTileEntity, aSide, aNodeValue, aConsumers);
        } finally {
            FJPool.GT_Cable_Consumers.unlock();
        }
    }
}