package co.honobono.hncorefix.listener;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import co.honobono.hncorefix.HNCoreFix;
import co.honobono.hncorefix.annotation.AddListener;
import co.honobono.hncorefix.constructor.SQLite;
import co.honobono.hncorefix.event.PlayerRightClickEvent;

@AddListener
public class Home implements Listener {
	private static SQLite sql = HNCoreFix.getSQLite();

	@EventHandler
	public void inHome(PlayerRightClickEvent event) {
		if (event.getClickedBlock().getType() != Material.BED_BLOCK) return;
		Location bed = event.getClickedBlock().getLocation();
		try {
			sql.checkPut("Home", "PlayerUUID", event.getPlayer().getUniqueId().toString(), event.getPlayer().getName(),
					SQLite.getTimeStamp(), bed.getWorld().getName(), bed.getBlockX(), bed.getBlockY(), bed.getBlockZ());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		event.getPlayer().sendMessage("BEDを設定しました");
	}

	@EventHandler
	public void onRespawn(PlayerRespawnEvent event) {
		Location loc = getHome(event.getPlayer());
		if (loc != null) {
			event.setRespawnLocation(loc);
		}
	}

	public static Location getHome(Player player) {
		String UUID = player.getUniqueId().toString();
		try {
			ResultSet result = sql.get("Home", "PlayerUUID", UUID, "Home-World", "Home-X", "Home-Y", "Home-Z");
			Location loc = new Location(Bukkit.getWorld(result.getString(1)), result.getInt(2), result.getInt(3),
					result.getInt(4));
			return loc;
		} catch (SQLException e) {
			return null;
		}
	}
}
