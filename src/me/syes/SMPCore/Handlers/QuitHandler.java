package me.syes.SMPCore.Handlers;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitHandler implements Listener {

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if(p.getScoreboard().getPlayerTeam(p) != null)
			e.setQuitMessage("§c[-] " + p.getScoreboard().getPlayerTeam(p).getPrefix() + p.getScoreboard().getPlayerTeam(p).getColor()
				+ p.getName() + ChatColor.WHITE + " has left the game");
		else
			e.setQuitMessage(ChatColor.GRAY + p.getName() + ChatColor.WHITE + " has left the game");
	}
	
}
