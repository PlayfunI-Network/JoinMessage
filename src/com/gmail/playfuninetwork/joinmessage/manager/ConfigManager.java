package com.gmail.playfuninetwork.joinmessage.manager;


import com.gmail.playfuninetwork.joinmessage.Main;

public class ConfigManager {
    public Main instance = Main.getInstance();

    public boolean orginalJoinMSG = this.instance.getConfig().getBoolean("Original-mc-join-msg");

    public boolean orginalLeaveMSG = this.instance.getConfig().getBoolean("Original-mc-leave-msg");

    public boolean personalJoinMSG = this.instance.getConfig().getBoolean("personal-join-msg");

    public boolean publicJoinMSG = this.instance.getConfig().getBoolean("public-join-msg");
}
