package org.example;

import java.io.Serializable;

public abstract class Task implements Serializable {
    private String statusTask = "";
    private static int id = 1;
    private int idTask;

    public Task() {
        this.statusTask = "Uncompleted";
        this.idTask = id++;
    }

    public String getStatusTask() {
        return statusTask;
    }

    public void setStatusTask(String statusTask) {
        this.statusTask = statusTask;
    }

    public int getIdTask() {
        return idTask;
    }

    public abstract int estimateDuration();
}
