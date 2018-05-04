package fr.murder.channels.command;

import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.murder.channels.ChatUtil;
import fr.murder.channels.Core;

public class Disconnect implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(! sender.hasPermission("channels.disconnect")) {
			sender.sendMessage("You don't have the permission to execute this command");
		}
		
		if(args.length != 0) {
			sender.sendMessage(ChatColor.RED + "Usage: /disconnect");
			return true;
		}
		
		UUID uuid = ((Player) sender).getUniqueId();
		
		if(Core.getPlayerChannel(uuid) == Core.getChannel("default")) {
			sender.sendMessage(ChatColor.RED + "You are already connected to default channel");
			return true;
		}
		
		ChatUtil.clear((Player) sender);
		Core.connectPlayer(uuid, "default");
		sender.sendMessage(ChatColor.GREEN + "Connected to default channel");
		
		sender.sendMessage(ChatColor.GREEN + "Loading channel history...");
		ChatUtil.sendList((Player) sender, Core.getChannel("default").getHistory());
		return false;
	}

}
