package me.syes.RJL;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SavedCoordsCommand implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(!CoordsCommand.savedCoords.containsKey(p.getScoreboard().getPlayerTeam(p))) {
				p.sendMessage("§cYour team hasn't saved any coords yet! Use /sc <name> to save coordinates.");
				return false;
			}
			
			p.sendMessage("");
			
			if(p.getScoreboard().getPlayerTeam(p) != null)
				p.sendMessage("§6§lTEAM §7» §6Saved Coords:");
			else
				return false;
			
			if(CoordsCommand.savedCoords.containsKey(p.getScoreboard().getPlayerTeam(p))) {
				int count = 0;
				for(Location loc : CoordsCommand.savedCoords.get(p.getScoreboard().getPlayerTeam(p)).keySet()) {
					count++;
					if(p.getScoreboard().getPlayerTeam(p) != null)
						p.sendMessage("  §7" + count + ") §f" + CoordsCommand.savedCoords.get(p.getScoreboard().getPlayerTeam(p)).get(loc) + ": §7X:§f" + loc.getBlockX()
								+ " §7Y:§f" + loc.getBlockY() + " §7Z:§f" + loc.getBlockZ()
								+ " in the " + CoordsCommand.getWorldName(loc.getWorld().getName()));
				}
			}
		}
		return false;
	}

}
