package xyz.twobtwofr.nochatreport;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.twobtwofr.nochatreport.commands.msg;
import xyz.twobtwofr.nochatreport.commands.reply;
import xyz.twobtwofr.nochatreport.commands.msg;

import java.util.ArrayList;
import java.util.List;

public final class Nochatreport extends JavaPlugin {

    public msg message;
    public reply reply;

    @Override
    public void onEnable()
    {
        message = new msg(this);
        reply = new reply(this);
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        getCommand("msg").setExecutor(message);
        getCommand("r").setExecutor(reply);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
