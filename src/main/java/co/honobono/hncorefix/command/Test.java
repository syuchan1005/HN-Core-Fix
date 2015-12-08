package co.honobono.hncorefix.command;

import org.bukkit.command.CommandSender;

import co.honobono.hncorefix.annotation.AddCommand;

@AddCommand(command = "test", description = "This command is test.", permission = "hncorefix.test")
public class Test {
	public static boolean onCommand(CommandSender sender, String[] args) {
		sender.sendMessage("実行！");
		return true;
	}
}
