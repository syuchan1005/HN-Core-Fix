package co.honobono.hncorefix.command;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;

import co.honobono.hncorefix.annotation.AddCommand;
import co.honobono.hncorefix.annotation.AddListener;
import co.honobono.hncorefix.enums.CommandType;

@AddListener
public class Test {
	Set<Player> p = new HashSet<>();

	@AddCommand(command = "test", alias = { "t" }, description = "This is Test Command.", permission = "hncorefix.test")
	public boolean c(CommandSender sender, String[] args) {
		return true;
	}

	@EventHandler
	public void onEvent(PlayerDeathEvent event) {
		if (p.contains(event.getEntity())) {
			event.setKeepInventory(true);
		}
	}

	@AddCommand(command = "abcde", description = "test command", permission = "hncorefix.test", usage = "/test", type = CommandType.DIRECT)
	public boolean d(CommandSender sender, String... args) {
		sender.sendMessage("§4Test");
		return true;
	}
}
