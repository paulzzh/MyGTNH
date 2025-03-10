package com.paulzzh.mygtnh.mixins.late.serverutilities;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import serverutils.client.gui.ThreadReloadChunkSelector;

@Mixin(value = ThreadReloadChunkSelector.class)
public class ThreadReloadChunkSelectorMixin {
    @WrapOperation(
        method = "run",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;isAir(Lnet/minecraft/world/IBlockAccess;III)Z"),
        remap = false)
    private boolean isAir(Block block, IBlockAccess world, int x, int y, int z, Operation<Boolean> original) {
        return original.call(block, world, x, y, z) || block.getMaterial() == Material.glass;
    }
}
