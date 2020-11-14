package me.syes.SMPCore.Utils;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class AntiGriefUtils {

	public HashMap<UUID, HashMap<Date, HashMap<Location, Material>>> savedCoords;

	public AntiGriefUtils() {
		savedCoords = new HashMap<UUID, HashMap<Date, HashMap<Location, Material>>>();
	}
	
	public HashMap<UUID, HashMap<Date, HashMap<Location, Material>>> getSavedCoords() {
		return savedCoords;
	}
	
	public void addBlock(Player p, Location loc, Material mat) {
		loc = loc.clone();
		if(savedCoords.containsKey(p.getUniqueId())) {
			HashMap<Location, Material> locMat = new HashMap<Location, Material>();
			locMat.put(loc, mat);
			savedCoords.get(p.getUniqueId()).put(new Date(), locMat);
		}else {
			HashMap<Location, Material> locMat = new HashMap<Location, Material>();
			locMat.put(loc, mat);
			
			HashMap<Date, HashMap<Location, Material>> dateLocMat = new HashMap<Date, HashMap<Location, Material>>();
			dateLocMat.put(new Date(), locMat);
			savedCoords.put(p.getUniqueId(), dateLocMat);
		}
	}
	
}
