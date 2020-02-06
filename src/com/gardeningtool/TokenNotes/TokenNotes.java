package com.gardeningtool.TokenNotes;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class TokenNotes extends JavaPlugin {

	public void onEnable() {
		this.saveDefaultConfig();
		new ConfigManager(this.getConfig());
		getCommand("tokenwithdraw").setExecutor(new TokenNotesCommand());
		registerEvents(new TokenEventHandler());
	}
	
	public void onDisable() {
		
	}
	
	public void registerEvents(Listener... listeners) {
		Arrays.asList(listeners).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, (Plugin) this));
	}
}
