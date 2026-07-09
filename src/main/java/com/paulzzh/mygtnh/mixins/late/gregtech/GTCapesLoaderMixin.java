package com.paulzzh.mygtnh.mixins.late.gregtech;

import com.paulzzh.mygtnh.MyGTNH;
import gregtech.common.GTCapesLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = GTCapesLoader.class)
public class GTCapesLoaderMixin {
    /**
     * @author Paulzzh
     * @reason remove capes
     */
    @Overwrite(remap = false)
    public void run() {
        MyGTNH.LOG.info("block Gregtech web request");
    }
}
