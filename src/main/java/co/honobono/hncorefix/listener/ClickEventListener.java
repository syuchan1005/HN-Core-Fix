package co.honobono.hncorefix.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import co.honobono.hncorefix.annotation.AddListener;
import co.honobono.hncorefix.event.PlayerLeftClickEvent;
import co.honobono.hncorefix.event.PlayerRightClickEvent;

@AddListener
public class ClickEventListener implements Listener {

	@EventHandler
	public void CallClickEvent(PlayerInteractEvent event) {
		Event call;
		switch (event.getAction()) {
		case LEFT_CLICK_AIR:
		case LEFT_CLICK_BLOCK:
			call = new PlayerLeftClickEvent(event.getPlayer(), event.getAction(), event.getItem(), event.getClickedBlock(), event.getBlockFace());
		case RIGHT_CLICK_AIR:
		case RIGHT_CLICK_BLOCK:
			call = new PlayerRightClickEvent(event.getPlayer(), event.getAction(), event.getItem(), event.getClickedBlock(), event.getBlockFace());
		// case PHYSICAL:
		default:
			call = event;
		}
		Bukkit.getServer().getPluginManager().callEvent(call);
	}

}