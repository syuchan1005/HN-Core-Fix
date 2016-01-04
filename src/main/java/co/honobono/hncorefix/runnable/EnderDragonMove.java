package co.honobono.hncorefix.runnable;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.scheduler.BukkitRunnable;

public class EnderDragonMove extends BukkitRunnable {
	public static World world = Bukkit.getWorld("end");
	public static boolean stop = false;
	public Location last;

	@Override
	public void run() {
		if(world == null) return;
		for (Entity e : world.getEntities()) {
			if (e.getType() == EntityType.ENDER_DRAGON) {
				// 地形破壊
				Location loc = e.getLocation();
				loc.getBlock().setType(Material.OBSIDIAN);
				int X = loc.getBlockX();
				int Y = loc.getBlockY() + 3;
				int Z = loc.getBlockZ();
				int radius = 6;
				int radiusSquared = radius * radius;
				for (int x = X - radius; x <= X + radius; x++) {
					for (int y = Y - radius + 1; y <= Y + radius; y++) {
						for (int z = Z - radius; z <= Z + radius; z++) {
							if ((X - x) * (X - x) + (Y - y) * (Y - y) + (Z - z) * (Z - z) <= radiusSquared) {
								new Location(world, x, y, z).getBlock().setType(Material.AIR);
							}
						}
					}
				}
				// 最大体力増加
				Damageable d = (Damageable)e;
				if(d.getMaxHealth() == 200.0D) {
					d.setMaxHealth(600.0D);
					d.setHealth(600.0D);
				}
				if(stop) {
					e.teleport(this.last);
				} else {
					this.last = loc;
				}
			}
		}
	}
}