package co.honobono.hncorefix.listener;

import org.bukkit.World.Environment;
import org.bukkit.entity.Endermite;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import co.honobono.hncorefix.annotation.AddListener;

@AddListener
public class EnderMan implements Listener{

	@EventHandler
	public void endermite(EntityDamageByEntityEvent event) {
		Entity enderman = event.getEntity();
		if(!(enderman.getLocation().getWorld().getEnvironment() == Environment.THE_END)) return;
		if(!(event.getDamager() instanceof Player)) return;
		if(enderman.getType() != EntityType.ENDERMAN) return;
		for (int i = 0; i <= 3 ; i++) enderman.getLocation().getWorld().spawn(enderman.getLocation(), Endermite.class);
	}
}
