package com.paulzzh.mygtnh.mixins.late.serverutilities;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import serverutils.client.gui.ThreadReloadChunkSelector;

@Mixin(value = ThreadReloadChunkSelector.class)
public class ThreadReloadChunkSelectorMixin {
    @Redirect(
        method = "run",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;isAir(Lnet/minecraft/world/IBlockAccess;III)Z"),
        remap = false)
    public boolean isAir(Block block, IBlockAccess world, int x, int y, int z) {
        return block.isAir(world, x, y, z) || block.getMaterial() == Material.glass;
    }
}
