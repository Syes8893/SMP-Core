package me.syes.SMPCore.Utils;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class AntiGriefUtils {

	public HashMap<Player, HashMap<Location, Material>> savedCoords;

	public HashMap<Player, HashMap<Location, Material>> getSavedCoords() {
		return savedCoords;
	}
	
}
