package com.paulzzh.mygtnh.mixins.late.galaxyspace;

import com.paulzzh.mygtnh.MyGTNH;
import galaxyspace.core.capes.GSCapeLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = GSCapeLoader.class)
public class GSCapeLoaderMixin {
    /**
     * @author Paulzzh
     * @reason remove capes
     */
    @Overwrite(remap = false)
    public void run() {
        MyGTNH.LOG.info("block Galaxyspace web request");
    }
}
