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

import co.honobono.hncorefix.HNCoreFix;
import co.honobono.hncorefix.annotation.AddCommand;
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
			if (e.getKey().getCommand().equalsIgnoreCase(args[0]) || Util.hasString(args[0], e.getKey().getAlias())) {
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

	@AddCommand(command = "help", description = "This is Help Command.", permission = "hncorefix.help")
	public boolean c(CommandSender sender, String[] args) {
		HNCoreFix.getManager().sendHalp(sender);
		return true;
	}

	public void putMap(CommandBase base, Method m) {
		commands.add(base.getCommand());
		for (String s : base.getAlias())
			commands.add(s);
		map.put(base, m);
	}

	public Map<CommandBase, Method> getMap() {
		return map;
	}

	public void sendHalp(CommandSender sender) {
		sender.sendMessage(ChatColor.GREEN + "======" + ChatColor.BLUE + "Command Help" + ChatColor.GREEN + "======");
		for (Map.Entry<CommandBase, Method> e : map.entrySet()) {
			CommandBase cb = e.getKey();
			sender.sendMessage(formatCommand(cb.getCommand(), cb.getAlias()) + ":" + cb.getUsage());
		}
	}

	private static String formatCommand(String command, String[] alias) {
		String s = command;
		if (alias.length >= 1) {
			s = s + "(";
			for (String s1 : alias)
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
