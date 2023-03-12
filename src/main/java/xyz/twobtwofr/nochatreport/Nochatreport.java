package xyz.twobtwofr.nochatreport;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.twobtwofr.nochatreport.commands.me;
import xyz.twobtwofr.nochatreport.commands.msg;
import xyz.twobtwofr.nochatreport.commands.reply;
import xyz.twobtwofr.nochatreport.commands.msg;

import java.util.ArrayList;
import java.util.List;

public final class Nochatreport extends JavaPlugin {

    public msg message;
    public reply reply;

    private static Nochatreport instance;

    public static Nochatreport getInstance(){
        return  instance;
    }

    @Override
    public void onEnable()
    {
        instance = this;

        message = new msg(this);
        reply = new reply(this);

        getServer().getPluginManager().registerEvents(new ChatListener(), this);

        getCommand("msg").setExecutor(message);
        getCommand("me").setExecutor(new me());
        getCommand("r").setExecutor(reply);

        getConfig().addDefault("chat_webhook_discord", "");

        getConfig().options().copyDefaults(true);

        saveConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
