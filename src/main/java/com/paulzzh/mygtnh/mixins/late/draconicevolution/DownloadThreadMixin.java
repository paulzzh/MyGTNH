package com.paulzzh.mygtnh.mixins.late.draconicevolution;

import com.paulzzh.mygtnh.MyGTNH;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = com.brandon3055.draconicevolution.client.handler.ResourceHandler.DownloadThread.class)
public class DownloadThreadMixin {
    /**
     * @author Paulzzh
     * @reason remove Images
     */
    @Overwrite(remap = false)
    private static boolean downloadImage(String urlString) {
        MyGTNH.LOG.info("block DraconiceVolution web request");
        return false;
    }
}
