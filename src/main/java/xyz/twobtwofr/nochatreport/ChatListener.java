package xyz.twobtwofr.nochatreport;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.Style;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.io.IOException;
import java.util.List;

public class ChatListener implements Listener
{

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerChat(AsyncPlayerChatEvent event)
    {
        event.setCancelled(true);

        if(!Nochatreport.getInstance().getConfig().getString("chat_webhook_discord").isEmpty())
        {
            DiscordWebhook webhook = new DiscordWebhook(Nochatreport.getInstance().getConfig().getString("chat_webhook_discord"));

            webhook.setUsername(event.getPlayer().getName());
            webhook.setContent(event.getMessage());
            try {
                webhook.execute();
            } catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
        for(Player player : Bukkit.getOnlinePlayers())
        {
            player.sendMessage("<" + event.getPlayer().getName() + "> " + (event.getMessage().startsWith(">") ? "§2" : "") + event.getMessage() + "§r");
        }
    }
}
