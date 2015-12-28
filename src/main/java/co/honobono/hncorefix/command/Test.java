package co.honobono.hncorefix.command;

import org.bukkit.command.CommandSender;

import co.honobono.hncorefix.annotation.AddCommand;

public class Test {

	@AddCommand(command = { "test", "t" }, description = "This is Test Command.", permission = "hncorefix.test")
	public boolean c(CommandSender sender, String[] args) {
		return true;
	}

}
