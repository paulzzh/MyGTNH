package tech.paulzzh.gtnhfix.mixins.galacticraft;

import micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = ClientProxyCore.class, remap = false)
public class ClientProxyCoreMixin {
    /**
     * @author Paulzzh
     * @reason remove GC armor render
     */
    @Overwrite
    public static void updateCapeList() {
    }
}
