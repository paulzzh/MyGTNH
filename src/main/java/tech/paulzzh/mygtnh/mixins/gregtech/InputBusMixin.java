package tech.paulzzh.mygtnh.mixins.gregtech;

import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_InputBus;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tech.paulzzh.mygtnh.mixinhelpers.gregtech.IMetaTileEntityMixin;


@Mixin(value = GT_MetaTileEntity_Hatch_InputBus.class, remap = false, priority = 1)
public abstract class InputBusMixin implements IMetaTileEntityMixin {
    @Inject(method = "updateSlots", at = @At("HEAD"))
    private void updateSlots(CallbackInfo ci) {
        getInvLock().trylock();
    }

    @Inject(method = "fillStacksIntoFirstSlots", at = @At("HEAD"))
    private void fillStacksIntoFirstSlots(CallbackInfo ci) {
        getInvLock().trylock();
    }

    @Inject(method = "limitedAllowPutStack", at = @At("HEAD"))
    private void limitedAllowPutStack(int aIndex, ItemStack aStack, CallbackInfoReturnable<Boolean> cir) {
        getInvLock().trylock();
    }
}