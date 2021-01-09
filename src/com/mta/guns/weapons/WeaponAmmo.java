package com.mta.guns.weapons;

import org.bukkit.Material;

public class WeaponAmmo {

    private boolean enabled;
    private Material ammoMaterial;
    private int[] noAmmoShootSoundIndicies;
    private int[] outOfAmmoSoundIndicies;

    public WeaponAmmo(boolean enabled, Material ammoMaterial, int[] noAmmoShootSoundIndicies, int[] outOfAmmoSoundIndicies) {
        this.enabled = enabled;
        this.ammoMaterial = ammoMaterial;
        this.noAmmoShootSoundIndicies = noAmmoShootSoundIndicies;
        this.outOfAmmoSoundIndicies = outOfAmmoSoundIndicies;
    }

    public WeaponAmmo() {
        this(false, null, null, null);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Material getAmmoMaterial() {
        return ammoMaterial;
    }

    public int[] getNoAmmoShootSoundIndicies() {
        return noAmmoShootSoundIndicies;
    }

    public int[] getOutOfAmmoSoundIndicies() {
        return outOfAmmoSoundIndicies;
    }
}
