package com.mta.guns.util;

import com.mta.guns.MTAGUNS;
import com.mta.guns.weapons.Gun;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;

import java.util.Arrays;

public class UtilMethods {

    public static String toColor(String in) {
        return ChatColor.translateAlternateColorCodes('&', in);
    }

    public static ItemStack makeItemStack(String name, String lore, Material mat, int data) {
        ItemStack i = new ItemStack(mat, 1, (short)data);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(toColor(name));
        im.setLore(Arrays.asList(toColor(lore).split("#")));
        i.setItemMeta(im);
        return i;
    }

    public static boolean isSimilar(ItemStack a, ItemStack b) {
        if (a.getType() == b.getType())
            if (a.getDurability() == b.getDurability())
                return true;
        return false;
    }

    public static Gun getGun(Player player) {
        Gun gun = null;
        ItemStack inHand = player.getItemInHand();
        if (inHand != null) {
            for (Gun g : MTAGUNS.getAvailableWeapons()) {
                if (inHand.isSimilar(g.getGunItem())) {
                    gun = g;
                    break;
                }
            }
        }
        return gun;
    }

    public static Vector getBulletVelocity(Player shooter) {
        double yaw = Math.toRadians((-shooter.getLocation().getYaw() - 90.0f));
        double pitch = Math.toRadians(-shooter.getLocation().getPitch());

        double x = Math.cos(pitch) * Math.cos(yaw);
        double y = Math.sin(pitch);
        double z = -Math.sin(yaw) * Math.cos(pitch);

        Vector dirVec = new Vector(x, y, z).normalize();
        return dirVec;
    }

    public static Vector getBulletSpread(Player shooter) {
        double shiftYaw = (shooter.getLocation().getYaw() + 180) * (Math.PI/180);
        Vector shiftVec = new Vector(Math.cos(shiftYaw), 0.0D, Math.sin(shiftYaw)); //TODO: add bullet_spread
        return shiftVec;
    }

    public static void setGunBulletAmount(ItemStack gun_item, short amount) {
        NamespacedKey key = new NamespacedKey(MTAGUNS.getPlugin(), "Gun-Bullet-Clip-Size");
        ItemMeta itemMeta = gun_item.getItemMeta();
        itemMeta.getPersistentDataContainer().set(key, PersistentDataType.SHORT, amount);
        gun_item.setItemMeta(itemMeta);
    }

    public static short getGunBulletAmount(ItemStack gun_item) {
        NamespacedKey key = new NamespacedKey(MTAGUNS.getPlugin(), "Gun-Bullet-Clip-Size");
        ItemMeta itemMeta = gun_item.getItemMeta();
        PersistentDataContainer dataContainer = itemMeta.getPersistentDataContainer();
        if (dataContainer.has(key, PersistentDataType.SHORT)) {
            return dataContainer.get(key, PersistentDataType.SHORT);
        }
        return -1;
    }

    public static void setGunReloading(ItemStack gun_item, boolean value) {
        NamespacedKey key = new NamespacedKey(MTAGUNS.getPlugin(), "Is-Gun-Reloading");
        ItemMeta itemMeta = gun_item.getItemMeta();
        itemMeta.getPersistentDataContainer().set(key, PersistentDataType.BYTE, value ? Byte.MAX_VALUE : Byte.MIN_VALUE);
        gun_item.setItemMeta(itemMeta);
    }

    public static boolean isGunReloading(ItemStack gun_item) {
        NamespacedKey key = new NamespacedKey(MTAGUNS.getPlugin(), "Is-Gun-Reloading");
        ItemMeta itemMeta = gun_item.getItemMeta();
        PersistentDataContainer dataContainer = itemMeta.getPersistentDataContainer();
        if (dataContainer.has(key, PersistentDataType.BYTE)) {
            Byte b = dataContainer.get(key, PersistentDataType.BYTE);
            return b.equals(Byte.MAX_VALUE);
        }
        return false;
    }

    public void getShotLocation(Player shooter, Projectile projectile, Player wounded) {
        
    }



}
