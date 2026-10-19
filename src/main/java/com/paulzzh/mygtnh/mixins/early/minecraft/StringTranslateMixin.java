package com.paulzzh.mygtnh.mixins.early.minecraft;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.util.StringTranslate;
import org.spongepowered.asm.mixin.Mixin;

import static com.paulzzh.mygtnh.config.MyGTNHConfig.mc_lang;

@Mixin(value = StringTranslate.class)
public class StringTranslateMixin {
    @WrapMethod(method = "tryTranslateKey")
    private String translateKey(String p_135064_1_, Operation<String> original) {
        String localizedString = LanguageRegistry.instance().getStringLocalization(p_135064_1_, mc_lang);
        if (localizedString.isEmpty()) {
            return original.call(p_135064_1_);
        }
        return localizedString;
    }
}
