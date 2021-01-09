package com.mta.guns.weapons;

import com.mta.guns.MTAGUNS;
import org.bukkit.entity.Player;

public class ProjectileInfo {

    private String entType;
    private int amount, speed, damage;
    private double spread;
    int[] shooting_sounds_index;

    public ProjectileInfo(String clazz, int amount, int speed, int damage, double spread, String shooting_sounds) {
        this.entType = clazz;
        this.amount = amount;
        this.speed = speed;
        this.damage = damage;
        this.shooting_sounds_index = MTAGUNS.getSoundData().getSoundIndexArray(shooting_sounds);
        this.spread = spread;
    }

    public String getProjectileType() {
        return entType.toUpperCase();
    }

    public int getAmount() {
        return amount;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDamage() {
        return damage;
    }

    public double getSpread() {
        return spread;
    }

    public void playShootGunSound(Player player) {
        MTAGUNS.getSoundData().playSounds(this.shooting_sounds_index, player.getLocation());
    }

}
