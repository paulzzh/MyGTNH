package com.paulzzh.mygtnh.mixins.late.adventurebackpack;

import com.darkona.adventurebackpack.handlers.RenderHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.event.RenderPlayerEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = RenderHandler.class)
public class RenderHandlerMixin {
    /**
     * @author Paulzzh
     * @reason remove backpack render
     */
    @Overwrite(remap = false)
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void playerSpecialsRendering(RenderPlayerEvent.Specials.Pre event) {
    }
}
