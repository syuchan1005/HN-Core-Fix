package co.honobono.hncorefix.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import co.honobono.hncorefix.annotation.AddListener;
import co.honobono.hncorefix.util.Util;
import co.honobono.hncorefix.util.japanese.IMEConverter;
import co.honobono.hncorefix.util.japanese.KanaConverter;
import ru.tehkode.permissions.bukkit.PermissionsEx;

@AddListener
public class JapaneseChat implements Listener {

	@EventHandler
	public void onASyncPlayerChat(AsyncPlayerChatEvent event) {
		String msg = event.getMessage();
		event.setFormat(
				Util.color(
						String.format("<%s%s>&r%s", PermissionsEx.getUser(event.getPlayer()).getPrefix(),
								event.getPlayer().getDisplayName(), msg)
						+ (hasZenkaku(msg) ? "" : "(" + IMEConverter.convByGoogleIME(KanaConverter.conv(msg)) + ")"),
						null));
	}

	public static boolean hasZenkaku(String text) {
		return text.length() != text.getBytes().length;
	}
}
