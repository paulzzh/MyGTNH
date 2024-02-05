package com.paulzzh.mygtnh.mixins.late.gregtech;

import gregtech.common.blocks.GT_Item_Machines;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = GT_Item_Machines.class, remap = false)
public class GTItemMachinesMixin {
    @Redirect(
        method = "onUpdate",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/EntityLivingBase;addPotionEffect(Lnet/minecraft/potion/PotionEffect;)V"
        )
    )
    private static void addPotionEffect(EntityLivingBase instance, PotionEffect potionEffect) {
        instance.addPotionEffect(new PotionEffect(potionEffect.getPotionID(), 1, 1));
    }
}
