package co.honobono.hncorefix.constructor;

public class CommandBase {

	private String[] command;
	private String description;
	private String permission;
	private String permissionmessage;
	private String usage;

	public CommandBase(String[] command, String description, String permission, String permissionmessage, String usage) {
		this.command = command;
		this.description = description;
		this.permission = permission;
		this.permissionmessage = permissionmessage;
		this.usage = usage;
	}

	public String[] getCommand() {
		return command;
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
}
