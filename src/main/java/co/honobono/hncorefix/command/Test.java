package co.honobono.hncorefix.command;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import co.honobono.hncorefix.annotation.AddCommand;
import co.honobono.hncorefix.annotation.AddListener;
import co.honobono.hncorefix.enums.CommandType;

@AddListener
public class Test {
	Set<Player> p = new HashSet<>();

	@AddCommand(command = "test", alias = { "t" }, description = "This is Test Command.", permission = "hncorefix.test")
	public boolean c(CommandSender sender, String[] args) {
		if (args[0].equalsIgnoreCase("r")) {
			p.add(Bukkit.getPlayer(args[1]));
			sender.sendMessage("追加");
			return true;
		}
		if (args[0].equalsIgnoreCase("s")) {
			for (Player player : p) {
				Inventory inv = player.getInventory();
				inv.addItem(new ItemStack(Material.DIAMOND_HELMET));
				inv.addItem(new ItemStack(Material.DIAMOND_CHESTPLATE));
				inv.addItem(new ItemStack(Material.DIAMOND_LEGGINGS));
				inv.addItem(new ItemStack(Material.DIAMOND_BOOTS));
				inv.addItem(new ItemStack(Material.DIAMOND_SWORD));
				ItemStack bow = new ItemStack(Material.BOW);
				bow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
				inv.addItem(bow);
				inv.addItem(new ItemStack(Material.ARROW));
				inv.addItem(new ItemStack(Material.COOKED_BEEF, 64));
			}
			return true;

		}
		Player p = (Player) sender;
		for (EnderDragon e : p.getLocation().getWorld().getEntitiesByClass(EnderDragon.class)) {
			sender.sendMessage("phase: "
					+ (e.getMetadata("phase2").size() >= 1 ? e.getMetadata("phase2").get(0).asBoolean() : "null"));
			sender.sendMessage("Health: " + e.getHealth() + "/" + e.getMaxHealth());
		}
		return true;
	}

	@EventHandler
	public void onEvent(PlayerDeathEvent event) {
		if (p.contains(event.getEntity())) {
			event.setKeepInventory(true);
		}
	}

	@AddCommand(command = "test", description = "test command", permission = "hncorefix.test", usage = "/test", type = CommandType.DIRECT)
	public boolean d(CommandSender sender, String... args) {
		sender.sendMessage("§4Test");
		return true;
	}
}
