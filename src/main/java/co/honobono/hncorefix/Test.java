package co.honobono.hncorefix;

import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import co.honobono.hncorefix.annotation.AddCommand;
import co.honobono.hncorefix.annotation.AddListener;

@AddListener
public class Test implements Listener{

	@AddCommand(command = {"test", "t"}, description = "This is Test Command.", permission = "hncorefix.test")
	public boolean c(CommandSender sender, String[] args) {
		sender.sendMessage("実行");
		return true;
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		event.getPlayer().sendMessage("実行");
	}


}
