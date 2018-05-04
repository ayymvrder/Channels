package fr.murder.channels.command;

import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.murder.channels.Channel;
import fr.murder.channels.ChatUtil;
import fr.murder.channels.Core;

public class Connect implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(! sender.hasPermission("channels.connect")) {
			sender.sendMessage("You don't have the permission to execute this command");
		}
		
		if(args.length != 1) {
			sender.sendMessage(ChatColor.RED + "Usage: /connect <channel>");
			return true;
		}
		
		Channel channel = Core.getChannel(args[1]);
		UUID uuid = ((Player) sender).getUniqueId();
		
		if(channel == null) {
			sender.sendMessage(ChatColor.RED + "Channel not found");
		}
		
		if(Core.getPlayerChannel(((Player) sender).getUniqueId()) == channel) {
			sender.sendMessage(ChatColor.RED + "You are already connected to this channel");
			return true;
		}
		
		ChatUtil.clear((Player) sender);
		Core.connectPlayer(uuid, channel.getName());
		sender.sendMessage(ChatColor.GREEN + "Connected to " + channel.getName());
		
		sender.sendMessage(ChatColor.GREEN + "Loading channel history...");
		ChatUtil.sendList((Player) sender, Core.getChannel(channel.getName()).getHistory());
		return false;
	}

}
