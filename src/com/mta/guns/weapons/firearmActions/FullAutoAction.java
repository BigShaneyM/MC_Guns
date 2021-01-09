package com.mta.guns.weapons.firearmActions;

import com.mta.guns.MTAGUNS;
import com.mta.guns.util.TaskManager;
import com.mta.guns.weapons.Gun;
import com.mta.guns.weapons.ReloadManager;
import com.mta.guns.weapons.WeaponAmmo;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class FullAutoAction extends FirearmAction {

    private int fire_rate;
    private int shoot_delay;
    private int bps;
    private boolean semi_auto;
    private int shots_per_burst;

    private static HashMap<String, Integer> shots;
    private static HashMap<String, Integer> ticks;

    public FullAutoAction(int fR) {
        super(FirearmActionType.FULLY_AUTO);
        if (shots == null)
            shots = new HashMap<>();
        if (ticks == null)
            ticks = new HashMap<>();
        this.fire_rate = fR > 16 ? 16 : fR;
        shoot_delay = calculateShootDelay();
        bps = calculateBPS();
    }

    public FullAutoAction(int fR, int bD, int sB) {
        this(fR);
        shoot_delay = bD;
        shots_per_burst = sB;
        semi_auto = true;
    }

    @Override
    public void shootBullets(Player player, Gun gun) {
        if (!this.isActable(player))
            return;

        int bDelay = 1;
        int bShots = 5;
        if (semi_auto) {
            bDelay = shoot_delay;
            bShots = shots_per_burst;
        }

        final int final_bShots = bShots;

        if (!shots.containsKey(player.getName()))
            shots.put(player.getName(), 0);
        int burst_start = shots.get(player.getName());
        shots.put(player.getName(), bShots);
        if (!semi_auto && !ticks.containsKey(player.getName()))
            ticks.put(player.getName(), 1);

        //System.out.println("New shot list, burst start = " + burst_start);
        for (int i = burst_start; i < bShots; i++) {
            //System.out.println("Shot #" + i + " of " + bShots);
            MTAGUNS.getTaskManager().createDelayedTask(player.getUniqueId(), TaskManager.TaskType.TASK_TYPE_FIREARM_ACTION, () -> {
                int shotsLeft = shots.get(player.getName()).intValue() - 1;
                if (!semi_auto) {
                    int tick = ticks.get(player.getName());
                    ticks.put(player.getName(), Integer.valueOf(tick >= 20 ? 1 : (tick +1)));
                    if (!isValidTick(tick)) {
                        //System.out.println("Not a valid shot!");
                        if ((shotsLeft + 1) == final_bShots)
                            shots.put(player.getName(), 0);
                        return;
                    }
                }
                shots.put(player.getName(), shotsLeft);
                gun.shootProjectile(player);
                //System.out.println("Shots left " + shots.get(player.getName()));
                if (shotsLeft == 0) {
                    MTAGUNS.getTaskManager().endPairedTaskType(player.getUniqueId(), TaskManager.TaskType.TASK_TYPE_FIREARM_ACTION);
                    return;
                }
            }, (bDelay * i) + 1);
        }

    }

    @Override
    public void reload(Player player, ReloadManager reloader, ItemStack gun, WeaponAmmo ammo) {


    }

    private int calculateShootDelay() {
        return 20 - this.bps;
    }

    private int calculateBPS() {
        return ((this.fire_rate * 60) + 240)/60;
    }

    private boolean isValidTick(int tick) {
        switch (fire_rate) {
            case 1:
                return (tick % 4 == 1);
            case 2:
                tick %= 7;
                return !(tick != 1 && tick != 4);
            case 3:
                return (tick % 3 == 1);
            case 4:
                tick %= 5;
                return !(tick != 1 && tick != 3);
            case 5:
                tick %= 7;
                return !(tick != 1 && tick != 3 && tick != 5);
            case 6:
                return (tick % 2 == 1);
            case 7:
                return !(tick != 2 && tick % 2 != 1);
            case 8:
                tick %= 5;
                return !(tick != 1 && tick != 2 && tick != 4);
            case 9:
                tick %= 6;
                return (tick != 2 && tick != 0);
            case 10:
                return (tick % 3 != 0);
            case 11:
                return (tick % 4 != 0);
            case 12:
                return (tick % 5 != 0);
            case 13:
                return (tick % 6 != 0);
            case 14:
                return (tick % 10 != 0);
            case 15:
                return (tick != 20);
            case 16:
                return true;
        }
        return true;
    }
}
