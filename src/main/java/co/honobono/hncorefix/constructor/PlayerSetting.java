package co.honobono.hncorefix.constructor;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class PlayerSetting {

	List<SettingComponent> Components = new ArrayList<>();

	public PlayerSetting() {}

	public void setComponent(int x, int y, String name, String format) {
		Components.add(new SettingComponent(x, y, name, format));
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