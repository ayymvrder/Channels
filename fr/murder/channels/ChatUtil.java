package fr.murder.channels;

import java.util.List;

import org.bukkit.entity.Player;

public class ChatUtil {
	
	public static void clear(Player player) {
		for(int i=0; i<100; i++) {
			player.sendMessage("");
		}
	}
	
	public static void sendList(Player player, List<String> list) {
		for(int i=0; i<list.size(); i++) {
			player.sendMessage(list.get(i));
		}
	}

}
