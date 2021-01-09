package com.mta.guns.util;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

public class TaskManager {

    private HashMap<TaskType, HashMap<UUID, HashSet<Integer>>> taskTypeIDs;
    private Plugin plugin;
    public TaskManager(Plugin plugin) {
        this.plugin = plugin;
        taskTypeIDs = new HashMap<>();

        for (TaskType taskType: TaskType.values()) {
            taskTypeIDs.put(taskType, new HashMap<>());
        }
    }

    private void addTaskID(UUID uuid, TaskType type, int taskID) {
        HashMap<UUID, HashSet<Integer>> taskIDs = taskTypeIDs.get(type);
        HashSet<Integer> tasks;
        if (taskIDs.containsKey(uuid)) {
            tasks = taskIDs.get(uuid);
        } else {
            tasks = new HashSet<>();
        }
        tasks.add(taskID);
    }

    public void createDelayedTask(UUID uuid, TaskType type, Runnable runnable, int delayed_task_ticks) {
        BukkitTask task = this.plugin.getServer().getScheduler().runTaskLater(this.plugin, runnable, (long)delayed_task_ticks);
        addTaskID(uuid, type, task.getTaskId());
    }

    public void createDelayedRepeatingTask(UUID uuid, TaskType type, Runnable runnable, int delayed_task_ticks, int repeating_task_ticks) {
        BukkitTask task = this.plugin.getServer().getScheduler().runTaskTimer(this.plugin, runnable, (long)delayed_task_ticks, (long)repeating_task_ticks);
        addTaskID(uuid, type, task.getTaskId());
    }

    public void endAllTasks() {
        for (TaskType taskType : TaskType.values()) {
            endAllTaskType(taskType);
        }
    }

    public void stop() {
        taskTypeIDs.clear();
    }

    public void endAllTaskType(TaskType type) {
        HashMap<UUID, HashSet<Integer>> taskIDs = taskTypeIDs.get(type);
        for (UUID u : taskIDs.keySet()) {
            endPairedTaskType(u, type);
        }
    }

    public void endPairedTaskType(UUID uuid, TaskType type) {
        if (taskTypeIDs.get(type).containsKey(uuid)) {
            for (int i : taskTypeIDs.get(type).get(uuid)) {
                this.plugin.getServer().getScheduler().cancelTask(i);
            }
        }
    }

    public void endAllPlayerTasks(UUID uuid) {
        for (TaskType t : TaskType.values()) {
            endPairedTaskType(uuid, t);
        }
    }

    public enum TaskType {
        TASK_TYPE_FIREARM_ACTION,
        TASK_TYPE_RELOADING
    }

}
