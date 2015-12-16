package co.honobono.hncorefix.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Config {

	public static FileConfiguration getConfig(File file) throws IOException, InvalidConfigurationException {
		Reader reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
		FileConfiguration conf = new YamlConfiguration();
		conf.load(reader);
		return conf;
	}
}
