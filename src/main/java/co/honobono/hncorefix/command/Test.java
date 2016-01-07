package co.honobono.hncorefix.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

import co.honobono.hncorefix.annotation.AddCommand;
import co.honobono.hncorefix.annotation.AddListener;

@AddListener
public class Test {

	@AddCommand(command = "test", alias = { "t" }, description = "This is Test Command.", permission = "hncorefix.test")
	public boolean c(CommandSender sender, String[] args) {
		Player p = (Player) sender;
		for(EnderDragon e : p.getLocation().getWorld().getEntitiesByClass(EnderDragon.class)) {
			sender.sendMessage("phase: " + (e.getMetadata("phase2").size() >= 1 ? e.getMetadata("phase2").get(0).asBoolean() : "null"));
			sender.sendMessage("Health: " + e.getHealth() + "/" + e.getMaxHealth());
		}
		return true;
	}

	@EventHandler
	public void onEvent() {

	}

}
