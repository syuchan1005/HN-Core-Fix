package co.honobono.hncorefix.listener;

import java.lang.reflect.Method;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import co.honobono.hncorefix.HNCoreFix;
import co.honobono.hncorefix.annotation.AddListener;
import co.honobono.hncorefix.constructor.CommandBase;

@AddListener
public class Test implements Listener{

	@EventHandler
	public void sendCommand(PlayerCommandPreprocessEvent event) {
		for (Entry<CommandBase, Method> e : HNCoreFix.getManager().getMap().entrySet()) {
			Bukkit.broadcastMessage(e.getKey().toString());
		}
	}
}
