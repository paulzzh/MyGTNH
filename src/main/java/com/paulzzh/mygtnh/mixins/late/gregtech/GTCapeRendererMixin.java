package com.paulzzh.mygtnh.mixins.late.gregtech;

import com.paulzzh.mygtnh.ClientUtils;
import gregtech.common.render.GTCapeRenderer;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static com.paulzzh.mygtnh.MyGTNH.CAPE_CACHE;
import static com.paulzzh.mygtnh.MyGTNH.LOG;
import static com.paulzzh.mygtnh.config.MyGTNHConfig.gt_cape_url;

@Mixin(value = GTCapeRenderer.class)
public class GTCapeRendererMixin {
    @Redirect(method = "receiveRenderSpecialsEvent", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/AbstractClientPlayer;getLocationCape()Lnet/minecraft/util/ResourceLocation;"))
    public ResourceLocation getLocationCape(AbstractClientPlayer instance) {
        String name = instance.getDisplayName();
        if (CAPE_CACHE.get(name) == null) {
            CAPE_CACHE.put(name, instance.getLocationCape());
            if (gt_cape_url != null && !gt_cape_url.isEmpty()) {
                LOG.info("new CapeFetcher " + name);
                new ClientUtils.CapeFetcher(name);
            }
        }
        return CAPE_CACHE.get(name);
    }
}
