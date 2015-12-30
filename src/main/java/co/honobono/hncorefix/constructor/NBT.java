package co.honobono.hncorefix.constructor;

import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_8_R3.NBTBase;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;

public class NBT {

	private static NBTTagCompound getNBTTagCompound(ItemStack item) {
		return CraftItemStack.asNMSCopy(item).getTag();
	}

	public static NBTBase get(ItemStack item, String key) {
		return getNBTTagCompound(item).get(key);
	}

	public static Boolean getBoolean(ItemStack item, String key) {
		return getNBTTagCompound(item).getBoolean(key);
	}

	public static Byte getByte(ItemStack item, String key) {
		return getNBTTagCompound(item).getByte(key);
	}

	public static Double getDouble(ItemStack item, String key) {
		return getNBTTagCompound(item).getDouble(key);
	}

	public static Float getFloat(ItemStack item, String key) {
		return getNBTTagCompound(item).getFloat(key);
	}

	public static int getInt(ItemStack item, String key) {
		return getNBTTagCompound(item).getInt(key);
	}

	public static Long getLong(ItemStack item, String key) {
		return getNBTTagCompound(item).getLong(key);
	}

	public static String getString(ItemStack item, String key) {
		return getNBTTagCompound(item).getString(key);
	}

	public static byte getTypeId(ItemStack item) {
		return getNBTTagCompound(item).getTypeId();
	}

	public static byte[] getByteArray(ItemStack item, String key) {
		return getNBTTagCompound(item).getByteArray(key);
	}

	public static int[] getIntArray(ItemStack item, String key) {
		return getNBTTagCompound(item).getIntArray(key);
	}

	public static NBTTagList getList(ItemStack item, String key, int num) {
		return getNBTTagCompound(item).getList(key, num);
	}

	public static NBTTagCompound getCompound(ItemStack item, String key) {
		return getNBTTagCompound(item).getCompound(key);
	}


}
