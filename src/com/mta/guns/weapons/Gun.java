package com.mta.guns.weapons;

import com.mta.guns.MTAGUNS;
import com.mta.guns.util.TaskManager;
import com.mta.guns.util.UtilMethods;
import com.mta.guns.weapons.firearmActions.FirearmAction;
import com.mta.guns.weapons.firearmActions.FirearmActionType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class Gun {

    private String key;
    private ItemStack gun_item;
    private ProjectileInfo projectileInfo;
    private ScopeSettings scopeSettings;
    private FirearmAction firearmAction;
    private ReloadManager reloadManager;
    private WeaponAmmo ammo;

    public Gun(String key, ItemStack gun_item, ProjectileInfo projectileInfo, ScopeSettings scopeSettings, FirearmAction firearmAction, ReloadManager reloadManager, WeaponAmmo ammo) {
        this.key = key;
        this.gun_item = gun_item;
        this.projectileInfo = projectileInfo;
        this.scopeSettings = scopeSettings;
        this.firearmAction = firearmAction;
        this.reloadManager = reloadManager;
        this.ammo = ammo;
    }

    public ProjectileInfo getProjectileInfo() {
        return projectileInfo;
    }

    public ItemStack getGunItem() {
        return gun_item;
    }

    public String getKey() {
        return key;
    }

    public FirearmAction getFirearmAction() {
        return firearmAction;
    }

    public ReloadManager getReloadManager() {
        return reloadManager;
    }

    public WeaponAmmo getAmmo() {
        return ammo;
    }

    public void shootGun(Player p) {
        firearmAction.shootBullets(p, this);
    }

    public void shootProjectile(Player player) {
        Vector shiftVec = UtilMethods.getBulletSpread(player);
        for (int i = 0; i < getProjectileInfo().getAmount(); i++) {
            Projectile proj = (Projectile)player.getWorld().spawnEntity(player.getEyeLocation().toVector().add(shiftVec.multiply(projectileInfo.getSpread())).toLocation(player.getWorld()), EntityType.valueOf(projectileInfo.getProjectileType()));
            proj.setVelocity(UtilMethods.getBulletVelocity(player).multiply(projectileInfo.getSpeed()));
            proj.setShooter(player);
        }
        projectileInfo.playShootGunSound(player);
    }

    public void reloadGun(Player p, boolean forceReload) {
        if (!getReloadManager().isReloadable())
            return;

        if (forceReload)
            MTAGUNS.getTaskManager().endPairedTaskType(p.getUniqueId(), TaskManager.TaskType.TASK_TYPE_RELOADING);
        getFirearmAction().reload(p, getReloadManager(), p.getItemInHand(), getAmmo());
    }

    public void stopReload(Player p) {
        getFirearmAction().removeActionCooldown(p);
        MTAGUNS.getTaskManager().endPairedTaskType(p.getUniqueId(), TaskManager.TaskType.TASK_TYPE_RELOADING);
    }

    public ScopeSettings getScopeSettings() {
        return scopeSettings;
    }

}
