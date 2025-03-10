package com.paulzzh.mygtnh.mixins.late.thaumcraft;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.paulzzh.mygtnh.MyGTNH;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraft.entity.player.EntityPlayerMP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import thaumcraft.common.lib.WarpEvents;
import thaumcraft.common.lib.network.misc.PacketMiscEvent;

@Mixin(value = WarpEvents.class)
public class WarpEventsMixin {
    @ModifyExpressionValue(
        method = "checkWarpEvent",
        at = @At(
            value = "INVOKE",
            target = "Ljava/util/Random;nextInt(I)I"
        ),
        remap = false
    )
    private static int nextInt(int original) {
        MyGTNH.LOG.debug("checkWarpEvent player.worldObj.rand.nextInt({}) return 0", original);
        return 0;
    }

    @WrapWithCondition(
        method = "checkWarpEvent",
        at = @At(
            value = "INVOKE",
            target = "Lcpw/mods/fml/common/network/simpleimpl/SimpleNetworkWrapper;sendTo(Lcpw/mods/fml/common/network/simpleimpl/IMessage;Lnet/minecraft/entity/player/EntityPlayerMP;)V"
        ),
        remap = false
    )
    private static boolean sendTo(SimpleNetworkWrapper instance, IMessage iMessage, EntityPlayerMP entityPlayerMP) {
        if (iMessage instanceof PacketMiscEvent) {
            MyGTNH.LOG.info("block warpVignette for {}", entityPlayerMP.getDisplayName());
            return false;
        }
        return true;
    }
}
