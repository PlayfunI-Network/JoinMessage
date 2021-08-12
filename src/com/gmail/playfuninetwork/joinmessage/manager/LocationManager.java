package com.gmail.playfuninetwork.joinmessage.manager;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class LocationManager {
    public void setMiddle(String spawn, Location location) {
        File file = new File("plugins//JoinMessage", "config.yml");
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
        String path = spawn + "_Spawn";
        yamlConfiguration.set(path + ".world", location.getWorld().getName());
        yamlConfiguration.set(path + ".x", Double.valueOf(location.getX()));
        yamlConfiguration.set(path + ".y", Double.valueOf(location.getY()));
        yamlConfiguration.set(path + ".z", Double.valueOf(location.getZ()));
        yamlConfiguration.set(path + ".yaw", Float.valueOf(location.getYaw()));
        yamlConfiguration.set(path + ".pitch", Float.valueOf(location.getPitch()));
        try {
            yamlConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Location getMiddle(String spawn) {
        File file = new File("plugins//JoinMessage", "config.yml");
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
        String path = spawn + "_Spawn";
        String world = yamlConfiguration.getString(path + ".world");
        Double x = Double.valueOf(yamlConfiguration.getDouble(path + ".x"));
        Double y = Double.valueOf(yamlConfiguration.getDouble(path + ".y"));
        Double z = Double.valueOf(yamlConfiguration.getDouble(path + ".z"));
        Float yaw = Float.valueOf(yamlConfiguration.getString(path + ".yaw"));
        Float pitch = Float.valueOf(yamlConfiguration.getString(path + ".pitch"));
        return new Location(Bukkit.getWorld(world), x.doubleValue(), y.doubleValue(), z.doubleValue(), yaw.floatValue(), pitch.floatValue());
    }
}
