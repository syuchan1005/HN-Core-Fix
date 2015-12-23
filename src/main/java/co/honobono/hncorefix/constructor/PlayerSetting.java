package co.honobono.hncorefix.constructor;

import org.bukkit.entity.Player;

public class PlayerSetting {

	public PlayerSetting() {

	}

	public void showWindow(Player player) {

	}

}

class SettingComponent {
	int x;
	int y;
	String name;
	String format;
	Object value;

	public SettingComponent(int x, int y, String name, String format) {
		this.x = x;
		this.y = y;
		this.name = name;
		this.format = format;
	}
}