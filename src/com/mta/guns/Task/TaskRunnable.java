package com.mta.guns.Task;

public class TaskRunnable implements Runnable {

    private int taskID = 0;
    private Runnable runnable;

    public TaskRunnable(Runnable runnable) {
        this.runnable = runnable;
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public void run() {
        runnable.run();
    }

}
