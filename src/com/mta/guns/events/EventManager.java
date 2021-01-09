package com.mta.guns.events;

import com.mta.guns.MTAGUNS;
import com.mta.guns.util.UtilMethods;
import com.mta.guns.weapons.Gun;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class EventManager implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.getPlayer().getInventory().clear();
        for (Gun g : MTAGUNS.getAvailableWeapons()) {
            event.getPlayer().getInventory().addItem(g.getGunItem());
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        MTAGUNS.getTaskManager().endAllPlayerTasks(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        List<Gun> gunList = MTAGUNS.getAvailableWeapons();
        ItemStack iih = event.getPlayer().getItemInHand();
        if (iih != null) {
            Gun gun = null;
            for(Gun g : gunList) {
                if (UtilMethods.isSimilar(iih, g.getGunItem())) {
                    gun = g;
                    break;
                }
            }

            if (gun != null) {
                if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    event.setCancelled(true);
                    gun.shootGun(event.getPlayer());
                } else if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
                    if (gun.getScopeSettings().isEnabled()) {
                        event.setCancelled(true);
                        gun.getScopeSettings().toggleScope(event.getPlayer());
                    }
                }
            }

        }
    }

    @EventHandler
    public void onEntDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Projectile) {
            Projectile p = (Projectile)event.getDamager();
            if (p.getShooter() instanceof Player) {
                Player player = (Player)p.getShooter();
                Gun gun = UtilMethods.getGun(player);
                if (gun != null) {
                    int damage = gun.getProjectileInfo().getDamage();
                    event.setDamage(damage);
                }
            }
        }
    }
}
