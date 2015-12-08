package co.honobono.hncorefix.util;

public class Util {

	public static boolean hasString(String cmd, String[] list) {
		for(String l : list) {
			if(cmd.equalsIgnoreCase(l)) return true;
		}
		return false;
	}
}
