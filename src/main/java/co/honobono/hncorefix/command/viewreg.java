package co.honobono.hncorefix.command;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.sk89q.worldedit.BlockVector;
import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import co.honobono.hncorefix.HNCoreFix;
import co.honobono.hncorefix.annotation.AddCommand;
import co.honobono.hncorefix.util.Util;
import net.minecraft.server.v1_8_R3.EnumParticle;

public class viewreg {

	@SuppressWarnings("deprecation")
	@AddCommand(command = {"viewreg" }, description = "This is View in Region Command.", permission = "hncorefix.viewreg")
	public boolean c(CommandSender sender, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("ゲーム内から実行して下さい.");
			return true;
		}
		if(args.length < 2) return false;
		Player player = (Player) sender;
		ProtectedRegion protect = WGBukkit.getRegionManager(player.getWorld()).getRegion(args[1]);
		BlockVector min = protect.getMinimumPoint();
		BlockVector max = protect.getMaximumPoint();
		for (int x = min.getBlockX(); x <= max.getBlockX(); x++) {
			for (int y = min.getBlockY(); y <= max.getBlockY(); y++) {
				for (int z = min.getBlockZ(); z <= max.getBlockZ(); z++) {
					Location loc = new Location(player.getWorld(), x, y, z);
					player.sendBlockChange(loc, Material.AIR, (byte) 0);
					Util.normalParticle(player, EnumParticle.REDSTONE, loc, 3);
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(HNCoreFix.getInstance(), new BukkitRunnable() {
						@Override
						public void run() {
							player.sendBlockChange(loc, loc.getBlock().getType(), (byte) 0);
						}
					}, 100L);
				}
			}
		}
		return true;
	}

}
