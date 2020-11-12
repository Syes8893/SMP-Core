package me.syes.SMPCore.Handlers;

import java.util.Date;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinHandler implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Date now = new Date();
		Player p = e.getPlayer();
		if(p.getScoreboard().getPlayerTeam(p) != null)
			e.setJoinMessage("§a[+] " + p.getScoreboard().getPlayerTeam(p).getPrefix() + p.getScoreboard().getPlayerTeam(p).getColor()
				+ p.getName() + ChatColor.WHITE + " has joined the game");
		else
			e.setJoinMessage(ChatColor.GRAY + p.getName() + ChatColor.WHITE + " has joined the game");
		if(now.getMonth() != 11)
			return;
		p.sendMessage("§7§m----------------------------------------");
		p.sendMessage("§f§l>> §eVandaag " + now.getDate() * 10 + " Pushups!");
		p.sendMessage("§7§m----------------------------------------");
	}
	
}
