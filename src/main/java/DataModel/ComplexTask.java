package DataModel;

import java.io.Serializable;
import java.util.*;

public non-sealed class ComplexTask extends Task implements Serializable {
    List<Task> tasks = new ArrayList<>();

    public ComplexTask() {
        this.tasks = new ArrayList<>();
    }
    public ComplexTask(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(Task task) {
        tasks.remove(task);
    }

    @Override
    public int estimateDuration() {
        int duration = 0;
        for(Task task : this.getTasks()) {
            duration += task.estimateDuration();
        }
        return duration;
    }

    @Override
    public String toString() {
        return "ComplexTask{" +
                "tasks=" + tasks +
                '}';
    }
}
