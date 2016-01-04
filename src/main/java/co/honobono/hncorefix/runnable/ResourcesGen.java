package co.honobono.hncorefix.runnable;

import org.bukkit.Bukkit;
import org.bukkit.World.Environment;
import org.bukkit.WorldType;
import org.bukkit.scheduler.BukkitRunnable;

import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MVWorldManager;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;

import co.honobono.hncorefix.HNCoreFix;

public class ResourcesGen extends BukkitRunnable {
	private static MultiverseCore mv = (MultiverseCore) Bukkit.getPluginManager().getPlugin("MultiverseCore");

	@Override
	public void run() {
		if(mv == null) mv = (MultiverseCore) Bukkit.getPluginManager().getPlugin("Multiverse-Core");
		MVWorldManager mvm = mv.getMVWorldManager();
		new BukkitRunnable() {
			@Override
			public void run() {
				Bukkit.broadcastMessage("資源が10分後に再生成されます。");
			}
		}.run();
		new BukkitRunnable() {
			@Override
			public void run() {
				Bukkit.broadcastMessage("資源が5分後に再生成されます。");
			}
		}.runTaskLater(HNCoreFix.getInstance(), 6000L);
		new BukkitRunnable() {
			@Override
			public void run() {
				Bukkit.broadcastMessage("資源が1分後に再生成されます。");
			}
		}.runTaskLater(HNCoreFix.getInstance(), 10800L);
		new BukkitRunnable() {
			@Override
			public void run() {
				Bukkit.broadcastMessage("資源が再生成されます...");
				MultiverseWorld world = mvm.getMVWorld("Resource_1");
				if(world != null) {
					world.getCBWorld().getPlayers().forEach(p -> p.teleport(Bukkit.getWorld("world").getSpawnLocation()));
					mvm.deleteWorld("Resource_1", true, true);
				}
				if(mvm.addWorld("Resource_1", Environment.NORMAL, "", WorldType.NORMAL, true, "")) {
					Bukkit.broadcastMessage("正常に作成されました。");
				} else {
					Bukkit.broadcastMessage("正常に作成されませんでした。");
				}
			}
		}.runTaskLater(HNCoreFix.getInstance(), 12000L);
	}

}