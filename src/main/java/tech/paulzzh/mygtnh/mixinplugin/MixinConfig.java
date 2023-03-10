package tech.paulzzh.mygtnh.mixinplugin;

import net.minecraft.launchwrapper.Launch;
import tech.paulzzh.mygtnh.Config;

import java.io.File;

public class MixinConfig {
    public static Config config;

    static {
        MixinConfig.config = new Config(new File(Launch.minecraftHome, "config/mygtnh.cfg"));
    }
}
