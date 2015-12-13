package co.honobono.hncorefix;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import co.honobono.hncorefix.constructor.CommandManager;

public class HNCoreFix extends JavaPlugin {
	private static Plugin instance;
	private static CommandManager manager = new CommandManager();

	@Override
	public void onEnable() {
		instance = this;
		PluginCommand pc = this.getCommand("hn");
		pc.setExecutor(this);
		pc.setTabCompleter(manager);
		try {
			Load.Register(this, manager);
		} catch (Exception e) { e.printStackTrace(); }
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (args.length < 1) { // help
			if(sender.hasPermission("hn.help")) manager.sendHalp(sender); else sender.sendMessage("You don't have Permission");
			return true;
		}
		return manager.run(sender, args);
	}

	public static Plugin getInstance() {
		return instance;
	}
}