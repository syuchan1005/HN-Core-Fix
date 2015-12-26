package co.honobono.hncorefix;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import co.honobono.hncorefix.constructor.CommandManager;
import co.honobono.hncorefix.util.Config;

public class HNCoreFix extends JavaPlugin {
	private static Plugin instance;
	private static Logger log;
	private static FileConfiguration config;
	private static CommandManager manager = new CommandManager();

	@Override
	public void onEnable() {
		instance = this;
		log = this.getLogger();
		PluginCommand pc = this.getCommand("hn");
		pc.setExecutor(this);
		pc.setTabCompleter(manager);
		this.saveDefaultConfig();
		try {
			config = Config.getConfig(new File(this.getDataFolder(), "config.yml"));
			Load.Register(this, manager);
		} catch (Exception e) { e.printStackTrace(); }
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (args.length < 1 || (args.length >= 1 && args[0].equalsIgnoreCase("help"))) {
			if(sender.hasPermission("hn.help")) manager.sendHalp(sender); else sender.sendMessage("You don't have Permission");
			return true;
		}
		return manager.run(sender, args);
	}

	public static Plugin getInstance() {
		return instance;
	}

	public static FileConfiguration getConfigFile() {
		return config;
	}

	public static Logger getLog() {
		return log;
	}
}