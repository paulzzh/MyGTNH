package com.paulzzh.mygtnh.mixins.late.warptheory;

import com.paulzzh.mygtnh.MyGTNH;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import shukaro.warptheory.handlers.IWarpEvent;
import shukaro.warptheory.handlers.WarpHandler;

@Mixin(value = WarpHandler.class)
public class WarpHandlerMixin {
    /**
     * @author Paulzzh
     * @reason remove warps
     */
    @Overwrite(remap = false)
    public static void queueEvent(EntityPlayer player, IWarpEvent event) {
        MyGTNH.LOG.info("block warp event for {} {}", player.getDisplayName(), event.getName());
    }
}
