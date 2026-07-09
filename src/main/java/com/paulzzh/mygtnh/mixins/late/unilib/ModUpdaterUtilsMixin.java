package com.paulzzh.mygtnh.mixins.late.unilib;

import com.gitlab.cdagaming.unilib.core.utils.ModUpdaterUtils;
import com.paulzzh.mygtnh.MyGTNH;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = ModUpdaterUtils.class)
public class ModUpdaterUtilsMixin {
    /**
     * @author Paulzzh
     * @reason remove update
     */
    @Overwrite(remap = false)
    private void process(Runnable callback) {
        MyGTNH.LOG.info("block UniLib web request");
    }
}
