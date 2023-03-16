package tech.paulzzh.mygtnh.mixins.gregtech;

import gregtech.api.metatileentity.BaseMetaTileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tech.paulzzh.mygtnh.Utils;

import java.util.Stack;
import java.util.concurrent.locks.ReentrantLock;

@Mixin(value = BaseMetaTileEntity.class, remap = false, priority = 1)
public abstract class BaseMetaTileEntityMixin {
    private ReentrantLock lock = new ReentrantLock();
    private Stack<String> stack = new Stack<>();

    @Inject(method = "updateEntity", at = @At("HEAD"))
    public void head(CallbackInfo ci) {
        lock.lock();
    }

    @Inject(method = "updateEntity", at = @At("RETURN"))
    public void tail(CallbackInfo ci) {
        lock.unlock();
    }

    @Inject(method = "*", at = @At(value = "INVOKE", target = "Lgregtech/api/metatileentity/BaseMetaTileEntity;canAccessData()Z"))
    public void head2(CallbackInfo ci) {
        lock.lock();
        String name = Utils.getMethodFullName(1);
        stack.push(name);
    }

    @Inject(method = "*", at = @At("RETURN"))
    public void tail2(CallbackInfo ci) {
        if (lock.isHeldByCurrentThread()) {
            String name = Utils.getMethodFullName(1);
            if (stack.peek().equals(name)) {
                stack.pop();
                lock.unlock();
            }
        }
    }
}