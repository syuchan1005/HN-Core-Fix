package co.honobono.hncorefix.command;

import java.util.Random;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

import co.honobono.hncorefix.annotation.AddCommand;

public class Fire {

	@AddCommand(command = "fire", description = "This is Fire", permission = "hncorefix.fire")
	public boolean c(CommandSender sender, String[] args) {
		Location loc;
		if (sender instanceof Player) {
			loc = ((Player) sender).getLocation();
		} else if(sender instanceof BlockCommandSender) {
			loc = ((BlockCommandSender) sender).getBlock().getLocation().add(0, 1, 0);
		} else {
			return true;
		}
			Firework fw = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
			FireworkMeta fwm = fw.getFireworkMeta();
			Random r = new Random();
			Type type = Type.BALL;
			switch (r.nextInt(4)) {
			case 0:
				type = Type.BALL;
				break;
			case 1:
				type = Type.BALL_LARGE;
				break;
			case 2:
				type = Type.BURST;
				break;
			case 3:
				type = Type.CREEPER;
				break;
			case 4:
				type = Type.STAR;
				break;
			}
			FireworkEffect effect = FireworkEffect.builder().flicker(r.nextBoolean())
					.withColor(getColor(r.nextInt(17) + 1)).withFade(getColor(r.nextInt(17) + 1)).with(type)
					.trail(r.nextBoolean()).build();
			fwm.addEffect(effect);
			fwm.setPower(r.nextInt(1) + 2);
			fw.setFireworkMeta(fwm);

		return true;
	}

	private Color getColor(int i) {
		switch (i) {
		case 1:
			return Color.AQUA;
		case 2:
			return Color.BLACK;
		case 3:
			return Color.BLUE;
		case 4:
			return Color.FUCHSIA;
		case 5:
			return Color.GRAY;
		case 6:
			return Color.GREEN;
		case 7:
			return Color.LIME;
		case 8:
			return Color.MAROON;
		case 9:
			return Color.NAVY;
		case 10:
			return Color.OLIVE;
		case 11:
			return Color.ORANGE;
		case 12:
			return Color.PURPLE;
		case 13:
			return Color.RED;
		case 14:
			return Color.SILVER;
		case 15:
			return Color.TEAL;
		case 16:
			return Color.WHITE;
		case 17:
			return Color.YELLOW;
		default:
			return Color.fromRGB(46, 29, 168);
		}
	}
}
