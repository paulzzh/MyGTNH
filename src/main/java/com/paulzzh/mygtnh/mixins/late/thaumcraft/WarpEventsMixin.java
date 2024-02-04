package com.paulzzh.mygtnh.mixins.late.thaumcraft;

import com.paulzzh.mygtnh.MyGTNH;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import thaumcraft.common.lib.WarpEvents;

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
        MyGTNH.LOG.info("checkWarpEvent player.worldObj.rand.nextInt(" + bound + ") return 0");
        return 0;
    }
}
