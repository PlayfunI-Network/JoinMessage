package com.gmail.playfuninetwork.joinmessage;

import com.gmail.playfuninetwork.joinmessage.commands.JoinMessage;
import com.gmail.playfuninetwork.joinmessage.listener.PlayerJoin;
import com.gmail.playfuninetwork.joinmessage.listener.PlayerLeave;
import com.gmail.playfuninetwork.joinmessage.manager.ConfigManager;
import com.gmail.playfuninetwork.joinmessage.manager.LocationManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Main extends JavaPlugin {
    public static Main instance;

    public ConfigManager configManager;

    private FileConfiguration fileConfiguration;

    private File file;

    public LocationManager locationManager;

    public PluginManager pluginManager = Bukkit.getPluginManager();

    public ConsoleCommandSender console = Bukkit.getConsoleSender();

    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        this.file = new File("plugins/JoinMessage", "config.yml");
        new YamlConfiguration();
        this.fileConfiguration = (FileConfiguration) YamlConfiguration.loadConfiguration(this.file);
        this.console.sendMessage("Successfully enabled");
        this.configManager = new ConfigManager();
        this.locationManager = new LocationManager();
        registerCommands();
        registerListener();
    }

    public void onDisable() {
        this.console.sendMessage("Failed to load plugin" );
    }

    void registerListener() {
        this.pluginManager.registerEvents((Listener) new PlayerJoin(), (Plugin) this);
        this.pluginManager.registerEvents((Listener) new PlayerLeave(), (Plugin) this);
    }

    void registerCommands() {
        getCommand("jm").setExecutor((CommandExecutor) new JoinMessage());
        getCommand("joinmessage").setExecutor((CommandExecutor) new JoinMessage());
    }

    public static Main getInstance() {
        return instance;
    }

    public ConfigManager getConfigManager() {
        return this.configManager;
    }

    public LocationManager getLocationManager() {
        return this.locationManager;
    }
}
