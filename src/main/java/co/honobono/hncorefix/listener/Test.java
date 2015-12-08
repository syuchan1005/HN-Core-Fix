package co.honobono.hncorefix.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import co.honobono.hncorefix.annotation.AddListener;

@AddListener
public class Test implements Listener{

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		event.getPlayer().sendMessage("Run!!");
	}
}
