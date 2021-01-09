package com.mta.guns.weapons.soundmanagement;

import org.bukkit.Location;
import org.bukkit.Sound;

public class Sounds {

    private Sound sound;
    private float v, p;

    public Sounds(String rawSound) {
        String[] rawSplit = rawSound.split("-");
        sound = Sound.valueOf(rawSplit[0]);
        v = Float.parseFloat(rawSplit[1]);
        p = Float.parseFloat(rawSplit[2]);
    }

    public void playSound(Location location) {
        location.getWorld().playSound(location, sound, v, p);
    }

    public boolean isSameSound(Sounds s) {
        if (!sound.name().equalsIgnoreCase(s.sound.name()))
            return false;
        if (this.v != s.v || this.p != s.p)
            return false;
        return true;
    }

}
