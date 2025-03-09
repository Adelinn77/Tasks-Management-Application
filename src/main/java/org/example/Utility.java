package org.example;

import java.util.*;

public class Utility {
    public static void over40Hours(TasksManagement tm) {
        Map <Employee, List<Task>> tasks = tm.getTasks();
        Map <Integer, ArrayList<Employee>> workers = new HashMap<>();
        for (Employee employee : tasks.keySet()) {
            int wd = tm.calculateEmployeeWorkDuration(employee.getIdEmployee());
            if(wd > 40) {
                workers.put(wd, new ArrayList<>());
                workers.get(wd).add(employee);
            }
        }
        for (Employee employee : tasks.keySet()) {
            System.out.println(employee.toString());
        }
    }
}
