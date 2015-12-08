package co.honobono.hncorefix;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.command.CommandSender;

import co.honobono.hncorefix.util.Util;

public class CommandManager {
	private Map<CommandBase, Class<?>> map;

	public CommandManager() {
		map = new HashMap<>();
	}

	public boolean run(CommandSender sender, String[] args) {
		for(Map.Entry<CommandBase, Class<?>> e : map.entrySet()) {
			if(Util.hasString(args[0], e.getKey().getCommand())) {
				Class<?> clazz = e.getValue();
				try {
					return (boolean)clazz.getMethod("onCommand").invoke(clazz.newInstance(), sender, args);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| NoSuchMethodException | SecurityException | InstantiationException e1) {
					e1.printStackTrace();
				}
			}
		}
		return false;
	}

	public void putMap(CommandBase base, Class<?> clazz) {
		map.put(base, clazz);
	}
}
