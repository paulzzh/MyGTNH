package com.paulzzh.mygtnh.mixins.late.ic2;

import com.paulzzh.mygtnh.Utils;
import ic2.core.block.reactor.tileentity.TileEntityNuclearReactorElectric;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static com.paulzzh.mygtnh.MyGTNH.freeze;

@Mixin(value = TileEntityNuclearReactorElectric.class)
public abstract class TileEntityNuclearReactorElectricMixin {

    @Redirect(
        method = "calculateHeatEffects",
        at = @At(value = "INVOKE", target = "Lic2/core/block/reactor/tileentity/TileEntityNuclearReactorElectric;explode()V"),
        remap = false
    )
    private void injected(TileEntityNuclearReactorElectric instance) {
        ChunkCoordinates c = instance.getPosition();
        String dim = Utils.getDimName(instance.getWorld().provider.dimensionId);
        String info = String.format("核反应堆@%d,%d,%d,%s 发生爆炸! 冻结游戏!", c.posX, c.posY, c.posZ, dim);
        MinecraftServer.getServer().getConfigurationManager().sendChatMsg(new ChatComponentText(info));
        freeze = true;
    }
}
