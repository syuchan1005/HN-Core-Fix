package co.honobono.hncorefix.constructor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.plugin.Plugin;

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
				if (e.getKey().getCommand().equalsIgnoreCase(args[0])
						|| Util.hasString(args[0], e.getKey().getAlias())) {
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
				if (e.getKey().getCommand().equalsIgnoreCase(cmd) || Util.hasString(cmd, e.getKey().getAlias())) {
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

	@AddCommand(command = "help", description = "This is Help Command.", permission = "hncorefix.help")
	public boolean c(CommandSender sender, String[] args) {
		HNCoreFix.getManager().sendHalp(sender);
		return true;
	}

	public void putIndirectionMap(CommandBase base, Method m) {
		commands.add(base.getCommand());
		for (String s : base.getAlias())
			commands.add(s);
		indirection.put(base, m);
	}

	public void putDirectMap(CommandBase base, Method m) {
		try {
			Constructor<?> cs = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
			cs.setAccessible(true);
			PluginCommand cmd = (PluginCommand) cs.newInstance(base.getCommand(), HNCoreFix.getInstance());
			cmd.setDescription(base.getDescription());
			cmd.setUsage(base.getUsage());
			cmd.setAliases(Arrays.asList(base.getAlias()));
			cmd.setPermission(base.getPermission());
			cmd.setPermissionMessage(base.getPermissionmessage());
			cmd.setExecutor(HNCoreFix.getInstance());
			((CraftServer) HNCoreFix.getInstance().getServer()).getCommandMap().register("HN-Core-Fix", cmd);
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		direct.put(base, m);
	}

	public void sendHalp(CommandSender sender) {
		sender.sendMessage(ChatColor.GREEN + "======" + ChatColor.BLUE + "Command Help" + ChatColor.GREEN + "======");
		for (Map.Entry<CommandBase, Method> e : indirection.entrySet()) {
			CommandBase cb = e.getKey();
			sender.sendMessage(formatCommand(cb.getCommand(), cb.getAlias()) + ":" + cb.getUsage());
		}
	}

	private static String formatCommand(String command, String[] alias) {
		String s = command;
		if (alias.length >= 1) {
			s = s + "(";
			if (alias.length == 1) {
				s = s + alias[0];
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
