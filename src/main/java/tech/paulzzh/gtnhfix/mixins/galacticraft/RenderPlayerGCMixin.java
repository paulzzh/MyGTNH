package tech.paulzzh.gtnhfix.mixins.galacticraft;

import micdoodle8.mods.galacticraft.core.client.render.entities.RenderPlayerGC;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RenderPlayerGC.class, remap = false)
public class RenderPlayerGCMixin {
    @Inject(method = "renderModelS", at = @At("HEAD"), cancellable = true)
    private static void renderModelS(RendererLivingEntity inst, EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4, float par5, float par6, float par7, CallbackInfo ci) {
        ci.cancel();
    }
}
