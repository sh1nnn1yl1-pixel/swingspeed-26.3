package com.swingspeed.config;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

public class SwingSpeedConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static File configFile;
    private static SwingSpeedConfig instance;

    public boolean enabled = true;
    public float swingSpeedMultiplier = 1.0f;

    public static SwingSpeedConfig getInstance() {
        if (instance == null) {
            load();
        }
        return instance;
    }

    public static void load() {
        configFile = new File(FabricLoader.getInstance().getConfigDir().toFile(), "swingspeed.json");
        if (configFile.exists()) {
            try (FileReader reader = new FileReader(configFile)) {
                instance = GSON.fromJson(reader, SwingSpeedConfig.class);
            } catch (Exception e) {
                instance = new SwingSpeedConfig();
            }
        } else {
            instance = new SwingSpeedConfig();
            save();
        }
    }

    public static void save() {
        if (configFile == null) return;
        try (FileWriter writer = new FileWriter(configFile)) {
            GSON.toJson(instance, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
