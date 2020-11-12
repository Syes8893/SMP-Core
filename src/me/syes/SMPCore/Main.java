package me.syes.SMPCore;

import org.bukkit.plugin.java.JavaPlugin;

import me.syes.SMPCore.Commands.CoordsCommand;
import me.syes.SMPCore.Commands.SavedCoordsCommand;
import me.syes.SMPCore.Commands.ToggleChatCommand;
import me.syes.SMPCore.Handlers.BlockHandler;
import me.syes.SMPCore.Handlers.ChatHandler;
import me.syes.SMPCore.Handlers.EntityHandler;
import me.syes.SMPCore.Handlers.JoinHandler;
import me.syes.SMPCore.Handlers.PlayerHandler;
import me.syes.SMPCore.Handlers.QuitHandler;
import me.syes.SMPCore.Utils.AntiGriefUtils;
import me.syes.SMPCore.Utils.CoordsUtils;
import me.syes.SMPCore.Utils.WorldUtils;

public class Main extends JavaPlugin {

	public WorldUtils worldUtils;
	public CoordsUtils coordsUtils;
	public AntiGriefUtils antiGriefUtils;
	
	public Main() {
		worldUtils = new WorldUtils();
		coordsUtils = new CoordsUtils();
		antiGriefUtils = new AntiGriefUtils();
	}
	
	public void onEnable() {
		getConfig().options().copyDefaults();
		
		getCommand("coords").setExecutor(new CoordsCommand(this));
		getCommand("gc").setExecutor(new SavedCoordsCommand(this));
		getCommand("tc").setExecutor(new ToggleChatCommand());
		
		this.getServer().getPluginManager().registerEvents(new BlockHandler(), this);
		this.getServer().getPluginManager().registerEvents(new ChatHandler(), this);
		this.getServer().getPluginManager().registerEvents(new EntityHandler(), this);
		this.getServer().getPluginManager().registerEvents(new JoinHandler(), this);
		this.getServer().getPluginManager().registerEvents(new QuitHandler(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerHandler(), this);
	}

	public WorldUtils getWorldUtils() {
		return worldUtils;
	}

	public CoordsUtils getCoordsUtils() {
		return coordsUtils;
	}
	
}
