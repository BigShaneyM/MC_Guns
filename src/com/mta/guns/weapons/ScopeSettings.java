package com.mta.guns.weapons;

import com.mta.guns.MTAGUNS;
import com.mta.guns.util.UtilMethods;
import com.mta.guns.weapons.soundmanagement.SoundData;
import com.mta.guns.weapons.soundmanagement.Sounds;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScopeSettings {

    private boolean enabled;
    private boolean night_vision;

    private int zoom_level;
    private int zoom_bullet_spread;

    private int[] sound_string_toggle;

    private static List<String> toggled_scopes;

    private ScopeSettings(boolean enabled, boolean night_vision, int zoom_level, int zoom_bullet_spread, String sound_string_toggle) {
        this.enabled = enabled;
        this.night_vision = night_vision;
        this.zoom_level = zoom_level;
        this.zoom_bullet_spread = zoom_bullet_spread;
        if (sound_string_toggle != null)
            this.sound_string_toggle = MTAGUNS.getSoundData().getSoundIndexArray(sound_string_toggle);
        if (toggled_scopes == null)
            toggled_scopes = new ArrayList<>();
    }

    public ScopeSettings(boolean night_vision, int zoom_level, int zoom_bullet_spread, String sound_string_toggle) {
        this(true, night_vision, zoom_level, zoom_bullet_spread, sound_string_toggle);
    }

    public ScopeSettings() {
        this(false, false, 0, 0, null);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public int getZoomLevel() {
        return zoom_level;
    }

    public int getZoomBulletSpread() {
        return zoom_bullet_spread;
    }

    public int[] getSoundStringToggle() {
        return sound_string_toggle;
    }

    public void toggleScope(Player player) {
        MTAGUNS.getSoundData().playSounds(this.sound_string_toggle, player.getLocation());
        if (toggled_scopes.contains(player.getName())) {
            if (night_vision) {
                player.removePotionEffect(PotionEffectType.NIGHT_VISION);
            }
            player.removePotionEffect(PotionEffectType.SLOW);
            toggled_scopes.remove(player.getName());
        } else {
            if (night_vision)
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 10000000, 0, true, false));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 10000000, this.zoom_level, true, false));
            toggled_scopes.add(player.getName());
        }
    }

    public static boolean isToggled(Player player) {
        return toggled_scopes.contains(player.getName());
    }
}
