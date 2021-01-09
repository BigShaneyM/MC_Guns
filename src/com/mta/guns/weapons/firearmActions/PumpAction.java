package com.mta.guns.weapons.firearmActions;

import com.mta.guns.weapons.Gun;
import com.mta.guns.weapons.ReloadManager;
import com.mta.guns.weapons.WeaponAmmo;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PumpAction extends FirearmAction {

    private int pump_begin_ticks, pump_end_ticks;

    public PumpAction() {
        super(FirearmActionType.PUMP);
    }

    @Override
    public void shootBullets(Player player, Gun gun) {

    }

    @Override
    public void reload(Player player, ReloadManager reloader, ItemStack gun, WeaponAmmo ammo) {

    }
}
