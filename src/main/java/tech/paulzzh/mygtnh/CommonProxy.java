package tech.paulzzh.mygtnh;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import tech.paulzzh.mygtnh.mcmt.FJPool;


public class CommonProxy {

    // preInit "Run before anything else. Read your config, create blocks, items,
    // etc, and register them with the GameRegistry."
    public void preInit(FMLPreInitializationEvent event) {
        Config.syncronizeConfiguration(event.getSuggestedConfigurationFile());

        MyGTNH.info(Config.greeting);
        MyGTNH.info("I am " + Tags.MODNAME + " at version " + Tags.VERSION + " and group name " + Tags.GROUPNAME);
    }

    // register server commands in this event handler (Remove if not needed)
    public void serverStarting(FMLServerStartingEvent event) {
        MyGTNH.serverStarting = true;
        if (Config.mc_temt) {
            MyGTNH.info("setupThreadPool");
            FJPool.setupThreadPool(8);
        }
    }
}
