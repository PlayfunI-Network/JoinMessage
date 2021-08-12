package com.gmail.playfuninetwork.joinmessage.commands;

import com.gmail.playfuninetwork.joinmessage.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class JoinMessage implements CommandExecutor {
    public Main instance = Main.getInstance();

    private File config = new File("plugins//JoinMessage", "config.yml");

    private FileConfiguration configManager;

    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player))
            return true;
        Player player = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("jm") || cmd.getName().equalsIgnoreCase("joinmessage"))
            if (player.hasPermission("joinmessage.*")) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("reload")) {
                        player.sendMessage("reload successfully.");
                        Bukkit.getScheduler().runTaskLater((Plugin) Main.getPlugin(Main.class), new Runnable() {
                            public void run() {
                                Main.instance.saveDefaultConfig();
                                Main.instance.reloadConfig();
                                Main.instance.saveConfig();
                            }
                        }, 5L);
                    } else if (args[0].equalsIgnoreCase("toggle")) {
                        if (!this.instance.getConfig().getBoolean("Original-mc-join-msg")) {
                            this.instance.getConfig().set("Original-mc-join-msg", Boolean.valueOf(true));
                            this.instance.saveConfig();
                            this.instance.reloadConfig();
                            this.instance.saveConfig();
                            player.sendMessage(ChatColor.GREEN + "JoinMessage ENABLED!");
                        } else {
                            this.instance.getConfig().set("Original-mc-join-msg", Boolean.valueOf(false));
                            this.instance.saveConfig();
                            this.instance.reloadConfig();
                            this.instance.saveConfig();
                            player.sendMessage(ChatColor.RED + "JoinMessage DISABLED");
                        }
                    } else if (args[0].equalsIgnoreCase("setspawn")) {
                        player.sendMessage("set the Spawn!");
                        this.instance.locationManager.setMiddle("Lobby", player.getLocation());
                    } else {
                        player.sendMessage("use reload/toggle");
                    }
                } else {
                    player.sendMessage("use reload/toggle");
                }
            } else {
                player.sendMessage("don't have permission.");
            }
        return false;
    }
}
