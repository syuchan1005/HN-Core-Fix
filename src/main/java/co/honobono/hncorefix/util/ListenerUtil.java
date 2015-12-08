package co.honobono.hncorefix.util;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import co.honobono.hncorefix.HNCoreFix;
import co.honobono.hncorefix.annotation.ListenerAdd;

public class ListenerUtil {

	public static void RegListeners(Plugin pl) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		File file = new File(HNCoreFix.getInstance().getClass().getProtectionDomain().getCodeSource().getLocation().getFile());
		JarFile jar = new JarFile(file);
		for (Enumeration<JarEntry> e = jar.entries(); e.hasMoreElements();) {
			JarEntry entry = e.nextElement();
			if(!entry.isDirectory() && entry.getName().endsWith(".class")) {
				String className = entry.getName();
				Class<?> clazz = Class.forName(className.replace('/', '.').substring(0, className.length() - 6));
				if(hasListenerAdd(clazz.getAnnotations()) && hasListener(clazz.getInterfaces())) {
					pl.getServer().getPluginManager().registerEvents((Listener) clazz.newInstance(), pl);
				}
			}
		}
		jar.close();
	}

	private static boolean hasListenerAdd(Annotation[] a) {
		for(Annotation b : a) {
			if(b instanceof ListenerAdd) return true;
		}
		return false;
	}

	private static boolean hasListener(Class<?>[] clazz) {
		for(Class<?> c : clazz) {
			if(c == Listener.class) return true;
		}
		return false;
	}
}
