package co.honobono.hncorefix.listener;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;

import co.honobono.hncorefix.annotation.AddListener;

@AddListener
public class EnderCrystal implements Listener {
	Random ran = new Random();

	@EventHandler
	public void Explode(EntityDamageByEntityEvent event) {
		Entity ent = event.getEntity();
		if (ent.getType() == EntityType.ENDER_CRYSTAL) {
			Location loc = ent.getLocation();
			Entity tnt = loc.getWorld().spawnEntity(loc, EntityType.PRIMED_TNT);
			for (int count = 0; count <= 1; count++) {
				tnt.setVelocity(new Vector(((double) ran.nextInt(11) / 10), ((double) ran.nextInt(11) / 10), 0));
				tnt = loc.getWorld().spawnEntity(loc, EntityType.PRIMED_TNT);
				tnt.setVelocity(new Vector(-((double) ran.nextInt(11) / 10), ((double) ran.nextInt(11) / 10), 0));
				tnt = loc.getWorld().spawnEntity(loc, EntityType.PRIMED_TNT);
				tnt.setVelocity(new Vector(0, ((double) ran.nextInt(11) / 10), ((double) ran.nextInt(11) / 10)));
				tnt = loc.getWorld().spawnEntity(loc, EntityType.PRIMED_TNT);
				tnt.setVelocity(new Vector(0, ((double) ran.nextInt(11) / 10), -((double) ran.nextInt(11) / 10)));
				tnt = loc.getWorld().spawnEntity(loc, EntityType.PRIMED_TNT);
				tnt.setVelocity(new Vector(((double) ran.nextInt(11) / 10), ((double) ran.nextInt(11) / 10),
						((double) ran.nextInt(11) / 10)));
				tnt = loc.getWorld().spawnEntity(loc, EntityType.PRIMED_TNT);
				tnt.setVelocity(new Vector(-((double) ran.nextInt(11) / 10), ((double) ran.nextInt(11) / 10),
						-((double) ran.nextInt(11) / 10)));
				tnt = loc.getWorld().spawnEntity(loc, EntityType.PRIMED_TNT);
				tnt.setVelocity(new Vector(((double) ran.nextInt(11) / 10), ((double) ran.nextInt(11) / 10),
						-((double) ran.nextInt(11) / 10)));
				tnt = loc.getWorld().spawnEntity(loc, EntityType.PRIMED_TNT);
				tnt.setVelocity(new Vector(-((double) ran.nextInt(11) / 10), ((double) ran.nextInt(11) / 10),
						((double) ran.nextInt(11) / 10)));
			}
		}
	}
}
