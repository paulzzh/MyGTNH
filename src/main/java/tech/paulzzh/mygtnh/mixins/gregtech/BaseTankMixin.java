package tech.paulzzh.mygtnh.mixins.gregtech;

import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_BasicTank;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tech.paulzzh.mygtnh.mixinhelpers.gregtech.IMetaTileEntityMixin;

@Mixin(value = GT_MetaTileEntity_BasicTank.class, remap = false, priority = 1)
public abstract class BaseTankMixin implements IMetaTileEntityMixin {
    //IFluidTank
    @Inject(method = "getFluid", at = @At("HEAD"))
    private void getFluid(CallbackInfoReturnable<FluidStack> cir) {
        getTankLock().trylock();
    }

    @Inject(method = "getFluidAmount", at = @At("HEAD"))
    private void getFluidAmount(CallbackInfoReturnable<Integer> cir) {
        getTankLock().trylock();
    }

    @Inject(method = "fill(Lnet/minecraftforge/fluids/FluidStack;Z)I", at = @At("HEAD"))
    private void fill(FluidStack resource, boolean doFill, CallbackInfoReturnable<Integer> cir) {
        getTankLock().trylock();
    }

    @Inject(method = "drain(IZ)Lnet/minecraftforge/fluids/FluidStack;", at = @At("HEAD"))
    private void drain(int maxDrain, boolean doDrain, CallbackInfoReturnable<FluidStack> cir) {
        getTankLock().trylock();
    }


    //IFluidHandler
    @Inject(method = "getTankInfo", at = @At("HEAD"))
    private void getTankInfo(ForgeDirection aSide, CallbackInfoReturnable<FluidTankInfo[]> cir) {
        getTankLock().trylock();
    }

}
