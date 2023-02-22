package tech.paulzzh.gtnhfix.mixins.galaxyspace;

import galaxyspace.core.proxy.ClientProxy;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = ClientProxy.class, remap = false)
public class ClientProxyMixin {
    /**
     * @author Paulzzh
     * @reason remove capes
     */
    @Overwrite
    public static void updateCapeList() {
    }
}