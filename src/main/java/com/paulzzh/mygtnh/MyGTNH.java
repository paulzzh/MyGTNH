package com.paulzzh.mygtnh;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@Mod(
    modid = MyGTNH.MODID,
    version = Tags.VERSION,
    name = "MyGTNH",
    acceptedMinecraftVersions = "[1.7.10]",
    acceptableRemoteVersions = "*",
    dependencies = " required-after:gtnhlib@[0.2.1,);",
    guiFactory = "com.paulzzh.mygtnh.config.MyGTNHGuiConfigFactory"
)
public class MyGTNH {

    public static final String MODID = "mygtnh";
    public static final Logger LOG = LogManager.getLogger(MODID);
    public static final Map<String, ResourceLocation> CAPE_CACHE = Collections.synchronizedMap(new LinkedHashMap<>());

    @SidedProxy(clientSide = "com.paulzzh.mygtnh.ClientProxy", serverSide = "com.paulzzh.mygtnh.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    // preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @Mod.EventHandler
    // register server commands in this event handler (Remove if not needed)
    public void serverStarting(FMLServerStartingEvent event) {
        proxy.serverStarting(event);
    }
}
