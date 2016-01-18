package co.honobono.hncorefix.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.github.ucchyocean.lc.japanize.IMEConverter;
import com.github.ucchyocean.lc.japanize.KanaConverter;

import co.honobono.hncorefix.annotation.AddListener;
import co.honobono.hncorefix.util.Util;
import ru.tehkode.permissions.bukkit.PermissionsEx;

@AddListener
public class JapaneseChat implements Listener {

	@EventHandler
	public void onASyncPlayerChat(AsyncPlayerChatEvent event) {
		String msg = event.getMessage();
		event.setFormat(Util.color(String.format("<%s%s>&r%s", PermissionsEx.getUser(event.getPlayer()).getPrefix(),
				event.getPlayer().getDisplayName(), IMEConverter.convByGoogleIME(KanaConverter.conv(msg)))
				+ (hasZenkaku(msg) ? "" : "(" + msg + ")"), null));
	}

	public static boolean hasZenkaku(String text) {
		return text.length() != text.getBytes().length;
	}
}
