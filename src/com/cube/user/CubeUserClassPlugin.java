package com.cube.user;

import com.cube.user.command.ClassCommand;
import com.cube.user.event.ClassEvent;
import com.cube.user.impl.ClassManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class CubeUserClassPlugin extends JavaPlugin{

    public static CubeUserClassPlugin instance;

    @Override
    public void onEnable() {
        instance = this;

        initialize();
    }

    private void initialize() {
        if(!getDataFolder().exists()) getDataFolder().mkdir();

        ClassManager.getManager().initialize();
        getCommand("등급").setExecutor(new ClassCommand());
        Bukkit.getPluginManager().registerEvents(new ClassEvent(), this);
    }
}
