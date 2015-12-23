package co.honobono.hncorefix.constructor;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import co.honobono.hncorefix.exception.HNInventoryOutOfSlotException;

public class PlayerSetting {

	private String Title;
	private List<SettingComponent> Components = new ArrayList<>();

	public PlayerSetting(String Title) {
		this.Title = Title;
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

}

class SettingComponent {
	private int slot;
	private String name;
	private String format;
	private Object value;

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

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
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