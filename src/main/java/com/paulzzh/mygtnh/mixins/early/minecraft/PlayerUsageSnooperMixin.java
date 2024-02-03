package com.paulzzh.mygtnh.mixins.early.minecraft;

import com.paulzzh.mygtnh.MyGTNH;
import net.minecraft.profiler.PlayerUsageSnooper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = PlayerUsageSnooper.class)
public class PlayerUsageSnooperMixin {
    /**
     * @author Paulzzh
     * @reason remove Snooper
     */
    @Overwrite
    public void startSnooper() {
        MyGTNH.LOG.info("block Minecraft Snooper web request");
    }
}
