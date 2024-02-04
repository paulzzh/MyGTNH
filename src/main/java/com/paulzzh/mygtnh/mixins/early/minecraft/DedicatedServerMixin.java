package com.paulzzh.mygtnh.mixins.early.minecraft;

import net.minecraft.server.dedicated.DedicatedServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = DedicatedServer.class)
public class DedicatedServerMixin {
    /**
     * @author Paulzzh
     * @reason remove Snooper
     */
    @Overwrite
    public boolean isSnooperEnabled() {
        return false;
    }
}
