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
	private Map<CommandBase, Method> indirection = new HashMap<>();
	private List<String> commands = new ArrayList<>();
	private Map<CommandBase, Method> direct = new HashMap<>();

	public CommandManager() {
	}

	public boolean run(CommandSender sender, String cmd, String[] args) {
		if (cmd.equalsIgnoreCase("hn")) {
			if (args.length == 0) {
				args = new String[1];
				args[0] = "help";
			}
			for (Map.Entry<CommandBase, Method> e : indirection.entrySet()) {
				if (e.getKey().getName().equalsIgnoreCase(args[0])
						|| Util.hasString(args[0], e.getKey().getAliases())) {
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
		} else {
			for (Map.Entry<CommandBase, Method> e : direct.entrySet()) {
				if (e.getKey().getName().equalsIgnoreCase(cmd) || Util.hasString(cmd, e.getKey().getAliases())) {
					Method method = e.getValue();
					try {
						return (boolean) method.invoke(method.getDeclaringClass().newInstance(), sender, args);
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
							| InstantiationException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
		return false;
	}

	@AddCommand(command = "help", description = "This is Help Command.", usage = "This", permission = "hncorefix.help")
	public boolean c(CommandSender sender, String[] args) {
		HNCoreFix.getManager().sendHelp(sender);
		return true;
	}

	public void putIndirectionMap(CommandBase base, Method m) {
		commands.add(base.getName());
		for (String s : base.getAliases())
			commands.add(s);
		indirection.put(base, m);
	}

	public void putDirectMap(CommandBase base, Method m) {
		direct.put(base, m);
	}

	public Map<CommandBase, Method> getDirectMap() {
		return direct;
	}

	public void sendHelp(CommandSender sender) {
		sender.sendMessage(ChatColor.GREEN + "======" + ChatColor.BLUE + "Command Help" + ChatColor.GREEN + "======");
		for (Map.Entry<CommandBase, Method> e : indirection.entrySet()) {
			CommandBase cb = e.getKey();
			sender.sendMessage(formatCommand(cb.getName(), cb.getAliases()) + ":" + cb.getUsage());
		}
	}

	private static String formatCommand(String command, List<String> alias) {
		String s = command;
		if (alias.size() >= 1) {
			s = s + "(";
			if (alias.size() == 1) {
				s = s + alias.get(0);
			} else {
				for (String s1 : alias)
					s = s + s1 + ",";
				s = s.substring(0, s.length() - 1);
			}
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
