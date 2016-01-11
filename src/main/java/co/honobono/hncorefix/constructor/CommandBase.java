package co.honobono.hncorefix.constructor;

import java.util.Arrays;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;

import co.honobono.hncorefix.HNCoreFix;

public class CommandBase extends BukkitCommand {

	private String command;
	private String[] alias;
	private String description;
	private String permission;
	private String permissionmessage;
	private String usage;

	public void Set(String command, String[] alias, String description, String permission, String permissionmessage, String usage) {
		this.command = command;
		this.alias = alias;
		this.description = description;
		this.permission = permission;
		this.permissionmessage = permissionmessage;
		this.usage = usage;
		this.setAliases(Arrays.asList(this.alias));
		this.setDescription(this.getDescription());
		this.setPermission(this.getPermission());
		this.setPermissionMessage(this.getPermissionmessage());
		this.setUsage(this.getUsage());
	}

	public CommandBase(String command) {
		super(command);
	}

	@Override
	public boolean execute(CommandSender sender, String alias, String[] args) {
		return HNCoreFix.getManager().run(sender, this.getCommand(), args);
	}

	public String getCommand() {
		return command;
	}

	public String[] getAlias() {
		return alias;
	}

	public String getDescription() {
		return description;
	}

	public String getPermission() {
		return permission;
	}

	public String getPermissionmessage() {
		return permissionmessage;
	}

	public String getUsage() {
		return usage;
	}

	@Override
	public String toString() {
		return "CommandBase [command=" + command + ", alias=" + Arrays.toString(alias) + ", description=" + description
				+ ", permission=" + permission + ", permissionmessage=" + permissionmessage + ", usage=" + usage + "]";
	}
}
