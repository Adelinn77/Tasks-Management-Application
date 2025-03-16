package org.example;
import java.io.*;
import java.util.*;

public class Model {
    private static final String EMPLOYEES_FILE = "employees.txt";
    private static final String TASKS_FILE = "tasks.txt";
    private static final String TM_FILE = "taskManagement.txt";
    private static List<Task> rootTasks = new ArrayList<>();

    public Model() {}

    public static void saveTasks() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(TASKS_FILE))) {
            out.writeObject(rootTasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Task> loadTasks() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(TASKS_FILE))) {
            rootTasks = (List<Task>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            rootTasks = new ArrayList<>();
        }
        resetIdTasks();
        return rootTasks;
    }

    public static void addTask(Task task) {
        if (!rootTasks.contains(task)) {
            rootTasks.add(task);
        }
        // rootTasks.add(task);
        saveTasks();
    }

    public static void saveEmployees(List<Employee> employees) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(EMPLOYEES_FILE))) {
            oos.writeObject(employees);
        } catch (IOException e) {
            System.out.println("error saving employees");
        }
    }

    public static List<Employee> loadEmployees() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(EMPLOYEES_FILE))) {
            List<Employee> employees = (List<Employee>) ois.readObject();
            if (employees == null) {
                employees = new ArrayList<>();
                //System.out.println("employee list is empty");
            }
            int maxId = 0;
            for (Employee employee : employees) {
                if (employee.getIdEmployee() > maxId) {
                    maxId = employee.getIdEmployee();
                }
            }
            Employee.setId(maxId);
            System.out.println("ID: " + maxId);
            return employees;
        } catch (IOException | ClassNotFoundException e) {
            //System.out.println("empty employee list");
            return null;
        }
    }

    public static void addEmployee(Employee employee) {
        List<Employee> employees = Model.loadEmployees();
        if(employees == null) {
            employees = new ArrayList<>();
        }
        employees.add(employee);
        saveEmployees(employees);
    }

    public static void resetIdTasks() {
        int maxId = 0;
        for (Task task : View.flattenTasks(rootTasks)) {
            if (task.getIdTask() > maxId) {
                maxId = task.getIdTask();
            }
        }
        Task.setId(maxId);
    }


    public static void saveTaskManagement(TasksManagement tasksManagement) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(TM_FILE))) {
            oos.writeObject(tasksManagement);
        } catch (IOException e) {
            System.out.println("error saving task management");
        }
    }

    public static TasksManagement loadTasksManagement() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(TM_FILE))) {
            TasksManagement tasksManagement = (TasksManagement) ois.readObject();
            if (tasksManagement == null) {
                tasksManagement = new TasksManagement();
            }
            return tasksManagement;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("error loading task management");
            return null;
        }
    }

    public static void addEmployeeInTaskManagement(Employee employee) {
        TasksManagement tasksManagement = loadTasksManagement();
        if (tasksManagement == null) {
            tasksManagement = new TasksManagement();
        }
        tasksManagement.getTasks().put(employee, new ArrayList<>());
        saveTaskManagement(tasksManagement);
    }
}
