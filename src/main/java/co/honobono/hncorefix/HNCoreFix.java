package co.honobono.hncorefix;

import java.io.File;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import co.honobono.hncorefix.constructor.CommandBase;
import co.honobono.hncorefix.constructor.CommandManager;
import co.honobono.hncorefix.constructor.SQLite;
import co.honobono.hncorefix.enums.SQLType;
import co.honobono.hncorefix.runnable.EnderDragonMove;
import co.honobono.hncorefix.runnable.WitherMove;
import co.honobono.hncorefix.util.Config;
import ru.BeYkeRYkt.LightAPI.LightAPI;
import ru.BeYkeRYkt.LightAPI.LightRegistry;

public class HNCoreFix extends JavaPlugin {
	private static Plugin instance;
	private static Logger log;
	private static FileConfiguration config;
	private static CommandManager manager = new CommandManager();
	private static LightRegistry light;
	private static SQLite SQL;
	private static LinkedHashMap<String, SQLType[]> map = new LinkedHashMap<>();

	{
		map.put("PlayerUUID", SQLType.toArray(SQLType.TEXT, SQLType.NOTNULL, SQLType.UNIQUE));
		map.put("PlayerName", SQLType.toArray(SQLType.TEXT, SQLType.NOTNULL));
		map.put("Time", SQLType.toArray(SQLType.TIMESTAMP));
		map.put("Home-World", SQLType.toArray(SQLType.TEXT));
		map.put("Home-X", SQLType.toArray(SQLType.INTEGER));
		map.put("Home-Y", SQLType.toArray(SQLType.INTEGER));
		map.put("Home-Z", SQLType.toArray(SQLType.INTEGER));
	}

	@Override
	public void onEnable() {
		instance = this;
		log = this.getLogger();
		light = LightAPI.getRegistry(this);
		PluginCommand pc = this.getCommand("hn");
		pc.setExecutor(this);
		pc.setTabCompleter(manager);
		this.saveDefaultConfig();
		try {
			config = Config.getConfig(new File(this.getDataFolder(), "config.yml"));
			Load.Register(this, manager, true);
			SQL = new SQLite(new File(this.getDataFolder(), "Setting.db"));
			SQL.create("Home", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (Entry<CommandBase, Method> entry : manager.getDirectMap().entrySet()) {
			((CraftServer) getServer()).getCommandMap().register("HN-Core-Fix", entry.getKey());
		}
		new EnderDragonMove().runTaskTimer(this, 0, 5);
		new WitherMove().runTaskTimer(this, 0, 5);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		return manager.run(sender, cmd.getName(), args);
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

	public static CommandManager getManager() {
		return manager;
	}

	public static LightRegistry getLight() {
		return light;
	}

	public static SQLite getSQLite() {
		if (SQL == null) {
			try {
				SQL = new SQLite(new File(instance.getDataFolder(), "Setting.db"));
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		return SQL;
	}
}