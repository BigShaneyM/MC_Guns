package com.mta.guns.weapons;

import com.mta.guns.weapons.firearmActions.FirearmAction;
import com.mta.guns.weapons.firearmActions.FirearmActionType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class BreakAction extends FirearmAction {

    public BreakAction() {
        super(FirearmActionType.BREAK_ACTION);
    }

    @Override
    public void shootBullets(Player player, Gun gun) {

    }

    @Override
    public void reload(Player player, ReloadManager reloader, ItemStack gun, WeaponAmmo ammo) {

    }
}
