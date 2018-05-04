package fr.murder.channels;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.murder.channels.command.Connect;
import fr.murder.channels.command.Disconnect;
import fr.murder.channels.event.Chat;

public class Core extends JavaPlugin {
	
	static Core instance;
	public Channel def;
	public static HashMap<String, Channel> channelname_oc;
	public static HashMap<UUID, Channel> uuid_oc;
	
	@Override
	public void onEnable() {
		instance = this;
		
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();
		
		channelname_oc = new HashMap<String, Channel>();
		uuid_oc = new HashMap<UUID, Channel>();
		
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new Chat(), this);
		
		getCommand("connect").setExecutor(new Connect());
		getCommand("disconnect").setExecutor(new Disconnect());
		
		this.def = new Channel(ChannelType.DEFAULT, "default", null);
		channelname_oc.put("default", this.def);
		
		@SuppressWarnings("deprecation")
		Player[] players = Bukkit.getOnlinePlayers();
		for(int i=0; i < players.length; i++) {
			uuid_oc.put(players[i].getUniqueId(), getChannel("default"));
		}
	}
	
	public static Core getInstance() {
		return instance;
	}
	
	public static Channel getChannel(String channelname) {
		return channelname_oc.containsKey(channelname) ? channelname_oc.get(channelname) : null;
	}
	
	public static Channel getPlayerChannel(UUID uuid) {
		return uuid_oc.containsKey(uuid) ? uuid_oc.get(uuid) : null;
	}
	
	public static void connectPlayer(UUID uuid, String channelname) {
		uuid_oc.put(uuid, getChannel(channelname));
	}
	
	public static void disconnectPlayer(UUID uuid) {
		uuid_oc.put(uuid, getChannel("default"));
	}

}
