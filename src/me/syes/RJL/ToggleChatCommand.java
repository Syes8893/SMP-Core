package me.syes.RJL;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ToggleChatCommand implements CommandExecutor{

	public static HashMap<UUID, Boolean> teamChat;
	
	public ToggleChatCommand() {
		teamChat = new HashMap<UUID, Boolean>();
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(!teamChat.containsKey(p.getUniqueId()))
				teamChat.put(p.getUniqueId(), true);
			else if(teamChat.get(p.getUniqueId()).equals(true))
				teamChat.put(p.getUniqueId(), false);
			else if(teamChat.get(p.getUniqueId()).equals(false))
				teamChat.put(p.getUniqueId(), true);
			
			sendToggleMessage(p);
		}
		return false;
	}
	
	private void sendToggleMessage(Player p) {
		if(teamChat.get(p.getUniqueId()) == true)
			p.sendMessage("§9§lTEAM §7» §fTeamchat has been §aEnabled!");
		else if(teamChat.get(p.getUniqueId()) == false)
			p.sendMessage("§9§lTEAM §7» §fTeamchat has been §cDisabled!");
	}
	
	public static boolean isInTeamChat(Player p) {
		if(teamChat.containsKey(p.getUniqueId()))
			if(teamChat.get(p.getUniqueId()) == true)
				return true;
			else if(teamChat.get(p.getUniqueId()) == false)
				return false;
		return false;
	}

}
