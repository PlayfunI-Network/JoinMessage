package com.gmail.playfuninetwork.joinmessage.listener;

import com.gmail.playfuninetwork.joinmessage.Main;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

public class PlayerJoin implements Listener {
    public Main instance = Main.getInstance();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage(null);
        try {
            player.playSound(player.getLocation(), Sound.valueOf(this.instance.getConfig().getString("Sound")), 5.0F, 5.0F);
        } catch (Exception e) {
//            System.out.println("You have to select a Sound!");
        }
        try {
            if (this.instance.locationManager.getMiddle("Lobby") != null)
                player.teleport(this.instance.locationManager.getMiddle("Lobby"));
        } catch (NullPointerException e) {
//            System.out.println("The Spawn must be set! /jm setspawn");
        }
        if (orginalJoinMSG()){
            String joinText = this.instance.getConfig().getString("OrginalJoinMSG").replace("&", "§");
            if(Main.hasPlaceHolder()){
                joinText = PlaceholderAPI.setPlaceholders(event.getPlayer(), joinText);
            }else {
                joinText = joinText.replace("%player%", player.getName()) + "\n";
            }
            event.setJoinMessage(joinText);
        }
        if (welcomeMSG()) {
            List<String> welocmeMessage = this.instance.getConfig().getStringList("WelcomeMessage");
            StringBuilder builder = new StringBuilder();
            welocmeMessage.forEach(msg -> builder.append(msg).append("\n"));
            if(Main.hasPlaceHolder()){
                player.sendMessage(PlaceholderAPI.setPlaceholders(event.getPlayer(), builder.toString()).replace("&", "§"));
            }else {
                player.sendMessage(builder.toString()
                        .replace("&", "§")
                        .replace("%line%", "\n")
                        .replace("%player%", player.getName()));
            }

        }
        if (publicJoinMSG())
            checkPlayerPermission(player);
    }

    boolean orginalJoinMSG() {
        if (!this.instance.getConfig().getBoolean("Original-mc-join-msg"))
            return false;
        return true;
    }

    boolean welcomeMSG() {
        if (!this.instance.getConfig().getBoolean("personal-join-msg"))
            return false;
        return true;
    }

    boolean publicJoinMSG() {
        if (!this.instance.getConfig().getBoolean("public-join-msg"))
            return false;
        return true;
    }

    private void checkPlayerPermission(Player player) {
        if(Main.hasPlaceHolder()){
            if (player.hasPermission("joinmessage.owner")) {
                Bukkit.broadcastMessage(PlaceholderAPI.setPlaceholders(player, this.instance.getConfig().getString("PublicJoin.Owner")).replace("&", "§") + "\n");
            } else if (player.hasPermission("joinmessage.admin")) {
                Bukkit.broadcastMessage(PlaceholderAPI.setPlaceholders(player, this.instance.getConfig().getString("PublicJoin.Admin")).replace("&", "§") + "\n");
            } else if (player.hasPermission("joinmessage.builder")) {
                Bukkit.broadcastMessage(PlaceholderAPI.setPlaceholders(player, this.instance.getConfig().getString("PublicJoin.BUILDER")).replace("&", "§") + "\n");
            } else if (player.hasPermission("joinmessage.youtuber")) {
                Bukkit.broadcastMessage(PlaceholderAPI.setPlaceholders(player, this.instance.getConfig().getString("PublicJoin.YOUTUBER")).replace("&", "§") + "\n");
            } else if (player.hasPermission("joinmessage.funi")) {
                Bukkit.broadcastMessage(PlaceholderAPI.setPlaceholders(player, this.instance.getConfig().getString("PublicJoin.FUNI")).replace("&", "§")+ "\n");
            } else if (player.hasPermission("joinmessage.mvp")) {
                Bukkit.broadcastMessage(PlaceholderAPI.setPlaceholders(player, this.instance.getConfig().getString("PublicJoin.MVP")).replace("&", "§") + "\n");
            } else if (player.hasPermission("joinmessage.vip")) {
                Bukkit.broadcastMessage(PlaceholderAPI.setPlaceholders(player, this.instance.getConfig().getString("PublicJoin.VIP")).replace("&", "§") + "\n");
            }
        }else{
            if (player.hasPermission("joinmessage.owner")) {
                Bukkit.broadcastMessage(String.valueOf(this.instance.getConfig().getString("PublicJoin.Owner")
                        .replace("%player%", player.getName()).replace("&", "§") + "\n"));
            } else if (player.hasPermission("joinmessage.admin")) {
                Bukkit.broadcastMessage(String.valueOf(this.instance.getConfig().getString("PublicJoin.ADMIN")
                        .replace("%player%", player.getName()).replace("&", "§") + "\n"));
            } else if (player.hasPermission("joinmessage.builder")) {
                Bukkit.broadcastMessage(String.valueOf(this.instance.getConfig().getString("PublicJoin.BUILDER")
                        .replace("%player%", player.getName()).replace("&", "§") + "\n"));
            } else if (player.hasPermission("joinmessage.youtuber")) {
                Bukkit.broadcastMessage(String.valueOf(this.instance.getConfig().getString("PublicJoin.YOUTUBER")
                        .replace("%player%", player.getName()).replace("&", "§") + "\n"));
            } else if (player.hasPermission("joinmessage.funi")) {
                Bukkit.broadcastMessage(String.valueOf(this.instance.getConfig().getString("PublicJoin.FUNI")
                        .replace("%player%", player.getName()).replace("&", "§") + "\n"));
            } else if (player.hasPermission("joinmessage.mvp")) {
                Bukkit.broadcastMessage(String.valueOf(this.instance.getConfig().getString("PublicJoin.MVP")
                        .replace("%player%", player.getName()).replace("&", "§") + "\n"));
            } else if (player.hasPermission("joinmessage.vip")) {
                Bukkit.broadcastMessage(String.valueOf(this.instance.getConfig().getString("PublicJoin.VIP")
                        .replace("%player%", player.getName()).replace("&", "§") + "\n"));
            }
        }

    }
}
