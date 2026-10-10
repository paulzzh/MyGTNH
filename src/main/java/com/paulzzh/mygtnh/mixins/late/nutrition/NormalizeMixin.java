package com.paulzzh.mygtnh.mixins.late.nutrition;

import ca.wescook.nutrition.api.INutritionManager;
import ca.wescook.nutrition.events.EventWorldTick;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = EventWorldTick.class)
public class NormalizeMixin {
    /**
     * @author Paulzzh
     * @reason block Normalize
     */
    @WrapOperation(
        method = "handleNonFoodHungerChanges",
        at = @At(value = "INVOKE", target = "Lca/wescook/nutrition/api/INutritionManager;normalize(Lnet/minecraft/entity/player/EntityPlayer;FF)Z", remap = false),
        remap = false)
    private boolean normalize(INutritionManager instance, EntityPlayer entityPlayer, float toValue, float delta, Operation<Boolean> original) {
        if (toValue == 50.0f) {
            return original.call(instance, entityPlayer, 100.0f, delta);
        }
        return original.call(instance, entityPlayer, toValue, delta);
    }
}
