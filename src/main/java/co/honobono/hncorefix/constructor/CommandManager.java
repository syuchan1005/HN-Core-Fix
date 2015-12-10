package co.honobono.hncorefix.constructor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import co.honobono.hncorefix.annotation.AddCommand;
import co.honobono.hncorefix.exception.HNCommandOverlapException;
import co.honobono.hncorefix.util.Util;

public class CommandManager implements TabCompleter {
	private Map<CommandBase, Method> map;
	private List<String> commands = new ArrayList<>();

	public CommandManager() {
		map = new HashMap<>();
	}

	public boolean run(CommandSender sender, String[] args) {
		for (Map.Entry<CommandBase, Method> e : map.entrySet()) {
			if (Util.hasString(args[0], e.getKey().getCommand())) {
				Method method = e.getValue();
				try {
					if(!(sender instanceof Player) || sender.hasPermission(((AddCommand)method.getAnnotation(AddCommand.class)).permission())) {
						Object obj = method.getClass().newInstance();
						return (boolean) method.invoke(obj, sender, args);
					} else {
						sender.sendMessage(((AddCommand)method.getAnnotation(AddCommand.class)).permissionmessage());
						return true;
					}
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| SecurityException | InstantiationException e1) {
					e1.printStackTrace();
					return false;
				}
			}
		}
		return false;
	}

	public void putMap(CommandBase base, Method m) {
		try {
			hasCommand(base.getCommand());
		} catch (HNCommandOverlapException e) {
			e.printStackTrace();
			return;
		}
		for (String s : base.getCommand())
			commands.add(s);
		map.put(base, m);
	}

	public boolean hasCommand(String[] a) throws HNCommandOverlapException {
		for (String b : a) {
			if (commands.contains(b)) {
				throw new HNCommandOverlapException(b);
			}
		}
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
		if(args.length == 1) return commands;
		return null;
	}
}
