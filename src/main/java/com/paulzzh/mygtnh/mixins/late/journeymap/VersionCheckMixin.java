package com.paulzzh.mygtnh.mixins.late.journeymap;

import com.paulzzh.mygtnh.MyGTNH;
import journeymap.common.version.VersionCheck;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = VersionCheck.class)
public class VersionCheckMixin {
    @Shadow(remap = false)
    private static volatile Boolean updateCheckEnabled;

    @Shadow(remap = false)
    private static volatile Boolean versionIsCurrent;

    @Shadow(remap = false)
    private static volatile Boolean versionIsChecked;

    /**
     * @author Paulzzh
     * @reason remove update
     */
    @Overwrite(remap = false)
    private static synchronized void checkVersion() {
        updateCheckEnabled = false;
        versionIsCurrent = true;
        versionIsChecked = true;
        MyGTNH.LOG.info("block Journeymap web request");
    }
}
