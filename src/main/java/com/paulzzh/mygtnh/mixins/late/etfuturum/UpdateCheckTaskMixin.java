package com.paulzzh.mygtnh.mixins.late.etfuturum;

import com.paulzzh.mygtnh.MyGTNH;
import cpw.mods.fml.common.versioning.ComparableVersion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(targets = "makamys.mclib.updatecheck.UpdateCheckTask")
public class UpdateCheckTaskMixin {
    /**
     * @author Paulzzh
     * @reason remove update
     */
    @Overwrite(remap = false)
    private ComparableVersion solveVersion() {
        MyGTNH.LOG.info("block mclib web request");
        return null;
    }
}
