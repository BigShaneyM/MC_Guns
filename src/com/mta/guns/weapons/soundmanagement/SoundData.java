package com.mta.guns.weapons.soundmanagement;

import org.bukkit.Location;

import java.util.ArrayList;

public class SoundData {

    private ArrayList<Sounds> soundsList;

    public SoundData() {
        soundsList = new ArrayList<>();
    }

    public int addSound(Sounds s) {
        int index = 0;
        if (soundsList.size() == 0)
        {
            soundsList.add(s);
            return index;
        }
        for (int i = 0; i < soundsList.size(); i++) {
            if (soundsList.get(i).isSameSound(s)) {
                index = i;
                return index;
            }
        }
        index = soundsList.size();
        soundsList.add(s);

        return index;
    }

    public void playSounds(int[] soundIndexes, Location location) {
        for (int i = 0; i < soundIndexes.length; i++) {
            soundsList.get(soundIndexes[i]).playSound(location);
        }
    }

    public int[] getSoundIndexArray(String sounds) {
        String[] s = sounds.split(",");
        int[] index = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            index[i] = this.addSound(new Sounds(s[i]));
        }

        System.out.println();
        System.out.print("Index:{");
        for (int i = 0; i < index.length; i++) {
            if ((index.length - 1) == i)
                System.out.print(index[i]);
            else
            System.out.print(index[i] + ",");
        }
        System.out.print("}");

        return index;
    }

    public void trash() {
        soundsList.clear();
        soundsList = null;
    }


}
