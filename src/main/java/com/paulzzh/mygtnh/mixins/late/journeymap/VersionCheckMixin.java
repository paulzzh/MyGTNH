package com.paulzzh.mygtnh.mixins.late.journeymap;

import com.paulzzh.mygtnh.MyGTNH;
import journeymap.common.version.VersionCheck;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = VersionCheck.class)
public class VersionCheckMixin {
    /**
     * @author Paulzzh
     * @reason remove update
     */
    @Overwrite(remap = false)
    private static synchronized void checkVersion() {
        MyGTNH.LOG.info("block Journeymap web request");
    }
}
