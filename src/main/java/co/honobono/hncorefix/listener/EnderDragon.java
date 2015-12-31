package co.honobono.hncorefix.listener;

import java.util.Random;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.scheduler.BukkitRunnable;

import co.honobono.hncorefix.HNCoreFix;
import co.honobono.hncorefix.annotation.AddListener;

@AddListener
public class EnderDragon implements Listener {
	private static Random ran = new Random();

	@EventHandler
	public void Fireball(EntityTargetEvent event) {
		if(!(event.getEntity().getType() == EntityType.ENDER_DRAGON)) return;
		if (!(event.getTarget() instanceof Player)) return;
		Player player = (Player) event.getTarget();
		Location loc = player.getLocation();
		for (float i = 0; i < 360; i = (float) (i + 0.5)) {
			loc.getWorld().playEffect(new Location(loc.getWorld(), loc.getX() + Math.sin(Math.toRadians(i)) * 2,
					loc.getY(), loc.getZ() + Math.cos(Math.toRadians(i)) * 2), Effect.PORTAL, 10);
		}
		player.playSound(loc, Sound.ENDERDRAGON_GROWL, 10, 10);
		new BukkitRunnable() {
			@Override
			public void run() {
				((TNTPrimed) loc.getWorld().spawn(loc, TNTPrimed.class)).setFuseTicks(1);
				loc.subtract(3, 1, 3);
				new BukkitRunnable() {
					@Override
					public void run() {
						for (int x = 0; x <= 6; x++) {
							for (int z = 0; z <= 6; z++) {
								for (int y = 0; y <= 1; y++) {
									Block b = new Location(loc.getWorld(), loc.getX() + x, loc.getY() + y,
											loc.getZ() + z).getBlock();
									if (b.getType() == Material.AIR && ran.nextInt(2) == 1)
										b.setType(Material.FIRE);
								}
							}
						}
					}
				}.runTaskLater(HNCoreFix.getInstance(), 3L);
			}
		}.runTaskLater(HNCoreFix.getInstance(), 70L);
	}
}
