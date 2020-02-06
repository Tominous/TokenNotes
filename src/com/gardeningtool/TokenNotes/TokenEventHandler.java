package com.gardeningtool.TokenNotes;

import java.text.NumberFormat;
import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import net.md_5.bungee.api.ChatColor;

public class TokenEventHandler implements Listener {

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e)  {
		Player p = e.getPlayer();
		if (p.getItemInHand().getItemMeta() != null) {
			if (p.getItemInHand().getItemMeta().getDisplayName() != null) {
				if (p.getItemInHand().getItemMeta().getLore() != null) {
					if (p.getItemInHand().getItemMeta().getDisplayName().contains(ChatColor.translateAlternateColorCodes('&', ConfigValues.ITEM_NAME))) {
						if (p.getItemInHand().getType().equals(ConfigValues.ITEM_TYPE)) {
							ArrayList<String> lore = (ArrayList<String>) p.getItemInHand().getItemMeta().getLore();
							ArrayList<String> configlore = ConfigValues.ITEM_LORE;
							int loc = 0;
							for (int i = 0; i < configlore.size(); i++) {
								if (configlore.get(i).contains("[AMOUNT]") || configlore.get(i).contains("[AMOUNT_COMMAS]")) {
									loc = i;
									break;
								}
							}
							String s = lore.get(loc);
							StringBuilder builder = new StringBuilder();
							for (int i = 0; i < s.length(); i++){
							    char c = s.charAt(i);
							    if (isInt(c)) {
							    	builder.append(c);
							    }
							}
							Integer amount = Integer.parseInt(builder.toString());
							EconUtils.addTokens(p, (double) amount);
							if (p.getItemInHand().getAmount() == 1) {
								p.setItemInHand(null);
							} else {
								p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
							}
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', formattedMessage(amount)));
							return;
						}
					}
				}
			}
		}
	}
	
	private String formattedMessage(Integer amount) {
		String s = ConfigValues.ADDED_BALANCE_MSG;
		if (s.contains("[AMOUNT]")) {
			s = s.replace("[AMOUNT]", amount.toString());
		}
		if (s.contains("[AMOUNT_COMMAS]")) {
			s = s.replace("[AMOUNT_COMMAS]", NumberFormat.getNumberInstance().format(amount).toString());
		}
		if (amount == 1) {
			s = s.replace("tokens", "token");
		}
		return s;
	}
	
	private boolean isInt(char s) {
		try {
			Integer.parseInt(String.valueOf(s));
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
