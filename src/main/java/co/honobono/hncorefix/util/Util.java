package co.honobono.hncorefix.util;

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Util {

	public static boolean hasString(String cmd, String[] list) {
		for(String l : list) {
			if(cmd.equalsIgnoreCase(l)) return true;
		}
		return false;
	}

	public static String getLocale(Player player) {
		return ((CraftPlayer) player).getHandle().locale;
	}

	public static String color(String text, Player player) {
		if (player != null) text = text.replaceAll("<player>", player.getName());
		return ChatColor.translateAlternateColorCodes('&', text);
	}
}
