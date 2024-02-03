package com.paulzzh.mygtnh.mixins.late.GTPP;

import com.paulzzh.mygtnh.MyGTNH;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(targets = "gtPlusPlus.xmod.gregtech.common.render.GTPP_CapeRenderer$CapeUtils", remap = false)
public class CapeUtilsMixin {
    /**
     * @author Paulzzh
     * @reason remove capes
     */
    @Overwrite
    private static void downloadCapeList() {
        MyGTNH.LOG.info("block gtPlusPlus web request");
    }
}
