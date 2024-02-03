package com.paulzzh.mygtnh.mixins.early.minecraft;

import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = Minecraft.class)
public class MinecraftMixin {
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
