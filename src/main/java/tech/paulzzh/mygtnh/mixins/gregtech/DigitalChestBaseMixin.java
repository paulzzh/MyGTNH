package tech.paulzzh.mygtnh.mixins.gregtech;

import appeng.api.config.AccessRestriction;
import appeng.api.config.Actionable;
import appeng.api.networking.security.BaseActionSource;
import appeng.api.storage.StorageChannel;
import appeng.api.storage.data.IAEItemStack;
import appeng.api.storage.data.IItemList;
import gregtech.common.tileentities.storage.GT_MetaTileEntity_DigitalChestBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tech.paulzzh.mygtnh.mixinhelpers.gregtech.IMetaTileEntityMixin;

@Mixin(value = GT_MetaTileEntity_DigitalChestBase.class, remap = false, priority = 1)
public abstract class DigitalChestBaseMixin implements IMetaTileEntityMixin {
    //IMEMonitor
    @Inject(method = "getAvailableItems", at = @At("HEAD"))
    private void getAvailableItems(IItemList out, CallbackInfoReturnable<IItemList<IAEItemStack>> cir) {
        getInvLock().trylock();
    }

    @Inject(method = "getStorageList", at = @At("HEAD"))
    private void getStorageList(CallbackInfoReturnable<IItemList<IAEItemStack>> cir) {
        getInvLock().trylock();
    }


    //IMEInventoryHandler
    @Inject(method = "getAccess", at = @At("HEAD"))
    private void getAccess(CallbackInfoReturnable<AccessRestriction> cir) {
        getInvLock().trylock();
    }

    @Inject(method = "isPrioritized(Lappeng/api/storage/data/IAEItemStack;)Z", at = @At("HEAD"))
    private void isPrioritized(IAEItemStack iaeItemStack, CallbackInfoReturnable<Boolean> cir) {
        getInvLock().trylock();
    }

    @Inject(method = "canAccept(Lappeng/api/storage/data/IAEItemStack;)Z", at = @At("HEAD"))
    private void canAccept(IAEItemStack iaeItemStack, CallbackInfoReturnable<Boolean> cir) {
        getInvLock().trylock();
    }

    @Inject(method = "getPriority", at = @At("HEAD"))
    private void getPriority(CallbackInfoReturnable<Integer> cir) {
        getInvLock().trylock();
    }

    @Inject(method = "getSlot", at = @At("HEAD"))
    private void getSlot(CallbackInfoReturnable<Integer> cir) {
        getInvLock().trylock();
    }

    @Inject(method = "validForPass", at = @At("HEAD"))
    private void validForPass(int i, CallbackInfoReturnable<Boolean> cir) {
        getInvLock().trylock();
    }


    //IMEInventory
    @Inject(method = "injectItems(Lappeng/api/storage/data/IAEItemStack;Lappeng/api/config/Actionable;Lappeng/api/networking/security/BaseActionSource;)Lappeng/api/storage/data/IAEItemStack;", at = @At("HEAD"))
    private void injectItems(IAEItemStack input, Actionable mode, BaseActionSource src, CallbackInfoReturnable<IAEItemStack> cir) {
        getInvLock().trylock();
    }

    @Inject(method = "extractItems(Lappeng/api/storage/data/IAEItemStack;Lappeng/api/config/Actionable;Lappeng/api/networking/security/BaseActionSource;)Lappeng/api/storage/data/IAEItemStack;", at = @At("HEAD"))
    private void extractItems(IAEItemStack request, Actionable mode, BaseActionSource src, CallbackInfoReturnable<IAEItemStack> cir) {
        getInvLock().trylock();
    }

    @Inject(method = "getChannel", at = @At("HEAD"))
    private void getChannel(CallbackInfoReturnable<StorageChannel> cir) {
        getInvLock().trylock();
    }
}
