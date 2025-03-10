package com.paulzzh.mygtnh.mixins.late.gregtech;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.paulzzh.mygtnh.ClientUtils;
import gregtech.common.render.GTCapeRenderer;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.paulzzh.mygtnh.MyGTNH.*;
import static com.paulzzh.mygtnh.config.MyGTNHConfig.gt_cape_url;

@Mixin(value = GTCapeRenderer.class)
public class GTCapeRendererMixin {
    @Inject(method = "receiveRenderSpecialsEvent", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glPopMatrix()V", shift = At.Shift.AFTER, remap = false), remap = false)
    private void inject(RenderPlayerEvent.Specials.Pre aEvent, CallbackInfo ci) {
        aEvent.renderCape = false;
    }

    @WrapOperation(method = "receiveRenderSpecialsEvent", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/AbstractClientPlayer;getLocationCape()Lnet/minecraft/util/ResourceLocation;"))
    private ResourceLocation getLocationCape(AbstractClientPlayer instance, Operation<ResourceLocation> original) {
        if (gt_cape_url != null && !gt_cape_url.isEmpty()) {
            String name = instance.getDisplayName();
            if (CAPE_CACHE.containsKey(name)) {
                return CAPE_CACHE.get(name);
            }
            if (!CAPE_PLAYER_CACHE.contains(name)) {
                CAPE_PLAYER_CACHE.add(name);
                LOG.info("new CapeFetcher " + name);
                new ClientUtils.CapeFetcher(name);
            }
        }
        return original.call(instance);
    }
}
