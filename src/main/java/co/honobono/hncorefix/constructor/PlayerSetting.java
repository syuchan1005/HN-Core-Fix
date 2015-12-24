package co.honobono.hncorefix.constructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import co.honobono.hncorefix.exception.HNInventoryOutOfSlotException;

public class PlayerSetting {

	private String Title;
	private List<SettingComponent> Components = new ArrayList<>();
	private Map<Player, Map<String, Object>> map = new HashMap<>();

	public PlayerSetting(String Title) {
		this.Title = ChatColor.RESET + Title;
	}

	public void setComponent(int x, int y, String name, String format) throws HNInventoryOutOfSlotException {
		this.setComponent(x + (y - 1) * 9, name, format);
	}

	public void setComponent(int slot, String name, String format) throws HNInventoryOutOfSlotException {
		if(slot > 45) throw new HNInventoryOutOfSlotException(slot);
		Components.add(new SettingComponent(slot, name, format));
	}

	public void showWindow(Player player) {

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

	public void setSetting(Inventory inv) {

	}
}

class SettingComponent {
	private int slot;
	private String name;
	private String format;

	public SettingComponent(int slot, String name, String format) {
		this.slot = slot;
		this.name = name;
		this.format = format;
	}

	public SettingComponent(int x, int y, String name, String format) {
		this.slot = x + (y - 1) * 9;
		this.name = name;
		this.format = format;
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