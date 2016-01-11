package co.honobono.hncorefix;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import co.honobono.hncorefix.annotation.AddCommand;
import co.honobono.hncorefix.annotation.AddListener;
import co.honobono.hncorefix.constructor.CommandBase;
import co.honobono.hncorefix.constructor.CommandManager;
import co.honobono.hncorefix.enums.CommandType;

public class Load {
	private static File langdir = new File(HNCoreFix.getInstance().getDataFolder(), "Languages");

	public static void Register(Plugin pl, CommandManager manager)
			throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		File file = new File(
				HNCoreFix.getInstance().getClass().getProtectionDomain().getCodeSource().getLocation().getFile());
		JarFile jar = new JarFile(file);
		for (Enumeration<JarEntry> e = jar.entries(); e.hasMoreElements();) {
			JarEntry entry = e.nextElement();
			if (!entry.isDirectory()) {
				if (entry.getName().endsWith(".class")) {
					String className = entry.getName();
					Class<?> clazz = Class.forName(className.replace('/', '.').substring(0, className.length() - 6));
					// Listener登録処理
					if (hasListenerAdd(clazz.getAnnotations()) && hasListener(clazz.getInterfaces())) {
						pl.getServer().getPluginManager().registerEvents((Listener) clazz.newInstance(), pl);
					}
					// Command登録処理
					for (Method m : clazz.getMethods()) {
						if (hasAddCommand(m.getAnnotations()) && hasArgs(m.getParameterTypes())) {
							AddCommand a = getCommand(m.getAnnotations());
							CommandBase cmd = new CommandBase(a.command());
							cmd.Set(a.command(), a.alias(), a.description(), a.permission(), a.permissionmessage(), a.usage());
							if (a.type() == CommandType.INDIRECTION) {
								manager.putIndirectionMap(cmd, m);
							} else {
								manager.putDirectMap(cmd, m);
							}
						}
					}
				}
				// 言語ファイルのコピー
				if (entry.getName().endsWith(".lang")) {
					if (!langdir.exists())
						langdir.mkdirs();
					File langfile = new File(langdir, new File(entry.getName()).getName());
					InputStream im = null;
					OutputStream os = null;
					try {
						if (!langfile.exists()) {
							langfile.createNewFile();
							im = HNCoreFix.getInstance().getResource(entry.getName());
							if (im == null)
								return;
							os = new FileOutputStream(langfile);
							int c = 0;
							while ((c = im.read()) != -1)
								os.write(c);
							im.close();
							os.close();
						}
					} finally {
						if (im != null)
							im.close();
						if (os != null)
							os.close();
					}
				}
			}
		}
		jar.close();
	}

	private static boolean hasListenerAdd(Annotation[] a) {
		for (Annotation b : a)
			if (b instanceof AddListener)
				return true;
		return false;
	}

	private static boolean hasListener(Class<?>[] clazz) {
		for (Class<?> c : clazz)
			if (c == Listener.class)
				return true;
		return false;
	}

	private static boolean hasAddCommand(Annotation[] a) {
		for (Annotation b : a)
			if (b instanceof AddCommand)
				return true;
		return false;
	}

	private static AddCommand getCommand(Annotation[] a) {
		for (Annotation b : a)
			if (b instanceof AddCommand)
				return (AddCommand) b;
		return null;
	}

	private static boolean hasArgs(Class<?>[] clazz) {
		if (clazz.length != 2)
			return false;
		if (clazz[0] == CommandSender.class && clazz[1] == String[].class)
			return true;
		return false;
	}
}
