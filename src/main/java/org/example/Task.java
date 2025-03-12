package org.example;

import java.io.Serializable;

public abstract class Task implements Serializable {
    private String statusTask = "";
    private int idTask;
    private static int id = 1;


    public Task() {
        this.statusTask = "Uncompleted";
        this.idTask = id+1;
        id++;
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
