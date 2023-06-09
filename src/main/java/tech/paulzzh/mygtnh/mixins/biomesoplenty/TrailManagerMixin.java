package tech.paulzzh.mygtnh.mixins.biomesoplenty;

import biomesoplenty.common.utils.remote.TrailManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = TrailManager.class, remap = false)
public class TrailManagerMixin {
    /**
     * @author Paulzzh
     * @reason remove trails
     */
    @Overwrite
    public static void retrieveTrails() {
    }
}
