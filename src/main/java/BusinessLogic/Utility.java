package BusinessLogic;

import DataModel.Employee;
import DataModel.Task;
import GUI.View;

import java.util.*;

public class Utility {

    public static HashMap <Integer, ArrayList<Employee>> over40Hours(TasksManagement tm) {
        Map <Employee, List<Task>> tasks = tm.getTasks();
        HashMap <Integer, ArrayList<Employee>> workers = new HashMap<>();
        for (Employee employee : tasks.keySet()) {
            int wd = tm.calculateEmployeeWorkDuration(employee.getIdEmployee());
            if(wd > 40) {
                workers.put(wd, new ArrayList<>());
                workers.get(wd).add(employee);
            }
        }
        for (Integer wd : workers.keySet()) {
            for (Employee employee : workers.get(wd)) {
                System.out.println(employee.getName());
            }
        }
        return workers;
    }

    public static HashMap <String, Map<String, Integer>> statusTasksForEmmployees(TasksManagement tm) {
        HashMap <String, Map<String, Integer>> statusTasks = new HashMap<>();
        for(Employee employee : tm.getTasks().keySet()){
            int noCompletedTasks = 0;
            int noUncompletedTasks = 0;
            for(Task task : View.flattenTasks(tm.getTasks().get(employee))){
                if(task != null){
                    if(task.getStatusTask().equals("Completed")){
                        noCompletedTasks++;
                    }
                    else {
                        noUncompletedTasks++;
                    }
                }
            }
            statusTasks.put(employee.getName(), new HashMap<>());
            statusTasks.get(employee.getName()).put("Completed", noCompletedTasks);
            statusTasks.get(employee.getName()).put("Uncompleted", noUncompletedTasks);
        }
        return statusTasks;
    }
}
