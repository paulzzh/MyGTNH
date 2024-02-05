package com.paulzzh.mygtnh.mixins.late.biomesoplenty;

import biomesoplenty.common.utils.remote.TrailManager;
import com.paulzzh.mygtnh.MyGTNH;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = TrailManager.class)
public class TrailManagerMixin {
    /**
     * @author Paulzzh
     * @reason remove trails
     */
    @Overwrite(remap = false)
    public static void retrieveTrails() {
        MyGTNH.LOG.info("block Biomes O' Plenty web request");
    }
}
