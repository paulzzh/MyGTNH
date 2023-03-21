package tech.paulzzh.mygtnh.mixins.gregtech;

import gregtech.api.metatileentity.BaseMetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntity;
import net.minecraft.inventory.IInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tech.paulzzh.mygtnh.mcmt.FJPool;
import tech.paulzzh.mygtnh.mcmt.MyLock;

@Mixin(value = BaseMetaTileEntity.class, remap = false, priority = 1)
public abstract class BaseMetaTileEntityMixin implements IInventory {
    @Shadow
    protected MetaTileEntity mMetaTileEntity;
    private MyLock objectlock = new MyLock();

    //remap = true
    @Inject(method = "updateEntity", at = @At("HEAD"), remap = true)
    private void head(CallbackInfo ci) {
        //MyGTNH.info("updateEntity");
        objectlock.lock();
        long tid = Thread.currentThread().getId();
        FJPool.tickmap.put(tid, objectlock);
        //MyGTNH.info(FJPool.tickmap.toString());
    }

    //@Inject(method = "*", at = @At(value = "INVOKE", target = "Lgregtech/api/metatileentity/BaseMetaTileEntity;canAccessData()Z"))
    @Inject(method = "canAccessData", at = @At("HEAD"))
    private void head2(CallbackInfoReturnable<Boolean> cir) {
        if (!objectlock.tryLock()) {
            FJPool.unlockte();
            objectlock.lock();
            FJPool.lockte();
        }
    }

    @Override
    public int getSizeInventory() {
        return mMetaTileEntity.getSizeInventory();
    }

    //@Redirect(method = "injectEnergyUnits",at = @At(value = "INVOKE", target = "Lgregtech/api/metatileentity/BaseMetaTileEntity;canAccessData()Z"))
    //private boolean injected(BaseMetaTileEntity b) {return true;}
}