package com.paulzzh.mygtnh.mixins.late.draconicevolution;

import com.brandon3055.draconicevolution.common.handler.ContributorHandler;
import com.paulzzh.mygtnh.MyGTNH;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = ContributorHandler.class, remap = false)
public class ContributorHandlerMixin {
    /**
     * @author Paulzzh
     * @reason remove Contributor
     */
    @Overwrite
    public static void init() {
        MyGTNH.LOG.info("block DraconiceVolution web request");
    }
}
