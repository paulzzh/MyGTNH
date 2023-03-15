package tech.paulzzh.mygtnh.mixins.gregtech;

import gregtech.api.util.GT_Utility;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tech.paulzzh.mygtnh.mcmt.FJPool;

import java.util.List;

@Mixin(value = GT_Utility.class, remap = false, priority = 1)
public abstract class UtilityMixin {
    @Inject(method = "moveStackIntoPipe(Lnet/minecraft/inventory/IInventory;Ljava/lang/Object;[IIILjava/util/List;ZBBBBZ)B", at = @At("HEAD"))
    private static void head(IInventory aTileEntity1, Object aTileEntity2, int[] aGrabSlots, int aGrabFrom, int aPutTo, List<ItemStack> aFilter, boolean aInvertFilter, byte aMaxTargetStackSize, byte aMinTargetStackSize, byte aMaxMoveAtOnce, byte aMinMoveAtOnce, boolean dropItem, CallbackInfoReturnable<Byte> cir) {
        FJPool.GT_Utility_moveStackIntoPipe.lock();
    }

    @Inject(method = "moveStackIntoPipe(Lnet/minecraft/inventory/IInventory;Ljava/lang/Object;[IIILjava/util/List;ZBBBBZ)B", at = @At("RETURN"))
    private static void tail(IInventory aTileEntity1, Object aTileEntity2, int[] aGrabSlots, int aGrabFrom, int aPutTo, List<ItemStack> aFilter, boolean aInvertFilter, byte aMaxTargetStackSize, byte aMinTargetStackSize, byte aMaxMoveAtOnce, byte aMinMoveAtOnce, boolean dropItem, CallbackInfoReturnable<Byte> cir) {
        FJPool.GT_Utility_moveStackIntoPipe.unlock();
    }

}