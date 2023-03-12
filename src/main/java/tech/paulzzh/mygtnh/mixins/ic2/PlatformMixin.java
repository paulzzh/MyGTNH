package tech.paulzzh.mygtnh.mixins.ic2;

import ic2.core.Platform;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = Platform.class, priority = 1, remap = false)
public class PlatformMixin {
    /**
     * @author Paulzzh
     * @reason fix mcmt side
     */
    @Overwrite
    public boolean isSimulating() {
        return true;
    }
}