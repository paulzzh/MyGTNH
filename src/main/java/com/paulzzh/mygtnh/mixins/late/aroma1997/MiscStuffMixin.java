package com.paulzzh.mygtnh.mixins.late.aroma1997;

import aroma1997.core.client.MiscStuff;
import com.paulzzh.mygtnh.MyGTNH;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = MiscStuff.class)
public class MiscStuffMixin {
    /**
     * @author Paulzzh
     * @reason remove capes
     */
    @Overwrite(remap = false)
    private static void load() {
        MyGTNH.LOG.info("block Aroma1997Core web request");
    }
}
