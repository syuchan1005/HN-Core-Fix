package co.honobono.hncorefix;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import co.honobono.hncorefix.util.ListenerUtil;

public class HNCoreFix extends JavaPlugin {

	PluginManager pm = this.getServer().getPluginManager();

	@Override
	public void onEnable() {
		ListenerUtil.getListeners().forEach(listener -> pm.registerEvents(listener, this));
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (args.length == 0 && sender.hasPermission("hn.help")) { // help
			sender.sendMessage("Help");
		} else {
			switch (args[0].toUpperCase()) {
			case "TEST":
				break;
			}
		}
		return false;
	}
}