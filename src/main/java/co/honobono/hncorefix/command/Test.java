package co.honobono.hncorefix.command;

import org.bukkit.command.CommandSender;

import co.honobono.hncorefix.annotation.AddCommand;
import co.honobono.hncorefix.runnable.EnderDragonMove;

public class Test {

	@AddCommand(command = "test", alias = { "t" }, description = "This is Test Command.", permission = "hncorefix.test")
	public boolean c(CommandSender sender, String[] args) {
		EnderDragonMove.stop = !EnderDragonMove.stop;
		return true;
	}

}
