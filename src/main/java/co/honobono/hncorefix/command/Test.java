package co.honobono.hncorefix.command;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;

import co.honobono.hncorefix.annotation.AddCommand;
import co.honobono.hncorefix.annotation.AddListener;

@AddListener
public class Test {

	@AddCommand(command = "test", alias = { "t" }, description = "This is Test Command.", permission = "hncorefix.test")
	public boolean c(CommandSender sender, String[] args) {
		for(World w : Bukkit.getWorlds()) {
			sender.sendMessage(w.getName());
		}
		return true;
	}

	@EventHandler
	public void onEvent() {

	}

}
