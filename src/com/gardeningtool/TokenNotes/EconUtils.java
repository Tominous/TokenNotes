package com.gardeningtool.TokenNotes;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.vk2gpz.tokenenchant.TokenEnchant;

public class EconUtils {
	
    public static TokenEnchant getTokenEnchant() {
        Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("TokenEnchant");
        if ((plugin == null) || (!(plugin instanceof TokenEnchant))) {
            return null;
           }
        return (TokenEnchant) plugin;
    }
    
    public static void addTokens(Player p, Double d) {
    	getTokenEnchant().addTokens(p, d);
    }
    
    public static void removeTokens(Player p, Double d) {
    	getTokenEnchant().removeTokens(p, d);
    }
    
    public static double getTokens(Player p) {
    	return getTokenEnchant().getTokens(p);
    }
}
