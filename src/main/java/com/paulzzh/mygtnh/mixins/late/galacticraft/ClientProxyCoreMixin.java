package com.paulzzh.mygtnh.mixins.late.galacticraft;

import com.paulzzh.mygtnh.MyGTNH;
import micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = ClientProxyCore.class)
public class ClientProxyCoreMixin {
    /**
     * @author Paulzzh
     * @reason remove GC armor render
     */
    @Overwrite(remap = false)
    private static void updateCapeList() {
        MyGTNH.LOG.info("block Galacticraft web request");
    }
}
