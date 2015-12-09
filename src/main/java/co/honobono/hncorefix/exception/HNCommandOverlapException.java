package co.honobono.hncorefix.exception;

import co.honobono.hncorefix.HNCoreFix;
import net.md_5.bungee.api.ChatColor;

public class HNCommandOverlapException extends Exception{

	public HNCommandOverlapException(String command) {
		HNCoreFix.getInstance().getServer().getConsoleSender().sendMessage(ChatColor.RED + "すでに" + command + "は登録されています");
	}
}
