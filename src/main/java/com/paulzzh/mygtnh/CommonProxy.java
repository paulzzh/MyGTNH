package com.paulzzh.mygtnh;

import com.paulzzh.mygtnh.config.MyGTNHConfig;
import com.paulzzh.mygtnh.events.CommandMyGTNH;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import net.minecraft.client.Minecraft;

import static com.paulzzh.mygtnh.MyGTNH.CAPE_CACHE;
import static com.paulzzh.mygtnh.MyGTNH.CAPE_PLAYER_CACHE;

public class CommonProxy {

    // preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    public void preInit(FMLPreInitializationEvent event) {
        MyGTNH.LOG.info(MyGTNHConfig.greeting);
        MyGTNH.LOG.info("I am MyGTNH at version " + Tags.VERSION);
        FMLCommonHandler.instance().bus().register(this);
    }

    // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    public void init(FMLInitializationEvent event) {
    }

    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    public void postInit(FMLPostInitializationEvent event) {
    }

    // register server commands in this event handler (Remove if not needed)
    public void serverStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandMyGTNH());
    }

    @SubscribeEvent
    public void onClientConnectedToServer(FMLNetworkEvent.ClientConnectedToServerEvent event) {
        if (event.isLocal) {
            MyGTNH.LOG.info("客户端连接到单人世界！");
        } else {
            MyGTNH.LOG.info("客户端连接到多人服务器！");
        }
        CAPE_PLAYER_CACHE.clear();
        CAPE_CACHE.forEach((name,rl) -> Minecraft.getMinecraft().getTextureManager().deleteTexture(rl));
        CAPE_CACHE.clear();
    }
}
