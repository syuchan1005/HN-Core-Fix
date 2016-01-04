package co.honobono.hncorefix.command;

import java.text.SimpleDateFormat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.honobono.hncorefix.annotation.AddCommand;

public class Look {

	@AddCommand(command = "look", description = "プレイヤーの詳細情報を表示します", permission = "hncorefix.look", usage = "<command> <PlayerName>")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player1 = null;
		if (args.length == 1) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("このコマンドはゲーム以内より実行してください。");
				return false;
			}
			player1 = (Player) sender;
		} else if (args.length == 2) {
			player1 = Bukkit.getPlayer(args[1]);
		}
		sender.sendMessage(
				ChatColor.YELLOW + "-----------" + ChatColor.AQUA + "Player Status" + ChatColor.YELLOW + "-----------");
		if (player1 == null) {
			OfflinePlayer p = Bukkit.getPlayer(args[1]);
			sender.sendMessage("名前: " + p.getName());
			sender.sendMessage("UUID: " + p.getUniqueId());
			sender.sendMessage("最終ログイン: " + getTime(p.getLastPlayed()));
		} else {
			sender.sendMessage("名前: " + player1.getName());
			sender.sendMessage("UUID: " + player1.getUniqueId());
			sender.sendMessage("最終ログイン: " + getTime(player1.getLastPlayed()));
			sender.sendMessage("経験値: " + player1.getExpToLevel());
			sender.sendMessage("体力: " + player1.getHealth() + "/" + player1.getMaxHealth());
			sender.sendMessage("AllowFlight: " + player1.getAllowFlight());
			Location loc = player1.getLocation();
			sender.sendMessage("現在座標: ");
			sender.sendMessage("      X: " + loc.getX());
			sender.sendMessage("      Y: " + loc.getY());
			sender.sendMessage("      Z: " + loc.getZ());
			sender.sendMessage("  Pitch: " + loc.getPitch());
			sender.sendMessage("    Yaw: " + loc.getYaw());
			sender.sendMessage("IPアドレス: " + player1.getAddress()/* + "(" + getLoc(player1.getAddress().toString()) + ")"*/);
		}
		return true;
	}

	private static SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	public static String getTime(long Mills) {
		return format.format(Mills);
	}

/*	private static File db = new File(instance.getDataFolder(), "GeoLite2-City.mmdb");
	private static DatabaseReader DBReader = null;{
		try {
			DBReader = new DatabaseReader.Builder(new FileInputStream(db)).build();
		} catch (IOException e) {
			HNCoreFix.getLog().info("DBファイルが存在しません。");
		}
	}

	public static String getLoc(String Address) {
		final String IP = Address.split(":")[0].substring(1);
		if (DBReader == null)
			return "DBファイルが存在しません。";
		if (IP.equals("127.0.0.1"))
			return ("localhostのため特定できません。");
		try {
			CityResponse res = DBReader.city(InetAddress.getByName(IP));
			return res.getContinent().getNames().get("ja") + "/" + res.getCountry().getNames().get("ja") + "/"
					+ res.getLeastSpecificSubdivision().getNames().get("ja");
		} catch (GeoIp2Exception | IOException e) {
			e.printStackTrace();
			return "特定できませんでした。";
		}
	}

	public static void checkDB() {
		if (db.exists()) {
			return;
		} else {
			HNCoreFix.getLog().info("DBファイルが存在しません\nダウンロードを開始します...");
		}
		GZIPInputStream gzipInStream = null;
		try {
			gzipInStream = new GZIPInputStream(
					new BufferedInputStream(new FileInputStream(new File(db.getAbsolutePath() + ".gz"))));
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			while (true) {
				int iRead = gzipInStream.read();
				if (iRead < 0)
					break;
				outStream.write(iRead);
			}
			outStream.flush();
			outStream.close();
		} catch (IOException e) {
			e.printStackTrace();
			if (gzipInStream != null) {
				try {
					gzipInStream.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			if (gzipInStream != null) {
				try {
					gzipInStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		try {
			DBReader = new DatabaseReader.Builder(new FileInputStream(db)).build();
		} catch (IOException e) {}
	}*/
}
