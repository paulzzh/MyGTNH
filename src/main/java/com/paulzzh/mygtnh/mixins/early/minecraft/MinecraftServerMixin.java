package com.paulzzh.mygtnh.mixins.early.minecraft;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = MinecraftServer.class)
public class MinecraftServerMixin {
    /**
     * @author Paulzzh
     * @reason remove Snooper
     */
    @Overwrite
    public boolean isSnooperEnabled()
    {
        return false;
    }
}
