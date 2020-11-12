package me.syes.SMPCore.Utils;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.scoreboard.Team;

public class CoordsUtils {
	
	public HashMap<Team, HashMap<Location, String>> savedCoords;
	
	public CoordsUtils() {
		savedCoords = new HashMap<Team, HashMap<Location, String>>();
	}

	public HashMap<Location, String> getCoords(Object key) {
		return savedCoords.get(key);
	}

	public HashMap<Location, String> putCoords(Team key, HashMap<Location, String> value) {
		return savedCoords.put(key, value);
	}
	
}
