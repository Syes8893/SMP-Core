package me.syes.SMPCore.Handlers;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockHandler implements Listener {
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		/*if(e.getBlockPlaced().getType() == Material.TNT || e.getBlockPlaced().getType() == Material.FIRE) {
			Date now = new Date();
			getConfig().set(now.toString(), e.getPlayer().getName() + " placed "
					+ e.getBlockPlaced().getType().toString().replace("_", " ")
							+ " at " + e.getBlockPlaced().getLocation().getBlockX() + " "
							+ e.getBlockPlaced().getLocation().getBlockY() + " "
							+ e.getBlockPlaced().getLocation().getBlockZ());
			saveConfig();
		}*/
		if(e.getBlock().getType() == Material.ENDER_CHEST) {
			e.setCancelled(true);
			e.getPlayer().sendMessage("§cEnderchests are blocked.");
		}
		List<Material> blockedTypes = Arrays.asList(Material.CHEST, Material.SHULKER_BOX, Material.TRAPPED_CHEST, Material.BARREL);
		if(blockedTypes.contains(e.getBlock().getType()))
			if(Math.abs(e.getBlock().getLocation().getBlockX()) > 1000 || Math.abs(e.getBlock().getLocation().getBlockZ()) > 1000) {
				e.setCancelled(true);
				e.getPlayer().sendMessage("§cStorage blocks cannot be placed outside the 2000x2000 area.");
			}
	}
	
}
