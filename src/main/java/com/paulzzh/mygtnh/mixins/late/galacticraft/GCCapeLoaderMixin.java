package com.paulzzh.mygtnh.mixins.late.galacticraft;

import com.paulzzh.mygtnh.MyGTNH;
import micdoodle8.mods.galacticraft.core.client.capes.GCCapeLoader;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.HashMap;
import java.util.Map;

@Mixin(value = GCCapeLoader.class)
public class GCCapeLoaderMixin {
    /**
     * @author Paulzzh
     * @reason remove GC armor render
     */
    @Overwrite(remap = false)
    private static Map<String, ResourceLocation> loadNameToCapeMap() {
        MyGTNH.LOG.info("block Galacticraft web request");
        return new HashMap<>();
    }
}
