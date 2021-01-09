package com.mta.guns.weapons;

import com.mta.guns.MTAGUNS;

public class ReloadManager {

    private boolean enabled, single_bullet_reload;
    private int start_amount, mag_size, duration_ticks;
    private int[] soundsIndex;


    public ReloadManager(boolean enabled, boolean single_bullet_reload, int start_amount, int mag_size, int duration_ticks, String sounds) {
        this.enabled = enabled;
        this.single_bullet_reload = single_bullet_reload;
        this.start_amount = start_amount;
        this.mag_size = mag_size;
        this.duration_ticks = duration_ticks;
        if (sounds != null)
            this.soundsIndex = MTAGUNS.getSoundData().getSoundIndexArray(sounds);
    }

    public ReloadManager() {
        this(false, false, 0, 0, 0, null);
    }

    public boolean isReloadable() {
        return this.enabled;
    }

    public boolean isSingleBulletReload() {
        return single_bullet_reload;
    }

    public int getStartAmount() {
        return start_amount;
    }

    public int getMagSize() {
        return mag_size;
    }

    public int getDurationTicks() {
        return duration_ticks;
    }

    public int[] getSoundsIndex() {
        return soundsIndex;
    }
}
