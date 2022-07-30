package xyz.twobtwofr.nochatreport.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;
import xyz.twobtwofr.nochatreport.Nochatreport;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class reply  implements Listener, CommandExecutor
{

    public Nochatreport report;

    public reply(Nochatreport _report)
    {
        report = _report;
    }


    public Map<Player, Player> lastPlayerMessaged = new HashMap<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args)
    {
        if(lastPlayerMessaged.get(sender) == null)
        {
            sender.sendMessage("You didn't messaged any player or the player is offline");
            return false;
        }else if(args.length == 0){
            sender.sendMessage("You need to type something !");
            return false;
        }else{
            String message = "";

            for(String str : args){
                message += str + " ";
            }
            sender.sendMessage("§5You whisper to " + lastPlayerMessaged.get(sender).getName() + " : §d" + message.trim());
            lastPlayerMessaged.get(sender).sendMessage("§5" + Objects.requireNonNull(sender).getName() + " whisper to you : §d" + message.trim());
        }
        return false;
    }
}
