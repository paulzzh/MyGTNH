package tech.paulzzh.mygtnh.mixins.gregtech;

import gregtech.common.tileentities.storage.GT_MetaTileEntity_QuantumChest;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tech.paulzzh.mygtnh.mixinhelpers.gregtech.IMetaTileEntityMixin;


@Mixin(value = GT_MetaTileEntity_QuantumChest.class, remap = false, priority = 1)
public abstract class QuantumChestMixin implements IMetaTileEntityMixin {
    @Inject(method = "getItemCount", at = @At("HEAD"))
    private void getItemCount(CallbackInfoReturnable<Integer> cir) {
        getInvLock().trylock();
    }

    @Inject(method = "setItemCount", at = @At("HEAD"))
    private void setItemCount(int aCount, CallbackInfo ci) {
        getInvLock().trylock();
    }

    @Inject(method = "getItemStack", at = @At("HEAD"))
    private void getItemStack(CallbackInfoReturnable<ItemStack> cir) {
        getInvLock().trylock();
    }

    @Inject(method = "setItemStack", at = @At("HEAD"))
    private void setItemStack(ItemStack s, CallbackInfo ci) {
        getInvLock().trylock();
    }
}