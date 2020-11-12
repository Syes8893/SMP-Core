package me.syes.SMPCore.Handlers;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;

public class EntityHandler implements Listener {

	@EventHandler
	public void onMobSpawn(EntitySpawnEvent e) {
		if(e.getEntityType() == EntityType.PHANTOM)
			e.setCancelled(true);
	}
	
	@EventHandler
	public void onMobDeath(EntityDeathEvent e) {
		if(e.getEntityType() == EntityType.SHEEP)
			if(new Random().nextInt(4) == 1)
				e.getDrops().add(new ItemStack(Material.PHANTOM_MEMBRANE, new Random().nextInt(2) + 1));
	}
	
}
