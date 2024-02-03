package com.paulzzh.mygtnh.mixins.late.aroma1997;

import aroma1997.core.client.MiscStuff;
import com.paulzzh.mygtnh.MyGTNH;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.io.InputStream;
import java.net.URL;

@Mixin(value = MiscStuff.class, remap = false)
public class MiscStuffMixin {
    /**
     * @author Paulzzh
     * @reason remove capes
     */
    @Redirect(
        method = "load",
        at = @At(
            value = "INVOKE",
            target = "Ljava/net/URL;openStream()Ljava/io/InputStream;"
        )
    )
    private static InputStream openStream(URL instance) {
        MyGTNH.LOG.info("block Aroma1997Core web request");
        return new InputStream() {
            @Override
            public int read() {
                return -1;  // end of stream
            }
        };
    }
}
