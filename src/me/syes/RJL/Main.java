package me.syes.RJL;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{

	public void onEnable() {
		getConfig().options().copyDefaults();
		getCommand("coords").setExecutor(new CoordsCommand());
		getCommand("gc").setExecutor(new SavedCoordsCommand());
		getCommand("tc").setExecutor(new ToggleChatCommand());
		this.getServer().getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		if(p.getScoreboard().getPlayerTeam(p) != null) {
			String[] split = e.getMessage().split("");
			if(split[0].equalsIgnoreCase("@") || ToggleChatCommand.isInTeamChat(p)) {
				e.setCancelled(true);
				for(OfflinePlayer pl : p.getScoreboard().getPlayerTeam(p).getPlayers()) {
					if(pl.isOnline()) {
						Player pla = pl.getPlayer();
						pla.sendMessage("§a§lTEAM §7» §f" + p.getName() + ": " + e.getMessage().replaceFirst("@", ""));
					}
				}
			}
			e.setFormat(p.getScoreboard().getPlayerTeam(p).getPrefix() + p.getScoreboard().getPlayerTeam(p).getColor()
					+ p.getName() + ChatColor.WHITE + ": " + e.getMessage());
		}else {
			e.setFormat(ChatColor.GRAY + p.getName() + ChatColor.WHITE + ": " + e.getMessage());
		}
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Date now = new Date();
		Player p = e.getPlayer();
		if(p.getScoreboard().getPlayerTeam(p) != null)
			e.setJoinMessage("§a[+] " + p.getScoreboard().getPlayerTeam(p).getPrefix() + p.getScoreboard().getPlayerTeam(p).getColor()
				+ p.getName() + ChatColor.WHITE + " has joined the game");
		else
			e.setJoinMessage(ChatColor.GRAY + p.getName() + ChatColor.WHITE + " has joined the game");
		if(now.getMonth() != 11)
			return;
		p.sendMessage("§7§m----------------------------------------");
		p.sendMessage("§f§l>> §eVandaag " + now.getDate() * 10 + " Pushups!");
		p.sendMessage("§7§m----------------------------------------");
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if(p.getScoreboard().getPlayerTeam(p) != null)
			e.setQuitMessage("§c[-] " + p.getScoreboard().getPlayerTeam(p).getPrefix() + p.getScoreboard().getPlayerTeam(p).getColor()
				+ p.getName() + ChatColor.WHITE + " has left the game");
		else
			e.setQuitMessage(ChatColor.GRAY + p.getName() + ChatColor.WHITE + " has left the game");
	}
	
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
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		if(e.getBlockPlaced().getType() == Material.TNT || e.getBlockPlaced().getType() == Material.FIRE) {
			Date now = new Date();
			getConfig().set(now.toString(), e.getPlayer().getName() + " placed "
					+ e.getBlockPlaced().getType().toString().replace("_", " ")
							+ " at " + e.getBlockPlaced().getLocation().getBlockX() + " "
							+ e.getBlockPlaced().getLocation().getBlockY() + " "
							+ e.getBlockPlaced().getLocation().getBlockZ());
			saveConfig();
		}
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
	
	@EventHandler
	public void onDragonDamage(EntityDamageEvent e) {
		if(e.getEntityType() == EntityType.ENDER_DRAGON) {
			e.setDamage(e.getFinalDamage() * 0.1);
		}
	}
	
}
