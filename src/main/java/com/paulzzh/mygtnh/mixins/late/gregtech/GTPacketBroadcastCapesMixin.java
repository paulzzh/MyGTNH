package com.paulzzh.mygtnh.mixins.late.gregtech;

import gregtech.api.net.cape.GTPacketBroadcastCapes;
import net.minecraft.world.IBlockAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = GTPacketBroadcastCapes.class)
public class GTPacketBroadcastCapesMixin {
    /**
     * @author Paulzzh
     * @reason remove color
     */
    @Overwrite(remap = false)
    public void process(IBlockAccess world) {
    }
}
