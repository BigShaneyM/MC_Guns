package com.mta.guns;

import com.mta.guns.events.EventManager;
import com.mta.guns.io.WeaponFiles;
import com.mta.guns.util.TaskManager;
import com.mta.guns.weapons.Gun;
import com.mta.guns.weapons.soundmanagement.SoundData;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class MTAGUNS extends JavaPlugin implements Listener {

    private WeaponFiles weaponFiles;
    private EventManager eventManager;
    private static SoundData soundData;
    private static List<Gun> available_weapons;
    private static TaskManager taskManager;
    public void onEnable() {
        taskManager = new TaskManager(MTAGUNS.getPlugin());
        weaponFiles = new WeaponFiles();
        soundData = new SoundData();
        available_weapons = weaponFiles.getGunsFromConfig();
        eventManager = new EventManager();
        this.getServer().getPluginManager().registerEvents(eventManager, getPlugin());
    }


    public void onDisable() {
        available_weapons.clear();
        soundData.trash();
        taskManager.endAllTasks();
        taskManager.stop();
    }

    public static List<Gun> getAvailableWeapons() {
        return available_weapons;
    }

    public static Plugin getPlugin() {
        return Bukkit.getServer().getPluginManager().getPlugin("MTAGUNS");
    }

    public static SoundData getSoundData() {
        return soundData;
    }

    public static TaskManager getTaskManager() {
        return taskManager;
    }


}
