package co.honobono.hncorefix;

import java.io.IOException;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import co.honobono.hncorefix.annotation.AddCommand;
import co.honobono.hncorefix.annotation.AddListener;
import co.honobono.hncorefix.util.Language;

@AddListener
public class Test implements Listener{

	@AddCommand(command = {"test", "t"}, description = "This is Test Command.", permission = "hncorefix.test")
	public boolean c(CommandSender sender, String[] args) {
		try {
			sender.sendMessage(Language.getString("test", sender));
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
			return true;
		}
		return true;
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		event.getPlayer().sendMessage("実行");
	}


}
