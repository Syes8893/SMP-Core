package me.syes.SMPCore.Handlers;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.syes.SMPCore.Commands.ToggleChatCommand;

public class ChatHandler implements Listener {
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		if(p.getScoreboard().getPlayerTeam(p) != null) {
			String[] split = e.getMessage().split("");
			if(split[0].equalsIgnoreCase("@") || ToggleChatCommand.isInTeamChat(p)) {
				e.setCancelled(true);
				for(OfflinePlayer pl : p.getScoreboard().getPlayerTeam(p).getPlayers()) {
					if(pl.isOnline()) {
						Player pla = pl.getPlayer();
						pla.sendMessage("§a§lTEAM §7» §f" + p.getName() + ": " + e.getMessage().replaceFirst("@", ""));
					}
				}
			}
			e.setFormat(p.getScoreboard().getPlayerTeam(p).getPrefix() + p.getScoreboard().getPlayerTeam(p).getColor()
					+ p.getName() + ChatColor.WHITE + ": " + e.getMessage());
		}else {
			e.setFormat(ChatColor.GRAY + p.getName() + ChatColor.WHITE + ": " + e.getMessage());
		}
	}
	
}
