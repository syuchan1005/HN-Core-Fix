package co.honobono.hncorefix.constructor;

import java.util.Arrays;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;

import co.honobono.hncorefix.HNCoreFix;

public class CommandBase extends BukkitCommand {

	public CommandBase(String command) {
		super(command);
	}

	public void Set(String command, String[] alias, String description, String permission, String permissionmessage, String usage) {
		this.setAliases(Arrays.asList(alias));
		this.setDescription(description);
		this.setPermission(permission);
		this.setPermissionMessage(permissionmessage);
		this.setUsage(usage);
	}

	@Override
	public boolean execute(CommandSender sender, String alias, String[] args) {
		return HNCoreFix.getManager().run(sender, this.getName(), args);
	}
}
