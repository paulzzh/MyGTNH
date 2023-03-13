package tech.paulzzh.mygtnh.mixins.minecraft;

import net.minecraft.world.WorldServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tech.paulzzh.mygtnh.mcmt.FJPool;

@Mixin(value = WorldServer.class, priority = 1)
public class WorldServerMixin {
    @Inject(method = "tickUpdates", at = @At("HEAD"))
    public void head(boolean p_72955_1_, CallbackInfoReturnable<Boolean> cir) {
        FJPool.MC_WorldServer_tickUpdates.lock();
    }

    @Inject(method = "tickUpdates", at = @At("RETURN"))
    public void tail(boolean p_72955_1_, CallbackInfoReturnable<Boolean> cir) {
        FJPool.MC_WorldServer_tickUpdates.unlock();
    }
}