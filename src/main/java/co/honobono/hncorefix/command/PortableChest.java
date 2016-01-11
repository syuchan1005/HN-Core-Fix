package co.honobono.hncorefix.command;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import co.honobono.hncorefix.HNCoreFix;
import co.honobono.hncorefix.annotation.AddCommand;

public class PortableChest {
	public static Map<Player, Inventory> map = new HashMap<>();
	public static Inventory inv = Bukkit.createInventory(null, InventoryType.CHEST);

	@AddCommand(command = "portablechest", alias = { "pc",
			"p" }, description = "PortableChest", permission = "hncorefix.portablechest", usage = "PortableChest")
	public boolean c(CommandSender sender, String... args) {
		if (args.length == 1) {
			switch (args[0].toUpperCase()) {
			case "SAVE":
				save("Inv", map);
				break;
			case "LOAD":
				load("Inv");
				break;
			}
			return true;
		}
		if (!(sender instanceof Player))
			return true;
		Player player = (Player) sender;
		if (!map.containsKey(player))
			map.put(player, Bukkit.createInventory(null, InventoryType.CHEST));
		player.openInventory(map.get(player));
		return true;
	}

	public static void save(String Key, Map<Player, Inventory> map) {
		Map<String, ItemStack[]> smap = new HashMap<>();
		for (Map.Entry<Player, Inventory> e : map.entrySet()) {
			List<ItemStack> il = new ArrayList<>();
			for (ItemStack i : e.getValue().getContents()) {
				if (i == null) {
					il.add(new ItemStack(Material.AIR));
				} else {
					il.add(i);
				}
			}
			smap.put(e.getKey().getUniqueId().toString(), (ItemStack[]) il.toArray(new ItemStack[0]));
		}
		HNCoreFix.inv.set(Key, smap);
		try {
			File f = new File(HNCoreFix.getInstance().getDataFolder(), "inventory.yml");
			HNCoreFix.inv.save(f);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static void load(String Key) { // List<Map<String, List<Map<String, Object>>>>
		for (Map<?, ?> lmap : HNCoreFix.inv.getMapList(Key)) {
			for (Entry<?, ?> e : lmap.entrySet()) {
				List<ItemStack> il = new ArrayList<>();
				for (Map<String, Object> item : (List<Map<String, Object>>) e.getValue()) {
					il.add(ItemStack.deserialize(item));
				}
				Inventory i = Bukkit.createInventory(null, InventoryType.CHEST);
				i.setContents(((ItemStack[]) il.toArray(new ItemStack[0])));
				map.put(Bukkit.getPlayer(((String) e.getKey())), i);
			}
		}
	}
}
