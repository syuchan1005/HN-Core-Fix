package co.honobono.hncorefix.util;

import java.io.File;
import java.io.IOException;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import co.honobono.hncorefix.HNCoreFix;

public class Language {

	private static Plugin instance = HNCoreFix.getInstance();
	private static FileConfiguration config = HNCoreFix.getConfigFile();
	private static File folder = new File(instance.getDataFolder(), "Languages");
	private static String Lang = config.getString("DefaultLanguages");

	public static String getString(String path, CommandSender sender) throws IOException, InvalidConfigurationException {
		String lang = Lang;
		if(sender instanceof Player) lang = Util.getLocale((Player)sender);
		return getString(path, lang);
	}

	private static String getString(String path, String lang) throws IOException, InvalidConfigurationException {
		FileConfiguration langconfig;
		File dir = new File(folder, lang + ".lang");
		if(dir.exists()) langconfig = Config.getConfig(dir);
		else langconfig = Config.getConfig(new File(folder, Lang + ".lang"));
		return langconfig.getString(path);
	}
}
