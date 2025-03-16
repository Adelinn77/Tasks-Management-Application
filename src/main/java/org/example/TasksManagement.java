package org.example;

import java.io.Serializable;
import java.util.*;

public class TasksManagement {
    private Map<Employee, List<Task>> tasks = new HashMap<>();

    public TasksManagement() {
        this.tasks = new HashMap<>();
    }

    public TasksManagement(Map<Employee, List<Task>> tasks) {
        this.tasks = tasks;
    }

    public Map<Employee, List<Task>> getTasks() {
        if (tasks == null) {
            tasks = new HashMap<>();
        }
        return tasks;
    }

    public void assignTaskToEmployee(int idEmployee, Task task) {
        tasks.get(idEmployee).add(task);
    }

    public int calculateEmployeeWorkDuration(int idEmployee) {
        int duration = 0;
        for (Task task : tasks.get(idEmployee)) {
            if(task.getStatusTask().equals("Completed")) duration += task.estimateDuration();
        }
        return duration;
    }

    public void modifyTaskStatus(int idEmployee, int idTask) {
        if(tasks.get(idEmployee).get(idTask).getStatusTask().equals("Uncompleted")) {
            tasks.get(idEmployee).get(idTask).setStatusTask("Completed");
        }
        else {
            tasks.get(idEmployee).get(idTask).setStatusTask("Uncompleted");
        }
    }


}
