package com.swingspeed;

import com.swingspeed.config.SwingSpeedConfig;
import net.fabricmc.api.ClientModInitializer;

public class SwingSpeedMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        SwingSpeedConfig.load();
    }
}
