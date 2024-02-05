package com.paulzzh.mygtnh.mixins.late.GTPP;

import com.paulzzh.mygtnh.MyGTNH;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(targets = "gtPlusPlus.xmod.gregtech.common.render.GTPP_CapeRenderer$CapeUtils")
public class CapeUtilsMixin {
    /**
     * @author Paulzzh
     * @reason remove capes
     */
    @Overwrite(remap = false)
    private static void downloadCapeList() {
        MyGTNH.LOG.info("block gtPlusPlus web request");
    }
}
