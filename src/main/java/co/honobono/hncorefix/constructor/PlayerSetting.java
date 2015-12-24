package co.honobono.hncorefix.constructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import co.honobono.hncorefix.exception.HNInventoryOutOfSlotException;

public class PlayerSetting {

	private String Title;
	private int slot;
	private List<SettingComponent> Components = new ArrayList<>();
	private Map<Player, Map<String, Object>> map = new HashMap<>();

	public PlayerSetting(String Title) {
		this.Title = ChatColor.RESET + Title;
	}

	public void setComponent(int x, int y, String name, ItemStack icon, String format) throws HNInventoryOutOfSlotException {
		this.setComponent(x + (y - 1) * 9, name, icon, format);
	}

	public void setComponent(int slot, String name, ItemStack icon, String format) throws HNInventoryOutOfSlotException {
		if(slot > 45) throw new HNInventoryOutOfSlotException(slot);
		if(this.slot <= slot) this.slot = slot;
		Components.add(new SettingComponent(slot, name, icon, format));
	}

	public void showWindow(Player player) {
		Inventory inv = Bukkit.createInventory(null, this.slot);
		for(SettingComponent com : Components) {
			inv.setItem(com.getSlot(), com.getIcon());
		}
		player.openInventory(inv);
	}

	public String getTitle() {
		return this.Title;
	}

	public Map<String, Object> getSetting(Player player) {
		if(map.containsKey(player)) {
			return map.get(player);
		}
		return null;
	}

	public void setSetting(Player player, Inventory inv) {
		Map<String, Object> value = new HashMap<>();
		for(SettingComponent com : this.Components) {
			if(com.getFormat() == null) continue;
			ItemStack item = inv.getItem(com.getSlot() + 9);
			if(item == null) continue;
			if(com.getFormat().equals("%d")) {
				value.put(com.getName(), item.getItemMeta().getDisplayName());
			} else if(com.getFormat().equals("%s")) {
				value.put(com.getName(), (int)item.getDurability());
			}
		}
		map.put(player, value);
	}

}

class SettingComponent {
	private int slot;
	private String name;
	private ItemStack icon;
	private String format;

	public SettingComponent(int slot, String name, ItemStack icon, String format) {
		this.slot = slot;
		this.name = name;
		this.icon = icon;
		this.format = format;
	}

	public SettingComponent(int x, int y, String name, ItemStack icon, String format) {
		this.slot = x + (y - 1) * 9;
		this.name = name;
		this.icon = icon;
		this.format = format;
	}

	public ItemStack getIcon() {
		return icon;
	}

	public int getSlot() {
		return slot;
	}

	public String getName() {
		return name;
	}

	public String getFormat() {
		return format;
	}
}