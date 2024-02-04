package com.paulzzh.mygtnh.mixins.early.minecraft;

import net.minecraft.server.integrated.IntegratedServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = IntegratedServer.class)
public class IntegratedServerMixin {
    /**
     * @author Paulzzh
     * @reason remove Snooper
     */
    @Overwrite
    public boolean isSnooperEnabled() {
        return false;
    }
}
