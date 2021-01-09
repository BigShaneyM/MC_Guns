package com.mta.guns.weapons.firearmActions;

import com.mta.guns.MTAGUNS;
import com.mta.guns.util.TaskManager;
import com.mta.guns.weapons.Gun;
import com.mta.guns.weapons.ReloadManager;
import com.mta.guns.weapons.WeaponAmmo;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SlideAction extends FirearmAction {

    private int shoot_delay_ticks;

    public SlideAction(int shoot_delay_ticks) {
        super(FirearmActionType.SLIDE);
        this.shoot_delay_ticks = shoot_delay_ticks;
    }

    @Override
    public void shootBullets(Player player, Gun gun) {
        if (!this.isActable(player))
            return;

        gun.shootProjectile(player);

        MTAGUNS.getTaskManager().createDelayedTask(player.getUniqueId(), TaskManager.TaskType.TASK_TYPE_FIREARM_ACTION, () -> {
            this.removeActionCooldown(player);
        }, shoot_delay_ticks);
    }

    @Override
    public void reload(Player player, ReloadManager reloader, ItemStack gun, WeaponAmmo ammo) {

    }
}
