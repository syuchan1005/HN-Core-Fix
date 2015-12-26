package co.honobono.hncorefix.command;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import co.honobono.hncorefix.annotation.AddCommand;
import co.honobono.hncorefix.constructor.PlayerSetting;
import co.honobono.hncorefix.exception.HNInventoryOutOfSlotException;

public class Test {

	@AddCommand(command = { "test", "t" }, description = "This is Test Command.", permission = "hncorefix.test")
	public boolean c(CommandSender sender, String[] args) {
		PlayerSetting sp = new PlayerSetting("TestWindow");
		try {
			sp.setComponent(1, 1, "name", new ItemStack(Material.ACTIVATOR_RAIL), null);
			sp.setComponent(2, 4, "name", new ItemStack(Material.TORCH), "%s");
			sp.setComponent(2, 5, "name", new ItemStack(Material.TORCH), "%s");
			sp.setComponent(3, 4, "name", new ItemStack(Material.TORCH), null);
			sp.setComponent(4, 4, "name", new ItemStack(Material.TORCH), "%d");
		} catch (HNInventoryOutOfSlotException e) {
			e.printStackTrace();
		}
		sp.showWindow((Player)sender);
		return true;
	}

}
