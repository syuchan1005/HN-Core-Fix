package co.honobono.hncorefix.command;

import org.bukkit.command.CommandSender;

import co.honobono.hncorefix.HNCoreFix;
import co.honobono.hncorefix.annotation.AddCommand;

public class Help {

	@AddCommand(command = { "help", "" }, description = "This is Help Command.", permission = "hncorefix.help")
	public boolean c(CommandSender sender, String[] args) {
		HNCoreFix.getManager().sendHalp(sender);
		return true;
	}

}
