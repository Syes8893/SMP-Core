package me.syes.SMPCore;

import org.bukkit.plugin.java.JavaPlugin;

import me.syes.SMPCore.Commands.BlockLogsCommand;
import me.syes.SMPCore.Commands.CoordsCommand;
import me.syes.SMPCore.Commands.SavedCoordsCommand;
import me.syes.SMPCore.Commands.TeamCommands;
import me.syes.SMPCore.Commands.ToggleChatCommand;
import me.syes.SMPCore.Handlers.BlockHandler;
import me.syes.SMPCore.Handlers.ChatHandler;
import me.syes.SMPCore.Handlers.EntityHandler;
import me.syes.SMPCore.Handlers.InventoryHandler;
import me.syes.SMPCore.Handlers.JoinHandler;
import me.syes.SMPCore.Handlers.PlayerHandler;
import me.syes.SMPCore.Handlers.QuitHandler;
import me.syes.SMPCore.Utils.AntiGriefUtils;
import me.syes.SMPCore.Utils.CoordsUtils;
import me.syes.SMPCore.Utils.MessageUtils;
import me.syes.SMPCore.Utils.WorldUtils;
import me.syes.SMPCore.managers.EndManager;

public class Main extends JavaPlugin {

	public WorldUtils worldUtils;
	public CoordsUtils coordsUtils;
	public AntiGriefUtils antiGriefUtils;
	public MessageUtils messageUtils;
	
	public Main() {
		worldUtils = new WorldUtils();
		coordsUtils = new CoordsUtils();
		antiGriefUtils = new AntiGriefUtils();
		messageUtils = new MessageUtils();
	}
	
	public void onEnable() {

		new EndManager(this);
		getCommand("coords").setExecutor(new CoordsCommand(this));
		getCommand("gc").setExecutor(new SavedCoordsCommand(this));
		getCommand("tc").setExecutor(new ToggleChatCommand());
		
		getCommand("bl").setExecutor(new BlockLogsCommand(this));

		getCommand("team").setExecutor(new TeamCommands());
		
		this.getServer().getPluginManager().registerEvents(new BlockHandler(this), this);
		this.getServer().getPluginManager().registerEvents(new ChatHandler(), this);
		this.getServer().getPluginManager().registerEvents(new EntityHandler(), this);
		this.getServer().getPluginManager().registerEvents(new JoinHandler(), this);
		this.getServer().getPluginManager().registerEvents(new QuitHandler(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerHandler(), this);
		this.getServer().getPluginManager().registerEvents(new InventoryHandler(), this);
	}

	public WorldUtils getWorldUtils() {
		return worldUtils;
	}

	public CoordsUtils getCoordsUtils() {
		return coordsUtils;
	}

	public AntiGriefUtils getAntiGriefUtils() {
		return antiGriefUtils;
	}

	public MessageUtils getMessageUtils() {
		return messageUtils;
	}
	
}
