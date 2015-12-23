package co.honobono.hncorefix.command;

import org.bukkit.command.CommandSender;

import co.honobono.hncorefix.annotation.AddCommand;
import co.honobono.hncorefix.util.japanese.IMEConverter;
import co.honobono.hncorefix.util.japanese.KanaConverter;

public class Test {

	@AddCommand(command = {"test", "t"}, description = "This is Test Command.", permission = "hncorefix.test")
	public boolean c(CommandSender sender, String[] args) {
		sender.sendMessage(IMEConverter.convByGoogleIME(KanaConverter.conv(args[1])));
		return true;
	}

}
