package com.mta.guns.io;

import com.mta.guns.MTAGUNS;
import com.mta.guns.util.UtilMethods;
import com.mta.guns.weapons.*;
import com.mta.guns.weapons.firearmActions.*;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class WeaponFiles extends FileManager {

    public WeaponFiles() {
        super(MTAGUNS.getPlugin(), "Weapons.yml");
    }

    @Override
    public void setDefaultConfigData() {
        FileConfiguration cfg = this.getFileConfig();
        cfg.addDefault("Sniper.Info.Item_material", "IRON_PICKAXE");
        cfg.addDefault("Sniper.Info.Item_name", "&cHigh Powered Sniper Rifle");
        cfg.addDefault("Sniper.Info.Item_lore", "&6Favored amongst marksman for its accuracy.#&6and magnification.#&2Night Vision scope attached.#&cHeadshots deal double damage.");
        cfg.addDefault("Sniper.Projectile.Amount", 1);
        cfg.addDefault("Sniper.Projectile.Speed", 100);
        cfg.addDefault("Sniper.Projectile.Damage", 20);
        cfg.addDefault("Sniper.Projectile.Type", "Snowball");
        cfg.addDefault("Sniper.Projectile.Sound_Shooting", "ENTITY_BLAZE_HURT-1-1-0,ENTITY_GENERIC_EXPLODE-1-2-0");
        cfg.addDefault("Sniper.Scope.Enabled", true);
        cfg.addDefault("Sniper.Scope.Night_Vision", true);
        cfg.addDefault("Sniper.Scope.Zoom_Level", 10);
        cfg.addDefault("Sniper.Scope.Zoom_Bullet_Spread", 0);
        cfg.addDefault("Sniper.Scope.Zoom_Sound_Toggle", "BLOCK_NOTE_BLOCK_HAT-1-2-0");
        cfg.addDefault("Sniper.Firearm_Action.Type", "BOLT_ACTION");
        cfg.addDefault("Sniper.Firearm_Action.Open_Duration", 8);
        cfg.addDefault("Sniper.Firearm_Action.Close_Duration", 7);
        cfg.addDefault("Sniper.Firearm_Action.Close_Shoot_Delay", 6);
        cfg.addDefault("Sniper.Firearm_Action.Open_Sounds", "BLOCK_NOTE_BLOCK_HAT-1-0-0,BLOCK_PISTON_CONTRACT-1-2-2");
        cfg.addDefault("Sniper.Firearm_Action.Close_Sounds", "BLOCK_PISTON_EXTEND-1-2-0");
        cfg.addDefault("Sniper.Reload.Enabled", true);
        cfg.addDefault("Sniper.Reload.Start_Amount", 1);
        cfg.addDefault("Sniper.Reload.Mag_Size", 7);
        cfg.addDefault("Sniper.Reload.Individual_Bullets", false);
        cfg.addDefault("Sniper.Reload.Duration_Ticks", 60);
        cfg.addDefault("Sniper.Reload.Sound", "BLOCK_NOTE_BLOCK_HAT-1-1-0");
        cfg.addDefault("Sniper.Ammo.Enabled", true);
        cfg.addDefault("Sniper.Ammo.Material", "MELON_SEEDS");
        cfg.addDefault("Sniper.Ammo.Out_Of_Ammo_Sounds", "ENTITY_FISHING_BOBBER_THROW-1-1-0");
        cfg.addDefault("Sniper.Ammo.Shoot_No_Ammo_Sounds", "ITEM_FLINTANDSTEEL_USE-1-1-0");

        cfg.addDefault("Minigun.Info.Item_material", "DIAMOND_PICKAXE");
        cfg.addDefault("Minigun.Info.Item_name", "&cTHE SHREDDER");
        cfg.addDefault("Minigun.Info.Item_lore", "&6This weapon destroys and shreds#&6any enemies in your path.#&3With a whopping 20 bullet per second#&3speed this gun is unmatched.");
        cfg.addDefault("Minigun.Projectile.Amount", 1);
        cfg.addDefault("Minigun.Projectile.Speed", 25);
        cfg.addDefault("Minigun.Projectile.Damage", 4);
        cfg.addDefault("Minigun.Projectile.Type", "Snowball");
        cfg.addDefault("Minigun.Projectile.Sound_Shooting", "ENTITY_IRON_GOLEM_ATTACK-1-2-0,ENTITY_SKELETON_HURT-1-2-0,ENTITY_ZOMBIE_ATTACK_WOODEN_DOOR-1-2-0");
        cfg.addDefault("Minigun.Firearm_Action.Type", "FULLY_AUTO");
        cfg.addDefault("Minigun.Firearm_Action.Fire_Rate", 16);

        cfg.addDefault("AssaultSMG.Info.Item_material", "DIAMOND_AXE");
        cfg.addDefault("AssaultSMG.Info.Item_name", "&cAssault SMG");
        cfg.addDefault("AssaultSMG.Info.Item_lore", "");
        cfg.addDefault("AssaultSMG.Projectile.Amount", 1);
        cfg.addDefault("AssaultSMG.Projectile.Speed", 25);
        cfg.addDefault("AssaultSMG.Projectile.Damage", 4);
        cfg.addDefault("AssaultSMG.Projectile.Type", "Snowball");
        cfg.addDefault("AssaultSMG.Projectile.Sound_Shooting", "ENTITY_IRON_GOLEM_ATTACK-1-2-0,ENTITY_SKELETON_HURT-1-2-0,ENTITY_ZOMBIE_ATTACK_WOODEN_DOOR-1-2-0");
        cfg.addDefault("AssaultSMG.Firearm_Action.Type", "SEMI_AUTO");
        cfg.addDefault("AssaultSMG.Firearm_Action.Fire_Rate", 10);
        cfg.addDefault("AssaultSMG.Firearm_Action.Delay_Between_Shots", 2);
        cfg.addDefault("AssaultSMG.Firearm_Action.Shots_Per_Burst", 3);

        cfg.addDefault("AP_Pistol.Info.Item_material", "IRON_AXE");
        cfg.addDefault("AP_Pistol.Info.Item_name", "&cAP Pistol");
        cfg.addDefault("AP_Pistol.Info.Item_lore", "");
        cfg.addDefault("AP_Pistol.Projectile.Amount", 1);
        cfg.addDefault("AP_Pistol.Projectile.Speed", 25);
        cfg.addDefault("AP_Pistol.Projectile.Damage", 6);
        cfg.addDefault("AP_Pistol.Projectile.Type", "Snowball");
        cfg.addDefault("AP_Pistol.Projectile.Sound_Shooting", "ENTITY_IRON_GOLEM_ATTACK-1-2-0,ENTITY_SKELETON_HURT-1-2-0,ENTITY_ZOMBIE_ATTACK_WOODEN_DOOR-1-2-0");
        cfg.addDefault("AP_Pistol.Firearm_Action.Type", "SLIDE");
        cfg.addDefault("AP_Pistol.Firearm_Action.Shoot_Delay_Ticks", 10);




    }

    public List<Gun> getGunsFromConfig() {
        FileConfiguration cfg = this.getFileConfig();
        List<Gun> guns = new ArrayList<>();
        for (String key : cfg.getKeys(false)) {
            Material mat = Material.valueOf(cfg.getString(key + ".Info.Item_material"));
            int mat_data = cfg.getInt(key + ".Info.Item_data");
            String gun_name = cfg.getString(key + ".Info.Item_name");
            String item_lore = cfg.getString(key + ".Info.Item_lore");
            ItemStack it = UtilMethods.makeItemStack(gun_name, item_lore, mat, mat_data);

            String proj_name = cfg.getString(key + ".Projectile.Type");
            int p_amount = cfg.getInt(key + ".Projectile.Amount");
            int p_speed = cfg.getInt(key + ".Projectile.Speed");
            int p_dmg = cfg.getInt(key + ".Projectile.Damage");
            String p_shoot_sounds = cfg.getString(key + ".Projectile.Sound_Shooting");
            ProjectileInfo pInfo = new ProjectileInfo(proj_name, p_amount, p_speed, p_dmg, 0, p_shoot_sounds);

            FirearmAction firearmAction = null;
            FirearmActionType fType = FirearmActionType.valueOf(cfg.getString(key + ".Firearm_Action.Type"));
            switch (fType) {
                case BOLT_ACTION:
                    int open_dur = cfg.getInt(key + ".Firearm_Action.Open_Duration");
                    int close_dur = cfg.getInt(key + ".Firearm_Action.Close_Duration");
                    int close_shoot_dur = cfg.getInt(key + ".Firearm_Action.Close_Shoot_Delay");
                    String open_sounds = cfg.getString(key + ".Firearm_Action.Open_Sounds");
                    String close_sounds = cfg.getString(key + ".Firearm_Action.Close_Sounds");
                    firearmAction = new BoltAction(open_dur, close_dur, close_shoot_dur, open_sounds, close_sounds);
                    break;
                case FULLY_AUTO:
                    int fire_rate = cfg.getInt(key + ".Firearm_Action.Fire_Rate");
                    firearmAction = new FullAutoAction(fire_rate);
                    break;
                case SEMI_AUTO:
                    int burst_fire_rate = cfg.getInt(key + ".Firearm_Action.Fire_Rate");
                    int shoot_delay = cfg.getInt(key + ".Firearm_Action.Delay_Between_Shots");
                    int shot_amnt = cfg.getInt(key + ".Firearm_Action.Shots_Per_Burst");
                    firearmAction = new FullAutoAction(burst_fire_rate, shoot_delay, shot_amnt);
                    break;
                case SLIDE:
                    int slide_delay_ticks = cfg.getInt(key + ".Firearm_Action.Shoot_Delay_Ticks");
                    firearmAction = new SlideAction(slide_delay_ticks);
                default:
                    break;
            }

            ScopeSettings ss = new ScopeSettings();
            ReloadManager rmg = new ReloadManager();
            WeaponAmmo ammo = new WeaponAmmo();
            for(String k : cfg.getConfigurationSection(key).getKeys(false)) {
                if (k.equals("Scope")) {
                    if (Boolean.parseBoolean(cfg.getString(key + ".Scope.Enabled"))) {
                        int zoom_level = Integer.parseInt(cfg.getString(key + ".Scope.Zoom_Level"));
                        int b_spread = Integer.parseInt(cfg.getString(key + ".Scope.Zoom_Bullet_Spread"));
                        boolean night_vision = Boolean.parseBoolean(cfg.getString(key + ".Scope.Night_Vision"));
                        String scope_sounds = cfg.getString(key + ".Scope.Zoom_Sound_Toggle");
                        ss = new ScopeSettings(night_vision, zoom_level, b_spread, scope_sounds);
                    }
                } else if (k.equals("Reload")) {
                    boolean rEn = cfg.getBoolean(key + ".Reload.Enabled");
                    if (rEn) {
                        int stAmnt = cfg.getInt(key + ".Reload.Start_Amount");
                        int mS = cfg.getInt(key + ".Reload.Mag_Size");
                        boolean indBullet = cfg.getBoolean(key + ".Reload.Individual_Bullets");
                        int rldDur = cfg.getInt(key + ".Reload.Duration_Ticks");
                        String reloadSound = cfg.getString(key + ".Reload.Sound");
                        rmg = new ReloadManager(rEn, indBullet, stAmnt, mS, rldDur, reloadSound);
                    }
                }else if (k.equals("Ammo")) {
                    boolean ammoEnabled = cfg.getBoolean(key + ".Ammo.Enabled");
                    if (ammoEnabled) {
                        Material ammo_mat = Material.valueOf(cfg.getString(key + ".Ammo.Material"));
                        int[] outSound = MTAGUNS.getSoundData().getSoundIndexArray(cfg.getString(key + ".Ammo.Out_Of_Ammo_Sounds"));
                        int[] noShootSound =MTAGUNS.getSoundData().getSoundIndexArray(cfg.getString(key + ".Ammo.Shoot_No_Ammo_Sounds"));
                        ammo = new WeaponAmmo(ammoEnabled, ammo_mat, outSound, noShootSound);
                    }
                }
            }

            Gun g = new Gun(key, it, pInfo, ss, firearmAction, rmg, ammo);
            guns.add(g);
        }
        return guns;
    }
}
