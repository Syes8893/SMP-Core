package me.syes.SMPCore.Utils;

public class WorldUtils {

	public String getWorldName(String worldName) {
		if(worldName.equals("world"))
			return "§aOverworld§f";
		else if(worldName.equals("world_nether"))
			return "§cNether§f";
		else if(worldName.equals("world_the_end"))
			return "§eEnd§f";
		return "§4ERROR";
	}
	
}
