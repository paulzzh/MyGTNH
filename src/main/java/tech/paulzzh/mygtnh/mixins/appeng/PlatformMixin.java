package tech.paulzzh.mygtnh.mixins.appeng;

import appeng.util.Platform;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = Platform.class, priority = 1, remap = false)
public class PlatformMixin {
    /**
     * @author Paulzzh
     * @reason fix mcmt side
     */
    @Overwrite
    public static boolean isClient() {
        return false;
    }
}