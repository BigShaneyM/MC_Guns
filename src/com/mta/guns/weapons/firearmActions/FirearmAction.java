package com.mta.guns.weapons.firearmActions;

import com.mta.guns.weapons.Gun;
import com.mta.guns.weapons.ReloadManager;
import com.mta.guns.weapons.WeaponAmmo;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.List;

public abstract class FirearmAction {

    private FirearmActionType actionType;
    private HashSet<String> action_cooldowns;

    public FirearmAction(FirearmActionType actionType) {
        this.actionType = actionType;
        action_cooldowns = new HashSet<>();
    }

    public abstract void shootBullets(Player player, Gun gun);


    public FirearmActionType getActionType() {
        return this.actionType;
    }

    public boolean isActable(Player player) {
        return !action_cooldowns.contains(player.getName());
    }

    public void removeActionCooldown(Player player) {
        action_cooldowns.remove(player.getName());
    }

    public void addActionCooldown(Player player) {
        action_cooldowns.add(player.getName());
    }

    public abstract void reload(Player player, ReloadManager reloader, ItemStack gun, WeaponAmmo ammo);

}
