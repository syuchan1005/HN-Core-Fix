package co.honobono.hncorefix.constructor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import co.honobono.hncorefix.exception.HNCommandOverlapException;
import co.honobono.hncorefix.util.Util;

public class CommandManager implements TabCompleter {
	private Map<CommandBase, Method> map = new HashMap<>();
	private List<String> commands = new ArrayList<>();

	public CommandManager() {
	}

	public boolean run(CommandSender sender, String[] args) {
		if(args.length == 0) {
			args = new String[1];
			args[0] = "help";
		}
		for (Map.Entry<CommandBase, Method> e : map.entrySet()) {
			if (Util.hasString(args[0], e.getKey().getCommand())) {
				Method method = e.getValue();
				try {
					String[] args1 = new String[args.length - 1];
					System.arraycopy(args, 1, args1, 0, args.length - 1);
					return (boolean) method.invoke(method.getDeclaringClass().newInstance(), sender, args1);
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

	public void sendHalp(CommandSender sender) {
		sender.sendMessage(ChatColor.GREEN + "======" + ChatColor.BLUE + "Command Help" + ChatColor.GREEN + "======");
		for (Map.Entry<CommandBase, Method> e : map.entrySet()) {
			CommandBase cb = e.getKey();
			sender.sendMessage(formatCommand(cb.getCommand()) + ":" + cb.getUsage());
		}
	}

	private static String formatCommand(String[] str) {
		String s = str[0];
		str[0] = "";
		if (str.length >= 2) {
			s = s + "(";
			for (String s1 : str)
				s = s + s1;
			s = s + ")";
		}
		return s;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
		if (args.length == 1)
			return commands;
		return null;
	}
}
