package tech.paulzzh.mygtnh.mixins.gregtech;

import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.MetaTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tech.paulzzh.mygtnh.mcmt.FJPool;
import tech.paulzzh.mygtnh.mcmt.MyLock;
import tech.paulzzh.mygtnh.mixinhelpers.gregtech.IMetaTileEntityMixin;

@Mixin(value = MetaTileEntity.class, remap = false, priority = 1)
public abstract class MetaTileEntityMixin implements IMetaTileEntityMixin {
    private MyLock invlock = new MyLock();
    private MyLock tanklock = new MyLock();

    @Override
    public MyLock getTankLock() {
        return tanklock;
    }

    @Override
    public MyLock getInvLock() {
        return invlock;
    }

    @Inject(method = "onPostTick", at = @At("HEAD"))
    private void onPostTick(IGregTechTileEntity aBaseMetaTileEntity, long aTick, CallbackInfo ci) {
        FJPool.unlocks();
    }


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

    @Inject(method = "markDirty", at = @At("HEAD"))
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


    //ISidedInventory
    @Inject(method = "getAccessibleSlotsFromSide", at = @At("HEAD"))
    private void getAccessibleSlotsFromSide(int aSide, CallbackInfoReturnable<int[]> cir) {
        invlock.trylock();
    }

    @Inject(method = "canInsertItem", at = @At("HEAD"))
    private void canInsertItem(int aIndex, ItemStack aStack, int aSide, CallbackInfoReturnable<Boolean> cir) {
        invlock.trylock();
    }

    @Inject(method = "canExtractItem", at = @At("HEAD"))
    private void canExtractItem(int aIndex, ItemStack aStack, int aSide, CallbackInfoReturnable<Boolean> cir) {
        invlock.trylock();
    }


    //IFluidTank
    @Inject(method = "getFluid", at = @At("HEAD"))
    private void getFluid(CallbackInfoReturnable<FluidStack> cir) {
        tanklock.trylock();
    }

    @Inject(method = "getFluidAmount", at = @At("HEAD"))
    private void getFluidAmount(CallbackInfoReturnable<Integer> cir) {
        tanklock.trylock();
    }

    @Inject(method = "getCapacity", at = @At("HEAD"))
    private void getCapacity(CallbackInfoReturnable<Integer> cir) {
        tanklock.trylock();
    }

    @Inject(method = "getInfo", at = @At("HEAD"))
    private void getInfo(CallbackInfoReturnable<FluidTankInfo> cir) {
        tanklock.trylock();
    }

    @Inject(method = "fill(Lnet/minecraftforge/fluids/FluidStack;Z)I", at = @At("HEAD"))
    private void fill(FluidStack resource, boolean doFill, CallbackInfoReturnable<Integer> cir) {
        tanklock.trylock();
    }

    @Inject(method = "drain(IZ)Lnet/minecraftforge/fluids/FluidStack;", at = @At("HEAD"))
    private void drain(int maxDrain, boolean doDrain, CallbackInfoReturnable<FluidStack> cir) {
        tanklock.trylock();
    }


    //IFluidHandler
    @Inject(method = "fill(Lnet/minecraftforge/common/util/ForgeDirection;Lnet/minecraftforge/fluids/FluidStack;Z)I", at = @At("HEAD"))
    private void fill2(ForgeDirection aSide, FluidStack aFluid, boolean doFill, CallbackInfoReturnable<Integer> cir) {
        tanklock.trylock();
    }

    @Inject(method = "drain(Lnet/minecraftforge/common/util/ForgeDirection;IZ)Lnet/minecraftforge/fluids/FluidStack;", at = @At("HEAD"))
    private void drain2(ForgeDirection aSide, int maxDrain, boolean doDrain, CallbackInfoReturnable<FluidStack> cir) {
        tanklock.trylock();
    }

    @Inject(method = "drain(Lnet/minecraftforge/common/util/ForgeDirection;Lnet/minecraftforge/fluids/FluidStack;Z)Lnet/minecraftforge/fluids/FluidStack;", at = @At("HEAD"))
    private void drain3(ForgeDirection aSide, FluidStack aFluid, boolean doDrain, CallbackInfoReturnable<FluidStack> cir) {
        tanklock.trylock();
    }

    @Inject(method = "canFill", at = @At("HEAD"))
    private void canFill(ForgeDirection aSide, Fluid aFluid, CallbackInfoReturnable<Boolean> cir) {
        tanklock.trylock();
    }

    @Inject(method = "canDrain", at = @At("HEAD"))
    private void canDrain(ForgeDirection aSide, Fluid aFluid, CallbackInfoReturnable<Boolean> cir) {
        tanklock.trylock();
    }

    @Inject(method = "getTankInfo", at = @At("HEAD"))
    private void getTankInfo(ForgeDirection aSide, CallbackInfoReturnable<FluidTankInfo[]> cir) {
        tanklock.trylock();
    }
}
