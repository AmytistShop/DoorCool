package me.doorcol.config;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashSet;
import java.util.Set;

public class ConfigManager {

    private final FileConfiguration config;

    public ConfigManager(FileConfiguration config) {
        this.config = config;
    }

    public int getFatigueLevel() {
        return config.getInt("fatigue-level", 0);
    }

    public boolean useEfficiency() {
        return config.getBoolean("use-efficiency", true);
    }

    public Set<Material> getBlocks() {
        Set<Material> set = new HashSet<>();
        for (String s : config.getStringList("blocks")) {
            try {
                set.add(Material.valueOf(s));
            } catch (Exception ignored) {}
        }
        return set;
    }
}
