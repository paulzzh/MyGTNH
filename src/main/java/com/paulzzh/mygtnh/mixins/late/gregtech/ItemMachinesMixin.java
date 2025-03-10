package com.paulzzh.mygtnh.mixins.late.gregtech;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import gregtech.common.blocks.ItemMachines;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = ItemMachines.class)
public class ItemMachinesMixin {
    @WrapOperation(
        method = "onUpdate",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/EntityLivingBase;addPotionEffect(Lnet/minecraft/potion/PotionEffect;)V"
        )
    )
    private void addPotionEffect(EntityLivingBase instance, PotionEffect potionEffect, Operation<Void> original) {
        original.call(instance, new PotionEffect(potionEffect.getPotionID(), 10, 1));
    }
}
