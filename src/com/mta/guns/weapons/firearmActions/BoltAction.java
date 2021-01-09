package com.mta.guns.weapons.firearmActions;

import com.mta.guns.MTAGUNS;
import com.mta.guns.util.TaskManager;
import com.mta.guns.util.UtilMethods;
import com.mta.guns.weapons.Gun;
import com.mta.guns.weapons.ReloadManager;
import com.mta.guns.weapons.WeaponAmmo;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class BoltAction extends FirearmAction {

    private int open_delay, close_delay, close_shoot_delay;
    private int[] open_sounds, close_sounds;

    public BoltAction(int open_delay, int close_delay, int close_shoot_delay, String open_sounds, String close_sounds) {
        super(FirearmActionType.BOLT_ACTION);
        this.open_delay = open_delay;
        this.close_delay = close_delay;
        this.close_shoot_delay = close_shoot_delay;
        this.open_sounds = MTAGUNS.getSoundData().getSoundIndexArray(open_sounds);
        this.close_sounds = MTAGUNS.getSoundData().getSoundIndexArray(close_sounds);
    }

    @Override
    public void shootBullets(Player player, Gun gun) {
        if (!this.isActable(player))
            return;
        this.addActionCooldown(player);
        gun.shootProjectile(player);
        //open bolt slide
        openSlide(player);
        //close bolt slide
        closeSlide(player, 0);
    }

    private void openSlide(Player player) {
        if (this.isActable(player))
            this.addActionCooldown(player);
        MTAGUNS.getTaskManager().createDelayedTask(player.getUniqueId(), TaskManager.TaskType.TASK_TYPE_FIREARM_ACTION, () -> {
                MTAGUNS.getSoundData().playSounds(open_sounds, player.getLocation());
                System.out.println("Open");
        }, open_delay);
    }

    private void closeSlide(Player player, int reloadDelay) {
        MTAGUNS.getTaskManager().createDelayedTask(player.getUniqueId(), TaskManager.TaskType.TASK_TYPE_FIREARM_ACTION,
                () -> {
                    MTAGUNS.getSoundData().playSounds(close_sounds, player.getLocation());
                    System.out.println("Close");
                }, (open_delay + close_delay + reloadDelay));
        //Remove Shoot Cooldown
        MTAGUNS.getTaskManager().createDelayedTask(player.getUniqueId(), TaskManager.TaskType.TASK_TYPE_FIREARM_ACTION, () ->
        {
                System.out.println("Close-Can-Shoot");
                this.removeActionCooldown(player);
                MTAGUNS.getTaskManager().endPairedTaskType(player.getUniqueId(), TaskManager.TaskType.TASK_TYPE_FIREARM_ACTION);
                }, (open_delay + close_delay + close_shoot_delay + reloadDelay));
    }

    @Override
    public void reload(Player player, ReloadManager reloader, ItemStack gun, WeaponAmmo ammo) {
        if (UtilMethods.isGunReloading(gun))
            return;
        int _bAmount = (int)UtilMethods.getGunBulletAmount(gun);
        int reloadAmount = reloader.getMagSize();
        if (_bAmount == reloadAmount)
            return;

        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(UtilMethods.toColor("&c|▌▌▌▌▌▌▌▌▌▌▌▌|[RELOADING]")));

        openSlide(player);
        UtilMethods.setGunReloading(gun, true);

        if (_bAmount > 0)
            reloadAmount -= _bAmount;
        boolean noAmmo = false;
        AMMO:
        //Take ammo using reloadAmount and then adjust reloadAmount to ammo supply.
        {
            int amountOfAmmo = 0;
            if (ammo.isEnabled()) {
                Material mat = ammo.getAmmoMaterial();
                for (ItemStack itemStack : player.getInventory().getContents()) {
                    if (itemStack.getType() == mat) {
                        amountOfAmmo += itemStack.getAmount();
                    }
                }

                if (amountOfAmmo == 0) {

                    MTAGUNS.getSoundData().playSounds(ammo.getOutOfAmmoSoundIndicies(), player.getLocation());
                    noAmmo = true;
                    break AMMO;
                }

                int takeAmmo = reloadAmount;
                if (reloadAmount > amountOfAmmo) {
                    takeAmmo = amountOfAmmo;
                }
                reloadAmount = takeAmmo;
            }

        }

        final int reloadamount = reloadAmount;
        final ItemStack gunItem = gun;

        System.out.println(reloader.getDurationTicks());

        MTAGUNS.getTaskManager().createDelayedTask(player.getUniqueId(), TaskManager.TaskType.TASK_TYPE_FIREARM_ACTION, () -> {
            System.out.println("Reload-task");
            MTAGUNS.getSoundData().playSounds(reloader.getSoundsIndex(), player.getLocation());
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(UtilMethods.toColor("&2|▌▌▌▌▌▌▌▌▌▌▌▌|[" + reloadamount + "&2]")));
            UtilMethods.setGunBulletAmount(gunItem, (short)reloadamount);
            UtilMethods.setGunReloading(gunItem, false);
        }, (open_delay + reloader.getDurationTicks()));

        closeSlide(player, reloader.getDurationTicks());

    }

}
