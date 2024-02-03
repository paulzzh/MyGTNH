package com.paulzzh.mygtnh.mixins.late.thaumcraft;

import com.paulzzh.mygtnh.MyGTNH;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Slice;
import thaumcraft.common.lib.WarpEvents;

@Mixin(value = WarpEvents.class, remap = false)
public class WarpEventsMixin {
    @ModifyVariable(method = "checkWarpEvent", at = @At("STORE"), ordinal = 0,
        slice = @Slice(
            from = @At(value = "INVOKE", target = "Lthaumcraft/common/lib/research/PlayerKnowledge;setWarpCounter(Ljava/lang/String;I)V"),
            to = @At(value = "INVOKE", target = "Lcpw/mods/fml/common/network/simpleimpl/SimpleNetworkWrapper;sendTo(Lcpw/mods/fml/common/network/simpleimpl/IMessage;Lnet/minecraft/entity/player/EntityPlayerMP;)V")
        ))
    private static int injected(int x) {
        MyGTNH.LOG.info("block warp effect " + x);
        return 4;
    }
}
