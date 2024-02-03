package com.paulzzh.mygtnh.mixins.late.journeymap;

import com.paulzzh.mygtnh.MyGTNH;
import modinfo.mp.v1.Client;
import modinfo.mp.v1.Message;
import modinfo.mp.v1.Payload;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.concurrent.Future;

@Mixin(value = Client.class, remap = false)
public class ClientMixin {
    /**
     * @author Paulzzh
     * @reason remove stat
     */
    @Overwrite
    public Future send(Payload payload, final Message.Callback callback) {
        MyGTNH.LOG.info("block Journeymap web request");
        return null;
    }
}
