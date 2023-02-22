package tech.paulzzh.gtnhfix.mixins.GTPP;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(targets = "gtPlusPlus.xmod.gregtech.common.render.GTPP_CapeRenderer$CapeUtils", remap = false)
public class CapeUtilsMixin {
    /**
     * @author Paulzzh
     * @reason remove capes
     */
    @Overwrite
    public static final void downloadCapeList() {
    }
}