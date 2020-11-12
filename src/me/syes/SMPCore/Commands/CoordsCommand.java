package me.syes.SMPCore.Commands;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import me.syes.SMPCore.Main;

public class CoordsCommand implements CommandExecutor {

	private Main main;
	
	private HashMap<Team, HashMap<Location, String>> savedCoords;
	
	public CoordsCommand(Main main) {
		this.main = main;
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			String name = "";
			if(args.length > 0)
				for(String arg : args)
					if(name.equals(""))
						name = arg;
					else
						name = name + " " + arg;
			else
				name = "Undefined";
			if(p.getScoreboard().getPlayerTeam(p) != null) {
				for(OfflinePlayer pl : p.getScoreboard().getPlayerTeam(p).getPlayers()) {
					if(pl.isOnline()) {
						Player pla = pl.getPlayer();
						pla.sendMessage("§a§lTEAM §7» §f" + name + ": §7X:§f" + p.getLocation().getBlockX()
								+ " §7Y:§f" + p.getLocation().getBlockY() + " §7Z:§f" + p.getLocation().getBlockZ()
								+ " in the " + main.getWorldUtils().getWorldName(p.getWorld().getName()) + " §7(by " + p.getName() + ")");
					}
				}
				if(!name.equals("Undefined")) {
					this.savedCoords = main.getCoordsUtils().savedCoords;
					if(savedCoords.get(p.getScoreboard().getPlayerTeam(p)) == null) {
						HashMap<Location, String> locs = new HashMap<Location, String>();
						locs.put(p.getLocation(), name);
						savedCoords.put(p.getScoreboard().getPlayerTeam(p), locs);
					}else {
						savedCoords.get(p.getScoreboard().getPlayerTeam(p)).put(p.getLocation(), name);
					}
				}
			}else {
				p.sendMessage("§b§lSOLO §7» §f" + name + ": §7X:§f" + p.getLocation().getBlockX()
						+ " §7Y:§f" + p.getLocation().getBlockY() + " §7Z:§f" + p.getLocation().getBlockZ()
						+ " in the " + main.getWorldUtils().getWorldName(p.getWorld().getName()) + " §7(by " + p.getName() + ")");
			}
		}
		return true;
	}

}
