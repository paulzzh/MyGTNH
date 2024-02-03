package com.paulzzh.mygtnh;

import com.gtnewhorizon.gtnhmixins.IEarlyMixinLoader;
import com.paulzzh.mygtnh.mixins.Mixins;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import net.minecraft.launchwrapper.Launch;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

@IFMLLoadingPlugin.MCVersion("1.7.10")
public class MyGTNHCore implements IFMLLoadingPlugin, IEarlyMixinLoader {
    static {
        Config.synchronizeConfiguration(new File(Launch.minecraftHome, "config/mygtnh.cfg"));
    }

    @Override
    public String getMixinConfig() {
        return "mixins.mygtnh.early.json";
    }

    @Override
    public List<String> getMixins(Set<String> loadedCoreMods) {
        return Mixins.getEarlyMixins(loadedCoreMods);
    }

    @Override
    public String[] getASMTransformerClass() {
        return new String[0];
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {

    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
