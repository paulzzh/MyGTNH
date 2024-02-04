package com.paulzzh.mygtnh.config;

import com.gtnewhorizon.gtnhlib.config.SimpleGuiFactory;
import net.minecraft.client.gui.GuiScreen;

public class MyGTNHGuiConfigFactory implements SimpleGuiFactory {
    @Override
    public Class<? extends GuiScreen> mainConfigGuiClass() {
        return MyGTNHGuiConfig.class;
    }
}
