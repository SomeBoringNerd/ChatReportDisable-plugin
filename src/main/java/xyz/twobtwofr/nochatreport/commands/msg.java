package xyz.twobtwofr.nochatreport.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xyz.twobtwofr.nochatreport.Nochatreport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class msg implements CommandExecutor
{

    public Nochatreport report;

    public msg(Nochatreport _report)
    {
        report = _report;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length == 0) {
            sender.sendMessage("You need to specify a player");
            return false;
        }else{
            Player player = Bukkit.getPlayer(sender.getName());
            Player player_2 = Bukkit.getPlayer(args[0]);

            // not supposed to be possible but ya never know
            if(player == null){
                return false;
            }if(player_2 == null){
                sender.sendMessage("Player is offline or the username is invalid");
                return false;
            }

            if(args.length <= 1){
                sender.sendMessage("You need to type something !");
                return false;
            }else
            {
                args[0] = "";
                String message = "";

                for(String str : args){
                    message += str + " ";
                }


                if(report.reply.lastPlayerMessaged.get(player) == null){
                    report.reply.lastPlayerMessaged.put(player, player_2);
                }else{
                    for (Map.Entry<Player, Player> entry : report.reply.lastPlayerMessaged.entrySet())
                    {
                        if(entry.getKey().getName() == player.getName()){
                            entry.setValue(player_2);
                        }
                    }
                }

                if(report.reply.lastPlayerMessaged.get(player_2) == null){
                    report.reply.lastPlayerMessaged.put(player_2, player);
                }else{
                    for (Map.Entry<Player, Player> entry : report.reply.lastPlayerMessaged.entrySet())
                    {
                        if(entry.getKey().getName() == player_2.getName()){
                            entry.setValue(player);
                        }
                    }
                }
                sender.sendMessage("§5You whisper to " + player_2.getName() + " : §d" + message.trim());
                player_2.sendMessage("§5" + Objects.requireNonNull(player.getPlayer()).getName() + " whisper to you : §d" + message.trim());
                return true;
            }
        }
    }
}
