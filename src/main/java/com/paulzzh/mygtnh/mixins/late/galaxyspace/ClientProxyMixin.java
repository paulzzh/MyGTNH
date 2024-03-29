package com.paulzzh.mygtnh.mixins.late.galaxyspace;

import com.paulzzh.mygtnh.MyGTNH;
import galaxyspace.core.proxy.ClientProxy;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = ClientProxy.class)
public class ClientProxyMixin {
    /**
     * @author Paulzzh
     * @reason remove capes
     */
    @Overwrite(remap = false)
    private static void updateCapeList() {
        MyGTNH.LOG.info("block Galaxyspace web request");
    }
}
