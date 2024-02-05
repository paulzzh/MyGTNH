package com.paulzzh.mygtnh.mixins.late.nutrition;

import ca.wescook.nutrition.network.PacketNormalizeServerNutrients.Handler;
import ca.wescook.nutrition.network.PacketNormalizeServerNutrients.Message;
import com.paulzzh.mygtnh.MyGTNH;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.player.EntityPlayerMP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = Handler.class)
public class NormalizeMixin {
    /**
     * @author Paulzzh
     * @reason block Normalize
     */
    @Overwrite(remap = false)
    public IMessage onMessage(final Message message, final MessageContext context) {
        EntityPlayerMP player = context.getServerHandler().playerEntity;
        MyGTNH.LOG.debug("block nutrient normalize for " + player.getDisplayName());
        return null;
    }
}
