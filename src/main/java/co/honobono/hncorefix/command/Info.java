package co.honobono.hncorefix.command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import co.honobono.hncorefix.annotation.AddCommand;

public class Info {

	@AddCommand(command = { "info", "i" }, description = "This is Info Command.", permission = "hncorefix.info")
	public boolean command(CommandSender sender, String[] args) {
		Runtime run = Runtime.getRuntime();
		sender.sendMessage(ChatColor.GREEN + "======" + ChatColor.BLUE + "Info" + ChatColor.GREEN + "======");
		sender.sendMessage("Processors: " + run.availableProcessors());
		sender.sendMessage("Memory: " + (run.totalMemory() - run.freeMemory()) + "/" + run.totalMemory());
		return true;
	}
}
