package co.honobono.hncorefix.command;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.honobono.hncorefix.annotation.AddCommand;

public class Home {

	@AddCommand(command = "home", description = "Home", permission = "hncorefix.home", usage = "/home")
	public boolean c(CommandSender sender, String... args) {
		if(!(sender instanceof Player)) return true;
		Location loc = co.honobono.hncorefix.listener.Home.getHome((Player)sender);
		if (loc == null) {
			sender.sendMessage("Homeが見つかりません");
		} else {
			((Player)sender).teleport(loc);
		}
		return true;
	}
}
