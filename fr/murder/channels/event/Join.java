package fr.murder.channels.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import fr.murder.channels.Channel;
import fr.murder.channels.Core;

public class Chat implements Listener {
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		Channel channel = Core.getPlayerChannel(player.getUniqueId());
		
		event.setCancelled(true);
		channel.logHistory(event.getFormat());
	}

}
