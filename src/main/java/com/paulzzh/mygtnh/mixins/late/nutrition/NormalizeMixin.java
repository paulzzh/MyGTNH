package com.paulzzh.mygtnh.mixins.late.nutrition;

import ca.wescook.nutrition.data.NutrientManager;
import ca.wescook.nutrition.network.PacketNormalizeServerNutrients.Handler;
import ca.wescook.nutrition.nutrients.Nutrient;
import com.paulzzh.mygtnh.MyGTNH;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = Handler.class)
public class NormalizeMixin {
    /**
     * @author Paulzzh
     * @reason block Normalize
     */
    @Redirect(
        method = "onMessage(Lca/wescook/nutrition/network/PacketNormalizeServerNutrients$Message;Lcpw/mods/fml/common/network/simpleimpl/MessageContext;)Lcpw/mods/fml/common/network/simpleimpl/IMessage;",
        at = @At(value = "INVOKE", target = "Lca/wescook/nutrition/data/NutrientManager;get(Lca/wescook/nutrition/nutrients/Nutrient;)Ljava/lang/Float;", remap = false),
        remap = false)
    public Float onMessage(NutrientManager manager, Nutrient nutrient) {
        Float currentValue = manager.get(nutrient);
        if (currentValue > 50f) {
            MyGTNH.LOG.debug("block nutrient normalize");
            return 50f;
        }
        return currentValue;
    }
}
