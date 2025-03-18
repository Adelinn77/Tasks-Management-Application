package org.example;

import java.io.Serializable;
import java.util.*;

public class TasksManagement implements Serializable {
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
        Model.addTaskToEmployee(idEmployee, task);
    }

    public int calculateEmployeeWorkDuration(int idEmployee) {
        int duration = 0;
        for (Task task : View.flattenTasks(tasks.get(Model.findEmployeeById(idEmployee)))) {
            if(task != null && task.getStatusTask().equals("Completed") && task instanceof SimpleTask){
                duration += task.estimateDuration();
                System.out.println("\n\n" + idEmployee + ": " + task.estimateDuration() + "\n\n");
            }
        }
        return duration;
    }

    public void modifyTaskStatus(int idEmployee, int idTask) {
        if(Model.findTaskById(idTask) != null){
            System.out.println("\n\n" + idEmployee + ": " + idTask + "\n\n gasit!");
            Model.changeStatusForTask(Model.findTaskById(idTask));
        }
        else{
            System.out.println("\n\n" + idEmployee + ": " + idTask + "\n\n negasit!");
        }
    }

    public List<Task> getTasksOfEmployee(Employee employee) {
        return tasks.get(employee);
    }
}
