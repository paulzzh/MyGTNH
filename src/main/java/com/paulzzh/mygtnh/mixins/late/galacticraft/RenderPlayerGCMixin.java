package com.paulzzh.mygtnh.mixins.late.galacticraft;

import micdoodle8.mods.galacticraft.core.client.render.entities.RenderPlayerGC;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = RenderPlayerGC.class)
public class RenderPlayerGCMixin {
    /**
     * @author Paulzzh
     * @reason remove GC armor render
     */
    @Overwrite(remap = false)
    public static void renderModelS(RendererLivingEntity inst, EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4, float par5, float par6, float par7) {
    }
}
