package co.honobono.hncorefix.constructor;

import java.util.Arrays;

public class CommandBase {

	private String command;
	private String[] alias;
	private String description;
	private String permission;
	private String permissionmessage;
	private String usage;

	public CommandBase(String command, String[] alias, String description, String permission, String permissionmessage, String usage) {
		this.command = command;
		this.alias = alias;
		this.description = description;
		this.permission = permission;
		this.permissionmessage = permissionmessage;
		this.usage = usage;
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
