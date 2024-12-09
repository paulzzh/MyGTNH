package com.paulzzh.mygtnh.mixins.late.gregtech;

import gregtech.common.blocks.ItemMachines;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = ItemMachines.class)
public class ItemMachinesMixin {
    @Redirect(
        method = "onUpdate",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/EntityLivingBase;addPotionEffect(Lnet/minecraft/potion/PotionEffect;)V"
        )
    )
    public void addPotionEffect(EntityLivingBase instance, PotionEffect potionEffect) {
        instance.addPotionEffect(new PotionEffect(potionEffect.getPotionID(), 10, 1));
    }
}
