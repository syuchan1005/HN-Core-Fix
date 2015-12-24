package co.honobono.hncorefix.exception;

import co.honobono.hncorefix.HNCoreFix;
import net.md_5.bungee.api.ChatColor;

public class HNInventoryOutOfSlotException extends Exception{

	public HNInventoryOutOfSlotException(int slot) {
		HNCoreFix.getInstance().getServer().getConsoleSender().sendMessage(ChatColor.RED + String.valueOf(slot) + "は指定できません");
	}
}
