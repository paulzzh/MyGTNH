package com.paulzzh.mygtnh.mixins.late.gregtech;

import com.paulzzh.mygtnh.Utils;
import gregtech.api.items.ItemCoolantCellIC;
import ic2.api.reactor.IReactor;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.paulzzh.mygtnh.MyGTNH.freeze;

@Mixin(value = ItemCoolantCellIC.class)
public abstract class ItemCoolantCellICMixin {

    @Inject(
        method = "alterHeat",
        at = @At(value = "INVOKE", target = "Lic2/api/reactor/IReactor;setItemAt(IILnet/minecraft/item/ItemStack;)V"),
        remap = false
    )
    private void injected(IReactor aReactor, ItemStack aStack, int x, int y, int aHeat, CallbackInfoReturnable<Integer> cir) {
        ChunkCoordinates c = aReactor.getPosition();
        String dim = Utils.getDimName(aReactor.getWorld().provider.dimensionId);
        String info = String.format("%s@%d,%d,%d,%s 过热融毁! 冻结游戏!", aStack.getDisplayName(), c.posX, c.posY, c.posZ, dim);
        MinecraftServer.getServer().getConfigurationManager().sendChatMsg(new ChatComponentText(info));
        freeze = true;
    }
}
