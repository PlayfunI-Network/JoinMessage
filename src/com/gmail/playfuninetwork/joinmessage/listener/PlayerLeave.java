package com.gmail.playfuninetwork.joinmessage.listener;

import com.gmail.playfuninetwork.joinmessage.Main;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeave implements Listener {
    public Main instance = Main.getInstance();

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (!orginalLeaveMSG()) {
            event.setQuitMessage(null);
            return;
        }
        if(Main.hasPlaceHolder()){
            event.setQuitMessage(PlaceholderAPI.setPlaceholders(player, this.instance.getConfig().getString("OrginalLeaveMSG").replace("&", "§")));
        }else {
            event.setQuitMessage(this.instance.getConfig().getString("OrginalLeaveMSG").replace("%player%", player.getName()).replace("&", "§"));
        }

    }

    boolean orginalLeaveMSG() {
        if (!this.instance.getConfig().getBoolean("Original-mc-leave-msg"))
            return false;
        return true;
    }
}
