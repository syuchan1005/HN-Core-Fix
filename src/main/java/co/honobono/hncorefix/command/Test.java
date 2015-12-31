package co.honobono.hncorefix.command;

import org.bukkit.command.CommandSender;

import co.honobono.hncorefix.annotation.AddCommand;
import co.honobono.hncorefix.autorun.ResourcesGen;

public class Test {

	@AddCommand(command = { "test", "t" }, description = "This is Test Command.", permission = "hncorefix.test")
	public boolean c(CommandSender sender, String[] args) {
		new ResourcesGen().run();
		return true;
	}

}
