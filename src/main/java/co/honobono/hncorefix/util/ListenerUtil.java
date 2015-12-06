package co.honobono.hncorefix.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.event.Listener;

public class ListenerUtil {

	public static List<Listener> getListeners() {
		String PName = "co.honobono.hncorefix.listener";
		File[] CFiles = (new File(Thread.currentThread().getContextClassLoader().getResource(PName.replace(".", "/")).getFile())).listFiles();
		List<Listener> o = new ArrayList<>();
		for (int i1 = 0; i1 < CFiles.length; i1++) {
			String FName = CFiles[i1].getName();
			try {
				o.add((Listener) Class.forName(PName + "." + FName.substring(0, FName.indexOf(".class"))).newInstance());
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return o;
	}
}
