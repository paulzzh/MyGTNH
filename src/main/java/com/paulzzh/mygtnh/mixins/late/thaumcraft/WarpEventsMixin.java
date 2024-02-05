package com.paulzzh.mygtnh.mixins.late.thaumcraft;

import com.paulzzh.mygtnh.MyGTNH;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraft.entity.player.EntityPlayerMP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import thaumcraft.common.lib.WarpEvents;
import thaumcraft.common.lib.network.misc.PacketMiscEvent;

import java.util.Random;

@Mixin(value = WarpEvents.class, remap = false)
public class WarpEventsMixin {
    @Redirect(
        method = "checkWarpEvent",
        at = @At(
            value = "INVOKE",
            target = "Ljava/util/Random;nextInt(I)I"
        )
    )
    private static int nextInt(Random random, int bound) {
        MyGTNH.LOG.debug("checkWarpEvent player.worldObj.rand.nextInt(" + bound + ") return 0");
        return 0;
    }

    @Redirect(
        method = "checkWarpEvent",
        at = @At(
            value = "INVOKE",
            target = "Lcpw/mods/fml/common/network/simpleimpl/SimpleNetworkWrapper;sendTo(Lcpw/mods/fml/common/network/simpleimpl/IMessage;Lnet/minecraft/entity/player/EntityPlayerMP;)V"
        )
    )
    private static void sendTo(SimpleNetworkWrapper instance, IMessage iMessage, EntityPlayerMP entityPlayerMP) {
        if (iMessage instanceof PacketMiscEvent) {
            MyGTNH.LOG.debug("block warpVignette for " + entityPlayerMP.getDisplayName());
        } else {
            instance.sendTo(iMessage, entityPlayerMP);
        }
    }
}
