package me.syes.SMPCore.Commands;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import me.syes.SMPCore.Main;

public class SavedCoordsCommand implements CommandExecutor {

	private Main main;
	
	private HashMap<Team, HashMap<Location, String>> savedCoords;
	
	public SavedCoordsCommand(Main main) {
		this.main = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			this.savedCoords = main.getCoordsUtils().savedCoords;
			if(!savedCoords.containsKey(p.getScoreboard().getPlayerTeam(p))) {
				p.sendMessage("§cYour team hasn't saved any coords yet! Use /sc <name> to save coordinates.");
				return false;
			}
			
			p.sendMessage("");
			
			if(p.getScoreboard().getPlayerTeam(p) != null)
				p.sendMessage("§6§lTEAM §7» §6Saved Coords:");
			else
				return false;
			
			if(savedCoords.containsKey(p.getScoreboard().getPlayerTeam(p))) {
				int count = 0;
				for(Location loc : savedCoords.get(p.getScoreboard().getPlayerTeam(p)).keySet()) {
					count++;
					if(p.getScoreboard().getPlayerTeam(p) != null)
						p.sendMessage("  §7" + count + ") §f" + savedCoords.get(p.getScoreboard().getPlayerTeam(p)).get(loc) + ": §7X:§f" + loc.getBlockX()
								+ " §7Y:§f" + loc.getBlockY() + " §7Z:§f" + loc.getBlockZ()
								+ " in the " + main.getWorldUtils().getWorldName(loc.getWorld().getName()));
				}
			}
		}
		return false;
	}

}
