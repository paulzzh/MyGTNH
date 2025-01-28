package com.paulzzh.mygtnh.mixins.late.journeymap;

import journeymap.client.model.BlockMD;
import journeymap.client.model.mod.vanilla.VanillaBlockHandler;
import net.minecraft.block.material.Material;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value = VanillaBlockHandler.class)
public class VanillaBlockHandlerMixin {
    @ModifyArg(
        method = "preInitialize",
        at = @At(
            value = "INVOKE",
            target = "Ljourneymap/client/model/mod/vanilla/VanillaBlockHandler;setFlags(Lnet/minecraft/block/material/Material;Ljava/lang/Float;[Ljourneymap/client/model/BlockMD$Flag;)V",
            remap = false),
        index = 1,
        remap = false)
    private Float injected(Material material, Float alpha, BlockMD.Flag... flags) {
        return material == Material.glass ? 0.0F : alpha;
    }
}
