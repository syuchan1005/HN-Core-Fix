package co.honobono.hncorefix.util;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.Packet;

public class Util {

	public static boolean hasString(String cmd, List<String> list) {
		return list.stream().anyMatch(s -> s.equalsIgnoreCase(cmd));
	}

	public static String getLocale(Player player) {
		return ((CraftPlayer) player).getHandle().locale;
	}

	public static String color(String text, Player player) {
		if (player != null) text = text.replaceAll("<player>", player.getName());
		return ChatColor.translateAlternateColorCodes('&', text);
	}

	@SuppressWarnings("rawtypes")
	public static void sendPlayerPacket(Player player,Packet packet) {
		((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
	}
}
