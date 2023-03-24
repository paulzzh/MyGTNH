package tech.paulzzh.mygtnh.mixins.appeng;

import appeng.tile.inventory.AppEngInternalInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tech.paulzzh.mygtnh.mcmt.MyLock;


@Mixin(value = AppEngInternalInventory.class, remap = false, priority = 1)
public abstract class AppEngInternalInventoryMixin {
    private MyLock invlock = new MyLock();

    //IInventory
    @Inject(method = "getSizeInventory", at = @At("HEAD"))
    private void getSizeInventory(CallbackInfoReturnable<Integer> cir) {
        invlock.trylock();
    }

    @Inject(method = "getStackInSlot", at = @At("HEAD"))
    private void getStackInSlot(int aIndex, CallbackInfoReturnable<ItemStack> cir) {
        invlock.trylock();
    }

    @Inject(method = "decrStackSize", at = @At("HEAD"))
    private void decrStackSize(int aIndex, int aAmount, CallbackInfoReturnable<ItemStack> cir) {
        invlock.trylock();
    }

    @Inject(method = "getStackInSlotOnClosing", at = @At("HEAD"))
    private void getStackInSlotOnClosing(int i, CallbackInfoReturnable<ItemStack> cir) {
        invlock.trylock();
    }

    @Inject(method = "setInventorySlotContents", at = @At("HEAD"))
    private void setInventorySlotContents(int aIndex, ItemStack aStack, CallbackInfo ci) {
        invlock.trylock();
    }

    @Inject(method = "getInventoryName", at = @At("HEAD"))
    private void getInventoryName(CallbackInfoReturnable<String> cir) {
        invlock.trylock();
    }

    @Inject(method = "hasCustomInventoryName", at = @At("HEAD"))
    private void hasCustomInventoryName(CallbackInfoReturnable<Boolean> cir) {
        invlock.trylock();
    }

    @Inject(method = "getInventoryStackLimit", at = @At("HEAD"))
    private void getInventoryStackLimit(CallbackInfoReturnable<Integer> cir) {
        invlock.trylock();
    }

    @Inject(method = "markDirty()V", at = @At("HEAD"))
    private void markDirty(CallbackInfo ci) {
        invlock.trylock();
    }

    @Inject(method = "isUseableByPlayer", at = @At("HEAD"))
    private void isUseableByPlayer(EntityPlayer entityplayer, CallbackInfoReturnable<Boolean> cir) {
        invlock.trylock();
    }

    @Inject(method = "openInventory", at = @At("HEAD"))
    private void openInventory(CallbackInfo ci) {
        invlock.trylock();
    }

    @Inject(method = "closeInventory", at = @At("HEAD"))
    private void closeInventory(CallbackInfo ci) {
        invlock.trylock();
    }

    @Inject(method = "isItemValidForSlot", at = @At("HEAD"))
    private void isItemValidForSlot(int aIndex, ItemStack aStack, CallbackInfoReturnable<Boolean> cir) {
        invlock.trylock();
    }
}