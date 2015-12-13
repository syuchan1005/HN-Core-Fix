package co.honobono.hncorefix.constructor;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import co.honobono.hncorefix.HNCoreFix;

public class Language {

	private static Plugin instance = HNCoreFix.getInstance();
	private static FileConfiguration config = instance.getConfig();
	private static File folder = new File(instance.getDataFolder(), "Languages");
	private static String Lang = config.getString("DefaultLanguages");

	public static String getString(String path, String lang) {
		File file = new File(folder, (lang != null) ? lang : Lang + ".lang");
		FileConfiguration langconfig;
		langconfig = new YamlConfiguration();
		try {
			langconfig.load(file);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
		return langconfig.getString(path);
	}
}
