package co.honobono.hncorefix.listener;

import org.bukkit.PortalType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCreatePortalEvent;

import co.honobono.hncorefix.annotation.AddListener;

@AddListener
public class NetherPortal implements Listener {

	@EventHandler
	public static void onCreatePortal(EntityCreatePortalEvent event) {
		if (event.getPortalType() == PortalType.NETHER) {
			event.setCancelled(!(event.getEntity() instanceof Player && ((Player) event.getEntity()).isOp()));
		}
	}
}
