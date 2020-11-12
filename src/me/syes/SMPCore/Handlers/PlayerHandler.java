package me.syes.SMPCore.Handlers;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class PlayerHandler implements Listener {
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Player p = e.getEntity();
		String[] split = e.getDeathMessage().split(" ");
		if(p.getScoreboard().getPlayerTeam(p) != null)
			e.setDeathMessage("§4[\u2620] " + p.getScoreboard().getPlayerTeam(p).getColor() + split[0] + " "
				+ p.getScoreboard().getPlayerTeam(p).getColor() + split[1] + e.getDeathMessage().replace(split[0] + " " + split[1] + "", "§f"));
		else
			e.setDeathMessage("§4[\u2620] §7" + split[0] + "§f" + e.getDeathMessage().replace(split[0] + "", ""));
		ItemStack is = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta im = (SkullMeta) is.getItemMeta();
		im.setOwningPlayer(p);
		is.setItemMeta(im);
		p.getWorld().dropItem(p.getLocation(), is);
	}

}
