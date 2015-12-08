package co.honobono.hncorefix;

import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import co.honobono.hncorefix.util.ListenerUtil;

public class HNCoreFix extends JavaPlugin {
	private static Plugin instance;

	public static Plugin getInstance() {
		return instance;
	}

	@Override
	public void onEnable() {
		instance = this;
		this.getCommand("hn").setExecutor(this);
		try {
			ListenerUtil.RegListeners(this);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (args.length == 0 && sender.hasPermission("hn.help")) { // help
			sender.sendMessage("Help");
			return true;
		}
		return false;
	}
}