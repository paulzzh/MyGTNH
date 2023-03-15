package tech.paulzzh.mygtnh.mixins.endercore;

import com.enderio.core.common.util.ItemUtil;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tech.paulzzh.mygtnh.mcmt.FJPool;

@Mixin(value = ItemUtil.class, remap = false, priority = 1)
public abstract class ItemUtilMixin {
    @Inject(method = "doInsertItem(Ljava/lang/Object;Lnet/minecraft/item/ItemStack;Lnet/minecraftforge/common/util/ForgeDirection;)I", at = @At("HEAD"))
    private static void head(Object into, ItemStack item, ForgeDirection side, CallbackInfoReturnable<Integer> cir) {
        FJPool.EC_ItemUtil_doInsertItem.lock();
    }

    @Inject(method = "doInsertItem(Ljava/lang/Object;Lnet/minecraft/item/ItemStack;Lnet/minecraftforge/common/util/ForgeDirection;)I", at = @At("RETURN"))
    private static void tail(Object into, ItemStack item, ForgeDirection side, CallbackInfoReturnable<Integer> cir) {
        FJPool.EC_ItemUtil_doInsertItem.unlock();
    }
}