package co.honobono.hncorefix.util;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.Packet;

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

	@SuppressWarnings("rawtypes")
	public static void sendPlayerPacket(Player player,Packet packet) {
		((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
	}

	public static void normalParticle(Player player , EnumParticle particle , Location loc , int num) {
		((CraftWorld) player.getWorld()).getHandle().a(particle, loc.getX(), loc.getY()+2, loc.getZ(), num, 0.32D, 0.32D, 0.32D, 0);
	}
}
