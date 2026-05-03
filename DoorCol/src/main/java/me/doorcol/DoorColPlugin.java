package me.doorcol;

import me.doorcol.listener.BlockListener;
import org.bukkit.plugin.java.JavaPlugin;

public class DoorColPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new BlockListener(this), this);
    }
}
