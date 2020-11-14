package me.syes.SMPCore.Handlers;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import me.syes.SMPCore.Main;

public class BlockHandler implements Listener {
	
	private Main main;
	
	public BlockHandler(Main main) {
		this.main = main;
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		List<Material> blockedTypes = Arrays.asList(Material.CHEST, Material.SHULKER_BOX, Material.TRAPPED_CHEST, Material.BARREL);
		
		if(e.getBlockPlaced().getType() == Material.TNT || e.getBlockPlaced().getType() == Material.FIRE
			 || e.getBlockPlaced().getType() == Material.END_CRYSTAL){
			main.getAntiGriefUtils().addBlock(e.getPlayer(), e.getBlockPlaced().getLocation(), e.getBlockPlaced().getType());
		}else if(e.getBlock().getType() == Material.ENDER_CHEST) {
			e.setCancelled(true);
			e.getPlayer().sendMessage("§cEnderchests are blocked.");
		}else if(blockedTypes.contains(e.getBlock().getType())) {
			if(Math.abs(e.getBlock().getLocation().getBlockX()) > 1000 || Math.abs(e.getBlock().getLocation().getBlockZ()) > 1000) {
				e.setCancelled(true);
				e.getPlayer().sendMessage("§cStorage blocks cannot be placed outside the 2000x2000 area.");
			}
		}else if(e.getBlock().getType() == Material.ENDER_CHEST) {
			e.setCancelled(true);
			e.getPlayer().sendMessage("§cEnderchests are blocked.");
		}else if(e.getBlock().getType().toString().contains("BED") && e.getPlayer().getWorld().getName().equals("world_the_end")) {
			e.setCancelled(true);
			e.getPlayer().sendMessage("§cBeds are blocked in the end.");
		}
	}
	
}
