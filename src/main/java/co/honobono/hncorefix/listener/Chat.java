package co.honobono.hncorefix.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import co.honobono.hncorefix.annotation.AddListener;
import co.honobono.hncorefix.util.japanese.IMEConverter;
import co.honobono.hncorefix.util.japanese.KanaConverter;

@AddListener
public class Chat implements Listener{

	@EventHandler
	public void onASyncPlayerChat(AsyncPlayerChatEvent event) {
		String msg = event.getMessage();
		String convmsg = IMEConverter.convByGoogleIME(KanaConverter.conv(msg));
		event.setMessage(String.format("%s(%s)", msg, convmsg));
	}
}
