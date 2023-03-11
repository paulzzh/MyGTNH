package tech.paulzzh.mygtnh;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Tags.MODID, version = Tags.VERSION, name = Tags.MODNAME, acceptedMinecraftVersions = "[1.7.10]")
public class MyGTNH {

    @SidedProxy(clientSide = Tags.GROUPNAME + ".ClientProxy", serverSide = Tags.GROUPNAME + ".CommonProxy")
    public static CommonProxy proxy;
    private static Logger LOG = LogManager.getLogger(Tags.MODID);

    public static void info(String message) {
        LOG.info(message);
    }

    public static void debug(String message) {
        LOG.debug(message);
    }

    @Mod.EventHandler
    // preInit "Run before anything else. Read your config, create blocks, items,
    // etc, and register them with the GameRegistry."
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    // register server commands in this event handler (Remove if not needed)
    public void serverStarting(FMLServerStartingEvent event) {
        proxy.serverStarting(event);
    }
}