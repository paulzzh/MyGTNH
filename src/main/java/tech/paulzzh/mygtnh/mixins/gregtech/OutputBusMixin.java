package tech.paulzzh.mygtnh.mixins.gregtech;

import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_OutputBus;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tech.paulzzh.mygtnh.mixinhelpers.gregtech.IMetaTileEntityMixin;


@Mixin(value = GT_MetaTileEntity_Hatch_OutputBus.class, remap = false, priority = 1)
public abstract class OutputBusMixin implements IMetaTileEntityMixin {
    @Inject(method = "onPostTick", at = @At("HEAD"))
    private void onPostTick(IGregTechTileEntity aBaseMetaTileEntity, long aTick, CallbackInfo ci) {
        getInvLock().trylock();
    }

    @Inject(method = "storeAll", at = @At("HEAD"))
    private void storeAll(ItemStack aStack, CallbackInfoReturnable<Boolean> cir) {
        getInvLock().trylock();
    }

}