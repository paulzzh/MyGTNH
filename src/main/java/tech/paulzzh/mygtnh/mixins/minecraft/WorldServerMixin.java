package tech.paulzzh.mygtnh.mixins.minecraft;

import net.minecraft.block.Block;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tech.paulzzh.mygtnh.mcmt.FJPool;

import java.util.List;

@Mixin(value = WorldServer.class, priority = 1)
public abstract class WorldServerMixin {
    @Inject(method = "scheduleBlockUpdateWithPriority", at = @At("HEAD"))
    public void head(int p_147454_1_, int p_147454_2_, int p_147454_3_, Block p_147454_4_, int p_147454_5_, int p_147454_6_, CallbackInfo ci) {
        FJPool.MC_WorldServer_pendingTickListEntriesTreeSet.lock();
    }

    @Inject(method = "scheduleBlockUpdateWithPriority", at = @At("RETURN"))
    public void tail(int p_147454_1_, int p_147454_2_, int p_147454_3_, Block p_147454_4_, int p_147454_5_, int p_147454_6_, CallbackInfo ci) {
        FJPool.MC_WorldServer_pendingTickListEntriesTreeSet.unlock();
    }

    @Inject(method = "func_147446_b", at = @At("HEAD"))
    public void head2(int p_147454_1_, int p_147454_2_, int p_147454_3_, Block p_147454_4_, int p_147454_5_, int p_147454_6_, CallbackInfo ci) {
        FJPool.MC_WorldServer_pendingTickListEntriesTreeSet.lock();
    }

    @Inject(method = "func_147446_b", at = @At("RETURN"))
    public void tail2(int p_147446_1_, int p_147446_2_, int p_147446_3_, Block p_147446_4_, int p_147446_5_, int p_147446_6_, CallbackInfo ci) {
        FJPool.MC_WorldServer_pendingTickListEntriesTreeSet.unlock();
    }

    @Inject(method = "tickUpdates", at = @At("HEAD"))
    public void head3(boolean p_72955_1_, CallbackInfoReturnable<Boolean> cir) {
        FJPool.MC_WorldServer_pendingTickListEntriesTreeSet.lock();
    }

    @Inject(method = "tickUpdates", at = @At("RETURN"))
    public void tail3(boolean p_72955_1_, CallbackInfoReturnable<Boolean> cir) {
        FJPool.MC_WorldServer_pendingTickListEntriesTreeSet.unlock();
    }

    @Inject(method = "getPendingBlockUpdates", at = @At("HEAD"))
    public void head4(Chunk p_72920_1_, boolean p_72920_2_, CallbackInfoReturnable<List> cir) {
        FJPool.MC_WorldServer_pendingTickListEntriesTreeSet.lock();
    }

    @Inject(method = "getPendingBlockUpdates", at = @At("RETURN"))
    public void tail4(Chunk p_72920_1_, boolean p_72920_2_, CallbackInfoReturnable<List> cir) {
        FJPool.MC_WorldServer_pendingTickListEntriesTreeSet.unlock();
    }

    /*
    @Inject(method = "scheduleBlockUpdateWithPriority", at = @At(value = "INVOKE",target="Ljava/util/TreeSet;add(Ljava/lang/Object;)Z"))
    public void head(CallbackInfo ci){
        FJPool.MC_WorldServer_pendingTickListEntriesTreeSet.lock();
        FJPool.MC_WorldServer_pendingTickListEntriesTreeSet.unlock();
    }*/
}