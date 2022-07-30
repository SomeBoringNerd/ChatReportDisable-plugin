package xyz.twobtwofr.nochatreport.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class me implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args)
    {
        if(args.length == 0){
            sender.sendMessage("You need to type something !");
            return false;
        }else{

            String message = "";

            for(String str : args){
                message += str + " ";
            }

            for(Player player : Bukkit.getServer().getOnlinePlayers()){
                player.sendMessage("§8§o" + sender.getName() + " " + message);
            }
        }

        return true;
    }
}
