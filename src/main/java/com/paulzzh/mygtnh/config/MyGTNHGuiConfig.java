package com.paulzzh.mygtnh.config;

import com.gtnewhorizon.gtnhlib.config.ConfigException;
import com.gtnewhorizon.gtnhlib.config.SimpleGuiConfig;
import net.minecraft.client.gui.GuiScreen;

public class MyGTNHGuiConfig extends SimpleGuiConfig {
    public MyGTNHGuiConfig(GuiScreen parent) throws ConfigException {
        super(parent, MyGTNHConfig.class, "mygtnh", "MyGTNH");
    }
}
