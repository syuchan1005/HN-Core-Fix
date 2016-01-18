package co.honobono.hncorefix.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.honobono.hncorefix.annotation.AddCommand;
import co.honobono.hncorefix.util.Util;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;

public class Test {

	@AddCommand(command = "test", description = "test", permission = "hncorefix.test", usage = "test")
	public boolean c(CommandSender sender, String[] args) {
		Player player = (Player) sender;
		org.bukkit.Location loc = player.getLocation();
		Util.sendPlayerPacket(player,
			new PacketPlayOutWorldParticles(EnumParticle.REDSTONE,
					Boolean.valueOf(args[0]),
					(float)loc.getX(),
					(float)loc.getY(),
					(float)loc.getZ(),
					255F,
					0F,
					0F,
					10.0F,
					0,
					new int[] { 0 }
			)
		);
		return true;
	}
}
