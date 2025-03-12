package org.example;
import java.io.*;
import java.util.*;

public class Model {
    private static final String EMPLOYEES_FILE_NAME = "employees.txt";
    private static final String TASKS_FILE_NAME = "tasks.txt";

    public Model() {}

    public static void saveTasks(List<Task> tasks) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(TASKS_FILE_NAME))) {
            oos.writeObject(tasks);
        } catch (IOException e) {
            System.out.println("error saving tasks");
        }
    }

    public static List<Task> loadTasks() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(TASKS_FILE_NAME))) {
            List<Task> tasks = (List<Task>) ois.readObject();
            if (tasks == null) {
                tasks = new ArrayList<>();
                //System.out.println("task list is empty");
            }
            return tasks;
        } catch (IOException | ClassNotFoundException e) {
            //System.out.println("Empty task list");
            return null;
        }
    }

    public static void saveEmployees(List<Employee> employees) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(EMPLOYEES_FILE_NAME))) {
            oos.writeObject(employees);
        } catch (IOException e) {
            System.out.println("error saving employees");
        }
    }

    public static List<Employee> loadEmployees() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(EMPLOYEES_FILE_NAME))) {
            List<Employee> employees = (List<Employee>) ois.readObject();
            if (employees == null) {
                employees = new ArrayList<>();
                //System.out.println("employee list is empty");
            }
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

    public static void addTask(Task task) {
        List<Task> tasks = Model.loadTasks();
        if(tasks == null) {
            tasks = new ArrayList<>();
        }
        tasks.add(task);
        saveTasks(tasks);
    }




}
