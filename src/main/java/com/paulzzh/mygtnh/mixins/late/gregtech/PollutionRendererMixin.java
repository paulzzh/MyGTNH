package com.paulzzh.mygtnh.mixins.late.gregtech;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import gregtech.common.pollution.PollutionRenderer;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = PollutionRenderer.class)
public class PollutionRendererMixin {
    /**
     * @author Paulzzh
     * @reason remove color
     */
    @Overwrite(remap = false)
    private static int color(int color, int pollution, int low, float high, short[] colors) {
        return color;
    }

    /**
     * @author Paulzzh
     * @reason remove fog
     */
    @SubscribeEvent(priority = EventPriority.LOWEST)
    @Overwrite(remap = false)
    public void renderGTPollutionFog(EntityViewRenderEvent.RenderFogEvent event) {

    }

    /**
     * @author Paulzzh
     * @reason remove fog
     */
    @SubscribeEvent(priority = EventPriority.LOWEST)
    @Overwrite(remap = false)
    public void renderGTPollutionFog(EntityViewRenderEvent.FogDensity event) {

    }

    /**
     * @author Paulzzh
     * @reason remove particles
     */
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    @Overwrite(remap = false)
    public void onClientTick(TickEvent.ClientTickEvent event) {

    }
}
