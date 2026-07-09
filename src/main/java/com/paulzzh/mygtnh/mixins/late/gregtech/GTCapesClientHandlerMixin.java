package com.paulzzh.mygtnh.mixins.late.gregtech;

import com.paulzzh.mygtnh.ClientUtils;
import com.paulzzh.mygtnh.config.MyGTNHConfig;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import gregtech.client.GTCapesClientHandler;
import gregtech.mixin.interfaces.accessors.AbstractClientPlayerAccessor;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static com.paulzzh.mygtnh.MyGTNH.*;
import static com.paulzzh.mygtnh.config.MyGTNHConfig.gt_cape_url;

@Mixin(value = GTCapesClientHandler.class)
public class GTCapesClientHandlerMixin {
    /**
     * @author Paulzzh
     * @reason modify cape
     */
    @SubscribeEvent
    @Overwrite(remap = false)
    public static void onEntityJoinWorld(EntityJoinWorldEvent event) {
        if (event.entity instanceof AbstractClientPlayerAccessor accessor) {
            if (MyGTNHConfig.gt_cape_url != null && !gt_cape_url.isEmpty()) {
                String name = event.entity.getCommandSenderName();
                if (CAPE_CACHE.containsKey(name)) {
                    accessor.gt5u$setCape(CAPE_CACHE.get(name));
                    return;
                }
                if (!CAPE_PLAYER_CACHE.contains(name)) {
                    CAPE_PLAYER_CACHE.add(name);
                    LOG.info("new CapeFetcher " + name);
                    new ClientUtils.CapeFetcher(name, accessor::gt5u$setCape);
                }
            }
        }
    }
}
