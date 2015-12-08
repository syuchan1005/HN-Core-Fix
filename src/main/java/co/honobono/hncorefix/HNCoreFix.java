package co.honobono.hncorefix;

import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class HNCoreFix extends JavaPlugin {
	private static Plugin instance;
	private static CommandManager manager = new CommandManager();
	public static Plugin getInstance() {
		return instance;
	}

	@Override
	public void onEnable() {
		instance = this;
		this.getCommand("hn").setExecutor(this);
		try {
			Load.Register(this, manager);
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
		return manager.run(sender, args);
	}
}