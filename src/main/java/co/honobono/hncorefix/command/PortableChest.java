package co.honobono.hncorefix.command;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import co.honobono.hncorefix.annotation.AddCommand;

public class PortableChest {
	public static Map<Player, Inventory> map = new HashMap<>();
	public static Inventory inv = Bukkit.createInventory(null, InventoryType.CHEST);

	@AddCommand(command = "portablechest", alias = { "pc",
			"p" }, description = "PortableChest", permission = "hncorefix.portablechest", usage = "PortableChest")
	public boolean c(CommandSender sender, String... args) {
		if (!(sender instanceof Player))
			return true;
		Player player = (Player) sender;
		if (!map.containsKey(player))
			map.put(player, Bukkit.createInventory(null, InventoryType.CHEST));
		player.openInventory(map.get(player));
		return true;
	}
}
