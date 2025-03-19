package DataModel;

import java.io.Serializable;
import java.util.Objects;

public sealed abstract class Task implements Serializable permits ComplexTask, SimpleTask {
    private String statusTask = "";
    private int idTask;
    private static int id = 0;


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

    public static void setId(int value) {
        id = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task task)) return false;
        return idTask == task.idTask && Objects.equals(statusTask, task.statusTask);
    }

}
