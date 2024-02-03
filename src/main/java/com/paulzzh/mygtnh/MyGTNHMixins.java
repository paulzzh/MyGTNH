package com.paulzzh.mygtnh;

import com.gtnewhorizon.gtnhmixins.ILateMixinLoader;
import com.gtnewhorizon.gtnhmixins.LateMixin;
import com.paulzzh.mygtnh.mixins.Mixins;

import java.util.List;
import java.util.Set;

@LateMixin
public class MyGTNHMixins implements ILateMixinLoader {
    @Override
    public String getMixinConfig() {
        return "mixins.mygtnh.late.json";
    }

    @Override
    public List<String> getMixins(Set<String> loadedMods) {
        return Mixins.getLateMixins(loadedMods);
    }

}
